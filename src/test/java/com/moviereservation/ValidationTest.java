package com.moviereservation;

import com.moviereservation.domain.Comment;
import com.moviereservation.domain.CommentResponseDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {
    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Long seq1 = 16L;
        CommentResponseDto crd = new CommentResponseDto(
                seq1,"~^&()","파라", LocalDateTime.now()
        );

        Set<ConstraintViolation<CommentResponseDto>> violations = validator.validate(crd);
        assertEquals(violations.size(),0);
    }

    @Test
    void beanValidationExceptionIncludeScriptCharacter() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Long seq2 = 13L;

        CommentResponseDto crd1 = new CommentResponseDto(
                seq2,"<script>${category.getName()}</script>","사라", LocalDateTime.now()
        );

        Set<ConstraintViolation<CommentResponseDto>> violations = validator.validate(crd1);
        assertEquals(violations.size(), 1);
    }

    @Test
    void beanValidationException() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Long seq2 = 13L;

        CommentResponseDto crd1 = new CommentResponseDto(
                seq2,"${category.getName()}</script>","사라", LocalDateTime.now()
        );

        Set<ConstraintViolation<CommentResponseDto>> violations = validator.validate(crd1);
        assertEquals(violations.size(), 1);
    }

}
