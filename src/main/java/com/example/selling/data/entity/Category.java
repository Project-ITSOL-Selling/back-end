package com.example.selling.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "category")
@Getter
@Setter
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;

}
