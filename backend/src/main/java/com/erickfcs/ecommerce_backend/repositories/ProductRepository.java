package com.erickfcs.ecommerce_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erickfcs.ecommerce_backend.models.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT p FROM Products p WHERE p.name LIKE %?1% OR p.description LIKE %?1%")
    List<Products> findByNameOrDescription(String nameOrDescription);
}
