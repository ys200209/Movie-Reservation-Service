package com.moviereservation.domain.register;

import static org.assertj.core.api.Assertions.assertThat;

import com.moviereservation.domain.member.Member;
import com.moviereservation.domain.member.register.JdbcRegisterRepository;
import com.moviereservation.domain.member.register.RegisterRepository;
import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto;
import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto.MemberRegisterDtoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class JdbcRegisterRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RegisterRepository repository;

    @BeforeEach
    void setUp() {
        repository = new JdbcRegisterRepository(jdbcTemplate, new BCryptPasswordEncoder());
    }

    @Test
    void testSaveMember() {
        // given
        MemberRegisterDto memberRegister = getStandardMemberRegister().build();
        Member member = Member.toEntity(memberRegister);

        // when
        int actual = repository.save(member);

        // then
        assertThat(actual).isEqualTo(1);
    }

    private static MemberRegisterDtoBuilder getStandardMemberRegister() {
        return MemberRegisterDto.builder()
                .memberId("memberId")
                .memberPassword("memberPassword1#")
                .gender("남자")
                .birth("2000-01-01")
                .name("name")
                .phoneNumber("010-1234-5678");
    }
}
