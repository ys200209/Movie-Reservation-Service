package com.moviereservation.domain.member;

import com.moviereservation.web.member.dto.MemberRegisterDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.jdbc.core.RowMapper;

@ToString
@Getter
@Builder
@RequiredArgsConstructor
@EqualsAndHashCode
public class Member {
    private final Long seq;
    private final String memberId;
    private final String memberPassword;
    private final Gender gender;
    private final LocalDate birth;
    private final String name;
    private final String phoneNumber;
    private final Grade grade;
    private final LocalDateTime createAt;
    private final LocalDateTime modifyAt;
    private final String roleName;
    private final Boolean enable;

    public static Member toEntity(MemberRegisterDto memberRegister) {
        return Member.builder()
                .memberId(memberRegister.getMemberId())
                .memberPassword(memberRegister.getMemberPassword())
                .gender(Gender.of(memberRegister.getGender()))
                .birth(LocalDate.parse(memberRegister.getBirth()))
                .name(memberRegister.getName())
                .phoneNumber(memberRegister.getPhoneNumber())
                .grade(Grade.SILVER)
                .createAt(LocalDateTime.now())
                .modifyAt(LocalDateTime.now())
                .roleName("ROLE_USER")
                .enable(true)
                .build();
    }

    public static final RowMapper<Member> memberMapper = (rs, rowNum) -> {
        return Member.builder()
                .seq(rs.getLong("seq"))
                .memberId(rs.getString("member_id"))
                .memberPassword(rs.getString("member_password"))
                .gender(Gender.of(rs.getString("gender")))
                .birth(rs.getObject("birth", LocalDate.class))
                .name(rs.getString("name"))
                .phoneNumber(rs.getString("phone_number"))
                .grade(Grade.of(rs.getString("grade")))
                .createAt(rs.getObject("create_at", LocalDateTime.class))
                .modifyAt(rs.getObject("modify_at", LocalDateTime.class))
                .roleName(rs.getString("role_name"))
                .enable(rs.getBoolean("enable"))
                .build();
    };
}
