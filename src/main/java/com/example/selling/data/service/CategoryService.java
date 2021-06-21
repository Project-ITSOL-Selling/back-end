package com.example.selling.data.service;

import com.example.selling.common.VfData;
import com.example.selling.data.dto.CategoryDTO;
import com.example.selling.data.entity.Category;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.CategoryMapper;
import com.example.selling.data.repository.CategoryRepository;
import com.example.selling.domain.DataTableResults;
import com.example.selling.ultis.bean.CategoryBean;
import com.example.selling.ultis.form.FormCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository repository;
    @Autowired
    public CategoryMapper mapper;
    @Autowired
    public CategoryService service;

    public List<CategoryDTO> getAll() {
        List<Category> all = repository.findAll();
        return mapper.toDTO(all);
    }

    public CategoryDTO getCusById(int id) {
        Category Category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id" + id + "not found!"));
        return mapper.toDTO(Category);
    }

    public CategoryDTO save(CategoryDTO CategoryDTO) {
        Category Category = mapper.toEntity(CategoryDTO);
        Category save = repository.save(Category);
        return mapper.toDTO(save);
    }

    public CategoryDTO update(CategoryDTO CategoryDTO, int id) {
        Category c = mapper.toEntity(CategoryDTO);
        Category Category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id" + id + "not found!"));

        Category.setId(id);
        Category.setDescription(c.getDescription());
        Category.setImage(c.getImage());
        Category.setName(c.getName());

        return mapper.toDTO(Category);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    @Autowired
    private VfData vfData;

    public DataTableResults<CategoryBean> getDataTables(FormCategory form) {
        DataTableResults<CategoryBean> dtTable = repository.getDatatable(vfData, form);
//        List<CategoryBean> lst = dtTable.getData();
        return dtTable;
    }

}
