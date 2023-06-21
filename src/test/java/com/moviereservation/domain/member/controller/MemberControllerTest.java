package com.moviereservation.domain.member.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.moviereservation.service.member.MemberService;
import com.moviereservation.utils.validator.RegisterValidator;
import com.moviereservation.web.member.dto.PasswordChangeDto;
import com.moviereservation.web.member.MemberController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MemberController controller;

    @MockBean
    MemberService service;

    @MockBean
    RegisterValidator validator;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "id")
    void testMe_GET_Success() throws Exception {
        // when
        mockMvc.perform(get("/member/me"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void testMe_GET_Unauthorized() throws Exception {
        // when
        mockMvc.perform(get("/member/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "id")
    void testChangePassword_GET_Success() throws Exception {
        // when
        mockMvc.perform(get("/member/password"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/change_password"));
    }

    @Test
    @WithMockUser(username = "id", roles = "USER")
    void testChangePassword_POST_Success() throws Exception {
        // when
        mockMvc.perform(post("/member/password")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .param("memberPassword", "password1@")
                        .param("newPassword", "newPassword1@")
                        .param("confirmPassword", "newPassword1@"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    void testChangePassword_POST_Unauthorized() throws Exception {
        // when
        mockMvc.perform(post("/member/password")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .param("memberPassword", "password1@")
                        .param("newPassword", "newPassword1@")
                        .param("confirmPassword", "newPassword1@"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "id", roles = "USER")
    void testChangePassword_POST_FieldError() throws Exception {
        // given
        PasswordChangeDto passwordChangeDto = new PasswordChangeDto("", "", "");

        // when
        mockMvc.perform(post("/member/password")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .flashAttr("member", passwordChangeDto))
                .andExpect(status().isOk())
                .andExpect(view().name("member/change_password"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("member", "memberPassword"))
                .andExpect(model().attributeHasFieldErrors("member", "newPassword"))
                .andExpect(model().attributeHasFieldErrors("member", "confirmPassword"));
    }
}
