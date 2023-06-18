package com.moviereservation.domain.member.register.utils.validator;

import com.moviereservation.domain.member.Gender;
import com.moviereservation.domain.member.register.controller.dto.MemberRegisterDto;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterValidator implements Validator {
    public static final String ID_PATTERN = "^[가-힣a-zA-Z0-9]*$";
    public static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$";
    public static final String BIRTH_PATTERN = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    public static final String NAME_PATTERN = "^[가-힣a-zA-Z]*$";
    public static final String PHONE_NUMBER_PATTERN = "^(010|011)-\\d{4}-\\d{4}$";
    public static final int ID_LENGTH_UPPER_BOUND = 20;
    public static final int PASSWORD_LENGTH_LOWER_BOUND = 8;
    public static final int PASSWORD_LENGTH_UPPER_BOUND = 45;
    public static final int NAME_LENGTH_UPPER_BOUND = 20;
    public static final int PHONE_NUMBER_VALID_LENGTH = 13;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberRegisterDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberRegisterDto member = (MemberRegisterDto) target;
        validateId(member.getMemberId(), errors);
        validatePassword(member.getMemberPassword(), errors);
        validateGender(Gender.of(member.getGender()), errors);
        validateBirth(member.getBirth(), errors);
        validateName(member.getName(), errors);
        validatePhoneNumber(member.getPhoneNumber(), errors);
    }

    private static void validateId(String memberId, Errors errors) {
        if (StringUtils.isEmpty(memberId)) {
            errors.rejectValue("memberId", "memberId.empty");
            return;
        }
        if (memberId.isBlank()) {
            errors.rejectValue("memberId", "memberId.empty");
        }
        if (memberId.length() > ID_LENGTH_UPPER_BOUND) {
            errors.rejectValue("memberId", "memberId.size");
        }
        if (!Pattern.matches(ID_PATTERN, memberId)) {
            errors.rejectValue("memberId", "memberId.pattern");
        }
    }

    private static void validatePassword(String memberPassword, Errors errors) {
        if (StringUtils.isEmpty(memberPassword)) {
            errors.rejectValue("memberPassword", "memberPassword.empty");
            return;
        }
        if (memberPassword.isBlank()) {
            errors.rejectValue("memberPassword", "memberPassword.empty");
        }
        if (memberPassword.length() < PASSWORD_LENGTH_LOWER_BOUND
                || memberPassword.length() > PASSWORD_LENGTH_UPPER_BOUND) {
            errors.rejectValue("memberPassword", "memberPassword.size");
        }
        if (!Pattern.matches(PASSWORD_PATTERN, memberPassword)) {
            errors.rejectValue("memberPassword", "memberPassword.pattern");
        }
    }

    private void validateGender(Gender gender, Errors errors) {
        if (gender == null) {
            errors.rejectValue("gender", "gender.empty");
        }
    }

    private void validateBirth(String birth, Errors errors) {
        if (StringUtils.isEmpty(birth)) {
            errors.rejectValue("birth", "birth.empty");
            return;
        }
        if (!Pattern.matches(BIRTH_PATTERN, birth)) {
            errors.rejectValue("birth", "birth.pattern");
        }
    }

    private void validateName(String name, Errors errors) {
        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", "name.empty");
            return;
        }
        if (name.isBlank()) {
            errors.rejectValue("name", "name.empty");
        }
        if (name.length() > NAME_LENGTH_UPPER_BOUND) {
            errors.rejectValue("name", "name.size");
        }
        if (!Pattern.matches(NAME_PATTERN, name)) {
            errors.rejectValue("name", "name.pattern");
        }
    }

    private void validatePhoneNumber(String phoneNumber, Errors errors) {
        if (StringUtils.isEmpty(phoneNumber)) {
            errors.rejectValue("phoneNumber", "phoneNumber.empty", "PhoneNumber must not be empty");
            return;
        }
        if (phoneNumber.isBlank()) {
            errors.rejectValue("phoneNumber", "phoneNumber.empty", "PhoneNumber must not be empty");
        }
        if (phoneNumber.length() != PHONE_NUMBER_VALID_LENGTH) {
            errors.rejectValue("phoneNumber", "phoneNumber.pattern", "PhoneNumber size must not be greater then 20");
            return;
        }
        if (!Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber)) {
            errors.rejectValue("phoneNumber", "phoneNumber.pattern", "PhoneNumber invalid match pattern");
        }
    }
}
