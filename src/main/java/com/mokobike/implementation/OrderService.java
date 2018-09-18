package com.mokobike.implementation;

import com.mokobike.domain.Order;
import com.mokobike.mapper.OrderMapper;
import com.mokobike.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderService implements OrderRepository {

    private static final OrderMapper ORDER_MAPPER = new OrderMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_ORDER_BY_ID = "";
    private static final String SQL_SELECT_ORDERS_BY_USERID = "select * from orders where user_id = ?";
    private static final String SQL_SELECT_LATEST_ORDER = "";
    private static final String SQL_SELECT_ALL_ORDERS = "";
    private static final String SQL_SELECT_ALL_ORDERS_PAGINATION = "";
    private static final String SQL_SELECT_ALL_ORDERS_COUNT = "select count(*) from orders";
    private static final String SQL_INSERT_INTO_ORDER = "";
    private static final String SQL_UPDATE_ORDER = "";
    private static final String SQL_DELETE_ORDER = "";


    @Override
    public Order findByID(Long ID) {
        return  jdbcTemplate.queryForObject(SQL_SELECT_ORDER_BY_ID, ORDER_MAPPER, ID);
    }

    @Override
    public List<Order> findByUserID(Long userID) {
        return  jdbcTemplate.query(SQL_SELECT_ORDERS_BY_USERID, ORDER_MAPPER, userID);
    }

    @Override
    public List<Order> findAllOrders(int page, int size) {
        int offset = page * size - size;
        return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS_PAGINATION, ORDER_MAPPER, size, offset);
    }

    @Override
    public List<Order> findAllOrders() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS, ORDER_MAPPER);
    }

    @Override
    public int ordersCount() {
        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_ORDERS_COUNT, new Object[]{}, Integer.class);
    }

    @Override
    public Order save(Order order) {
        sqlUpdateAndSave(SQL_INSERT_INTO_ORDER, order);
        return  jdbcTemplate.queryForObject(SQL_SELECT_LATEST_ORDER, ORDER_MAPPER);
    }

    @Override
    public void update(Order order) {
        sqlUpdateAndSave(SQL_UPDATE_ORDER, order);
    }

    @Override
    public void delete(Long orderID) {
        jdbcTemplate.update(SQL_DELETE_ORDER, orderID);
    }

    private void sqlUpdateAndSave(String query, Order order){
        jdbcTemplate.update(query,
                order.getStatus(),
                order.getCreatedDate(),
                order.getDateFrom(),
                order.getDateTo(),
                order.getUserId(),
                order.getAdultBike(),
                order.getChildBike(),
                order.getHelmet(),
                order.getLock(),
                order.getPickup(),
                order.getPickupFrom(),
                order.getPickupTo(),
                order.getPickupDistance(),
                order.getPickupValue(),
                order.getInitialValue(),
                order.getFinalValue()
        );
    }
}
