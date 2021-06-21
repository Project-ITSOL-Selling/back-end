package com.example.selling.data.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
public class OrderDetailDTO {

    private int id;

    private int idProduct;

    private int idOrder;

    private int quantity;

    private int totalMoney;

}
