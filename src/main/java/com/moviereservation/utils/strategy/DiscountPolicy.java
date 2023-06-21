package com.moviereservation.utils.strategy;

import com.moviereservation.domain.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
