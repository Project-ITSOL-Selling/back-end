package com.example.selling.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class CategoryDTO {

    private int id;

    private String name;

    private String description;

    private String image;

}
