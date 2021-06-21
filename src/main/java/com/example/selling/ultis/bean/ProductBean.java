package com.example.selling.ultis.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBean {
    private int id;
    private int idCategory;
    private int idSupplier;
    private String name;
    private double price;
    private String image;
    private int quantity;
}
