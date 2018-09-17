package com.mokobike;

import com.mokobike.controller.UserController;
import com.mokobike.implementation.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTests extends TestCase {

    @MockBean
    private UserService userService;

    @Autowired
    public UserController controller;

    @Test
    public void userServiceSmokeTest() throws Exception{
        assertNotNull(controller);
    }

    @Test
    public void getUserControllerTest() throws Exception{
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/users?page=1&size=10")
                        .header("Authorization", "Bearer " + token)
                ).andDo(
                    print()
                )
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                )
                .andExpect(
                        content().string(
                                containsString("total_users")
                        )
                );
    }

    @Test
    public void userControllerAuthTest() throws Exception{
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/users?page=1&size=10")
                )
                .andExpect(
                        MockMvcResultMatchers.status().isUnauthorized()
                )
                .andExpect(
                        content().string(
                                containsString("unauthorized")
                        )
                );
    }

}
