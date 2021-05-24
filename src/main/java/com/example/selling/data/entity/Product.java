package com.example.selling.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "product")
@Setter
@Getter
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_category")
    private int idCategory;
    @Column(name = "id_supplier")
    private int idSupplier;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "image")
    private String image;
    @Column(name = "quantity")
    private int quantity;


}
