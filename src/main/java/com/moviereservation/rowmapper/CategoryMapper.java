package com.moviereservation.rowmapper;

import com.moviereservation.domain.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category(
                rs.getLong("seq"),
                rs.getString("name")
        );
        return category;
    }
}
