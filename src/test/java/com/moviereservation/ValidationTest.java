package com.moviereservation;

public class ValidationTest {

    /*private Timestamp now;

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
    }*/

}
