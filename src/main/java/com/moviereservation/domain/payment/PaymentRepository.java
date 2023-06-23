package com.moviereservation.domain.payment;

public interface PaymentRepository {
    Payment findByMemberId(String id);
    boolean CheckByMemberId(String id);
}
