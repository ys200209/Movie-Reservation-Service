package com.moviereservation.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
public class SearchDto {
    private final String movies_seq;
    private final String date;

    @JsonCreator
    public SearchDto(@JsonProperty("field1") String field1, @JsonProperty("field2") String field2) {
        this.movies_seq = field1;
        this.date = field2;
    }
}
