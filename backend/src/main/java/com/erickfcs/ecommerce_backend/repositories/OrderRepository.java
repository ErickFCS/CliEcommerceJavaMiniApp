package com.erickfcs.ecommerce_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erickfcs.ecommerce_backend.models.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
