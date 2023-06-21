package com.moviereservation.domain.payment;

public interface PaymentQueryFactory {
    String getSelectPaymentByMemberIdQuery();
    String getSelectCountByMemberIdQuery();
    String getSelectUsernameByMemberIdQuery();
}
