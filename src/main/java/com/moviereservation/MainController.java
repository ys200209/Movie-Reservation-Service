package com.moviereservation;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class MainController {

    @Autowired
    private MovieRepository movieRepository;
    private AuthenticationManager authenticationManager;


    @GetMapping("/")
    public String main(Model model, String name) {
        List<MoviePreviewDto> moviePreviewDtos = movieRepository.getAllMovieNames();
        model.addAttribute("movies", moviePreviewDtos);
        model.addAttribute("name", name);
        return "main";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member, Model model) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getName(), member.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("name", member.getName());
        return "main";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}

