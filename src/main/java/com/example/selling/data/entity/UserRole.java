package com.example.selling.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user_role")
@Getter
@Setter
public class UserRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private  int id;
    @Column(name = "id_role")
    private int idRole;
    @Column(name = "id_user")
    private int idUser;
}
