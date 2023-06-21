package com.moviereservation.web.schedule.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
public class AddScheduleDto {
    private final long movies_seq;
    private final String date;
    private final String start_time;
    private final long theaters_seq;

    @JsonCreator
    public AddScheduleDto(
            @JsonProperty("movies_seq") long movies_seq,
            @JsonProperty("date") String date,
            @JsonProperty("start_time") String start_time,
            @JsonProperty("theaters_seq") long theaters_seq) {
        this.movies_seq = movies_seq;
        this.date = date;
        this.start_time = start_time;
        this.theaters_seq = theaters_seq;
    }
}
