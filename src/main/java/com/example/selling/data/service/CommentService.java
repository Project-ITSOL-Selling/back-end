package com.example.selling.data.service;

import com.example.selling.data.dto.CommentDTO;
import com.example.selling.data.entity.Comment;
import com.example.selling.data.exception.ResourceNotFoundException;
import com.example.selling.data.mapper.CommentMapper;
import com.example.selling.data.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    public CommentRepository repository;
    @Autowired
    public CommentMapper mapper;

    public List<CommentDTO> getAll(){
        List<Comment> all = repository.findAll();
        return mapper.toDTO(all);
    }
    public CommentDTO getCusById(int id){
        Comment Comment = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Comment with id"+id+"not found!"));
        return mapper.toDTO(Comment);
    }
    public CommentDTO save(CommentDTO CommentDTO){
        Comment Comment = mapper.toEntity(CommentDTO);
        Comment save = repository.save(Comment);
        return mapper.toDTO(save);
    }
    public CommentDTO update(CommentDTO CommentDTO,int id){
        Comment c = mapper.toEntity(CommentDTO);
        Comment Comment = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Comment with id"+id+"not found!"));

            Comment.setId(id);
            Comment.setContent(c.getContent());
            Comment.setCreatedDate(c.getCreatedDate());
            Comment.setIdCustomer(c.getIdCustomer());
            Comment.setIdProduct(c.getIdProduct());


        return mapper.toDTO(repository.save(Comment));
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
