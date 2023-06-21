package com.moviereservation.domain.schedule;

import com.moviereservation.domain.movie.domain.Movie;
import com.moviereservation.domain.movie.dto.MoviePreviewDto;
import com.moviereservation.domain.movie.service.MovieService;
import com.moviereservation.domain.schedule.dto.ScheduleDto;
import com.moviereservation.domain.schedule.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final MovieService movieService;
    private final ScheduleService scheduleService;

    @GetMapping
    public String getView(Model model){
        List<MoviePreviewDto> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "schedule/scheduleView";
    }

    @PostMapping("/search")
    @ResponseBody
    public List<ScheduleDto> getSchedule(@RequestBody SearchDto searchDto){
        List<ScheduleDto> dtos = scheduleService.findByIdAndDate(searchDto);
        return dtos;
    }
}
