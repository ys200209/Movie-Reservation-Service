package com.moviereservation.domain.member;

import static com.moviereservation.domain.member.Gender.FEMALE;
import static com.moviereservation.domain.member.Gender.MALE;
import static com.moviereservation.domain.member.Gender.of;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GenderTest {
    @Test
    void testGenderMale() {
        // then
        assertThat(MALE).isEqualTo(of("남자"));
    }

    @Test
    void testGenderFemale() {
        // then
        assertThat(FEMALE).isEqualTo(of("여자"));
    }

    @Test
    void testGenderException() {
        // then
        assertThat(of("념")).isNull();
    }
}
