package com.moviereservation.repository;

import com.moviereservation.domain.Login;
import com.moviereservation.domain.Member;

import java.time.LocalDate;

public interface MemberRepository {

    public boolean checkLogin(Login login);
    public void regist(Member member);
}
