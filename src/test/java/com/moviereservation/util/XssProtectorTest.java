package com.moviereservation.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.moviereservation.utils.xssprotector.XssProtector;
import org.junit.jupiter.api.Test;


public class XssProtectorTest {

    @Test
    void nonXssInputTest() {
        String input = "abcde";

        String result = XssProtector.xssProtecting(input);

        assertThat(result).isEqualTo(input);
    }

    @Test
    void angleInInputTest() {
        String input = "<abcde>";
        String result = XssProtector.xssProtecting(input);

        assertThat(result).isEqualTo("&lt;abcde&gt;");
    }

    @Test
    void parenthesisInInputTest() {
        String input = "(abcde)";
        String result = XssProtector.xssProtecting(input);

        assertThat(result).isEqualTo("&#40;abcde&#41;");
    }

    @Test
    void xssInInputTest() {
        String input = "<script>alert('hello')</script>";
        String result = XssProtector.xssProtecting(input);

        assertThat(result).isEqualTo("&lt;script&gt;alert&#40;'hello'&#41;&lt;/script&gt;");
    }
}
