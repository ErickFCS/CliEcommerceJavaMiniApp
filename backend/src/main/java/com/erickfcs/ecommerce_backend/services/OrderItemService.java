package com.erickfcs.ecommerce_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erickfcs.ecommerce_backend.models.OrderItems;
import com.erickfcs.ecommerce_backend.repositories.OrderItemRepository;

@Service
public class OrderItemService {
    private final OrderItemRepository repo;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.repo = orderItemRepository;
    }

    public List<OrderItems> getAll() {
        return repo.findAll();
    }

    public OrderItems getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public OrderItems create(OrderItems orderItem) {
        return repo.save(orderItem);
    }

    public OrderItems update(Integer id, OrderItems orderItem) {
        if (repo.existsById(id)) {
            orderItem.setId(id);
            return repo.save(orderItem);
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
