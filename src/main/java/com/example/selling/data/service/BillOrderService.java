package com.example.selling.data.service;

import com.example.selling.common.VfData;
import com.example.selling.data.dto.BillOrderDTO;
import com.example.selling.data.entity.BillOrder;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.BillOrderMapper;
import com.example.selling.data.repository.BillOrderRepository;
import com.example.selling.domain.DataTableResults;
import com.example.selling.ultis.bean.BillOrderBean;
import com.example.selling.ultis.bean.CategoryBean;
import com.example.selling.ultis.form.FormCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillOrderService {
    @Autowired
    public BillOrderRepository repository;
    @Autowired
    public BillOrderMapper mapper;

    public List<BillOrderDTO> getAll(){
        List<BillOrder> all = repository.findAll();
        return mapper.toDTO(all);
    }
    public BillOrderDTO getCusById(int id){
        BillOrder BillOrder = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("BillOrder with id"+id+"not found!"));
        return mapper.toDTO(BillOrder);
    }
    public BillOrderDTO save(BillOrderDTO BillOrderDTO){
        BillOrder BillOrder = mapper.toEntity(BillOrderDTO);
        BillOrder save = repository.save(BillOrder);
        return mapper.toDTO(save);
    }
    public BillOrderDTO update(BillOrderDTO BillOrderDTO,int id){
        BillOrder c = mapper.toEntity(BillOrderDTO);
        BillOrder BillOrder = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("BillOrder with id"+id+"not found!"));

            BillOrder.setId(id);
            BillOrder.setCreatedDate(c.getCreatedDate());
            BillOrder.setDescription(c.getDescription());
            BillOrder.setPrice(c.getPrice());
            BillOrder.setQuantity(c.getQuantity());

        return mapper.toDTO(BillOrder);
    }
    public void delete(int id){
        repository.deleteById(id);
    }
    @Autowired
    private VfData vfData;

    public DataTableResults<BillOrderBean> getDataTables(BillOrderDTO form) {
        DataTableResults<BillOrderBean> dtTable = repository.getDatatable(vfData, form);
//        List<CategoryBean> lst = dtTable.getData();
        return dtTable;
    }

}
