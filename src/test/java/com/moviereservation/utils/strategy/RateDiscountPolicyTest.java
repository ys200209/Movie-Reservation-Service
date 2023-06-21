package com.moviereservation.utils.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import com.moviereservation.domain.member.Grade;
import com.moviereservation.domain.member.Member;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @ParameterizedTest
    @MethodSource("getRateDiscountParameter")
    void testDiscount(Grade grade, int expected) {
        // given
        int price = 10_000;
        Member member = new Member(1L, "memberId", null, null, null, null, null, grade, null, null, null, false);

        // when
        int actual = discountPolicy.discount(member, price);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> getRateDiscountParameter() {
        return Stream.of(
                Arguments.of(Grade.VIP, 2_000),
                Arguments.of(Grade.SILVER, 0)
        );
    }
}
