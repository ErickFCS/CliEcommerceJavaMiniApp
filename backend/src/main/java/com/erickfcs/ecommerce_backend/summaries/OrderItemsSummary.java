package com.erickfcs.ecommerce_backend.summaries;

public interface OrderItemsSummary {
    Integer getQuantity();
    String getName();
    String getDescription();
    Double getPrice();
    String getCategory();
    Integer getStock();
    Double getSubTotal();
}
