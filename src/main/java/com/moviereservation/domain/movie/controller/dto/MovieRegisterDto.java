package com.moviereservation.domain.movie.controller.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@RequiredArgsConstructor
@Getter
@ToString
public class MovieRegisterDto {
    private final String name;
    private final MultipartFile poster;
    private final String category;
    private final String story;
    private final String runningTime;
    private final String director;
    private final String actor;
    private final String ageLimit;
}
