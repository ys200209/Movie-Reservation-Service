package com.moviereservation.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class XssProtector {
    public static String xssProtecting(String input){
        return input.replaceAll("\\<", "&lt;").replaceAll("\\>", "&gt;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
    }
}
