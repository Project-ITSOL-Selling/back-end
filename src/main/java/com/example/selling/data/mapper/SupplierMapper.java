package com.example.selling.data.mapper;



import com.example.selling.data.dto.SupplierDTO;

import com.example.selling.data.entity.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper extends MapperManager<Supplier, SupplierDTO>{
}
