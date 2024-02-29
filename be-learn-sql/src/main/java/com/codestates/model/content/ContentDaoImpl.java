package com.codestates.model.content;

import com.codestates.lib.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ContentDaoImpl implements ContentDao {

  private final Mysql mysql = Mysql.getInstance();

  // 데이터 삽입을 위한 메서드
  @Override
  public int insert(ContentDto contentDto) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int response = 0;
    try {

      connection = mysql.getConnection();
      mysql.query(connection, "USE learnSQL");
      String sql = "INSERT INTO content(id, title, body, userId) values (?, ?, ?, ?)";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, contentDto.getId());
      preparedStatement.setString(2, contentDto.getTitle());
      preparedStatement.setString(3, contentDto.getBody());
      if(contentDto.getUserId() == null) {
        preparedStatement.setNull(4, Types.INTEGER);
      }else {
        preparedStatement.setInt(4, contentDto.getUserId());
      }

      response = preparedStatement.executeUpdate();
      preparedStatement.close();
    } finally {
      mysql.terminate(connection);
    }
    return response;
  }
}
