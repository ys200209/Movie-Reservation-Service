package com.moviereservation.repository;

import com.moviereservation.domain.Login;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberRepositoryImplTest {


    @Autowired
    MockMvc mockMvc;

    /*@Test
    public void login() throws Exception {

        mockMvc.perform(formLogin("/member/login").user("reqw").password("reqw")).andExpect(authenticated());
    }*/

    //WithMockUser의 접근권한 테스트
    @Test
    //유저정보 설정
    @WithMockUser(username="reqw", password="reqw", roles="USER")
    public void testCheckEndpointAccessAllowed() throws Exception {
        mockMvc.perform(get("/member/check"))
                .andExpect(status().isOk());
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