package com.mokobike.repository;

import com.mokobike.domain.Order;

import java.util.List;

public interface OrderRepository {

    Order findByID(Long ID);

    List<Order> findByUserID(Long userID);

    List<Order> findAllOrders(int page, int size);

    List<Order> findAllOrders();

    int ordersCount();

    Order save(Order order);

    void update(Order order);

    void delete(Long orderID);

}
