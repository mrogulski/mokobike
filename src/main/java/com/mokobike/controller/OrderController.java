package com.mokobike.controller;

import com.mokobike.domain.Order;
import com.mokobike.domain.User;
import com.mokobike.exceptions.NoBikeAvailableException;
import com.mokobike.repository.UserRepository;
import com.mokobike.service.MailSender;
import com.mokobike.repository.BikeRepository;
import com.mokobike.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.mokobike.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( value = "/orders")
public class OrderController extends Controller{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BikeRepository bikeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Qualifier("gMailSender")
    MailSender mailSender;


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
    public Long save(@RequestBody Order order)throws Exception{
        
        Long orderID;

        String dateFrom = order.getDateFrom().toString();
        String dateTo = order.getDateTo().toString();
        Integer availableAdultBikes = bikeRepository.findAvailableBikes(dateFrom, dateTo, "ADULT");
        Integer availableChildBikes = bikeRepository.findAvailableBikes(dateFrom, dateTo, "CHILD");

        if(availableAdultBikes < order.getAdultBike() || availableChildBikes < order.getChildBike()){
            throw new NoBikeAvailableException(dateFrom, dateTo, availableAdultBikes, availableChildBikes);
        }else{
            orderRepository.save(order);
            order = orderRepository.findLatestOrder();
            orderID = order.getId();
        }

        User user = userRepository.findByID(order.getUserId());
        mailSender.sendMail(order, user);

        return orderID;
    }

    @GetMapping(value = "/{order_id}")
    @ResponseBody
    public Order getById( @PathVariable("order_id") long orderID){
        Order order = orderRepository.findByID(orderID);
        if(order == null){
            throw new NotFoundException(orderID);
        }
        logger.info("someone is asking about order number " + orderID);
        return order;
    }


    @DeleteMapping(value = "/{order_id}" )
    public void deleteOrder(@PathVariable("order_id") long orderID) {
        Order order = orderRepository.findByID(orderID);
        if(order == null){
            throw new NotFoundException(orderID);
        }else{
            orderRepository.delete(orderID);
        }
        logger.info("someone trying to delete order number " + orderID);
    }

    @PatchMapping(value = "/{order_id}")
    @ResponseBody
    public Order updateOrder(@PathVariable("order_id") long orderID, @RequestBody Order order){
        order.setId(orderID);
        Order updatedOrder = orderRepository.update(order);
        if(updatedOrder == null){
            throw new NotFoundException(orderID);
        }
        return updatedOrder;
    }
}
