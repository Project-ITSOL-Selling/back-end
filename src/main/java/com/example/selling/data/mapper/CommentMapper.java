package com.example.selling.data.mapper;



import com.example.selling.data.dto.CommentDTO;
import com.example.selling.data.entity.Comment;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends MapperManager<Comment, CommentDTO>{
}
