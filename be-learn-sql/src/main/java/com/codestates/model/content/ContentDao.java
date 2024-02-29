package com.codestates.model.content;

import java.sql.SQLException;

public interface ContentDao {
  int insert(ContentDto contentDto) throws SQLException;
}
