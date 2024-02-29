package com.codestates.model.category;

import java.sql.SQLException;

public interface CategoryDao {
  int insert(CategoryDto categoryDto) throws SQLException;
}
