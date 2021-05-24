package com.example.selling.ultis;

import com.example.selling.data.entity.Role;
import com.example.selling.data.entity.User;
import com.example.selling.data.repository.UserRepository;
import com.example.selling.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (Objects.nonNull(user)) {
            List<Role> listActiveRoles = userService.getActiveListRole(user.getId());
            boolean isActiveRoles = true;
            if (listActiveRoles.size() == 0) {
                isActiveRoles = false;
            }
            Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
            for (Role role : listActiveRoles) {
                setAuths.add(new SimpleGrantedAuthority(role.getName()));
            }
            org.springframework.security.core.userdetails.User userDetail =
                    new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                            isActiveRoles, true, true, true, setAuths);

            return userDetail;
        }
        throw new UsernameNotFoundException(username + " not found");

    }


}
