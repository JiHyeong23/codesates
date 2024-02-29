package com.codestates;

import com.codestates.lib.FactoryService;
import com.codestates.lib.Mysql;
import com.codestates.script.part3;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class Part3_Test {
  private static final Mysql mysql = Mysql.getInstance();
  private static final FactoryService factoryService = FactoryService.getInstance();
  private static Connection connection = null;

  @BeforeAll
  public static void init() throws SQLException {
    connection = mysql.getConnection();
    factoryService.init(connection);
    factoryService.migration(connection);
    factoryService.part3_setup();
  }

  @AfterAll
  public static void terminate() throws SQLException {
    mysql.terminate(connection);
  }

  @Test
  @DisplayName("Q 3-1-1. category 테이블의 구조를 보기위한 SQL을 작성해주세요.")
  public void Query_Test_3_1_1() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_1_1);

    assertThat(response.size()).isEqualTo(2);

    assertThat(response.get(0).toString().contains("id")).isTrue();
    assertThat(response.get(0).toString().contains("PRI")).isTrue();
    assertThat(response.get(0).toString().contains("auto_increment")).isTrue();
    assertThat(response.get(1).toString().contains("name")).isTrue();
    assertThat(response.get(1).toString().contains("varchar")).isTrue();

    // 결과 출력
    for(HashMap<String, Object> map: response) {
      System.out.println(map);
    }
  }

  @Test
  @DisplayName("Q 3-1-2. content_category 테이블의 구조를 보기위한 SQL을 작성해주세요.")
  public void Query_Test_3_1_2() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_1_2);

    System.out.println(response);
    assertThat(response.size()).isEqualTo(3);

    assertThat(response.get(0).toString().contains("id")).isTrue();
    assertThat(response.get(0).toString().contains("PRI")).isTrue();
    assertThat(response.get(0).toString().contains("auto_increment")).isTrue();
    assertThat(response.get(1).toString().contains("contentId")).isTrue();
    assertThat(response.get(1).toString().contains("MUL")).isTrue();
    assertThat(response.get(2).toString().contains("categoryId")).isTrue();
    assertThat(response.get(2).toString().contains("MUL")).isTrue();

    // 결과 출력
    for(HashMap<String, Object> map: response) {
      System.out.println(map);
    }
  }

  @Test
  @DisplayName("Q 3-1-3. role 테이블의 구조를 보기위한 SQL을 작성해주세요.")
  public void Query_Test_3_1_3() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_1_3);

    assertThat(response.size()).isEqualTo(2);

    assertThat(response.get(0).toString().contains("id")).isTrue();
    assertThat(response.get(0).toString().contains("PRI")).isTrue();
    assertThat(response.get(0).toString().contains("auto_increment")).isTrue();
    assertThat(response.get(1).toString().contains("name")).isTrue();
    assertThat(response.get(1).toString().contains("varchar")).isTrue();


    // 결과 출력
    for(HashMap<String, Object> map: response) {
      System.out.println(map);
    }
  }

  @Test
  @DisplayName("Q 3-1-4. user 테이블의 구조를 보기위한 SQL을 작성해주세요.")
  public void Query_Test_3_1_4() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_1_4);

    assertThat(response.size()).isEqualTo(4);

    assertThat(response.get(0).toString().contains("id")).isTrue();
    assertThat(response.get(0).toString().contains("PRI")).isTrue();
    assertThat(response.get(0).toString().contains("auto_increment")).isTrue();
    assertThat(response.get(1).toString().contains("name")).isTrue();
    assertThat(response.get(1).toString().contains("varchar")).isTrue();
    assertThat(response.get(2).toString().contains("email")).isTrue();
    assertThat(response.get(2).toString().contains("varchar")).isTrue();
    assertThat(response.get(3).toString().contains("roleId")).isTrue();
    assertThat(response.get(3).toString().contains("MUL")).isTrue();

    // 결과 출력
    for(HashMap<String, Object> map: response) {
      System.out.println(map);
    }
  }

  @Test
  @DisplayName("Q 3-2-1. category 테이블에 존재하는 데이터에서 id, name을 찾는 SQL을 작성해주세요.")
  public void Query_Test_3_2_1() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_1);

    assertThat(response.size()).isEqualTo(3);
    for(HashMap<String, Object> map: response) {
      assertThat(map.size()).isEqualTo(2);
      System.out.println(map);
    }
  }

  @Test
  @DisplayName("Q 3-2-2. user의 name과 email 그리고 그 user가 속한 role name(컬럼명: roleName)을 찾기 위한 SQL을 작성해주세요. - 속한 role이 없더라도, user의 name과 email,role name을 모두 찾아야합니다.")
  public void Query_Test_3_2_2() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_2);

    assertThat(response.size()).isEqualTo(5);

    int count = 0;

    for(HashMap<String, Object> map: response) {
      assertThat(map.size()).isEqualTo(3);
      if(map.get("roleName") == null) count++;
    }
    assertThat(count).isEqualTo(1);
  }

  @Test
  @DisplayName("Q 3-2-3. 어느 role에도 속하지 않는 user의 모든 컬럼 데이터를 찾기위한 SQL을 작성해주세요.")
  public void Query_Test_3_2_3() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_3);

    assertThat(response.size()).isEqualTo(1);
    assertThat(response.get(0).get("roleId")).isNull();
    assertThat(response.get(0).get("name")).isEqualTo("jungsikhwang");
  }

  @Test
  @DisplayName("Q 3-2-4. content_category 테이블에 존재하는 모든 칼럼의 데이터를 찾기위한 SQL을 작성해주세요.")
  public void Query_Test_3_2_4() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_4);

    assertThat(response.size()).isEqualTo(4);
    for(HashMap<String, Object> map: response) {
      assertThat(map.size()).isEqualTo(3);
    }
  }

  @Test
  @DisplayName("Q 3-2-5. minsanggu이 작성한 content의 title을 찾기위한 SQL을 작성해주세요.")
  public void Query_Test_3_2_5() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_5);

    assertThat(response.size()).isEqualTo(1);
    assertThat(response.get(0).get("title")).isEqualTo("developer proverb");
  }

  @Test
  @DisplayName("Q 3-2-6. minsanggu이 작성한 content의 category name을 찾기위한 SQL을 작성해주세요.")
  public void Query_Test_3_2_6() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_6);

    System.out.println(response);
    assertThat(response.size()).isEqualTo(2);
    assertThat(response.get(0).get("name")).isEqualTo("java");
    assertThat(response.get(1).get("name")).isEqualTo("query");
  }

  @Test
  @DisplayName("Q 3-2-7. category의 name이 java인 content의 title, body, created_at을 찾기위한 SQL을 작성해주세요.")
  public void Query_Test_3_2_7() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_7);

    assertThat(response.size()).isEqualTo(2);
    assertThat(response.get(0).get("title")).isEqualTo("developer proverb");
    assertThat(response.get(1).get("title")).isEqualTo("common proverb");
  }

  @Test
  @DisplayName("Q 3-2-8. category의 name이 java인 content의 title, body, created_at, user의 name을 찾기위한 SQL을 작성해주세요.")
  public void Query_Test_3_2_8() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_8);

    assertThat(response.size()).isEqualTo(2);
    assertThat(response.get(0).get("title")).isEqualTo("developer proverb");
    assertThat(response.get(0).get("name")).isEqualTo("minsanggu");
    assertThat(response.get(0).size()).isEqualTo(4);
    assertThat(response.get(1).get("title")).isEqualTo("common proverb");
    assertThat(response.get(1).get("name")).isEqualTo("teawoongna");
    assertThat(response.get(1).size()).isEqualTo(4);
  }

  @Test
  @DisplayName("Q 3-2-9. teawoongna가 작성한 글의 개수 (컬럼명: ContentCount)를 출력하기 위한 SQL을 작성해주세요.")
  public void Query_Test_3_2_9() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_9);

    assertThat(response.size()).isEqualTo(1);
    assertThat(response.get(0).get("ContentCount")).isEqualTo(1L);
  }

  @Test
  @DisplayName("Q 3-2-10. 각 user(컬럼명: name)가 작성한 글의 개수 (컬럼명: ContentCount)를 출력하기 위한 SQL을 작성해주세요.")
  public void Query_Test_3_2_10() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part3.PART3_2_10);

    assertThat(response.size()).isEqualTo(5);
    int count = 0;
    for(HashMap<String, Object> map: response) {
      assertThat(map.size()).isEqualTo(2);
      if(map.get("ContentCount").toString().equals("1")) count++;
    }
    assertThat(count).isEqualTo(2);
  }
}
