package com.example.selling.data.rest;

import com.example.selling.data.dto.BillOrderDTO;
import com.example.selling.data.service.BillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/billorder")
public class BillOrderRest {
    @Autowired
    public BillOrderService service;

    @GetMapping
    public ResponseEntity<List<BillOrderDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BillOrderDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getCusById(id));
    }
    @PostMapping
    public ResponseEntity<BillOrderDTO> save(@RequestBody BillOrderDTO c){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(c));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BillOrderDTO> update(@PathVariable Integer id,@RequestBody BillOrderDTO c){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(c,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
