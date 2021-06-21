package com.example.selling.data.mapper;

import com.example.selling.data.dto.OrderDetailDTO;
import com.example.selling.data.entity.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper extends MapperManager<OrderDetail, OrderDetailDTO>{
}
