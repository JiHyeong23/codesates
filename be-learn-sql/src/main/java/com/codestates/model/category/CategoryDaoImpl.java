package com.codestates.model.category;

import com.codestates.lib.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryDaoImpl implements CategoryDao {

  private final Mysql mysql = Mysql.getInstance();

  // 데이터 삽입을 위한 메서드
  @Override
  public int insert(CategoryDto categoryDto) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int response = 0;

    try {
      connection = mysql.getConnection();
      mysql.query(connection, "USE learnSQL");
      String sql = "INSERT INTO category(id, name) values (?, ?)";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, categoryDto.getId());
      preparedStatement.setString(2, categoryDto.getName());

      response = preparedStatement.executeUpdate();
      preparedStatement.close();
    } finally {
      mysql.terminate(connection);
    }
    return response;
  }
}
