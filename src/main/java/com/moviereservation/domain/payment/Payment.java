package com.moviereservation.domain.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class Payment {
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
