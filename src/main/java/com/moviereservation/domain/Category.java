package com.moviereservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Category {
    @Id
    private Long seq;
    private String name;


}
