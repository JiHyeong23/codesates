package com.codestates.model.user;

import java.sql.SQLException;

public interface UserDao {
  int insert(UserDto userDto) throws SQLException;
//  List<UserDto> select() throws SQLException;
}
