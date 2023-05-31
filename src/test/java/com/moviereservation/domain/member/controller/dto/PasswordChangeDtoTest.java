package com.moviereservation.domain.member.controller.dto;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PasswordChangeDtoTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreate() {
        // given
        PasswordChangeDto dto = new PasswordChangeDto("memberPassword", "newPassword", "confirmPassword");

        // then
        assertThat(dto.getMemberPassword()).isEqualTo("memberPassword");
        assertThat(dto.getNewPassword()).isEqualTo("newPassword");
        assertThat(dto.getConfirmPassword()).isEqualTo("confirmPassword");
    }

    @ParameterizedTest
    @MethodSource("getBlankValue")
    void testValidation_NotBlank(String value) {
        // given
        PasswordChangeDto passwordDto = new PasswordChangeDto(value, value, value);

        // when
        Set<ConstraintViolation<PasswordChangeDto>> violations = validator.validate(passwordDto);

        // then
        assertThat(violations.isEmpty()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("getInvalidSizeValue")
    void testValidation_Size(String value) {
        // given
        PasswordChangeDto passwordDto = new PasswordChangeDto(value, value, value);

        // when
        Set<ConstraintViolation<PasswordChangeDto>> violations = validator.validate(passwordDto);

        // then
        assertThat(violations.isEmpty()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("getInvalidPattern")
    void testValidation_Pattern(String value) {
        // given
        PasswordChangeDto passwordDto = new PasswordChangeDto(value, value, value);

        // when
        Set<ConstraintViolation<PasswordChangeDto>> violations = validator.validate(passwordDto);

        // then
        assertThat(violations.isEmpty()).isFalse();
    }

    private static Stream<String> getBlankValue() {
        return Stream.of(null, "", " ");
    }

    public static Stream<String> getInvalidSizeValue() {
        return Stream.of("a".repeat(7), "a".repeat(20), "a".repeat(46));
    }

    public static Stream<String> getInvalidPattern() {
        return Stream.of("123456aa", "!!!@#!@#a");
    }
}
