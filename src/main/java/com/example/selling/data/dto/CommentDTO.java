package com.example.selling.data.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private int id;

    private int idCustomer;

    private int idProduct;

    private Date createdDate;

    private String content;
}
