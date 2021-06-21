package com.example.selling.data.dto;

import lombok.*;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private int id;

    private int idCustomer;

    private Date createdDate;

    private Long totalMoney;

    private String description;

}
