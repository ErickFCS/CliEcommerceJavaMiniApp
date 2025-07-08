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

import com.erickfcs.ecommerce_backend.datatransferobjects.OrderItemRequest;
import com.erickfcs.ecommerce_backend.models.OrderItems;
import com.erickfcs.ecommerce_backend.models.Orders;
import com.erickfcs.ecommerce_backend.models.Products;
import com.erickfcs.ecommerce_backend.services.OrderItemService;
import com.erickfcs.ecommerce_backend.services.OrderService;
import com.erickfcs.ecommerce_backend.services.ProductService;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
    final private OrderItemService orderItemService;
    final private ProductService productService;
    final private OrderService orderService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService, ProductService productService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItems>> getOrderItems() {
        return ResponseEntity.ok(orderItemService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItems> getOrderItemById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderItemService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OrderItems> createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        final Products targetProduct = productService.getById(orderItemRequest.getProductId());
        Orders targetOrder = orderService.getById(orderItemRequest.getOrderId());
        OrderItems newOrderItem = new OrderItems();
        newOrderItem.setOrder(targetOrder);
        newOrderItem.setProduct(targetProduct);
        newOrderItem.setQuantity(orderItemRequest.getQuantity());
        return ResponseEntity.ok(orderItemService.create(newOrderItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItems> updateOrderItem(@PathVariable Integer id, @RequestBody OrderItemRequest orderItemRequest) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        final Products targetProduct = productService.getById(orderItemRequest.getProductId());
        Orders targetOrder = orderService.getById(orderItemRequest.getOrderId());
        OrderItems newOrderItem = new OrderItems();
        newOrderItem.setOrder(targetOrder);
        newOrderItem.setProduct(targetProduct);
        newOrderItem.setQuantity(orderItemRequest.getQuantity());
        return ResponseEntity.ok(orderItemService.update(id, newOrderItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrderItem(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderItemService.delete(id));
    }
}
