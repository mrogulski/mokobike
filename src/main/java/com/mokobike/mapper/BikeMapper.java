package com.mokobike.mapper;

import com.mokobike.domain.Bike;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BikeMapper implements RowMapper<Bike> {
    @Override
    public Bike mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bike bike = new Bike(
                rs.getLong("id"),
                rs.getString("reg_number"),
                rs.getString("producer"),
                rs.getString("model"),
                rs.getString("bike_type"),
                rs.getString("bike_condition"),
                rs.getDouble("rental_price"),
                rs.getDouble("purchase_amount"),
                rs.getDate("date_of_purchase"),
                rs.getBoolean("in_use")
        );
        return bike;
    }
}
