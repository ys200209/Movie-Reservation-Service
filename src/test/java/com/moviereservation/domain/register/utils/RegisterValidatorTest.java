package com.moviereservation.domain.register.utils;

import static org.assertj.core.api.Assertions.assertThat;

import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto;
import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto.MemberRegisterDtoBuilder;
import com.moviereservation.domain.member.register.utils.validator.RegisterValidator;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@SpringBootTest
class RegisterValidatorTest {
    private RegisterValidator validator;

    @BeforeEach
    void setUp() {
        validator = new RegisterValidator();
    }

    @Test
    void testValidMember() {
        // given
        MemberRegisterDto member = getStandardMemberRegister().build();
        Errors errors = new BeanPropertyBindingResult(member, "member");

        // when
        validator.validate(member, errors);

        // then
        assertThat(errors.hasErrors()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("getInvalidMemberId")
    void testInvalidMemberId(String memberId) {
        // given
        MemberRegisterDto member = getStandardMemberRegister().memberId(memberId).build();
        Errors errors = new BeanPropertyBindingResult(member, "member");

        // when
        validator.validate(member, errors);

        // then
        assertThat(errors.hasErrors()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getInvalidMemberPassword")
    void testInvalidMemberPassword(String memberPassword) {
        // given
        MemberRegisterDto member = getStandardMemberRegister().memberPassword(memberPassword).build();
        Errors errors = new BeanPropertyBindingResult(member, "member");

        // when
        validator.validate(member, errors);

        // then
        assertThat(errors.hasErrors()).isTrue();
    }

    @Test
    void testInvalidGender() {
        // given
        MemberRegisterDto member = getStandardMemberRegister().gender(null).build();
        Errors errors = new BeanPropertyBindingResult(member, "member");

        // when
        validator.validate(member, errors);

        // then
        assertThat(errors.hasErrors()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getInvalidBirth")
    void testInvalidBirth(String birth) {
        // given
        MemberRegisterDto member = getStandardMemberRegister().birth(birth).build();
        Errors errors = new BeanPropertyBindingResult(member, "member");

        // when
        validator.validate(member, errors);

        // then
        assertThat(errors.hasErrors()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getInvalidName")
    void testInvalidName(String name) {
        // given
        MemberRegisterDto member = getStandardMemberRegister().name(name).build();
        Errors errors = new BeanPropertyBindingResult(member, "member");

        // when
        validator.validate(member, errors);

        // then
        assertThat(errors.hasErrors()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getInvalidPhoneNumber")
    void testInvalidPhoneNumber(String phoneNumber) {
        // given
        MemberRegisterDto member = getStandardMemberRegister().phoneNumber(phoneNumber).build();
        Errors errors = new BeanPropertyBindingResult(member, "member");

        // when
        validator.validate(member, errors);

        // then
        assertThat(errors.hasErrors()).isTrue();
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

    private static Stream<String> getInvalidMemberId() {
        return Stream.of("@", "", " ", null, "012345678901234567890");
    }

    private static Stream<String> getInvalidMemberPassword() {
        return Stream.of("aaaa1111", "@@@@aaaa", "@@@@1111", "", " ", null, "0".repeat(46));
    }

    public static Stream<String> getInvalidBirth() {
        return Stream.of("20000101", "2000-01*01", "2000-00-00", null);
    }

    public static Stream<String> getInvalidName() {
        return Stream.of("name1", "123123", "name@");
    }

    public static Stream<String> getInvalidPhoneNumber() {
        return Stream.of("0112345678", "0101234567", "111123456789");
    }
}
