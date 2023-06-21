package com.moviereservation.domain.member.register;

import com.moviereservation.domain.member.Member;

public interface RegisterRepository {
    int save(Member member);
}
