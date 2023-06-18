package com.moviereservation.domain.movie.controller;

import com.moviereservation.domain.movie.service.MovieService;
import com.moviereservation.domain.movie.dto.MoviePreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {
    private final MovieService movieService;
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String main(Model model, String name) {
        List<MoviePreviewDto> moviePreviewDtos = movieService.getAllMovies();
        model.addAttribute("movies", moviePreviewDtos);
        model.addAttribute("name", name);
        return "main";
    }
}
