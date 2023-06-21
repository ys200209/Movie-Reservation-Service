package com.moviereservation.domain.member;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Grade {
    SILVER("SILVER"),
    VIP("VIP"),
    ;

    private final String value;

    public static Grade of(String gradeText) {
        return Arrays.stream(values())
                .filter(grade -> grade.value.equals(gradeText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등급 값이 올바르지 않습니다."));
    }
}
