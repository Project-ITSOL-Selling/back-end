package com.example.selling.ultis.form;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormProduct {
    private int id;
    private int idCategory;
    private int idSupplier;
    private String name;
    private double price;
    private String image;
    private int quantity;
}
