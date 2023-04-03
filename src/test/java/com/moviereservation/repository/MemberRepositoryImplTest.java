package com.moviereservation.repository;

import com.moviereservation.domain.Login;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class MemberRepositoryImplTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void login(){
        Login login = new Login("reqw", passwordEncoder.encode("reqw"));
        mockMvc.perform(post("/member/login").content())
    }

}