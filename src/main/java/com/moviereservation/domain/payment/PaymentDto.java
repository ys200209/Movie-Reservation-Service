package com.moviereservation.domain.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Builder
@Getter
@RequiredArgsConstructor
@ToString
public class PaymentDto {
    private final String cardNumber;
    private final int countChild;
    private final int countTeenager;
    private final int countAdult;
    private final int payAmount;
    private final Timestamp paymentAt;

    private final String memberId;
    private final String movieName;
    private final String poster;

}
