package com.mokobike;

import com.mokobike.controller.OrderController;
import com.mokobike.implementation.OrderService;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class OrderTests extends TestCase{

    @MockBean
    private OrderService orderService;

    @Autowired
    public OrderController controller;

    @Test
    public void orderControllerSmokeTest() throws Exception{
        assertNotNull(controller);
    }

    @Test
    public void orderControllerAuthTest() throws Exception{
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/orders?page=1&size=10")
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

    @Test
    public void getOrderControllerTest() throws Exception{
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/orders?page=1&size=10")
                                .header("Authorization", "Bearer " + token)
                ).andDo(
                print()
        )
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                )
                .andExpect(
                        content().string(
                                containsString("total_orders")
                        )
                );
    }

}