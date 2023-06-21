package com.moviereservation.utils.strategy;

import com.moviereservation.domain.member.Grade;
import com.moviereservation.domain.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 20;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price / 100 * discountPercent;
        }
        return 0;
    }
}
