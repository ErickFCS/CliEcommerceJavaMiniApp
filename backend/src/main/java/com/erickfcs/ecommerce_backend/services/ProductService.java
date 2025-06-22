package com.erickfcs.ecommerce_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erickfcs.ecommerce_backend.models.Products;
import com.erickfcs.ecommerce_backend.repositories.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repo;

    @Autowired
    ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Products> getAll() {
        return repo.findAll();
    }

    public Products getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public List<Products> getByNameOrDescription(String nameOrDescription) {
        return repo.findByNameOrDescription(nameOrDescription);
    }

    public Products create(Products product) {
        return repo.save(product);
    }

    public Products update(Integer id, Products product) {
        if (repo.existsById(id)) {
            product.setId(id);
            return repo.save(product);
        }
        return null;
    }

    public boolean delete(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
