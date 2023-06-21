package com.moviereservation.utils.exception;

import com.moviereservation.web.member.dto.MemberRegisterDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DuplicateMemberException extends RuntimeException {
    private final MemberRegisterDto member;
}
