package com.moviereservation;

;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;




@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private MovieDao movieDao;
    private AuthenticationManager authenticationManager;


    @GetMapping("/")
    public String main(Model model, String name, HttpSession session) {
        List<Movie> movies = movieDao.getAllMovieNames();
        model.addAttribute("movies", movies);
        model.addAttribute("name", name);
        System.out.println("aaaaaaa");
        return "main";
    }
    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, Model model) {
        // 사용자 인증 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(name, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 로그인 후 main 페이지 반환
        model.addAttribute("name", name);
        System.out.println("bbbbb");
        return "main";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // 로그아웃 처리 로직

        session.invalidate();

        System.out.println("ccccccc");
        //메인페이지로 리다이렉트
        return "redirect:/";
    }

}
