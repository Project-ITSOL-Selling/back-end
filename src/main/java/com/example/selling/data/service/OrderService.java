package com.example.selling.data.service;

import com.example.selling.data.dto.OrderDTO;
import com.example.selling.data.entity.Order;
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
        List<Order> all = repository.findAll();
        return mapper.toDTO(all);
    }
    public OrderDTO getCusById(int id){
        Order Order = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Order with id"+id+"not found!"));
        return mapper.toDTO(Order);
    }
    public OrderDTO save(OrderDTO OrderDTO){
        Order Order = mapper.toEntity(OrderDTO);
        Order save = repository.save(Order);
        return mapper.toDTO(save);
    }
    public OrderDTO update(OrderDTO OrderDTO,int id){
        Order c = mapper.toEntity(OrderDTO);
        Order Order = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Order with id"+id+"not found!"));

            Order.setId(id);
            Order.setCreatedDate(c.getCreatedDate());
            Order.setDescription(c.getDescription());
            Order.setIdCustomer(c.getIdCustomer());
            Order.setTotalMoney(c.getTotalMoney());


        return mapper.toDTO(Order);
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
