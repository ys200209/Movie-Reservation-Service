package com.moviereservation.service;

import com.moviereservation.domain.Login;
import com.moviereservation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberRepository memberRepository;

    public boolean checkLogin(Login login){
        return memberRepository.checkLogin(login);
    }
}
