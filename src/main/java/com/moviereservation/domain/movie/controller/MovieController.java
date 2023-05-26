package com.moviereservation.domain.movie.controller;

import com.moviereservation.domain.movie.service.MovieService;
import com.moviereservation.domain.movie.comment.dto.AddCommentDto;
import com.moviereservation.domain.movie.controller.dto.DetailDto;
import com.moviereservation.domain.movie.controller.dto.MovieRegisterDto;
import com.moviereservation.domain.movie.utils.MovieValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieValidator validator;

    @GetMapping("/{movieId}")
    public String detail(@PathVariable Long movieId, Model model){
            DetailDto responseDto = movieService.findByMovieId(movieId);
            model.addAttribute("detail", responseDto);
            model.addAttribute("requestDto", new HashMap<String, String>());
        return "movies/detail_page";
    }

    @PostMapping("/{movieId}")
    @ResponseBody
    public DetailDto writeComment(@PathVariable Long movieId, @RequestBody AddCommentDto requestDto){
        movieService.addComment(requestDto, movieId);
        DetailDto responseDto = movieService.findByMovieId(movieId);
        return responseDto;
    }

    @GetMapping("/addMovie")
    public String getAddMovieForm(Model model){
        MovieRegisterDto movieRegisterDto = new MovieRegisterDto(null, null, null, null, null, null, null, null);
        model.addAttribute("data", movieRegisterDto);
        return "movies/addMovie";
    }

    @PostMapping("/addMovie")
    public String SaveMovie(@ModelAttribute("data") MovieRegisterDto data, Errors errors)throws IOException {
        validator.validate(data, errors);
        if(errors.hasErrors()){
            return "movies/addMovie";
        }
        MultipartFile posterImage = data.getPoster();
        String saveName = posterImage.getOriginalFilename();
        String uploadDirectory = ResourceUtils.getFile("file:src/main/resources/static/images/").getAbsolutePath();
        File saveFile = new File(uploadDirectory, saveName);
        if(!posterImage.isEmpty() && posterImage != null){
            try{
                posterImage.transferTo(saveFile);
            }catch (Exception e){
                throw new RuntimeException("포스터 이미지 업로드가 실패했습니다.",  e);
            }
        }
        movieService.saveMovie(data);
        return "redirect:/";
    }
}
