package com.moviereservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Movie {
    @Id
    private Long seq;
    private String name;
    private String poster;
}
