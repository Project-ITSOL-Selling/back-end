package com.example.selling.data.service;

import com.example.selling.data.dto.OrderDTO;
import com.example.selling.data.entity.DBOOrder;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.OrderMapper;
import com.example.selling.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    public OrderRepository repository;
    @Autowired
    public OrderMapper mapper;

    public List<OrderDTO> getAll(){
        List<DBOOrder> all = repository.findAll();
        return mapper.toDTO(all);
    }
    public OrderDTO getCusById(int id){
        DBOOrder DBOOrder = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Order with id"+id+"not found!"));
        return mapper.toDTO(DBOOrder);
    }
    public OrderDTO save(OrderDTO OrderDTO){
        DBOOrder DBOOrder = mapper.toEntity(OrderDTO);
        DBOOrder save = repository.save(DBOOrder);
        return mapper.toDTO(save);
    }
    public OrderDTO update(OrderDTO OrderDTO,int id){
        DBOOrder c = mapper.toEntity(OrderDTO);
        DBOOrder DBOOrder = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Order with id"+id+"not found!"));

            DBOOrder.setId(id);
            DBOOrder.setCreatedDate(c.getCreatedDate());
            DBOOrder.setDescription(c.getDescription());
            DBOOrder.setIdCustomer(c.getIdCustomer());
            DBOOrder.setTotalMoney(c.getTotalMoney());


        return mapper.toDTO(DBOOrder);
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
