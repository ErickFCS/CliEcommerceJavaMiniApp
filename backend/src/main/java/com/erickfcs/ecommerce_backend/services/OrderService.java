package com.erickfcs.ecommerce_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erickfcs.ecommerce_backend.models.Orders;
import com.erickfcs.ecommerce_backend.repositories.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository repo;

    @Autowired
    OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<Orders> getAll() {
        return repo.findAll();
    }

    public List<Orders> getByUserId(Integer userId) {
        return repo.findByUserId(userId);
    }

    public Orders getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Orders create(Orders order) {
        return repo.save(order);
    }

    public Orders update(Integer id, Orders order) {
        if (repo.existsById(id)) {
            order.setId(id);
            return repo.save(order);
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
