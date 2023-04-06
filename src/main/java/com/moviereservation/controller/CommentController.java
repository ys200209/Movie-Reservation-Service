package com.moviereservation.controller;

import com.moviereservation.service.CommentService;
import com.moviereservation.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    MovieService movieService;


    @GetMapping("/detail/{seq}")
    public String detailPage(@PathVariable Long seq, Model model){
        model.addAttribute("comments", commentService.getCommentByMoviesSeq(seq));
        model.addAttribute("movieDescription", movieService.findById(seq));
        return "detail_page";
    }


}
