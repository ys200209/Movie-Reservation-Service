package com.moviereservation.utils.advice;

import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto;
import com.moviereservation.utils.exception.DuplicateMemberException;
import com.moviereservation.utils.exception.ReservationNotAllowedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateMemberException.class)
    public ModelAndView handleMemberException(final DuplicateMemberException exception) {
        log.info("DuplicateMemberException occur : {}", exception.getMessage());
        MemberRegisterDto member = exception.getMember();
        Errors errors = new BeanPropertyBindingResult(member, "member");
        errors.rejectValue("memberId", "memberId.duplicate", new Object[]{member.getMemberId()}, null);

        ModelAndView mav = new ModelAndView();
        mav.addObject(BindingResult.MODEL_KEY_PREFIX + "member", errors);
        mav.setViewName("/member/register");
        return mav;
    }

    @ExceptionHandler(ReservationNotAllowedException.class)
    public String handleReservationException(final ReservationNotAllowedException exception) {
        log.info("ReservationNotAllowedException occur : {}", exception.getMessage());

        return null;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(final Exception exception) {
        log.info("Exception occur : {}", exception.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/main");
        return mav;
    }
}
