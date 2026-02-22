package com.spring.storeapi.mappers;


import com.spring.storeapi.dtos.CartDto;
import com.spring.storeapi.dtos.CartItemDto;
import com.spring.storeapi.entities.Cart;
import com.spring.storeapi.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "items",  source = "cartItems")
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotal())")
    CartItemDto toDto(CartItem cartItem);
}
