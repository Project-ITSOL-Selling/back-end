package com.example.selling.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "bill_detail")
@Setter
@Getter
public class BillDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_product")
    private int idProduct;
    @Column(name = "id_bill_order")
    private int idBillOrder;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "total_money")
    private Long totalMoney;
}
