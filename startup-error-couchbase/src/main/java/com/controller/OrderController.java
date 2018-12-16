package com.controller;

import com.entity.Order;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/all")
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @GetMapping("/hello")
    public String sayeHell() {
        return "hello";
    }
}
