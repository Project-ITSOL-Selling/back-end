package com.example.selling.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
public class BillDetailDTO {


    private int id;

    private int idProduct;

    private int idBillOrder;

    private Date createdDate;

    private Long totalMoney;
}
