package com.erickfcs.ecommerce_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erickfcs.ecommerce_backend.datatransferobjects.OrderRequest;
import com.erickfcs.ecommerce_backend.models.Orders;
import com.erickfcs.ecommerce_backend.models.Users;
import com.erickfcs.ecommerce_backend.services.OrderService;
import com.erickfcs.ecommerce_backend.services.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    final OrderService orderService;
    final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody OrderRequest orderRequest) {
        final Users user = userService.getById(orderRequest.getUserId());
        Orders newOrder = new Orders();
        newOrder.setStatus(orderRequest.getStatus());
        newOrder.setUser(user);
        return ResponseEntity.ok(orderService.create(newOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Integer id, @RequestBody OrderRequest orderRequest) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        final Users user = userService.getById(orderRequest.getUserId());
        Orders newOrder = new Orders();
        newOrder.setStatus(orderRequest.getStatus());
        newOrder.setUser(user);
        return ResponseEntity.ok(orderService.update(id, newOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.delete(id));
    }
}
