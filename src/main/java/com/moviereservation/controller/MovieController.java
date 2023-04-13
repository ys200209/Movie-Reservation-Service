package com.moviereservation.controller;

import com.moviereservation.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("/movie/{id}")
    public String test(Model model, @PathVariable Long id){
        model.addAttribute("movieDescription", movieService.findById(id));
        return "detail_page";
    }
}
