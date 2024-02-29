이번 연습문제의 목표는 데이터베이스 연결을 할 수 있고, 주어진 스키마를 구현할 수 있도록 SQL을 작성해야합니다.


## Bare minimum requirement

Part_1, Part_2, Part_3 테스트를 전부 통과하십시오.

## Getting Started

### 1. 본인의 MySQL 정보를 입력해 주세요.
`script/Properties.java` 파일을 확인하고 내 정보를 수정해주세요.

1. `learnSQL`이라는 이름으로 데이터베이스를 만드세요. 앞으로 이 데이터베이스를 사용합니다.
2. `lib/Mysql.java`에서 `Properties.java` 변수가 어떻게 사용되는지 확인해보세요.
5. 아래 질문에 고민해보시고, 페어와 토론해보세요.
   - 왜 `.Properties.java` 파일은 `.gitignore`에 존재할까요?
   - `Properties.java` 파일이 왜 필요했을지 고민해보세요.

#### \[주의\]
다행히도 여러분들이 직접 데이터베이스 연결 부분을 직접 구현할 필요는 없습니다. 현재 연습문제에서 작성해야 할 데이터베이스 연결 부분은 이미 다 구현되어있으며, 이후 Section3에서 학습하시는 내용을 통해 직접 연결하는 부분을 작성하게 될 것입니다. 이 시간에는 해당 부분을 전부 이해려고하기 보다는, `Properties.java` 관련하여 MySQL 정보를 관리하는 방법에 초점을 두고 보시면 됩니다.


### 2. 연습문제 디렉토리

##### `/lib`

* `/lib/FactoryService.java`

   * FactoryService는 여러분들이 데이터베이스에 연결하여, 원하는 SQL을 작성하여 결과를 확인하는 것과 별개로 TEST가 동작할 수 있도록 도와주는 Class 입니다. 구체적으로 해당 연습문제의 동작이 궁금하신 분들만 참고해주시면 됩니다.

* `/lib/Mysql.java`

   * Mysql 서비스에 접속하고 쿼리를 보내는 작업과 자원을 종료하는 동작을 도와주는 Class입니다.

##### `/migrations`

* `/schema.sql`

   * **이번 연습문제의 핵심이자, 꼭 작성해야하는 부분입니다.** 원하는 테이블과 칼럼을 생성할 수 있어야합니다.

##### `/model`

   * FactoryService가 사용하는 별도의 객체입니다. 해당 객체를 사용하여, 테이블을 조작하게됩니다.
   * DTO, DAO에 대한 내용은 Section3에서 학습하게 됩니다. 해당 코드를 이해하지 않아도 됩니다.

##### `/script`

   * 각 파트에 맞게 SQL STATEMENT를 채워넣어주세요. 원하는 것을 확인하고, 그에 맞는 SQL을 작성해야합니다.


##### 그외 디렉토리
`.gitignore` 혹은 `build.gradle` 와 같은 부분은 코드를 보거나 검색을 통해서 이해하실 수 있었으면 합니다.


#### \[참고 1\] 모든 테스트케이스가 실행되기 전, schema.sql 파일이 실행되고, 끝난 후, database가 삭제된 후 새로 생성됩니다.
`/lib/FactoryService.java` 의 setup 메소드를 참고해주세요.


#### \[참고 2\] Schema Visualizer
스키마를 디자인하기 위해 다양한 외부 도구를 사용할 수 있습니다.

- 연필과 펜을 이용한 A4용지 및 화이트 보드
- [DBdiagram](https://dbdiagram.io)
- [WWW SQL Designer](http://ondras.zarovi.cz/sql/demo/)
- [MySQL Workbench](https://www.mysql.com/products/workbench/)

#### \[참고 3\]
현재 테이블의 결과값을 List와 Map을 활용하여 출력하고 있습니다. 실제 Mysql에서 보여주는 결과와 형식이 다를 수 있습니다.

#### \[참고 4\]
part1의 경우 연습문제의 구조를 파악하고, schema.sql을 작성하는데 시간을 많이 보내시게 될 것입니다.

# be-learn-sql
