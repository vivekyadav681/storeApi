package com.spring.storeapi.dtos;


import lombok.Data;

@Data
public class CheckoutResponse {
    private Long OrderId;

    public CheckoutResponse(Long id) {
        this.OrderId = id;
    }
}
