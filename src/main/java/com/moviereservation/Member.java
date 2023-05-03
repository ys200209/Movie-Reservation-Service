package com.moviereservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Member {
    private final String name;
    private final String password;

}
