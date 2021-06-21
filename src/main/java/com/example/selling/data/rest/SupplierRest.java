package com.example.selling.data.rest;

import com.example.selling.data.dto.SupplierDTO;
import com.example.selling.data.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/supplier")
public class SupplierRest {
    @Autowired
    public SupplierService service;

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getCusById(id));
    }
    @PostMapping
    public ResponseEntity<SupplierDTO> save(@RequestBody SupplierDTO c){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(c));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> update(@PathVariable Integer id,@RequestBody SupplierDTO c){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(c,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
