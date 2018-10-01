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

    private static final String SQL_SELECT_ORDER_BY_ID = "select * from orders where id = ?";
    private static final String SQL_SELECT_ORDERS_BY_USERID = "select * from orders where user_id = ?";
    private static final String SQL_SELECT_LATEST_ORDER = "select * from orders order by id desc limit 1";
    private static final String SQL_SELECT_ALL_ORDERS = "select * from orders";
    private static final String SQL_SELECT_ALL_ORDERS_PAGINATION = "select * from orders order by id limit ? offset ?";
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
    private static final String SQL_DELETE_ORDER = "update orders set status = inactive where id = ?";


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

    @Override
    public Order update(Order order) {
        jdbcTemplate.update(SQL_UPDATE_ORDER,
                order.getStatus(),
                order.getDateFrom(),
                order.getDateTo(),
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

        return  jdbcTemplate.queryForObject(SQL_SELECT_ORDER_BY_ID, ORDER_MAPPER, order.getId());
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
