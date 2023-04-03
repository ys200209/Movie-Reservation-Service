package com.moviereservation.service;


import com.moviereservation.domain.Login;
import com.moviereservation.domain.Member;

import java.time.LocalDate;

public interface MemberService {
    public boolean checkLogin(Login login);
    public void regist(Member member);
}
