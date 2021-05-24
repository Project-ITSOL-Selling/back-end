package com.example.selling.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Data
public class CustomerDTO {

    private int id;

    private String name;

    private Date dob;

    private String gender;

    private String address;

    private String email;

    private String phoneNumber;
}
