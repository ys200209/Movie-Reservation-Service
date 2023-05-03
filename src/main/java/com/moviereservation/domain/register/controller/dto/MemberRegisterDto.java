package com.moviereservation.domain.register.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@RequiredArgsConstructor
@ToString
public class MemberRegisterDto {
    private final String memberId;
    private final String memberPassword;
    private final String gender;
    private final String birth;
    private final String name;
    private final String phoneNumber;
}
