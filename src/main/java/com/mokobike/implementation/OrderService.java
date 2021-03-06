package com.mokobike.implementation;

import com.mokobike.domain.Order;
import com.mokobike.mapper.OrderMapper;
import com.mokobike.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderService implements OrderRepository {

    private static final OrderMapper ORDER_MAPPER = new OrderMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT \n" +
            "orders.id, \n" +
            "orders.status, \n" +
            "orders.created_date, \n" +
            "orders.date_from, \n" +
            "orders.date_to, \n" +
            "orders.user_id, \n" +
            "orders.adult_bike, \n" +
            "orders.child_bike, \n" +
            "orders.helmet, \n" +
            "orders.lock, \n" +
            "orders.pickup, \n" +
            "orders.pickup_from, \n" +
            "orders.pickup_to, \n" +
            "orders.pickup_distance, \n" +
            "orders.pickup_value, \n" +
            "orders.initial_value, \n" +
            "orders.final_value, \n" +
            "app_user.id as \"user_id\",\n" +
            "app_user.username,\n" +
            "app_user.first_name,\n" +
            "app_user.last_name,\n" +
            "app_user.email\n" +
            "FROM orders inner join app_user on orders.user_id = app_user.id where orders.id = ?";
    private static final String SQL_SELECT_ORDERS_BY_USERID = "SELECT \n" +
            "orders.id, \n" +
            "orders.status, \n" +
            "orders.created_date, \n" +
            "orders.date_from, \n" +
            "orders.date_to, \n" +
            "orders.user_id, \n" +
            "orders.adult_bike, \n" +
            "orders.child_bike, \n" +
            "orders.helmet, \n" +
            "orders.lock, \n" +
            "orders.pickup, \n" +
            "orders.pickup_from, \n" +
            "orders.pickup_to, \n" +
            "orders.pickup_distance, \n" +
            "orders.pickup_value, \n" +
            "orders.initial_value, \n" +
            "orders.final_value, \n" +
            "app_user.id as \"user_id\",\n" +
            "app_user.username,\n" +
            "app_user.first_name,\n" +
            "app_user.last_name,\n" +
            "app_user.email\n" +
            "FROM orders inner join app_user on orders.user_id = app_user.id where user_id = ?";
    private static final String SQL_SELECT_LATEST_ORDER = "select * from orders order by id desc limit 1";
    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_SELECT_ALL_ORDERS_PAGINATION = "SELECT \n" +
            "orders.id, \n" +
            "orders.status, \n" +
            "orders.created_date, \n" +
            "orders.date_from, \n" +
            "orders.date_to, \n" +
            "orders.user_id, \n" +
            "orders.adult_bike, \n" +
            "orders.child_bike, \n" +
            "orders.helmet, \n" +
            "orders.lock, \n" +
            "orders.pickup, \n" +
            "orders.pickup_from, \n" +
            "orders.pickup_to, \n" +
            "orders.pickup_distance, \n" +
            "orders.pickup_value, \n" +
            "orders.initial_value, \n" +
            "orders.final_value, \n" +
            "app_user.id as \"user_id\",\n" +
            "app_user.username,\n" +
            "app_user.first_name,\n" +
            "app_user.last_name,\n" +
            "app_user.email\n" +
            "FROM orders inner join app_user on orders.user_id = app_user.id order by id limit ? offset ?";
    private static final String SQL_SELECT_ALL_ORDERS_COUNT = "select count(*) from orders";
    private static final String SQL_INSERT_INTO_ORDER =
            "insert into orders(" +
                    "status," +
                    "created_date," +
                    "date_from," +
                    "date_to," +
                    "user_id ," +
                    "adult_bike," +
                    "child_bike," +
                    "helmet," +
                    "lock," +
                    "pickup," +
                    "pickup_from," +
                    "pickup_to," +
                    "pickup_distance," +
                    "pickup_value," +
                    "initial_value," +
                    "final_value" +
            ")values" +
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ORDER =
            "update orders set " +
                    "status = ?," +
                    "date_from = ?," +
                    "date_to = ?," +
                    "user_id = ?," +
                    "adult_bike = ?," +
                    "child_bike = ?," +
                    "helmet = ?," +
                    "lock = ?," +
                    "pickup = ?," +
                    "pickup_from = ?," +
                    "pickup_to = ?," +
                    "pickup_distance = ?," +
                    "pickup_value = ?," +
                    "final_value = ?" +
            "where id = ?";
    private static final String SQL_DELETE_ORDER = "update orders set status = 'cancelled' where id = ?";


    @Override
    public Order findByID(Long ID) {
        Order order;
        try{
            order = jdbcTemplate.queryForObject(SQL_SELECT_ORDER_BY_ID, ORDER_MAPPER, ID);
        }catch (EmptyResultDataAccessException e){
            order = null;
        }
        return order;
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
    public List<Order> findOrdersByDate(String start, String end) {
        return jdbcTemplate.query( "select * from orders where date_from >= '" + start + "' and date_to <= '" + end + "'", ORDER_MAPPER);
    }

    @Override
    public int ordersCount() {
        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_ORDERS_COUNT, new Object[]{}, Integer.class);
    }

    @Override
    public void save(Order order) {
        jdbcTemplate.update(SQL_INSERT_INTO_ORDER,
                order.getStatus(),
                order.getCreatedDate(),
                order.getDateFrom(),
                order.getDateTo(),
                order.getUser(),
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

    @Override
    public Order update(Order order) {

        Order updatedOrder;

        try{
            //first to check if order is available
            jdbcTemplate.queryForObject(SQL_SELECT_ORDER_BY_ID, ORDER_MAPPER, order.getId());

            jdbcTemplate.update(SQL_UPDATE_ORDER,
                    order.getStatus(),
                    order.getDateFrom(),
                    order.getDateTo(),
                    order.getUser(),
                    order.getAdultBike(),
                    order.getChildBike(),
                    order.getHelmet(),
                    order.getLock(),
                    order.getPickup(),
                    order.getPickupFrom(),
                    order.getPickupTo(),
                    order.getPickupDistance(),
                    order.getPickupValue(),
                    order.getFinalValue(),
                    order.getId()
            );

            updatedOrder = jdbcTemplate.queryForObject(SQL_SELECT_ORDER_BY_ID, ORDER_MAPPER, order.getId());
        }catch (EmptyResultDataAccessException e){
            updatedOrder = null;
        }
        return updatedOrder;
    }

    @Override
    public void delete(Long orderID) {
        jdbcTemplate.update(SQL_DELETE_ORDER, orderID);
    }

    @Override
    public Order findLatestOrder(){

        return jdbcTemplate.queryForObject(SQL_SELECT_LATEST_ORDER, ORDER_MAPPER);
    }

}
