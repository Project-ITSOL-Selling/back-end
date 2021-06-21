package com.example.selling.data.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillOrderDTO {
    private int id;

    private double price;

    private String description;

    private int quantity;

    private Date createdDate;
}
