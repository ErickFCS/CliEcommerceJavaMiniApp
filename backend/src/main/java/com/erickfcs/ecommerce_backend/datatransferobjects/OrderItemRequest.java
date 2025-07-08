package com.erickfcs.ecommerce_backend.datatransferobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private Integer quantity;
    private Integer productId;
    private Integer orderId;
}
