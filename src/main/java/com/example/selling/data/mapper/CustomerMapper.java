package com.example.selling.data.mapper;


import com.example.selling.data.dto.CustomerDTO;
import com.example.selling.data.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends MapperManager<Customer, CustomerDTO>{
}
