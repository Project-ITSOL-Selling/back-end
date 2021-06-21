package com.example.selling.data.repository;

import com.example.selling.data.entity.BillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillOrderRepository extends JpaRepository<BillOrder,Integer> {
}
