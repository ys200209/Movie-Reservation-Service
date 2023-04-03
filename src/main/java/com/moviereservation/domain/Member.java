package com.moviereservation.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Member {
    @Id
    private Long seq;
    private String member_id;
    private String member_password;
    private String gender;
    private LocalDate birth;
    private String name;
    private String phone_number;
    private LocalDateTime create_at;
    private LocalDateTime modify_at;
    private String role_name;
    private boolean enable;

    public Member(String id, String password, String gender, LocalDate birth, String name, String phone_number){
        this.member_id = id;
        this.member_password = password;
        this.gender = gender;
        this.birth = birth;
        this.name = name;
        this.phone_number = phone_number;
    }
}
