package com.moviereservation.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    //동작 확인후 post로 전환
    @GetMapping("/Memberid")
    public String display(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if(!isNotFoundMemberId(userDetails.getUsername())){
            return "/payment/design";
        }
        PaymentDto paymentDto = paymentService.findByMemberId(userDetails.getUsername());
        model.addAttribute("payemnt", paymentDto);
        return "/payment/design";
    }

    private boolean isNotFoundMemberId(String memberId){
        boolean check = paymentService.CheckByMemberId(memberId);
        return check;
    }
}