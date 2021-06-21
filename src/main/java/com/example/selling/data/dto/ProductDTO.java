package com.example.selling.data.dto;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int id;

    private int idCategory;

    private int idSupplier;

    private String name;

    private double price;

    private String image;

    private int quantity;


}
