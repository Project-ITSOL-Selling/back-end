package com.example.selling.data.mapper;


import com.example.selling.data.dto.BillOrderDTO;

import com.example.selling.data.entity.BillOrder;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillOrderMapper extends MapperManager<BillOrder, BillOrderDTO>{
}
