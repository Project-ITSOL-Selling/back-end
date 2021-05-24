package com.example.selling.data.repository;

import com.example.selling.data.entity.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
//    @Query("select u from user_role u where u.idUser = :id")
//    Iterable<UserRole> findUserRoleById(@Param("id") int userId);

    Iterable<UserRole> findUserRoleByIdUser(int idUser);

}
