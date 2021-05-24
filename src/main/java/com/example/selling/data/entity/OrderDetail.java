package com.example.selling.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "order_detail")
@Getter
@Setter
public class OrderDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_product")
    private int idProduct;
    @Column(name = "id_order")
    private int idOrder;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "total_money")
    private int totalMoney;

}
