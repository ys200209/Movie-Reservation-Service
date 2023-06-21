package com.moviereservation.study;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class StudyTest {

    @Test
    void test() {
        // given
        String encodedPassword = new BCryptPasswordEncoder().encode("password");

        // then
        System.out.println("encodedPassword = " + encodedPassword);
    }
}
