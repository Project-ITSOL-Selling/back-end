package com.example.selling.data.rest;

import com.example.selling.data.dto.OrderDTO;
import com.example.selling.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderRest {
    @Autowired
    public OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getCusById(id));
    }
    @PostMapping
    public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO c){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(c));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable Integer id,@RequestBody OrderDTO c){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(c,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
