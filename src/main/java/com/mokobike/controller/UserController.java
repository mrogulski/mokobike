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
    public List<User> getUsers(
            @RequestParam(name="page", required = false) Integer page,
            @RequestParam(name="size", required = false) Integer size,
            @RequestParam(name="q", required = false) String q
    ){
        List<User> users;

        if(page != null & size != null){
            users = userRepository.findAllUsers(page, size);
        }else if(q != null){
            users = userRepository.findAllUsers(q);
        }
        else{
            users = userRepository.findAllUsers();
        }

        return users;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public Long save(@RequestBody User user){
        return userRepository.save(user);
    }

}

