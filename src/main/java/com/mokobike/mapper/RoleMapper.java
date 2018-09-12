package com.mokobike.mapper;

import com.mokobike.domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role(
                rs.getLong("id"),
                rs.getString("role_name"),
                rs.getString("description"));
        return role;
    }
}

