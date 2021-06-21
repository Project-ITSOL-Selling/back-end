package com.example.selling.data.mapper;

import java.util.List;

public interface MapperManager<E,D> {
     E toEntity(D b);
     D toDTO(E e);
     List<E> toEntity(List<D> lstD);
     List<D> toDTO(List<E> lstE);
}
