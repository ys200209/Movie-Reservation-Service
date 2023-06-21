package com.moviereservation.domain.payment.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Getter
@RequiredArgsConstructor
public class NotFoundReserveException {
    @ExceptionHandler(RuntimeException.class)
    private ModelAndView handleError(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("/payment/NotFoundReserveException");
        return modelAndView;
    }
}
