package com.moviereservation.service.member;

import com.moviereservation.domain.member.Member;
import com.moviereservation.domain.member.MemberRepository;
import com.moviereservation.utils.exception.DuplicateMemberException;
import com.moviereservation.web.member.dto.MemberRegisterDto;
import com.moviereservation.web.member.dto.PasswordChangeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public int save(MemberRegisterDto registerDto) {
        try {
            return repository.save(Member.toEntity(registerDto));
        } catch (DuplicateKeyException e) {
            throw new DuplicateMemberException(registerDto);
        }
    }

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
