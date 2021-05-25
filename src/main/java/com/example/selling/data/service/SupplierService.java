package com.example.selling.data.service;

import com.example.selling.data.dto.SupplierDTO;
import com.example.selling.data.entity.Supplier;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.SupplierMapper;
import com.example.selling.data.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    public SupplierRepository repository;
    @Autowired
    public SupplierMapper mapper;

    public List<SupplierDTO> getAll() {
        List<Supplier> all = repository.findAll();
        return mapper.toDTO(all);
    }

    public SupplierDTO getCusById(int id) {
        Supplier Supplier = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with id" + id + "not found!"));
        return mapper.toDTO(Supplier);
    }

    public SupplierDTO save(SupplierDTO SupplierDTO) {
        Supplier Supplier = mapper.toEntity(SupplierDTO);
        Supplier save = repository.save(Supplier);
        return mapper.toDTO(save);
    }

    public SupplierDTO update(SupplierDTO SupplierDTO, int id) {
        Supplier c = mapper.toEntity(SupplierDTO);
        Supplier Supplier = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with id" + id + "not found!"));

        Supplier.setId(id);
        Supplier.setAddress(c.getAddress());
        Supplier.setDescription(c.getDescription());
        Supplier.setLogo(c.getLogo());
        Supplier.setName(c.getName());

        return mapper.toDTO(Supplier);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}
