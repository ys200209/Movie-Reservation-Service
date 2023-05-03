package com.moviereservation.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.moviereservation.domain.register.controller.dto.MemberRegisterDto;
import com.moviereservation.domain.register.controller.dto.MemberRegisterDto.MemberRegisterDtoBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    void testCreateMember() {
        // given
        Member member = Member.builder()
                .memberId("memberId")
                .memberPassword("memberPassword1#")
                .gender(Gender.MALE)
                .birth(LocalDate.parse("2000-01-01"))
                .name("name")
                .phoneNumber("010-1234-5678")
                .createAt(LocalDateTime.now())
                .modifyAt(LocalDateTime.now())
                .roleName("ROLE_USER")
                .enable(true)
                .build();

        // then
        assertThat(member.getMemberId()).isEqualTo("memberId");
    }

    @Test
    void testToEntity() {
        // given
        MemberRegisterDto memberRegister = getStandardMemberRegister().build();

        // when
        Member member = Member.toEntity(memberRegister);

        // then
        assertThat(member.getMemberId()).isEqualTo("memberId");
        assertThat(member.getRoleName()).isEqualTo("ROLE_USER");
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
