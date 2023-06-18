package com.moviereservation.domain.register.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.moviereservation.domain.member.register.RegisterService;
import com.moviereservation.domain.member.register.controller.RegisterController;
import com.moviereservation.domain.member.register.utils.validator.RegisterValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class RegisterControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private RegisterController controller;

    @Mock
    private RegisterService service;

    @Mock
    private RegisterValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetSave() throws Exception {
        // then
        mockMvc.perform(get("/member/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/register"));
    }

    @Test
    void testPostSave() throws Exception {
        // then
        mockMvc.perform(post("/member/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("memberId", "memberId")
                        .param("memberPassword", "memberPassword1@")
                        .param("gender", "남자")
                        .param("birth", "2000-01-01")
                        .param("name", "홍길동")
                        .param("phoneNumber", "010-1234-5678"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/login"))
                .andExpect(model().hasNoErrors());
    }
}
