package com.spring.storeapi.mappers;


import com.spring.storeapi.dtos.OrderDto;
import com.spring.storeapi.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
