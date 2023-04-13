package com.moviereservation.domain;


import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public class CommentResponseDto {
    private final Long seq;

    @Pattern(regexp ="^(?!.*<script>).*$", flags=Pattern.Flag.CASE_INSENSITIVE)
    private final String content;


    private final String name;
    private final LocalDateTime createAt;

    public CommentResponseDto(Comment comment){
        this.seq = comment.getSeq();
        this.content = comment.getContent();
        this.name = comment.getName();
        this.createAt = comment.getCreateAt().toLocalDateTime();
    }

}
