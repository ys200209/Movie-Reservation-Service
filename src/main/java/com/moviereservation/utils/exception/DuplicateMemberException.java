package com.moviereservation.utils.exception;

import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DuplicateMemberException extends RuntimeException {

    private final MemberRegisterDto member;
}
