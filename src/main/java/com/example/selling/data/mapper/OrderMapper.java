package com.example.selling.data.mapper;



import com.example.selling.data.dto.OrderDTO;

import com.example.selling.data.entity.DBOOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends MapperManager<DBOOrder, OrderDTO>{
}
