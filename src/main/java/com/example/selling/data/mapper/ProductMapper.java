package com.example.selling.data.mapper;



import com.example.selling.data.dto.ProductDTO;

import com.example.selling.data.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends MapperManager<Product, ProductDTO>{
}
