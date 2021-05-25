package com.example.selling.data.service;

import com.example.selling.data.dto.OrderDetailDTO;
import com.example.selling.data.entity.OrderDetail;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.OrderDetailMapper;
import com.example.selling.data.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    public OrderDetailRepository repository;
    @Autowired
    public OrderDetailMapper mapper;

    public List<OrderDetailDTO> getAll(){
        List<OrderDetail> all = repository.findAll();
        return mapper.toDTO(all);
    }
    public OrderDetailDTO getCusById(int id){
        OrderDetail OrderDetail = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("OrderDetail with id"+id+"not found!"));
        return mapper.toDTO(OrderDetail);
    }
    public OrderDetailDTO save(OrderDetailDTO OrderDetailDTO){
        OrderDetail OrderDetail = mapper.toEntity(OrderDetailDTO);
        OrderDetail save = repository.save(OrderDetail);
        return mapper.toDTO(save);
    }
    public OrderDetailDTO update(OrderDetailDTO OrderDetailDTO,int id){
        OrderDetail c = mapper.toEntity(OrderDetailDTO);
        OrderDetail OrderDetail = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("OrderDetail with id"+id+"not found!"));

            OrderDetail.setId(id);
            OrderDetail.setIdOrder(c.getIdOrder());
            OrderDetail.setIdProduct(c.getIdProduct());
            OrderDetail.setQuantity(c.getQuantity());
            OrderDetail.setTotalMoney(c.getTotalMoney());

        return mapper.toDTO(OrderDetail);
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
