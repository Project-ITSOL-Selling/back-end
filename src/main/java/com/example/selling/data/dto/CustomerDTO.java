package com.example.selling.data.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private int id;

    private String name;

    private Date dob;

    private String gender;

    private String address;

    private String email;

    private String phoneNumber;
}
