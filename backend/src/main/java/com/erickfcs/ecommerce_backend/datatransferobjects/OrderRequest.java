package com.erickfcs.ecommerce_backend.datatransferobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String status;
    private Integer userId;
}
