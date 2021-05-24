package com.example.selling.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class SupplierDTO {

    private int id;

    private String name;

    private String logo;
    private String address;
    private String description;
}
