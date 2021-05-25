package com.example.selling.data.mapper;


import com.example.selling.data.dto.CategoryDTO;

import com.example.selling.data.entity.Category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends MapperManager<Category, CategoryDTO>{
}
