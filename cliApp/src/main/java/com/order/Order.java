package com.order;

import java.util.HashMap;

import com.product.Product;

public class Order {
    private static HashMap<Integer, Order> orders = new HashMap<Integer, Order>();
    private static Integer biggestId = 0;
    private Integer id;
    private HashMap<Integer, Integer> items = new HashMap<Integer, Integer>(); // Maps product ID to quantity

    public Order() {
        this.id = biggestId;
        orders.put(this.id, this);
        biggestId++;
    }

    public Integer getId() {
        return id;
    }

    public void addItem(Product productId, Integer quantity) {
        if (items.containsKey(productId.getId())) {
            items.put(productId.getId(), items.get(productId.getId()) + quantity);
        } else {
            items.put(productId.getId(), quantity);
        }
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Integer productId : items.keySet()) {
            Product product = Product.getProductById(productId); // Assuming a method to get Product by ID
            total += product.getPrice() * items.get(productId);
        }
        return total;
    }

    public HashMap<Integer, Integer> getItems() {
        return items;
    }

    public static void cleanNullProducts() {
        HashMap<Integer, Order> newOrders = new HashMap<Integer, Order>();
        for (Order order : orders.values()) {
            HashMap<Integer, Integer> newItems = new HashMap<Integer, Integer>();
            for (Integer productId : order.items.keySet()) {
                if (Product.getProductById(productId) != null) {
                    newItems.put(productId, order.items.get(productId));
                }
            }
            if (!newItems.isEmpty()) {
                order.items = newItems;
                newOrders.put(order.id, order);
            }
        }
        orders = newOrders;
    }

    public static HashMap<Integer, Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
