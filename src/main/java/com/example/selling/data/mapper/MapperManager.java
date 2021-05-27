package com.example.selling.data.mapper;

import java.util.List;

public interface MapperManager<E,D> {
    public E toEntity(D b);
     public  D toDTO(E e);
     public  List<E> toEntity(List<D> lstD);
     public List<D> toDTO(List<E> lstE);
}
