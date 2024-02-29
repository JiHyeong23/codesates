package com.codestates.lib;

import com.codestates.script.Properties;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Mysql {

  // script/Properties.java 에서 사용자 정보 로드
  private final String URL = Properties.getURL();
  private final String USER_ID = Properties.getDatabaseUsername();
  private final String USER_PWD = Properties.getDatabasePassword();

  private static Mysql instance;

  private Mysql() {
  }

  //Singleton
  public static Mysql getInstance() {
    if (instance == null)
      instance = new Mysql();
    return instance;
  }

  // 데이터베이스 접속
  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER_ID, USER_PWD);
  }

  // 쿼리 메서드
  public void query(Connection connection, String sql) throws SQLException {
    Statement statement = connection.createStatement();
    statement.execute(sql);
    statement.close();
  }

  //ResultSet to Convert ArrayList<HashMap<>>
  public ArrayList<HashMap<String,Object>> convertResultSetToArrayList(ResultSet rs) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

    while(rs.next()) {
      HashMap<String,Object> row = new HashMap<String, Object>(columns);
      for(int i=1; i<=columns; ++i) {
        if(!row.containsKey(md.getColumnName(i))) row.put(md.getColumnName(i), rs.getObject(i));
        else row.put("roleName", rs.getObject(i));
      }
      list.add(row);
    }
    return list;
  }

  // 쿼리 메서드(Select 구문) ArrayList로 반환(위의 convertResultSetToArrayList 지정 메서드 사용)
  public ArrayList<HashMap<String,Object>> selectQuery(Connection connection, String sql) throws SQLException {
    Statement statement = connection.createStatement();
    ArrayList<HashMap<String,Object>> response = convertResultSetToArrayList(statement.executeQuery(sql));
    statement.close();
    return response;
  }

  // 쿼리 메서드(Select 구문) 오버로딩
  public ArrayList<HashMap<String,Object>> selectQuery(Connection connection, String table, String column) throws SQLException {
    Statement statement = connection.createStatement();
    ArrayList<HashMap<String,Object>> response = convertResultSetToArrayList(statement.executeQuery(String.format("SELECT %s.%s FROM %s", table, column, table)));
    statement.close();
    return response;
  }

  // 데이터베이스 접속 종료
  public void terminate(Connection connection) throws SQLException {
    if(connection != null && !connection.isClosed())
      connection.close();
  }
}