package com.example.selling.data.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private int id;
    private String name;
    private String logo;
    private String address;
    private String description;
}
