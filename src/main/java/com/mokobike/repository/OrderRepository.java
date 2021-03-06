package com.mokobike.repository;

import com.mokobike.domain.Order;

import java.util.List;

public interface OrderRepository {

    Order findByID(Long ID);

    List<Order> findByUserID(Long userID);

    List<Order> findAllOrders(int page, int size);

    List<Order> findAllOrders();

    List<Order> findOrdersByDate(String start, String end);

    int ordersCount();

    void save(Order order);

    Order update(Order order);

    void delete(Long orderID);

    Order findLatestOrder();

}
