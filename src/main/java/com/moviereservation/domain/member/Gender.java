package com.moviereservation.domain.member;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("남자"),
    FEMALE("여자"),
    ;

    private final String gender;

    public static Gender of(String genderParam) {
        return Arrays.stream(values())
                .filter(genderType -> genderType.gender.equals(genderParam))
                .findFirst()
                .orElse(null);
    }
}
