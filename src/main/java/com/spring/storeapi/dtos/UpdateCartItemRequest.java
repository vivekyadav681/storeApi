package com.spring.storeapi.dtos;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    @NotNull(message = "quantity must not be null")
    @Min(value = 1, message = "quantity must be at least 1")
    @Max(value = 1000, message = "quantity must be at most 1000")
    private Integer quantity;
}
