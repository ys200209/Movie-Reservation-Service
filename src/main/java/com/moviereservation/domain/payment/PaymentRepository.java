package com.moviereservation.domain.payment;

public interface PaymentRepository {
    Payment findByMemberId(String id);
    int CheckByMemberId(String id);
}
