package com.mokobike.controller;

import com.mokobike.domain.Order;
import com.mokobike.error.Error;
import com.mokobike.exceptions.order.OrderNotFoundException;
import com.mokobike.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @PostMapping( value = "/new")
    @ResponseBody
    public Long save(@RequestBody Order order){
        Long orderID;
        orderRepository.save(order);
        orderID = orderRepository.findLatestOrder().getId();

        return orderID;
    }

    @GetMapping(value = "/{order_id}")
    @ResponseBody
    public Order getById( @PathVariable("order_id") long orderID){
        Order order = orderRepository.findByID(orderID);
        if(order == null){
            throw new OrderNotFoundException(orderID);
        }

        return order;
    }

}
