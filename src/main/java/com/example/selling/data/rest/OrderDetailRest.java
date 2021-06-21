package com.example.selling.data.rest;

import com.example.selling.data.dto.OrderDetailDTO;
import com.example.selling.data.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orderdetail")
public class OrderDetailRest {
    @Autowired
    public OrderDetailService service;

    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getCusById(id));
    }
    @PostMapping
    public ResponseEntity<OrderDetailDTO> save(@RequestBody OrderDetailDTO c){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(c));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> update(@PathVariable Integer id,@RequestBody OrderDetailDTO c){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(c,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
