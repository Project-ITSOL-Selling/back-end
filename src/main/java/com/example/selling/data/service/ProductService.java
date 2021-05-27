package com.example.selling.data.service;

import com.example.selling.data.dto.ProductDTO;
import com.example.selling.data.entity.Product;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.ProductMapper;
import com.example.selling.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    public ProductRepository repository;
    @Autowired
    public ProductMapper mapper;

    public List<ProductDTO> getAll() {

        List<Product> all = repository.findAll();
        return mapper.toDTO(all);
    }

    public ProductDTO getCusById(int id) {
        Product Product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id" + id + "not found!"));
        return mapper.toDTO(Product);
    }

    public ProductDTO save(ProductDTO ProductDTO) {
        Product Product = mapper.toEntity(ProductDTO);
        Product save = repository.save(Product);
        return mapper.toDTO(save);
    }

    public ProductDTO update(ProductDTO ProductDTO, int id) {
        Product c = mapper.toEntity(ProductDTO);
        Product Product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id" + id + "not found!"));

        Product.setId(id);
        Product.setIdCategory(c.getIdCategory());
        Product.setIdSupplier(c.getIdSupplier());
        Product.setImage(c.getImage());
        Product.setName(c.getName());
        Product.setPrice(c.getPrice());
        Product.setQuantity(c.getQuantity());

        return mapper.toDTO(Product);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}
