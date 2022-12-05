package com.codestates.script;

public class part2 {
  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-1. user 테이블에 존재하는 모든 컬럼을 포함한 모든 데이터를 확인하기 위한 SQL을 작성해주세요.
  */
  public static final String PART2_1 = "SELECT * FROM user;";

  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-2. user 테이블에 존재하는 모든 데이터에서 name 컬럼만을 확인하기 위한 SQL을 작성해주세요.
  */
  public static final String PART2_2 = "SELECT name FROM user;";

  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-3. user 테이블에 데이터를 추가하기 위한 SQL을 작성해주세요.
          - 원하는 name, email을 사용하시면 됩니다.
  */
  public static final String PART2_3 = "INSERT INTO user(name, email) VALUE('lib32','lib32@naver.com');";

  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-4. user 테이블에서 특정 조건을 가진 데이터를 찾기위한 SQL을 작성해주세요.
          - 조건 : name이 luckykim이여야 합니다.
  */
  public static final String PART2_4 = "SELECT * FROM user WHERE name = 'luckykim';";

  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-5. user 테이블에서 특정 조건을 가진 데이터를 찾기위한 SQL을 작성해주세요.
          - 조건 : name이 luckykim이 아니여야 합니다.
  */
  public static final String PART2_5 = "SELECT * FROM user WHERE name NOT IN ('luckykim');";

  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-6. content 테이블에 존재하는 모든 데이터에서 title 컬럼만을 찾기 위한 SQL을 작성해주세요.
  */

  public static final String PART2_6 = "SELECT title FROM content;";

  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-7. content의 title과 그 컨텐츠를 작성한 user의 name을 찾기 위한 SQL을 작성해주세요.
          - 저자가 없더라도, 켄턴츠의 title을 모두 찾아야합니다.
  */
  public static final String PART2_7 = "SELECT content.title, user.name FROM content LEFT OUTER JOIN user ON content.userid = user.id;";

  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-8. content의 title과 그 컨텐츠를 작성한 user의 name을 찾기 위한 SQL을 작성해주세요.
          - 저자가 있는 컨텐츠의 title만 찾아야합니다.
  */
  public static final String PART2_8 = "SELECT content.title, user.name FROM content, user WHERE content.userid = user.id;";

  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-9. content의 데이터를 수정하기 위한 SQL을 작성해주세요.
          - title이 database homework인 content 데이터에서 body를 database is very easy로 수정해야합니다.
  */
  public static final String PART2_9 = "UPDATE content SET body = 'database is very easy' WHERE title = 'database homework';";

  /*
  ----------------------------------------------------------------------------------------------
      TODO: Q 2-10. content의 데이터를 추가하기 위한 SQL을 작성해주세요.
          - luckykim이 작성한 컨텐츠를 추가해주세요. 제목과 본문은 자유입니다. (참고: luckykim의 아이디는 1입니다.)
  */
  public static final String PART2_10 = "INSERT INTO content(title, body, userId) VALUES ('a', 'b', 1);";
}
