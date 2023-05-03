package com.moviereservation.detail;

import com.google.gson.Gson;
import com.moviereservation.domain.detail.DetailService;
import com.moviereservation.domain.detail.comment.AddCommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddCommentTest {

    @Autowired
    MockMvc mockMvc;

    private JdbcTemplate jdbcTemplate;

    private Gson gson;

    @BeforeEach
    void setUp() {
        gson = new Gson();
    }

    @Test
    @WithMockUser(username = "reqw",password = "reqw")
    public void GenericAddCommentTest()throws Exception{
        String requestJson = "{\"memberId\":\"reqw\", \"content\": \"12345\"}";
        mockMvc.perform(post("/movies/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "reqw",password = "reqw")
    public void XSSAddCommentTest()throws Exception{
        String requestJson = "{\"memberId\":\"reqw\", \"content\": \"<script>alert('hello')</script>\"}";
        mockMvc.perform(post("/movies/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
