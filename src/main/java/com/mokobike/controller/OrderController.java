package com.mokobike.controller;

import com.mokobike.domain.Order;
import com.mokobike.domain.User;
import com.mokobike.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.GET)
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
        count.put("total_users", orderRepository.ordersCount());

        response.add(count);
        response.add(orders);

        return response;
    }
}
