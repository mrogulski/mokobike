package com.mokobike;

import com.mokobike.controller.UsersController;


import com.mokobike.implementation.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTests {

    @Autowired
    private UsersController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoibWFyY2luIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTUzNzIyMzA3OSwiYXV0aG9yaXRpZXMiOlsiQURNSU5fVVNFUiJdLCJqdGkiOiJhMGQwNzVlMy01MTFhLTRhNWYtOTY2NS01MTBmNzMyYTkyZjMiLCJjbGllbnRfaWQiOiJtb2tvIn0.u12mMJ-YIfjwufHjqVNDZqe1oH4jeryils2WRmsQGqM";

    @Before
    public void setUp(){

    }

    @Test
    public void smokeTest() throws Exception{
        assertNotNull(controller);
    }

    @Test
    public void statusOKTest() throws Exception{
        this.mockMvc
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
    public void authorizationTest() throws Exception{
        this.mockMvc
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
