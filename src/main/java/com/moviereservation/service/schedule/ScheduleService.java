package com.moviereservation.service.schedule;

import com.moviereservation.domain.schedule.Schedule;
import com.moviereservation.domain.schedule.ScheduleRepository;
import com.moviereservation.web.schedule.dto.ScheduleDto;
import com.moviereservation.web.schedule.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<ScheduleDto> findByIdAndDate(SearchDto searchDto){
        long id = Long.parseLong(searchDto.getMovies_seq());
        String date = dateConversion(searchDto.getDate());
        List<Schedule> schedules = scheduleRepository.findByMovieIdAndDate(id, date);
        List<ScheduleDto> dtos = new ArrayList<ScheduleDto>();
        for(Schedule schedule : schedules){
            dtos.add(mapToDto(schedule));
        }
        return dtos;
    }

    private ScheduleDto mapToDto(Schedule schedule){
        return new ScheduleDto(schedule.getSchedules_seq(), schedule.getDate(), schedule.getStart_time(), schedule.getTheaters_seq());
    }

    private String dateConversion(String s_date){
        String[] arr = s_date.split("-");
        return arr[0]+ "/" + arr[1] + "/" + arr[2];
    }
}
