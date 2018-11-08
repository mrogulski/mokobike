package com.mokobike.controller;

import com.mokobike.domain.Order;
import com.mokobike.exceptions.NoBikeAvailableException;
import com.mokobike.repository.BikeRepository;
import com.mokobike.repository.OrderRepository;
import com.mokobike.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import com.mokobike.exceptions.NotFoundException;
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
    NotifyService notifyService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @ResponseBody
    public List<Order> getOrders(
            @RequestParam(name="page", required = false) Integer page,
            @RequestParam(name="size", required =  false) Integer size
    ){
        List<Order> orders;

        if(page != null & size != null){
            orders = orderRepository.findAllOrders(page, size);
        }
        else {
            orders =  orderRepository.findAllOrders();
        }

        return orders;
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

        notifyService.notify(order);

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
    public void deleteOrder(@PathVariable("order_id") long orderID) throws Exception{
        Order order = orderRepository.findByID(orderID);
        if(order == null){
            throw new NotFoundException(orderID);
        }else{
            orderRepository.delete(orderID);
//            notifyService.notify(order);
        }
        logger.info("someone trying to delete order number " + orderID);
    }

    @PatchMapping(value = "/{order_id}")
    @ResponseBody
    public Order updateOrder(@PathVariable("order_id") long orderID, @RequestBody Order order) throws Exception{
        order.setId(orderID);
        Order updatedOrder = orderRepository.update(order);
        if(updatedOrder == null){
            throw new NotFoundException(orderID);
        }
//        notifyService.notify(order);
        logger.info("someone trying to update order number " + orderID);
        return updatedOrder;
    }
}
