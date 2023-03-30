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

    @Override
    public boolean checkLogin(Login login) {
        Login check = jdbcTemplate.queryForObject("select member_id, member_password from members where member_id = ?", new RowMapper<Login>() {
            @Override
            public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
                Login login = new Login(
                        rs.getString("member_id"),
                        rs.getString("member_password")
                );
                return login;
            }
        }, login.getMember_id());
        if(check.getMember_id().equals(login.getMember_id()) && check.getMember_password().equals(login.getMember_password())){
            return true;
        }
        return false;
    }
}
