package com.moviereservation.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.moviereservation.service.member.MemberService;
import com.moviereservation.web.member.dto.PasswordChangeDto;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;

class MemberServiceTest {
    MemberService service;

    @Mock
    BindingResult bindingResult;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @Mock
    MemberRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new MemberService(repository, passwordEncoder);
    }

    @ParameterizedTest
    @MethodSource("getInvalidPasswordDto")
    void testChangePassword_Invalid_Argument_Fail(Member member, PasswordChangeDto passwordDto) {
        // given
        String id = "id";

        // when
        when(bindingResult.hasErrors()).thenReturn(true);
        when(repository.findById(id)).thenReturn(Optional.of(member));

        // then
        assertThat(service.changePassword(id, passwordDto, bindingResult)).isEqualTo(-1);
    }

    public static Stream<Arguments> getInvalidPasswordDto() {
        Member member = new Member(null, "id", "password", null, null, null, null, null, null, null, null);
        return Stream.of(
                Arguments.of(member, new PasswordChangeDto(null, "newPassword1@", "newPassword123!@#")),
                Arguments.of(member, new PasswordChangeDto("password", "newPassword1@", "newPassword1@")),
                Arguments.of(member, new PasswordChangeDto("password1", "newPassword1@", "newPassword1@"))
        );
    }
}
