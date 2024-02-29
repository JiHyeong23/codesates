package com.codestates.model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.codestates.lib.Mysql;

public class UserDaoImpl implements UserDao {

  private final Mysql mysql = Mysql.getInstance();

  // 데이터 삽입을 위한 메서드
  @Override
  public int insert(UserDto userDto) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int response = 0;

    try {
      connection = mysql.getConnection();
      mysql.query(connection, "USE learnSQL");
      String sql = "INSERT INTO user(id, name, email, roleId) values (?, ?, ?, ?)";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, userDto.getId());
      preparedStatement.setString(2, userDto.getName());
      preparedStatement.setString(3, userDto.getEmail());
      if(userDto.getRoleId() == null) {
        preparedStatement.setNull(4, Types.INTEGER);
      }else {
        preparedStatement.setInt(4, userDto.getRoleId());
      }

      response = preparedStatement.executeUpdate();
      preparedStatement.close();
    } finally {
      mysql.terminate(connection);
    }
    return response;
  }
}
