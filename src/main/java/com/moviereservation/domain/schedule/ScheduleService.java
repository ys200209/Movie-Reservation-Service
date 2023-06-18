package com.moviereservation.domain.schedule;

import com.moviereservation.domain.schedule.domain.Schedule;
import com.moviereservation.domain.schedule.dto.ScheduleDto;
import com.moviereservation.domain.schedule.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    List<ScheduleDto> findByIdAndDate(SearchDto searchDto){
        long id = Long.parseLong(searchDto.getMovies_seq());
        System.out.print(searchDto.getDate());
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
