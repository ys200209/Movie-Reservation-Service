package com.moviereservation;

import com.moviereservation.domain.detail.comment.CommentMember;
import com.moviereservation.domain.detail.comment.CommentMemberDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {

    private Timestamp now;

    @BeforeEach
    void setTimestamp(){
        this.now = Timestamp.valueOf(LocalDateTime.now());
    }

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        CommentMember commentMember = new CommentMember(16L, "파라", 1L, "~^&()", now, now);

        CommentMemberDto crd = new CommentMemberDto(commentMember);

        Set<ConstraintViolation<CommentMemberDto>> violations = validator.validate(crd);
        assertEquals(violations.size(),0);
    }

    @Test
    void beanValidationExceptionIncludeScriptCharacter() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Long commentSeq = 13L;
        Long moiveSeq = 1L;
        String content = "<script>${category.getName()}</script>";
        String memberName = "사라";
        CommentMember commentMember = new CommentMember(commentSeq, memberName, moiveSeq, content, now, now);


        CommentMemberDto crd1 = new CommentMemberDto(commentMember);

        Set<ConstraintViolation<CommentMemberDto>> violations = validator.validate(crd1);
        assertEquals(violations.size(), 1);
    }

    @Test
    void beanValidationException() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Long commentSeq = 13L;
        Long moiveSeq = 1L;
        String content = "${category.getName()}</script>";
        String memberName = "사라";

        CommentMember commentMember = new CommentMember(commentSeq, memberName, moiveSeq, content, now, now);

        CommentMemberDto crd1 = new CommentMemberDto(commentMember);

        Set<ConstraintViolation<CommentMemberDto>> violations = validator.validate(crd1);
        assertEquals(violations.size(), 0);
    }
    // 작성자 id, 표현되는 id 일치여부
    //
}
