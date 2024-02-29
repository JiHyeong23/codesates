package com.codestates.model.content_category;

import java.sql.SQLException;

public interface Content_CategoryDao {
  int insert(Content_CategoryDto content_categoryDto) throws SQLException;
}
