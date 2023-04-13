package com.moviereservation.controller;

import com.moviereservation.domain.CommentResponseDto;
import com.moviereservation.service.CommentService;
import com.moviereservation.service.MovieService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/main")
public class CommentController {

    @Autowired
    CommentService commentService;

//    @Autowired
//    MovieService movieService;

    @GetMapping("/detail/{seq}")
    public String detailPage(@Valid CommentResponseDto dto, Errors errors,Model model){
        model.addAttribute("seq", commentService.getCommentByMoviesSeq(dto.getSeq()));
//        model.addAttribute("movieDescription", movieService.findById(seq));
        return "detail_page";
    }



}
