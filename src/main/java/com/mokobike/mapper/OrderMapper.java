package com.mokobike.mapper;

import com.mokobike.domain.Order;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order(
                rs.getLong("id"),
                rs.getString("status"),
                rs.getDate("createdDate"),
                rs.getDate("date_from"),
                rs.getDate("date_to"),
                rs.getLong("user_id"),
                rs.getInt("adult_bike"),
                rs.getInt("child_bike"),
                rs.getInt("helmet"),
                rs.getInt("lock"),
                rs.getBoolean("pickup"),
                rs.getString("pickup_from"),
                rs.getString("pickup_to"),
                rs.getLong("pickup_distance"),
                rs.getLong("pickup_value"),
                rs.getLong("initial_value"),
                rs.getLong("final_value")
        );
        return order;
    }
}
