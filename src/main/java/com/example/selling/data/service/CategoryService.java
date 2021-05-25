package com.example.selling.data.service;

import com.example.selling.data.dto.CategoryDTO;
import com.example.selling.data.entity.Category;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.CategoryMapper;
import com.example.selling.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository repository;
    @Autowired
    public CategoryMapper mapper;

    public List<CategoryDTO> getAll(){
        List<Category> all = repository.findAll();
        return mapper.toDTO(all);
    }
    public CategoryDTO getCusById(int id){
        Category Category = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category with id"+id+"not found!"));
        return mapper.toDTO(Category);
    }
    public CategoryDTO save(CategoryDTO CategoryDTO){
        Category Category = mapper.toEntity(CategoryDTO);
        Category save = repository.save(Category);
        return mapper.toDTO(save);
    }
    public CategoryDTO update(CategoryDTO CategoryDTO,int id){
        Category c = mapper.toEntity(CategoryDTO);
        Category Category = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category with id"+id+"not found!"));

            Category.setId(id);
            Category.setDescription(c.getDescription());
            Category.setImage(c.getImage());
            Category.setName(c.getName());

        return mapper.toDTO(Category);
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
