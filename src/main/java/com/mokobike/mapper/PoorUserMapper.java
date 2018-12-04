package com.mokobike.mapper;

import com.mokobike.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PoorUserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getInt("phone")
        );
        return user;
    }
}