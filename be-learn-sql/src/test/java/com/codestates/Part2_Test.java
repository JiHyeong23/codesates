package com.codestates;

import com.codestates.lib.FactoryService;
import com.codestates.lib.Mysql;
import com.codestates.script.part2;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class Part2_Test {
  private static final Mysql mysql = Mysql.getInstance();
  private static final FactoryService factoryService = FactoryService.getInstance();
  private static Connection connection = null;

  @BeforeAll
  public static void init() throws SQLException {
    connection = mysql.getConnection();
    factoryService.init(connection);
    factoryService.migration(connection);
    factoryService.part2_setup();
  }

  @AfterAll
  public static void terminate() throws SQLException {
    mysql.terminate(connection);
  }

  @Test
  @DisplayName("Q 2-1. user 테이블에 존재하는 모든 컬럼을 포함한 모든 데이터를 확인하기 위한 SQL을 작성해주세요.")
  public void Query_Test_2_1() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection, part2.PART2_1);
    ArrayList<HashMap<String,Object>> factoryResponse = factoryService.find(connection, "user", "*");

    assertThat(response.size()).isEqualTo(4);
    assertThat(response).usingRecursiveComparison().isEqualTo(factoryResponse);
  }

  @Test
  @DisplayName("Q 2-2. user 테이블에 존재하는 모든 데이터에서 name 컬럼만을 확인하기 위한 SQL을 작성해주세요.")
  public void Query_Test_2_2() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection,part2.PART2_2);
    ArrayList<HashMap<String,Object>> factoryResponse = factoryService.find(connection, "user", "name");

    assertThat(response.size()).isEqualTo(4);
    assertThat(response).usingRecursiveComparison().isEqualTo(factoryResponse);
  }

  @Test
  @DisplayName("Q 2-3. user 테이블에 데이터를 추가하기 위한 SQL을 작성해주세요.")
  public void Query_Test_2_3() throws SQLException {
    mysql.query(connection,part2.PART2_3);
    ArrayList<HashMap<String,Object>> factoryResponse = factoryService.find(connection, "user", "*");

    assertThat(factoryResponse.size()).isEqualTo(5);
  }

  @Test
  @DisplayName("Q 2-4. user 테이블에서 특정 조건을 가진 데이터를 찾기위한 SQL을 작성해주세요.")
  public void Query_Test_2_4() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection,part2.PART2_4);

    System.out.println(response);
    assertThat(response.size()).isEqualTo(1);
    assertThat(response.get(0).get("name")).isEqualTo("luckykim");
  }

  @Test
  @DisplayName("Q 2-5. user 테이블에서 특정 조건을 가진 데이터를 찾기위한 SQL을 작성해주세요.")
  public void Query_Test_2_5() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection,part2.PART2_5);

    assertThat(response.size()).isBetween(3, 4);
    for(HashMap<String, Object> data : response) {
      assertThat(data.get("name")).isNotEqualTo("luckykim");
    }
  }

  @Test
  @DisplayName("Q 2-6. content 테이블에 존재하는 모든 데이터에서 title 컬럼만을 찾기 위한 SQL을 작성해주세요.")
  public void Query_Test_2_6() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection,part2.PART2_6);
    ArrayList<HashMap<String,Object>> factoryResponse = factoryService.find(connection, "content", "title");

    assertThat(factoryResponse.size()).isEqualTo(3);
    assertThat(response).usingRecursiveComparison().isEqualTo(factoryResponse);
  }

  @Test
  @DisplayName("Q 2-7. content의 title과 그 컨텐츠를 작성한 user의 name을 찾기 위한 SQL을 작성해주세요. - 저자가 없더라도, 켄턴츠의 title을 모두 찾아야합니다.")
  public void Query_Test_2_7() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection,part2.PART2_7);

    assertThat(response.size()).isEqualTo(3);
    int count = 0;
    for(HashMap<String, Object> data : response) {
      if(data.get("name") == null) continue;
      if(data.get("name").equals("luckykim")) count++;
    }

    assertThat(count).isEqualTo(1);
  }

  @Test
  @DisplayName("Q 2-8. content의 title과 그 컨텐츠를 작성한 user의 name을 찾기 위한 SQL을 작성해주세요. - 저자가 있는 컨텐츠의 title만 찾아야합니다.")
  public void Query_Test_2_8() throws SQLException {
    ArrayList<HashMap<String,Object>> response = mysql.selectQuery(connection,part2.PART2_8);

    assertThat(response.size()).isEqualTo(1);
    assertThat(response.get(0).get("title")).isEqualTo("database homework");
    assertThat(response.get(0).get("name")).isEqualTo("luckykim");
  }

  @Test
  @DisplayName("Q 2-9. content의 데이터를 수정하기 위한 SQL을 작성해주세요. - title이 database homework인 content 데이터에서 body를 database is very easy로 수정해야합니다.")
  public void Query_Test_2_9() throws SQLException {
    mysql.query(connection,part2.PART2_9);
    ArrayList<HashMap<String,Object>> factoryResponse = factoryService.find(connection, "content", "*");

    assertThat(factoryResponse.size()).isEqualTo(3);

    for(HashMap<String, Object> data : factoryResponse) {
      assertThat(data.get("body")).isNotEqualTo("database is easy");
    }
  }

  @Test
  @DisplayName("Q 2-10. content의 데이터를 추가하기 위한 SQL을 작성해주세요. - luckykim이 작성한 컨텐츠를 추가해주세요. 제목과 본문은 자유입니다. (참고: luckykim의 아이디는 1입니다.)")
  public void Query_Test_2_10() throws SQLException {
    mysql.query(connection,part2.PART2_10);
    ArrayList<HashMap<String,Object>> factoryResponse = factoryService.find(connection, "content", "*");

    assertThat(factoryResponse.size()).isEqualTo(4);
    int count = 0;
    for(HashMap<String, Object> data : factoryResponse) {
      if(data.get("userId") == null) continue;
      int userId = (int)data.get("userId");
      if(userId == 1) count++;
    }
    assertThat(count).isEqualTo(2);
  }
}
