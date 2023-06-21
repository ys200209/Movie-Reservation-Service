package com.moviereservation.domain.register;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.moviereservation.domain.member.Member;
import com.moviereservation.domain.member.MemberRepository;
import com.moviereservation.service.member.MemberService;
import com.moviereservation.utils.exception.DuplicateMemberException;
import com.moviereservation.web.member.dto.MemberRegisterDto;
import com.moviereservation.web.member.dto.MemberRegisterDto.MemberRegisterDtoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

class RegisterServiceTest {
    @InjectMocks
    private MemberService service;

    @Mock
    private MemberRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMember() {
        // given
        MemberRegisterDto member = getStandardMemberRegister().build();

        // when
        when(repository.save(any(Member.class))).thenReturn(1);
        int result = service.save(member);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testSaveDuplicateException() {
        // given
        MemberRegisterDto member = getStandardMemberRegister().build();

        // when
        when(repository.save(any(Member.class))).thenThrow(new DuplicateKeyException(""));

        // then
        assertThatThrownBy(() -> service.save(member))
                .isInstanceOf(DuplicateMemberException.class);
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
