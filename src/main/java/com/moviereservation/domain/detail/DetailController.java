package com.moviereservation.domain.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
public class DetailController {

    private final DetailService detailService;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        DetailDto dto = detailService.findByMovie(id);
        model.addAttribute("detail", dto);
        return "movie/detail_page";
    }
}
