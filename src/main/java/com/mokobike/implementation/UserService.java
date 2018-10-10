package com.mokobike.implementation;

import com.mokobike.domain.User;
import com.mokobike.mapper.UserMapper;
import com.mokobike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserService implements UserRepository{

    private static final String SQL_SELECT_USER_BY_USERNAME = "select \n" +
            "\tapp_user.id,\n" +
            "\tapp_user.first_name,\n" +
            "\tapp_user.last_name,\n" +
            "\tapp_user.password,\n" +
            "\tapp_user.username,\n" +
            "\tapp_user.role_id,\n" +
            "\tapp_user.email,\n" +
            "\tapp_role.description,\n" +
            "\tapp_role.role_name\n" +
            "from app_user, app_role \n" +
            "where app_user.role_id= app_role.id \n" +
            "and app_user.username =  ?";

    private static final String SQL_SELECT_ALL_USERS = "select \n" +
            "\tapp_user.id,\n" +
            "\tapp_user.first_name,\n" +
            "\tapp_user.last_name,\n" +
            "\tapp_user.password,\n" +
            "\tapp_user.username,\n" +
            "\tapp_user.role_id,\n" +
            "\tapp_user.email,\n" +
            "\tapp_role.description,\n" +
            "\tapp_role.role_name\n" +
            "from app_user, app_role \n" +
            "where app_user.role_id= app_role.id  \n" +
            "order by id limit ? offset ?";

    private static final String SQL_SELECT_ALL_USERS_COUNT = "select count(*) from app_user";

    private static final String SQL_SELECT_USER_BY_ID = "select \n" +
            "\tapp_user.id,\n" +
            "\tapp_user.first_name,\n" +
            "\tapp_user.last_name,\n" +
            "\tapp_user.password,\n" +
            "\tapp_user.username,\n" +
            "\tapp_user.role_id,\n" +
            "\tapp_user.email,\n" +
            "\tapp_role.description,\n" +
            "\tapp_role.role_name\n" +
            "from app_user, app_role \n" +
            "where app_user.role_id= app_role.id \n" +
            "and app_user.id =  ?";

    public static final UserMapper USER_MAPPER = new UserMapper();


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME, USER_MAPPER, username);
    }

    @Override
    public List<User> findAllUsers(int page, int size) {
        int offset = page * size - size;
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, USER_MAPPER, size, offset);
    }

    @Override
    public int usersCount(){
        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_USERS_COUNT, new Object[]{}, Integer.class);
    }

    @Override
    public User findByID(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, USER_MAPPER, id);
    }
}
