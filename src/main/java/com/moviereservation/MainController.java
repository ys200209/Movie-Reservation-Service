package com.moviereservation;

;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.stereotype.Controller
public class MainController {

    @Autowired
    private MovieDao movieDao;
    private AuthenticationManager authenticationManager;


    @GetMapping("/")
    public String main(Model model, String name, HttpSession session) {
        List<Movie> movies = movieDao.getAllMovieNames();
        model.addAttribute("movies", movies);
        model.addAttribute("name", name);
        // 세션에 사용자 정보가 있는 경우만 loggedIn 속성을 true로 설정합니다.
        /*if (session.getAttribute("user") != null) {
            model.addAttribute("loggedIn", true);
        }*/
        System.out.println("aaaaaaa");
        return "main";
    }
}

