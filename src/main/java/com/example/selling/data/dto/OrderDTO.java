package com.example.selling.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class OrderDTO {

    private int id;

    private int idCustomer;

    private Date createdDate;

    private Long totalMoney;

    private String description;

}
