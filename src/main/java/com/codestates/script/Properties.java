package com.codestates.script;

public class Properties {
  public static String URL = "jdbc:mysql://127.0.0.1:3306"; // 기본 설정값
  public static String DATABASE_USERNAME = "root"; // 기본값은 root입니다.
  public static String DATABASE_PASSWORD = "yourPassword"; // 본인의 패스워드로 변경해주세요.

  public static String getURL() {
    return URL;
  }

  public static String getDatabaseUsername() {
    return DATABASE_USERNAME;
  }

  public static String getDatabasePassword() {
    return DATABASE_PASSWORD;
  }
}
