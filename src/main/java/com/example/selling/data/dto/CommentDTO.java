package com.example.selling.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Data
public class CommentDTO {

    private int id;

    private int idCustomer;

    private int idProduct;

    private Date createdDate;

    private String content;
}
