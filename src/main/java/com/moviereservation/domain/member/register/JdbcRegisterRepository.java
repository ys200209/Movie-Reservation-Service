package com.moviereservation.domain.member.register;

import com.moviereservation.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcRegisterRepository implements RegisterRepository {
    public static final String INSERT_MEMBER_QUERY = "INSERT INTO MEMBERS(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int save(Member member) {
        return jdbcTemplate.update(INSERT_MEMBER_QUERY,
                member.getMemberId(),
                passwordEncoder.encode(member.getMemberPassword()),
                member.getGender().getGender(),
                member.getBirth(),
                member.getName(),
                member.getPhoneNumber(),
                member.getCreateAt(),
                member.getModifyAt(),
                member.getRoleName(),
                member.getEnable());
    }
}
