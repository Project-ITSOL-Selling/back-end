package com.example.selling.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class OrderDetailDTO {

    private int id;

    private int idProduct;

    private int idOrder;

    private int quantity;

    private int totalMoney;

}
