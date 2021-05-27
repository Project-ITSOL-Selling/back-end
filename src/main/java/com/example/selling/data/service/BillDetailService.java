package com.example.selling.data.service;

import com.example.selling.data.dto.BillDetailDTO;
import com.example.selling.data.entity.BillDetail;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.BillDetailMapper;
import com.example.selling.data.repository.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailService {
    @Autowired
    public BillDetailRepository repository;
    @Autowired
    public BillDetailMapper mapper;

    public List<BillDetailDTO> getAll(){
        List<BillDetail> all = repository.findAll();
        return mapper.toDTO(all);
    }
    public BillDetailDTO getCusById(int id){
        BillDetail BillDetail = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("BillDetail with id"+id+"not found!"));
        return mapper.toDTO(BillDetail);
    }
    public BillDetailDTO save(BillDetailDTO BillDetailDTO){
        BillDetail BillDetail = mapper.toEntity(BillDetailDTO);
        BillDetail save = repository.save(BillDetail);
        return mapper.toDTO(save);
    }
    public BillDetailDTO update(BillDetailDTO BillDetailDTO,int id){
        BillDetail c = mapper.toEntity(BillDetailDTO);
        BillDetail BillDetail = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("BillDetail with id"+id+"not found!"));

            BillDetail.setId(id);
            BillDetail.setCreatedDate(c.getCreatedDate());
            BillDetail.setIdBillOrder(c.getIdBillOrder());
            BillDetail.setIdProduct(c.getIdProduct());
            BillDetail.setTotalMoney(c.getTotalMoney());

        return mapper.toDTO(repository.save(BillDetail));
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
