package com.example.selling.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "dbo_order")
@Setter
@Getter
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_customer")
    private int idCustomer;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "total_money")
    private Long totalMoney;
    @Column(name = "description")
    private String description;

}
