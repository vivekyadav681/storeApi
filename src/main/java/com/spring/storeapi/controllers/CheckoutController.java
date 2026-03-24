package com.spring.storeapi.controllers;


import com.spring.storeapi.dtos.CheckoutRequest;
import com.spring.storeapi.dtos.CheckoutResponse;
import com.spring.storeapi.dtos.ErrorDto;
import com.spring.storeapi.exceptions.CartEmptyException;
import com.spring.storeapi.exceptions.CartNotFoundException;
import com.spring.storeapi.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;


    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request){
        return checkoutService.checkout(request);
    }


    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex){
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }
}
