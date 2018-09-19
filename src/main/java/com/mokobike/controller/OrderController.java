package com.mokobike.controller;

import com.mokobike.domain.Order;
import com.mokobike.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping( value = "/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @ResponseBody
    public List<Object> getOrders(
            @RequestParam(name="page") int page,
            @RequestParam(name="size") int size
    ){
        List<Object> response = new ArrayList<>();
        Map<String, Integer> count = new HashMap<>();
        List<Order> orders;
        orders = orderRepository.findAllOrders(page, size);
        count.put("total_orders", orderRepository.ordersCount());

        response.add(count);
        response.add(orders);

        return response;
    }

    @PostMapping
    @ResponseBody
    public Integer save(@RequestBody Order order){
        Integer orderID;
        orderRepository.save(order);
        orderID = orderRepository.findLatestOrder();

        return orderID;
    }

}
