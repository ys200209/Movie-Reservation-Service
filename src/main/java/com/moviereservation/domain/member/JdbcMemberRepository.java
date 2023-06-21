package com.moviereservation.domain.member;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcMemberRepository implements MemberRepository {
    public static final String INSERT_MEMBER_QUERY = "INSERT INTO MEMBERS(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_MEMBER_BY_ID_QUERY = "SELECT * FROM Members WHERE member_id = ?";
    public static final String UPDATE_MEMBER_PASSWORD_QUERY = "UPDATE Members SET member_password = ? WHERE member_id = ?";

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

    @Override
    public Optional<Member> findById(String id) {
        List<Member> members = jdbcTemplate.query(
                SELECT_MEMBER_BY_ID_QUERY,
                Member.memberMapper,
                id);

        if (members.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(members.get(0));
    }

    @Override
    public int changePassword(Member member, String newEncodedPassword) {
        return jdbcTemplate.update(UPDATE_MEMBER_PASSWORD_QUERY,
                newEncodedPassword, member.getMemberId());
    }
}
