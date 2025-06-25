package com.erickfcs.ecommerce_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erickfcs.ecommerce_backend.models.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT o FROM Orders o WHERE o.userId = ?1")
    List<Orders> findByUserId(Integer userId);
}
