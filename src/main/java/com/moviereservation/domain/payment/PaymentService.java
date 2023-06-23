package com.moviereservation.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentDto findByMemberId(String id){
        Payment payment = paymentRepository.findByMemberId(id);
        PaymentDto paymentDto = new PaymentDto(
                payment.getCardNumber(),
                payment.getCountChild(),
                payment.getCountTeenager(),
                payment.getCountAdult(),
                payment.getPayAmount(),
                payment.getPaymentAt(),
                payment.getMemberId(),
                payment.getMovieName(),
                payment.getPoster()
        );
        return paymentDto;
    }

    public boolean CheckByMemberId(String id){
        boolean check = paymentRepository.CheckByMemberId(id);
        return check;
    }
}
