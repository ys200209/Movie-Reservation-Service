package com.moviereservation.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginTest {

    @Autowired
    MockMvc mockMvc;

    //WithMockUser의 접근권한 테스트
    @Test
    //유저정보 설정
    @WithMockUser(username="reqww", password="reqw")
    public void testCheckEndpointAccessAllowed() throws Exception {
        mockMvc.perform(get("/member/check"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogin() throws Exception{
        String userId= "reqw";
        String password = "reqw";

        mockMvc.perform(formLogin("/member/login").user(userId).password(password)).andDo(print())
                .andExpect(authenticated());
    }

    //role이 ADMIN인 유저의 접근테스트
    @Test
    @WithMockUser(roles="ADMIN")
    public void testCheckEndpointAccessDeniedForNonUser() throws Exception {
        mockMvc.perform(get("/member/check"))
                .andExpect(status().isOk());
    }
}
