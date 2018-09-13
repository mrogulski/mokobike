package com.mokobike.controller;

import com.mokobike.domain.User;
import com.mokobike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;




import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping( value = "/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, params = {"page, size"})
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @ResponseBody
    public List<User> getUsers(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            UriComponentsBuilder uriBuilder,
            HttpServletResponse response
    ){
        return userRepository.findAllUsers(page, size);
    }

}

