package com.moviereservation.service;

import com.moviereservation.domain.Login;
import com.moviereservation.domain.Member;
import com.moviereservation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean checkLogin(Login login){
        return memberRepository.checkLogin(login);
    }

    public void regist(Member member){
        String encodedPassword = passwordEncoder.encode(member.getMember_password());
        member.setMember_password(encodedPassword);
        member.setCreate_at(LocalDateTime.now());
        member.setModify_at(LocalDateTime.now());
        member.setEnable(true);
        member.setRole_name("ROLE_USER");
        memberRepository.regist(member);
    }
}
