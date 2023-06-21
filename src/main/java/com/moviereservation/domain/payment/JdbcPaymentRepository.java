package com.moviereservation.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class JdbcPaymentRepository implements PaymentRepository{

    private final JdbcTemplate jdbcTemplate;

    private final String SELECT_PAYMENT_BY_MEMBER_ID_SQL = "select card_number, count_child, count_teenager, count_adult, pay_amount, payment_at, mb.member_id, m.movie_name, m.poster" +
            "from payments p inner join members mb on p.members_seq = mb.seq inner join movies m on m.seq = p.movies_seq" +
            "where member_id = ?";

    private final String SELECT_COUNT_BY_MEMBER_ID_SQL = "select count(*) from payments where member_id= ? ";

    @Override
    public Payment findByMemberId(String id) {
        Payment payment = jdbcTemplate.queryForObject(SELECT_PAYMENT_BY_MEMBER_ID_SQL, new RowMapper<Payment>() {
            @Override
            public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
                Payment payment = new Payment(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getTimestamp(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                );
                return payment;
            }
        },id);
        return payment;
    }

    @Override
    public int CheckByMemberId(String id) {
        int notFound = jdbcTemplate.queryForObject(SELECT_COUNT_BY_MEMBER_ID_SQL, Integer.class);
        return notFound;
    }
}
