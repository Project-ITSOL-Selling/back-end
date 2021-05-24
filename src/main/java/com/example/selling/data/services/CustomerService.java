package com.example.selling.data.services;

import com.example.selling.data.dto.CustomerDTO;
import com.example.selling.data.entity.Customer;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.CustomerMapper;
import com.example.selling.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    public CustomerRepository repository;
    @Autowired
    public CustomerMapper mapper;

    public List<CustomerDTO> getAll(){
        List<Customer> all = repository.findAll();
        return mapper.toDTO(all);
    }
    public CustomerDTO getCusById(int id){
        Customer customer = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer with id"+id+"not found!"));
        return mapper.toDTO(customer);
    }
    public CustomerDTO save(CustomerDTO customerDTO){
        Customer customer = mapper.toEntity(customerDTO);
        Customer save = repository.save(customer);
        return mapper.toDTO(save);
    }
    public CustomerDTO update(CustomerDTO customerDTO,int id){
        Customer c = mapper.toEntity(customerDTO);
        Customer customer = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer with id"+id+"not found!"));

            customer.setId(id);
            customer.setAddress(c.getAddress());
            customer.setDob(c.getDob());
            customer.setEmail(c.getEmail());
            customer.setGender(c.getGender());
            customer.setName(c.getName());
            customer.setPhoneNumber(c.getPhoneNumber());

        return mapper.toDTO(customer);
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
