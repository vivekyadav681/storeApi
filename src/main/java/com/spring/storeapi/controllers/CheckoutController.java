package com.spring.storeapi.controllers;


import com.spring.storeapi.dtos.CheckoutRequest;
import com.spring.storeapi.dtos.CheckoutResponse;
import com.spring.storeapi.entities.Order;
import com.spring.storeapi.entities.OrderItem;
import com.spring.storeapi.entities.OrderStatus;
import com.spring.storeapi.repositories.CartRepository;
import com.spring.storeapi.repositories.OrderRepository;
import com.spring.storeapi.services.AuthService;
import com.spring.storeapi.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final CartService cartService;

    public ResponseEntity<?> checkout(@Valid @RequestBody CheckoutRequest request){
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if(cart == null) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Cart not found")
            );
        }
        if(cart.getCartItems().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Cart is empty")
            );
        }

        var order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCustomer(authService.getCurrentUser());

        cart.getCartItems().forEach(cartItem -> {
            var orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotal());
            order.getItems().add(orderItem);
        });

        orderRepository.save(order);
        cartService.clearCart(cart.getId());


        return ResponseEntity.ok(new CheckoutResponse(order.getId()));

    }
}
