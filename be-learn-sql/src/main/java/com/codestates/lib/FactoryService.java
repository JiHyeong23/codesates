package com.codestates.lib;

import com.codestates.model.category.CategoryDao;
import com.codestates.model.category.CategoryDaoImpl;
import com.codestates.model.category.CategoryDto;
import com.codestates.model.content.ContentDao;
import com.codestates.model.content.ContentDaoImpl;
import com.codestates.model.content.ContentDto;
import com.codestates.model.content_category.Content_CategoryDao;
import com.codestates.model.content_category.Content_CategoryDaoImpl;
import com.codestates.model.content_category.Content_CategoryDto;
import com.codestates.model.role.RoleDao;
import com.codestates.model.role.RoleDaoImpl;
import com.codestates.model.role.RoleDto;
import com.codestates.model.user.UserDao;
import com.codestates.model.user.UserDaoImpl;
import com.codestates.model.user.UserDto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class FactoryService {

  private final Mysql mysql = Mysql.getInstance();
  private final UserDao userDao = new UserDaoImpl();
  private final ContentDao contentDao = new ContentDaoImpl();
  private final RoleDao roleDao = new RoleDaoImpl();
  private final Content_CategoryDao content_categoryDao = new Content_CategoryDaoImpl();
  private final CategoryDao categoryDao = new CategoryDaoImpl();
  private static FactoryService instance;

  private FactoryService(){}

  // Singleton
  public static FactoryService getInstance() {
    if (instance == null)
      instance = new FactoryService();
    return instance;
  }

  // initialize
  public void init(Connection connection) throws SQLException {
    mysql.query(connection, "DROP DATABASE IF EXISTS learnSQL");
    mysql.query(connection, "CREATE DATABASE learnSQL");
    mysql.query(connection, "USE learnSQL");
  }

  // migration
  // 작성된 schema.sql 파일을 통해 테이블 생성
  public void migration(Connection connection) throws SQLException {

    File file = new File("src/main/java/com/codestates/migrations/schema.sql");
    String absolutePath = file.getAbsolutePath(); //절대 경로 찾기

    StringBuilder contentBuilder = new StringBuilder();

    try (Stream<String> stream = Files.lines(Paths.get(absolutePath), StandardCharsets.UTF_8)) {
      stream.forEach(contentBuilder::append);
    } catch (IOException e) {
      e.printStackTrace();
    }

    String[] querys = contentBuilder.toString().split(";");

    Statement statement = connection.createStatement();

    for(String str : querys) {
      statement.execute(str);
    }

    statement.close();
  }

  // 검증 메서드
  public ArrayList<HashMap<String,Object>> find(Connection connection, String table, String column) throws SQLException {
    return mysql.selectQuery(connection, String.format("SELECT %s.%s FROM %s", table, column, table));
  }

  // part-2 테스트를 위한 컬럼값 셋팅
  public void part2_setup() throws SQLException {
    List<UserDto> users = new ArrayList<UserDto>() {{
      add(new UserDto(1, "luckykim", "luckykim@codestates.com"));
      add(new UserDto(2, "lattekim", "lattekim@codestates.com"));
      add(new UserDto(3, "nillava", "nillava@codestates.com"));
      add(new UserDto(4, "jungminlee", "jungminlee@codestates.com"));
    }};

    for(UserDto userDto : users) {
      userDao.insert(userDto);
    }

    List<ContentDto> contents = new ArrayList<>() {{
      add(new ContentDto(1, "database homework", "database is easy", new Timestamp(System.currentTimeMillis()), users.get(0).getId()));
      add(new ContentDto(2, "deploy homework", "deploy is difficult", new Timestamp(System.currentTimeMillis())));
      add(new ContentDto(3, "first project", "code states", new Timestamp(System.currentTimeMillis())));
    }};

    for(ContentDto contentDto : contents) {
      contentDao.insert(contentDto);
    }
  }

  // part-3 테스트를 위한 컬럼값 셋팅
  public void part3_setup() throws SQLException {
    List<RoleDto> roles = new ArrayList<>() {{
      add(new RoleDto(1, "teacher"));
      add(new RoleDto(2, "student"));
    }};

    for(RoleDto roleDto : roles) {
      roleDao.insert(roleDto);
    }

    List<UserDto> users = new ArrayList<UserDto>() {{
      add(new UserDto(1, "minsanggu", "minsanggu@codestates.com", roles.get(0).getId()));
      add(new UserDto(2, "jungsikhwang", "jungsikhwang@codestates.com"));
      add(new UserDto(3, "younghyuncho", "younghyuncho@codestates.com", roles.get(1).getId()));
      add(new UserDto(4, "jungminlee", "luckykim@codestates.com", roles.get(1).getId()));
      add(new UserDto(5, "teawoongna", "teawoongna@codestates.com", roles.get(1).getId()));
    }};

    for(UserDto userDto : users) {
      userDao.insert(userDto);
    }

    List<ContentDto> contents = new ArrayList<>() {{
      add(new ContentDto(1, "developer proverb", "Even if the server goes down, there is a backup", new Timestamp(System.currentTimeMillis()), users.get(0).getId()));
      add(new ContentDto(2, "common proverb", "Bamboo basket can’t carry water.", new Timestamp(System.currentTimeMillis()), users.get(4).getId()));
    }};

    for(ContentDto contentDto : contents) {
      contentDao.insert(contentDto);
    }

    List<CategoryDto> category = new ArrayList<>() {{
      add(new CategoryDto(1, "java"));
      add(new CategoryDto(2, "spring"));
      add(new CategoryDto(3, "query"));
    }};

    for(CategoryDto categoryDto : category) {
      categoryDao.insert(categoryDto);
    }

    List<Content_CategoryDto> content_category = new ArrayList<>() {{
      add(new Content_CategoryDto(1, contents.get(0).getId(), category.get(0).getId()));
      add(new Content_CategoryDto(2, contents.get(0).getId(), category.get(2).getId()));
      add(new Content_CategoryDto(3, contents.get(1).getId(), category.get(1).getId()));
      add(new Content_CategoryDto(4, contents.get(1).getId(), category.get(0).getId()));
    }};

    for(Content_CategoryDto content_categoryDto : content_category) {
      content_categoryDao.insert(content_categoryDto);
    }
  }
}
