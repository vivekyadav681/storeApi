package com.spring.storeapi.services;


import com.spring.storeapi.dtos.OrderDto;
import com.spring.storeapi.exceptions.OrderNotFoundException;
import com.spring.storeapi.mappers.OrderMapper;
import com.spring.storeapi.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



@AllArgsConstructor
@Service
public class OrderService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var orders = orderRepository.getAllByCustomer(user);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrder(Long orderId) {
        var order = orderRepository
                .getOrderWithItems(orderId)
                .orElseThrow(OrderNotFoundException::new);
        var user = authService.getCurrentUser();
        if(!(order.isPlacedBy(user))) {
            throw new AccessDeniedException("You dont have access to this order");
        }

        return orderMapper.toDto(order);
    }
}
