package com.moviereservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Category {

    private final Long seq;
    private final String name;


}
