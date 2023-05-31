package com.moviereservation.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DataJdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class JdbcMemberRepositoryTest {
    MemberRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        repository = new JdbcMemberRepository(jdbcTemplate);
    }

    @Test
    void testFindMemberById() {
        // given
        String id = "id";

        // when
        Member findMember = repository.findById(id).get();

        // then
        assertThat(findMember.getName()).isEqualTo("박새로일");
    }

    @Test
    void testFindMemberById_Null() {
        // given
        String id = "id00";

        // when
        Optional<Member> member = repository.findById(id);

        // then
        assertThat(member.isEmpty()).isTrue();
    }

    @Test
    void testChangePassword_Encode() {
        // given
        String id = "id";
        String encodedPassword = new BCryptPasswordEncoder().encode("changePassword1@");

        // when
        Member member = repository.findById(id).get();
        assertThat(repository.changePassword(member, encodedPassword)).isEqualTo(1);

        // then
        member = repository.findById(id).get();
        assertNotEquals(member.getMemberPassword(), "changePassword1@");
    }
}
