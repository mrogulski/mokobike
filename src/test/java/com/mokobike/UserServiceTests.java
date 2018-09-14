package com.mokobike;

import com.mokobike.controller.UsersController;
import com.mokobike.domain.Role;
import com.mokobike.domain.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UsersController usersController;


    @Test
    public void findByUsernameTest() throws Exception{
        User user = new User(new Long(1), "elo", "elo", "elo", "elo", new Role(new Long(1),"ROLE_ADMIN", "desc"));
        List<Object> users = new ArrayList<>();
        users.add(user);
        given(usersController.getUsers(1, 5)).willReturn(users);

        mockMvc.perform(get("/users?page=1&size=5"))
                            .andExpect(status().isOk());

    }

}
