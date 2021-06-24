package com.example.selling.ultis.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBean {
    private Integer id;
    private String nameCategory;
    private Integer idSupplier;
    private String name;
    private Double price;
    private String image;
    private Integer quantity;
}
