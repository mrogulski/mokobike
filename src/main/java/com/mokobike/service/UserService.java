package com.mokobike.service;

import com.mokobike.domain.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> findAllUsers();


}
