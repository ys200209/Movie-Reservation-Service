package com.moviereservation.domain.register.controller.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.moviereservation.web.member.dto.MemberRegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRegisterDtoTest {
    @Test
    void testCreateDto() {
        // given
        MemberRegisterDto member = MemberRegisterDto.builder()
                .memberId("memberId")
                .memberPassword("memberPassword1#")
                .gender("남자")
                .birth("2000-01-01")
                .name("name")
                .phoneNumber("010-1234-5678")
                .build();

        // then
        assertThat(member.getMemberId()).isEqualTo("memberId");
        assertThat(member.getMemberPassword()).isEqualTo("memberPassword1#");
    }
}