package com.example.selling.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "comment")
@Getter
@Setter
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_customer")
    private int idCustomer;
    @Column(name = "id_product")
    private int idProduct;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "content")
    private String content;
}
