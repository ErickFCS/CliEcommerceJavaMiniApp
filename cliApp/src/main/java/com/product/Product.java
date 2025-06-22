package com.product;

import java.util.HashMap;

public class Product {
    private static HashMap<Integer, Product> products = new HashMap<Integer, Product>();
    private static Integer biggestId = 0;
    private Integer id;
    private String name;
    private Double price;
    private Integer stock;

    public Product(String name, Double price, Integer initialStock) {
        this.id = biggestId;
        this.name = name;
        this.price = price;
        this.stock = initialStock;
        products.put(this.id, this);
        biggestId++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public static Product getProductById(Integer id) {
        return products.get(id);
    }

    public static Product getProductByName(String name) {
        for (Integer element : products.keySet()) {
            if (products.get(element).getName().equalsIgnoreCase(name)) {
                return products.get(element);
            }
        }
        return null;
    }

    public static HashMap<Integer, Product> getProducts() {
        return products;
    }

    public static Product deleteProductById(Integer id) {
        return products.remove(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
