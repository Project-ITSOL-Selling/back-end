package com.example.selling.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class BillOrderDTO {
    private int id;

    private double price;

    private String description;

    private int quantity;

    private Date createdDate;
}
