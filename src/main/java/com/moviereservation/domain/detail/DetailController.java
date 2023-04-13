package com.moviereservation.domain.detail;

import com.moviereservation.domain.detail.comment.AddCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
public class DetailController {

    private final DetailService detailService;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        DetailDto responseDto = detailService.findByMovieId(id);
        model.addAttribute("detail", responseDto);
        return "movie/detail_page";
    }

    @PostMapping("detail/{id}")
    public String writeComment(@PathVariable Long id, @ModelAttribute AddCommentDto requestDto){
        detailService.addComment(requestDto, id);
        return "redirect:/movie/detail/"+id;
    }
}
