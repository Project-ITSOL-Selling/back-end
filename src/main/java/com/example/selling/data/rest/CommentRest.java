package com.example.selling.data.rest;

import com.example.selling.data.dto.CommentDTO;
import com.example.selling.data.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comment")
public class CommentRest {
    @Autowired
    public CommentService service;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getCusById(id));
    }
    @PostMapping
    public ResponseEntity<CommentDTO> save(@RequestBody CommentDTO c){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(c));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable Integer id,@RequestBody CommentDTO c){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(c,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
