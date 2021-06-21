package com.example.selling.control;

import com.example.selling.common.Response;
import com.example.selling.constants.Constants;
import com.example.selling.data.entity.Category;
import com.example.selling.data.repository.CategoryRepository;
import com.example.selling.data.service.CategoryService;
import com.example.selling.domain.DataTableResults;
import com.example.selling.ultis.bean.CategoryBean;
import com.example.selling.ultis.form.FormCategory;
import com.example.selling.ultis.form.FormUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
@CrossOrigin("'http://localhost:4200")
public class ControlCategory {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/getlistcategory")
    public @ResponseBody
    Response getList() {
        List<Category> lst = categoryRepository.findAll();
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(lst);
    }

    @PostMapping("/createcategory")
    public @ResponseBody
    Response saveOrUpdate(@RequestBody FormCategory formCategory) {
        Category c;
        if (formCategory.getId() > 0) {
            c = categoryRepository.findById(formCategory.getId()).orElse(null);
        } else {
            c = new Category();
        }
        c.setId(formCategory.getId());
        c.setDescription(formCategory.getDescription());
        c.setImage(formCategory.getImage());
        c.setName(formCategory.getName());
        categoryRepository.save(c);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @DeleteMapping("deletecategory/{id}")
    public @ResponseBody
    Response deleteCategory(@PathVariable int id) {
        Category c = categoryRepository.findById(id).orElse(null);
        if (Objects.isNull(c)) {
            return new Response("Ban ghi da bi xoa");
        }
        categoryRepository.deleteById(id);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }
    @GetMapping("/search")
    public @ResponseBody
    DataTableResults<CategoryBean> processSearch(FormCategory form) {
        return categoryService.getDataTables(form);
    }
}

