package com.spring.storeapi.controllers;


import com.spring.storeapi.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
        @RequestMapping("/message")
        public Message message() {
            return new Message("Hello World");
        }
}
