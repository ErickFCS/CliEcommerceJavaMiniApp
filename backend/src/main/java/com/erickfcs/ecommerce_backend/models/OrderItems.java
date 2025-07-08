package com.erickfcs.ecommerce_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Orders order;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", nullable = false)
    private Products product;

    @Transient
    public Double getSubTotal() {
        return quantity * product.getPrice();
    }
}
