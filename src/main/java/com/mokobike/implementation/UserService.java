package com.mokobike.implementation;

import com.mokobike.domain.User;
import com.mokobike.mapper.PoorUserMapper;
import com.mokobike.mapper.UserMapper;
import com.mokobike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
            "\tapp_user.address,\n" +
            "\tapp_user.phone,\n" +
            "\tapp_role.description,\n" +
            "\tapp_role.role_name\n" +
            "from app_user, app_role \n" +
            "where app_user.role_id= app_role.id \n" +
            "and app_user.username =  ?";

    private static final String SQL_SELECT_ALL_USERS_PAGINATION = "select \n" +
            "\tapp_user.id,\n" +
            "\tapp_user.first_name,\n" +
            "\tapp_user.last_name,\n" +
            "\tapp_user.password,\n" +
            "\tapp_user.username,\n" +
            "\tapp_user.role_id,\n" +
            "\tapp_user.email,\n" +
            "\tapp_user.address,\n" +
            "\tapp_user.phone,\n" +
            "\tapp_role.description,\n" +
            "\tapp_role.role_name\n" +
            "from app_user, app_role \n" +
            "where app_user.role_id= app_role.id  \n" +
            "order by id limit ? offset ?";

    private static final String SQL_SELECT_ALL_USERS = "select * from app_user";

    private static final String SQL_SELECT_ALL_USERS_COUNT = "select count(*) from app_user";

    private static final String SQL_SELECT_USER_BY_ID = "select * from app_user where app_user.id =  ?";

    private static final String SQL_SAVE_USER = "insert into app_user (first_name, last_name, password, username, role_id, email, address, phone) values (?, ?, ?, ?, ?, ?, ?, ?) returning id";

    private static final String SQL_SELECT_FILTERED_USERS = "SELECT * FROM app_user WHERE concat(first_name, ' ',last_name) ILIKE ?";

    private static final String SQL_SELECT_USERS_FULLNAME_BY_ID = "select concat(first_name, ' ',last_name) from app_user where app_user.id =  ?";

    private static final String SQL_UPDATE_USER = "update app_user set first_name = ?, last_name = ?, username = ?, email = ?, address = ?, phone = ? where id = ?";


    public static final UserMapper USER_MAPPER = new UserMapper();
    public static final PoorUserMapper POOR_USER_MAPPER = new PoorUserMapper();


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME, USER_MAPPER, username);
    }

    @Override
    public List<User> findAllUsers(int page, int size) {
        int offset = page * size - size;
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS_PAGINATION, USER_MAPPER, size, offset);
    }

    @Override
    public List<User> findAllUsers(String q) {

            return jdbcTemplate.query(SQL_SELECT_FILTERED_USERS, POOR_USER_MAPPER, new String[]{"%" +q +"%"});
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, POOR_USER_MAPPER);
    }

    @Override
    public int usersCount(){
        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_USERS_COUNT, new Object[]{}, Integer.class);
    }

    @Override
    public User findByID(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, POOR_USER_MAPPER, id);
    }

    @Override
    public Long save(User user) {
        return new Long(jdbcTemplate.queryForObject(SQL_SAVE_USER, new Object[]{user.getFirstName(), user.getLastName(), null, user.getFirstName() +  "." + user.getLastName(), 1, user.getEmail(), user.getAddress(), user.getPhone()}, Integer.class));
    }

    @Override
    public String findFullName(Long user_id) {
       return new String(jdbcTemplate.queryForObject(SQL_SELECT_USERS_FULLNAME_BY_ID, new Object[] { user_id }, String.class));
    }

    @Override
    public User update(User user) {
        User updatedUser;

        try{
            //first to check if user is available
            jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, POOR_USER_MAPPER, user.getId());

            jdbcTemplate.update(SQL_UPDATE_USER,
                user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getPhone(),
                    user.getId()
            );

            updatedUser = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, POOR_USER_MAPPER, user.getId());
        }catch (EmptyResultDataAccessException e){
            updatedUser = null;
        }
        return updatedUser;
    }
}
