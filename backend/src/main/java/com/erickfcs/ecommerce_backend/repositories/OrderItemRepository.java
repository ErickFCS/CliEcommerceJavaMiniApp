package com.erickfcs.ecommerce_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erickfcs.ecommerce_backend.models.OrderItems;
import com.erickfcs.ecommerce_backend.summaries.OrderItemsSummary;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Integer>{
    @Query("SELECT oi.quantity as quantity, p.name as name, p.description as description, p.price as price, p.category as category, p.stock as stock, p.price * oi.quantity as subTotal FROM OrderItems oi INNER JOIN Products p ON oi.productId = p.id WHERE oi.orderId = ?1")
    List<OrderItemsSummary> findByOrderId(Integer orderId);
}