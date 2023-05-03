package com.moviereservation.domain.register;

import com.moviereservation.domain.member.Member;

public interface RegisterRepository {
    int save(Member member);

    Member findById(String id);
}
