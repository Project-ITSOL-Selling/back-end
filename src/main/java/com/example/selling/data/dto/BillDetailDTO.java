package com.example.selling.data.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillDetailDTO {


    private int id;

    private int idProduct;

    private int idBillOrder;

    private Date createdDate;

    private Long totalMoney;
}
