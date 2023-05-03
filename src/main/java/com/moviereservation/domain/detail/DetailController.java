package com.moviereservation.domain.detail;

import com.moviereservation.domain.detail.comment.AddCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class DetailController {

    private final DetailService detailService;

    @GetMapping("/movies/{movieId}")
    public String detail(@PathVariable Long movieId, Model model){
            DetailDto responseDto = detailService.findByMovieId(movieId);
            model.addAttribute("detail", responseDto);
            model.addAttribute("requestDto", new HashMap<String, String>());
        return "movies/detail_page";
    }

    @PostMapping("/movies/{movieId}")
    @ResponseBody
    public DetailDto writeComment(@PathVariable Long movieId, @RequestBody AddCommentDto requestDto){
        detailService.addComment(requestDto, movieId);
        DetailDto responseDto = detailService.findByMovieId(movieId);
        return responseDto;
    }
}
