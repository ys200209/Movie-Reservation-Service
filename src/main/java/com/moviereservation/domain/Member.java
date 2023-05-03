package com.moviereservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Member {
    private final Long seq;
    private final String member_id;
    private final String member_password;
    private final String gender;
    private final LocalDate birth;
    private final String name;
    private final String phone_number;
    private final LocalDateTime create_at;
    private final LocalDateTime modify_at;
    private final String role_name;
    private final boolean enable;

    public Long getSeq() {
        return seq;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getMember_password() {
        return member_password;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public LocalDateTime getModify_at() {
        return modify_at;
    }

    public String getRole_name() {
        return role_name;
    }

    public boolean isEnable() {
        return enable;
    }
}
