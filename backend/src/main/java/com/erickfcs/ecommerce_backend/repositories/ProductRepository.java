package com.erickfcs.ecommerce_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erickfcs.ecommerce_backend.models.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
}
