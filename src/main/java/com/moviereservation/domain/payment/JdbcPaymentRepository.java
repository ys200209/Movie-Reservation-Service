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

    private final String SELECT_PAYMENT_BY_MEMBER_ID_SQL = "select card_number, count_child, count_teenager, count_adult, pay_amount, payment_at, mb.member_id, m.movie_name, m.poster " +
            "FROM payments p inner join members mb on p.members_seq = mb.seq inner join movies m on m.seq = p.movies_seq " +
            "WHERE members_seq = ?";

    private final String SELECT_COUNT_BY_MEMBER_ID_SQL = "SELECT member_id FROM payments inner join members mb on payments.members_seq = mb.seq WHERE mb.member_id = ?";

    private final String SELECT_USERNAME_BY_MEMBER_ID_SQL = "SELECT seq FROM members WHERE member_id = ?";

    @Override
    public Payment findByMemberId(String id) {
        List<Member> result = jdbcTemplate.query(SELECT_USERNAME_BY_MEMBER_ID_SQL, new RowMapper<>() {
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

        Payment payment = jdbcTemplate.queryForObject(SELECT_PAYMENT_BY_MEMBER_ID_SQL, new RowMapper<Payment>() {
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
        List<Member> result1 = jdbcTemplate.query(SELECT_USERNAME_BY_MEMBER_ID_SQL, new RowMapper<>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(
                        rs.getInt(1)
                );
                return member;
            }
        }, new Object[]{id});

        int memberSeq = result1.get(0).getSeq();

        List<Member> result = jdbcTemplate.query(SELECT_COUNT_BY_MEMBER_ID_SQL, (rs, rowNum) -> {
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
