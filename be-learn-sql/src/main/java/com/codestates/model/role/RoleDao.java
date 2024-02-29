package com.codestates.model.role;

import java.sql.SQLException;

public interface RoleDao {
  int insert(RoleDto roleDto) throws SQLException;
}
