package com.mokobike.mapper;

import com.mokobike.domain.Role;
import com.mokobike.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                new Role(
                        rs.getLong("role_id"),
                        rs.getString("role_name"),
                        rs.getString("description")
                ),
                rs.getString("email")
        );
        return user;
    }
}
