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

import com.erickfcs.ecommerce_backend.models.OrderItems;
import com.erickfcs.ecommerce_backend.services.OrderItemService;
import com.erickfcs.ecommerce_backend.summaries.OrderItemsSummary;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
    final private OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItems>> getOrderItems() {
        return ResponseEntity.ok(orderItemService.getAll());
    }

    @GetMapping("/{orderId}/products")
    public ResponseEntity<List<OrderItemsSummary>> getOrderItemsByOrderId(@PathVariable Integer orderId) {
        if (orderId == null || orderId <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderItemService.getByOrderId(orderId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItems> getOrderItemById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderItemService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OrderItems> createOrderItem(@RequestBody OrderItems order) {
        return ResponseEntity.ok(orderItemService.create(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItems> updateOrderItem(@PathVariable Integer id, @RequestBody OrderItems order) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderItemService.update(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrderItem(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderItemService.delete(id));
    }
}
