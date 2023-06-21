package com.moviereservation.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    //동작 확인후 post로 전환
    @PostMapping("/Memberid")
    public String display(@AuthenticationPrincipal UserDetails userDetails,
                                 @ModelAttribute(name = "payment") PaymentDto paymentDto) {
        if(!isNotFoundMemberId(userDetails.getUsername())){
            return "redirect:/payment";
        }
        paymentService.findByMemberId(userDetails.getUsername());
        return "/payment";
    }

    private boolean isNotFoundMemberId(String memberId){
        return paymentService.CheckByMemberId(memberId) > 0;
    }
}