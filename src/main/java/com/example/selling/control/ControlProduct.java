package com.example.selling.control;

import com.example.selling.common.Response;
import com.example.selling.constants.Constants;
import com.example.selling.data.entity.Product;
import com.example.selling.data.repository.ProductRepository;
import com.example.selling.data.service.ProductService;
import com.example.selling.ultis.bean.ProductBean;
import com.example.selling.ultis.form.FormProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ControlProduct {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/getlistproduct")
    public @ResponseBody
    Response getlist() {
        List<Product> lst = productRepository.findAll();
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(lst);
    }

    @PostMapping("/createproduct")
    public @ResponseBody
    Response saveOrUpdate(@RequestBody FormProduct formProduct) {
        Product p;
        if (formProduct.getId() > 0) {
            p = productRepository.findById(formProduct.getId()).orElse(null);
        } else {
            p = new Product();
        }
        p.setId(formProduct.getId());
//        p.setIdCategory(formProduct.getIdCategory());
        p.setIdSupplier(formProduct.getIdSupplier());
        p.setImage(formProduct.getImage());
        p.setName(formProduct.getName());
        p.setPrice(formProduct.getPrice());
        p.setQuantity(formProduct.getQuantity());
        productRepository.save(p);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @DeleteMapping("/deleteproduct/{id}")
    public @ResponseBody
    Response deleteProduct(@PathVariable int id) {
        Product p = productRepository.findById(id).orElse(null);
        if (Objects.isNull(p)) {
            return new Response("Ban ghi da bi xoa");
        }
        productRepository.deleteById(id);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }
}


