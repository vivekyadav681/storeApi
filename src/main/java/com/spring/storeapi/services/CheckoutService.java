package com.spring.storeapi.services;


import com.spring.storeapi.dtos.CheckoutRequest;
import com.spring.storeapi.dtos.CheckoutResponse;
import com.spring.storeapi.dtos.ErrorDto;
import com.spring.storeapi.entities.Order;
import com.spring.storeapi.exceptions.CartEmptyException;
import com.spring.storeapi.exceptions.CartNotFoundException;
import com.spring.storeapi.repositories.CartRepository;
import com.spring.storeapi.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class CheckoutService {
    private CartRepository cartRepository;
    private AuthService authService;
    private OrderRepository orderRepository;
    private CartService cartService;

    public CheckoutResponse checkout(CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if(cart == null) {
            throw new CartNotFoundException();

        }
        if(cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);
        cartService.clearCart(cart.getId());


        return new CheckoutResponse(order.getId());
    }
}
