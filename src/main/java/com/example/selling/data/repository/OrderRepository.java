package com.example.selling.data.repository;


import com.example.selling.data.entity.DBOOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<DBOOrder,Integer> {
}
