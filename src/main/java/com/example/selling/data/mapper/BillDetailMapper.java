package com.example.selling.data.mapper;


import com.example.selling.data.dto.BillDetailDTO;

import com.example.selling.data.entity.BillDetail;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillDetailMapper extends MapperManager<BillDetail, BillDetailDTO>{
}
