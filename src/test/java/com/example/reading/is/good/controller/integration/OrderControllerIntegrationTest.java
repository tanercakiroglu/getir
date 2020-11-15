package com.example.reading.is.good.controller.integration;

import com.auth0.jwt.JWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void given_Order_Add_thenCorrectResponse() throws Exception {
        MediaType jsonUtf8 = new MediaType(MediaType.APPLICATION_JSON);
        String order = "{\"productId\": 1," +
                "\"quantity\" : 2 }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/order/")
                .content(order)
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+ getToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(jsonUtf8));
    }

    @Test
    public void given_UserId_thenNotFoundResponse() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/{userId}", 1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void given_UserId_withoutToken_thenUnauthorizedResponse() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/{userId}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    private String getToken(){

        return JWT.create()
                .withSubject("abc")
                .withExpiresAt(new Date(System.currentTimeMillis() +  100000))
                .sign(HMAC512("5A9D6FB223EAD9CCEEDF9F38BB187".getBytes()));
    }
}


