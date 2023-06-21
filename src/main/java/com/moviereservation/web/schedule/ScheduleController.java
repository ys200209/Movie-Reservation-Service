package com.moviereservation.web.schedule;

import com.moviereservation.web.movie.dto.MoviePreviewDto;
import com.moviereservation.service.movie.MovieService;
import com.moviereservation.service.schedule.ScheduleService;
import com.moviereservation.web.schedule.dto.ScheduleDto;
import com.moviereservation.web.schedule.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
