package com.moviereservation.study.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudyController {

    @GetMapping("/test")
    public String test(Model model) {
        Car car = new Car("model이에요");
        model.addAttribute("car", car);
        return "test";
    }

    @Getter
    @RequiredArgsConstructor
    static class Car {
        private final String model;
    }
}
