package com.moviereservation.repository;

import com.moviereservation.domain.Login;
import com.moviereservation.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberRepositoryImpl implements MemberRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

}
