package com.mokobike.controller;

import com.mokobike.domain.User;
import com.mokobike.exceptions.NotFoundException;
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

    @GetMapping(value = "/{user_id}")
    @ResponseBody
    public User getUserByID(@PathVariable(name="user_id", required = true) Long user_id){
        return userRepository.findByID(user_id);
    }

    @GetMapping(value = "/{user_id}/fullname")
    @ResponseBody
    public String getFullName(@PathVariable(name="user_id", required = true) Long user_id){
        return userRepository.findFullName(user_id);
    }

    @PatchMapping(value = "/{user_id}")
    @ResponseBody
    public User updateUser(@PathVariable("user_id") long userID, @RequestBody User user) throws Exception{
        user.setId(userID);
        User updatedUser = userRepository.update(user);
        if(updatedUser == null){
            throw new NotFoundException(userID);
        }

        return updatedUser;
    }

}

