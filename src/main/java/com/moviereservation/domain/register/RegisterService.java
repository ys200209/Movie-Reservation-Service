package com.moviereservation.domain.register;

import com.moviereservation.domain.member.Member;
import com.moviereservation.domain.register.controller.dto.MemberRegisterDto;
import com.moviereservation.domain.register.utils.exception.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegisterService {
    private final RegisterRepository repository;

    @Transactional
    public int save(MemberRegisterDto registerDto) {
        try {
            return repository.save(Member.toEntity(registerDto));
        } catch (DuplicateKeyException e) {
            throw new DuplicateMemberException(registerDto);
        }
    }

    public Member findById(String id) {
        return repository.findById(id);
    }
}
