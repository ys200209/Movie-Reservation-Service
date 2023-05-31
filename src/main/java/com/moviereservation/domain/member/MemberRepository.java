package com.moviereservation.domain.member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(String id);

    int changePassword(Member member, String newPassword);
}
