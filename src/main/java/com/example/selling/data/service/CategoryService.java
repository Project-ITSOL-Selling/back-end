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
        Category category = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category with id"+id+"not found!"));
        Category category1 = new Category();
            category1.setId(id);
            category1.setDescription(c.getDescription());
            category1.setImage(c.getImage());
            category1.setName(c.getName());

        return mapper.toDTO(repository.save(category1));
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
