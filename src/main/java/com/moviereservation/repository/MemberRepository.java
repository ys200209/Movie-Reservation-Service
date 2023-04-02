package com.moviereservation.repository;

import com.moviereservation.domain.Login;

public interface MemberRepository {

    public boolean checkLogin(Login login);
}
