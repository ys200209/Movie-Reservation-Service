package com.moviereservation.domain.payment;

public class MysqlPaymentQueryFactory implements PaymentQueryFactory {

    @Override
    public String getSelectPaymentByMemberIdQuery() {
        return  "select card_number, count_child, count_teenager, count_adult, pay_amount, payment_at, mb.member_id, m.movie_name, m.poster " +
                "FROM payments p inner join members mb on p.members_seq = mb.seq inner join movies m on m.seq = p.movies_seq " +
                "WHERE members_seq = ?";

    }

    @Override
    public String getSelectCountByMemberIdQuery() {
        return "SELECT member_id FROM payments inner join members mb on payments.members_seq = mb.seq WHERE mb.member_id = ?";

    }

    @Override
    public String getSelectUsernameByMemberIdQuery() {
        return "SELECT seq FROM members WHERE member_id = ?";
    }
}
