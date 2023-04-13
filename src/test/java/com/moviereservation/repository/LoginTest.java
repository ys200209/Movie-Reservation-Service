package com.moviereservation.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviereservation.config.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

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

    /*@Test
    public void login() throws Exception {

        mockMvc.perform(formLogin("/member/login").user("reqw").password("reqw")).andExpect(authenticated());
    }*/

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

    //익명유저 = 로그인X 유저의 접근 테스트
    @Test
    @WithAnonymousUser
    public void testCheckEndpointAccessDeniedForAnonymousUser() throws Exception {
        mockMvc.perform(get("/member/check"))
                .andExpect(status().is3xxRedirection());
    }

    //role이 ADMIN인 유저의 접근테스트
    @Test
    @WithMockUser(roles="ADMIN")
    public void testCheckEndpointAccessDeniedForNonUser() throws Exception {
        mockMvc.perform(get("/member/check"))
                .andExpect(status().isOk());
    }

}