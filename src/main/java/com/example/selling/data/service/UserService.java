package com.example.selling.data.service;

import com.example.selling.constants.RoleIdConstant;
import com.example.selling.constants.StatusRegisterUserEnum;
import com.example.selling.data.entity.Role;
import com.example.selling.data.entity.User;
import com.example.selling.data.entity.UserRole;
import com.example.selling.data.repository.RoleRepository;
import com.example.selling.data.repository.UserRepository;
import com.example.selling.data.repository.UserRoleRepository;
import com.example.selling.ultis.form.FormUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    public StatusRegisterUserEnum registerUser(FormUser formUser) {
        if (Objects.nonNull(userRepository.findUserByUsername(formUser.getUsername()))) {
            return StatusRegisterUserEnum.Existed_Username;
        }
        if (Objects.nonNull(userRepository.findUserByEmail(formUser.getEmail()))) {
            return StatusRegisterUserEnum.Existed_Email;
        }
        User user = new User();
        user.setUsername(formUser.getUsername());
        user.setPassword(passwordEncoder.encode(formUser.getPassword()));
        user.setAddress(formUser.getAddress());
        user.setPhoneNumber(formUser.getPhoneNumber());
        userRepository.save(user);
        UserRole userRole = new UserRole();
        userRole.setIdRole(RoleIdConstant.Role_Admin);
        userRole.setIdUser(user.getId());
        userRoleRepository.save(userRole);
        return StatusRegisterUserEnum.Success;

    }

    public List<Role> getListRole() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false).collect(Collectors.toList());

    }

    public List<Role> getActiveListRole(int userId) {
        List<UserRole> userRoles = StreamSupport.
                stream(userRoleRepository.findUserRoleByIdUser(userId).spliterator(), false)
                .collect(Collectors.toList());
        return getListRole().stream().filter(role -> {
            return (userRoles.stream().filter(userRole -> userRole.getIdRole() == role.getId())
                    .findFirst().orElse(null) != null);
        }).collect(Collectors.toList());

    }

}
