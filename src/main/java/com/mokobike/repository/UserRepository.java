package com.mokobike.repository;

import com.mokobike.domain.User;
import java.util.List;

public interface UserRepository{

    User findByUsername(String username);

    List<User> findAllUsers(int page, int size);
}
