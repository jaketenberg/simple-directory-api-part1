package com.ping.simpledirectoryapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private RateLimiter rateLimiter;

    @Test
    void test201IsReturnedWhenUserIsCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/environments/env-1/users")
                        .content("{\"username\":\"jdoe\"}")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
