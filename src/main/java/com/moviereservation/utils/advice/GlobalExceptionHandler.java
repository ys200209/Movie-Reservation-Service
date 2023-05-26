package com.moviereservation.utils.advice;

import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto;
import com.moviereservation.utils.exception.DuplicateMemberException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateMemberException.class)
    public ModelAndView handleMemberException(final DuplicateMemberException exception) {
        MemberRegisterDto member = exception.getMember();
        Errors errors = new BeanPropertyBindingResult(member, "member");
        errors.rejectValue("memberId", "memberId.duplicate", new Object[]{member.getMemberId()}, null);

        ModelAndView mav = new ModelAndView();
        mav.addObject(BindingResult.MODEL_KEY_PREFIX + "member", errors);
        mav.setViewName("/member/register");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerArgumentException(final Exception excecption) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/main");
        return mav;
    }
}
