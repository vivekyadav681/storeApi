package com.spring.storeapi.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


@Data
public class CartItemDto {
    private CartProductDto product;
    private int quantity;
    private BigDecimal totalPrice;


}
