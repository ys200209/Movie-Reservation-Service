package com.moviereservation.repository;

import com.moviereservation.domain.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieDescriptionImpl implements MovieDescriptionRepository{
    private final JdbcTemplate jdbcTemplate;

    private static final String commentsByMoviesSeq = "SELECT c.seq, c.content, c.create_at, m.name FROM comments c inner join members m on c.members_seq = m.seq where movies_seq = ?";

    @Override
    public List<CommentResponseDto> getCommentsByMoviesSeq(Long moviesSeq){
        List<CommentResponseDto> rs = jdbcTemplate.query(commentsByMoviesSeq, commentsRowMapper(), moviesSeq);
        return rs;
    }

    private RowMapper<CommentResponseDto> commentsRowMapper(){
        return new RowMapper<CommentResponseDto>() {
            @Override
            public CommentResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommentResponseDto comments = new CommentResponseDto(
                        rs.getLong("c.seq"),
                        rs.getString("c.content"),
                        rs.getString("m.name"),
                        rs.getDate("c.create_at")
                );
                return comments;
            }
        };
    }
}
