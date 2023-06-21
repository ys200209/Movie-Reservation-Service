package com.moviereservation.domain.member;

import com.moviereservation.domain.member.controller.dto.PasswordChangeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public int changePassword(String id, PasswordChangeDto passwordDto, BindingResult result) {
        Member member = repository.findById(id).stream()
                .findFirst()
                .orElse(null);

        checkPassword(passwordDto, result, member);
        checkConfirmPassword(passwordDto, result);

        if (result.hasErrors()) {
            return -1;
        }
        return repository.changePassword(member, passwordEncoder.encode(passwordDto.getNewPassword()));
    }

    private void checkPassword(PasswordChangeDto passwordDto, BindingResult result, Member member) {
        if (isPasswordMismatch(passwordDto, member)) {
            result.rejectValue("memberPassword", "memberPassword.mismatch");
        }
    }

    private void checkConfirmPassword(PasswordChangeDto passwordDto, BindingResult result) {
        if (isConfirmPasswordMismatch(passwordDto)) {
            result.rejectValue("confirmPassword", "confirmPassword.mismatch");
        }
    }

    private boolean isConfirmPasswordMismatch(PasswordChangeDto passwordDto) {
        return !passwordDto.getNewPassword().equals(passwordDto.getConfirmPassword());
    }

    private boolean isPasswordMismatch(PasswordChangeDto passwordDto, Member member) {
        return member == null ||
                !passwordEncoder.matches(passwordDto.getMemberPassword(), member.getMemberPassword());
    }
}
