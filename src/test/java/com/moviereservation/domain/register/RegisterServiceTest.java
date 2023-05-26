package com.moviereservation.domain.register;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.moviereservation.domain.member.Member;
import com.moviereservation.domain.member.register.RegisterRepository;
import com.moviereservation.domain.member.register.RegisterService;
import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto;
import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto.MemberRegisterDtoBuilder;
import com.moviereservation.utils.exception.DuplicateMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

class RegisterServiceTest {
    @InjectMocks
    private RegisterService service;

    @Mock
    private RegisterRepository repository;

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

    @Test
    void testFindMemberNull() {
        // given
        MemberRegisterDto requestDto = getStandardMemberRegister().build();

        // when
        when(repository.findById(anyString())).thenReturn(null);
        Member findMember = service.findById(requestDto.getMemberId());

        // then
        assertThat(findMember).isNull();
    }

    @Test
    void testFindMemberNotNull() {
        // given
        MemberRegisterDto requestDto = getStandardMemberRegister().build();
        Member savedMember = Member.toEntity(requestDto);

        // when
        when(repository.findById(anyString())).thenReturn(savedMember);
        Member findMember = service.findById(requestDto.getMemberId());

        // then
        assertThat(findMember).isNotNull();
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
