package com.codestates;

import com.codestates.lib.FactoryService;
import com.codestates.lib.Mysql;
import com.codestates.script.Properties;
import com.codestates.script.part1;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Part1_Test {

  private static Mysql mysql = Mysql.getInstance();
  private static FactoryService factoryService = FactoryService.getInstance();
  private static Connection connection = null;

  // 테스트 시작전 initializr
  @BeforeAll
  public static void init() throws SQLException {
    connection = mysql.getConnection();
    factoryService.init(connection);
    factoryService.migration(connection);
  }

  // 테스트 종료후 데이터베이스 자원 종료
  @AfterAll
  public static void terminate() throws SQLException {
    mysql.terminate(connection);
  }

  @Test
  @DisplayName("Q 1-0. 데이터베이스 접속 확인")
  public void Connect_Test() throws SQLException {
    System.out.printf("<YOUR DATABASE CONFIG>%n");
    System.out.printf("URL : %s%n", com.codestates.script.Properties.getURL());
    System.out.printf("DATABASE_USERNAME : %s%n", Properties.getDatabaseUsername());
    System.out.printf("DATABASE_PASSWORD : %s%n", com.codestates.script.Properties.getDatabasePassword());
  }

  @Test
  @DisplayName("Q 1-1. 현재 있는 데이터베이스에 존재하는 모든 테이블 정보를 보기위한 SQL을 작성해주세요.")
  public void Query_Test_1_1() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part1.PART1_1);
    assertThat(response.size() >= 2).isTrue();

    assertThat(response.toString().contains("user")).isTrue();
    assertThat(response.toString().contains("content")).isTrue();

    // 결과 출력
    for(HashMap<String, Object> map: response) {
      Set set = map.entrySet();
      for (Object o : set) {
        Map.Entry<String, Object> entry = (Map.Entry) o;
        Object value = entry.getValue();
        System.out.printf("TABLE : %s%n", value);
      }
    }
  }

  @Test
  @DisplayName("Q 1-2. user 테이블의 구조를 보기위한 SQL을 작성해주세요.")
  public void Query_Test_1_2() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part1.PART1_2);

    assertThat(response.size() >= 3).isTrue();

    assertThat(response.get(0).toString().contains("id")).isTrue();
    assertThat(response.get(0).toString().contains("PRI")).isTrue();
    assertThat(response.get(1).toString().contains("name")).isTrue();
    assertThat(response.get(1).toString().contains("varchar")).isTrue();
    assertThat(response.get(2).toString().contains("email")).isTrue();

    // 결과 출력
    for(HashMap<String, Object> map: response) {
      System.out.println(map);
    }
  }

  @Test
  @DisplayName("Q 1-3. content 테이블의 구조를 보기위한 SQL을 작성해주세요.")
  public void Query_Test_1_3() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part1.PART1_3);

    assertThat(response.size()).isEqualTo(5);

    assertThat(response.get(0).toString().contains("id")).isTrue();
    assertThat(response.get(0).toString().contains("PRI")).isTrue();
    assertThat(response.get(1).toString().contains("title")).isTrue();
    assertThat(response.get(1).toString().contains("varchar")).isTrue();
    assertThat(response.get(2).toString().contains("body")).isTrue();
    assertThat(response.get(3).toString().contains("created_at")).isTrue();
    assertThat(response.get(3).toString().contains("CURRENT_TIMESTAMP")).isTrue();
    assertThat(response.get(4).toString().contains("userId")).isTrue();
    assertThat(response.get(4).toString().contains("MUL")).isTrue();

    // 결과 출력
    for(HashMap<String, Object> map: response) {
      System.out.println(map);
    }
  }
}
