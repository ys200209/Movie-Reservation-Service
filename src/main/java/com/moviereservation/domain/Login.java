package com.moviereservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
    private String member_id;
    private String member_password;
}
