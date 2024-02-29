package com.codestates.model.content_category;

import com.codestates.lib.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Content_CategoryDaoImpl implements Content_CategoryDao {

  private final Mysql mysql = Mysql.getInstance();

  // 데이터 삽입을 위한 메서드
  @Override
  public int insert(Content_CategoryDto content_categoryDto) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int response = 0;

    try {
      connection = mysql.getConnection();
      mysql.query(connection, "USE learnSQL");
      String sql = "INSERT INTO content_category(id, contentId, categoryId) values (?, ?, ?)";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, content_categoryDto.getId());
      preparedStatement.setInt(2, content_categoryDto.getContentId());
      preparedStatement.setInt(3, content_categoryDto.getCategoryId());

      response = preparedStatement.executeUpdate();
      preparedStatement.close();
    } finally {
      mysql.terminate(connection);
    }
    return response;
  }
}
