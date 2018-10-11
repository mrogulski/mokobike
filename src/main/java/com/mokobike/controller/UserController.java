package com.mokobike.controller;

import com.mokobike.domain.User;
import com.mokobike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @ResponseBody
    public List<Object> getUsers(
            @RequestParam(name="page") int page,
            @RequestParam(name="size") int size
    ){
        List<Object> response = new ArrayList<>();
        Map<String, Integer> count = new HashMap<>();
        List<User> users;

        users = userRepository.findAllUsers(page, size);
        count.put("total_users", userRepository.usersCount());

        response.add(count);
        response.add(users);

        return response;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public Long save(@RequestBody User user){
        return userRepository.save(user);
    }

}

