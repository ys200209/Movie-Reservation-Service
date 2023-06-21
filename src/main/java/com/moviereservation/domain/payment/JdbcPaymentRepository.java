package com.moviereservation.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcPaymentRepository implements PaymentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PaymentQueryFactory paymentQueryFactory;

    @Override
    public Payment findByMemberId(String id) {
        List<Member> result = jdbcTemplate.query(paymentQueryFactory.getSelectUsernameByMemberIdQuery(), new RowMapper<>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(
                        rs.getInt(0)
                );
                return member;
            }
        }, new Object[]{id});

        if (result.isEmpty()) {
            throw new IllegalArgumentException("회원 아이디 값이 잘못되었습니다.");
        }

        int memberSeq = result.get(0).getSeq();

        Payment payment = jdbcTemplate.queryForObject(paymentQueryFactory.getSelectPaymentByMemberIdQuery(), new RowMapper<Payment>() {
            @Override
            public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
                Payment payment = new Payment(
                        rs.getString(0),
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getTimestamp(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
                return payment;
            }
        }, memberSeq);
        return payment;
    }

    @Override
    public boolean CheckByMemberId(String id) {
        List<Member> result1 = jdbcTemplate.query(paymentQueryFactory.getSelectUsernameByMemberIdQuery(), new RowMapper<>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(
                        rs.getInt(1)
                );
                return member;
            }
        }, new Object[]{id});

        int memberSeq = result1.get(0).getSeq();

        List<Member> result = jdbcTemplate.query(paymentQueryFactory.getSelectCountByMemberIdQuery(), (rs, rowNum) -> {
            Member payment = new Member(
                    rs.getInt(1)
            );
            return payment;
        }, memberSeq);

        if (!result.isEmpty()) {
            return result.get(0).getSeq() == memberSeq;
        }
        return false;
    }
}
