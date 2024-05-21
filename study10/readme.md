<nav id="quick" style="position:fixed;top:150px;right:100px;width:200px;height:auto;overflow:hidden;z-index:999;background-color:rgba(255,255,255,0.75);">
	<label for="chap10" style="color:black;font-size:28px;">10. Mybatis & Mapper XML</label>
	<input type="radio" name="chap" id="chap10" style="display:none;">
	<ul class="menu">
		<li><a href="#10-1" style="color:black;font-size:20px;text-decoration:none;">10-1. MyBatis 개념</a></li>
		<li><a href="#10-2" style="color:black;font-size:20px;text-decoration:none;">10-2. MyBatis 설정</a></li>
		<li><a href="#10-3" style="color:black;font-size:20px;text-decoration:none;">10-3. MyBatis 기본 문법</a></li>
		<li><a href="#10-4" style="color:black;font-size:20px;text-decoration:none;">10-4. MyBatis 동적 SQL 구현</a></li>
	</ul>	
</nav>

<div id="10"></div>

# 10. Mybatis & Mapper XML

<div id="10-1"></div>

## 10-1. MyBatis 개념

### 10-1-1. MyBatis 란?

- 객체 지향 언어인 자바의 관계형 데이터베이스 프로그래밍을 좀 더 쉽게 할 수 있게 도와 주는 개발 프레임 워크로서 JDBC를 통해 데이터베이스에 엑세스하는 작업을 캡슐화하고 일반 SQL 쿼리, 저장 프로 시저 및 고급 매핑을 지원하며 모든 JDBC 코드 및 매개 변수의 중복작업을 제거 합니다. Mybatis에서는 프로그램에 있는 SQL쿼리들을 한 구성파일에 구성하여 프로그램 코드와 SQL을 분리할 수 있는 장점을 가지고 있습니다.

<br><br>

### 10-1-2. MyBatis 주요 구성 요소

<br>

![MyBatis 기본 구조](../images/mybatis001.png)

<br>

1. 응용 프로그램이 SqlSessionFactoryBuilder를 위해 SqlSessionFactory를 빌드하도록 요청합니다.
2. SqlSessionFactoryBuilder는 SqlSessionFactory를 생성하기 위한 MyBatis 구성 파일을 읽습니다.
3. SqlSessionFactoryBuilder는 MyBatis 구성 파일의 정의에 따라 SqlSessionFactory를 생성합니다.

<br>

| 구성 요소 | 설명 |
|------------------------|---------------------------------------------------------------------------|
| SqlSessionFactoryBuilder | - MyBatis3 구성 파일을 읽고 SqlSessionFactory를 생성하는 구성 요소입니다.<br>- 해당 클래스는 인스턴스화되어 사용되고 던져질 수 있으며, SqlSessionFactory를 생성한 후 유지할 필요가 없다. <br><br> · XML, 어노테이션, 자바 설정을 통해 SqlSessionFactory를 생성할 수 있습니다. <br><br> · SqlSessionFactory build(InputStream inputStream) <br> · SqlSessionFactory build(InputStream inputStream, String environment) <br> · SqlSessionFactory build(InputStream inputStream, Properties properties) <br> · SqlSessionFactory build(InputStream inputStream, String env, Properties props) <br> · SqlSessionFactory build(Configuration config) |
| SqlSessionFactory | SqlSession을 생성하는 구성 요소입니다. <br> SqlSessionFactory는 애플리케이션을 실행하는 동안 존재해야한다. <br> 때문에, SqlSessionFactory의 생명주기를 싱글톤으로 관리하는 것이 좋다. <br> · 오토 커밋 여부, 설정된 DataSource 사용, 적용 모드를 파라미터로 설정할 수 있습니다. <br> · ExecutorType.SIMPLE : 구문 실행마다 새로운 PreparedStatement를 생성합니다. <br> · ExecutorType.REUSE : 생성된 PreparedStatements를 재사용합니다. <br> · ExecutorType.BATCH : update 구문을 일괄 처리합니다. <br><br> · SqlSession openSession() <br> · SqlSession openSession(boolean autoCommit) <br> · SqlSession openSession(Connection connection) <br> · SqlSession openSession(TransactionIsolationLevel level) <br> · SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) <br> · SqlSession openSession(ExecutorType execType) <br> · SqlSession openSession(ExecutorType execType, boolean autoCommit) <br> · SqlSession openSession(ExecutorType execType, Connection connection) |
| SqlSession | SQL 실행 및 트랜잭션 제어를 위한 API를 제공하는 구성 요소입니다. <br> SqlSession 인스턴스는 공유되지 않고, 쓰레드에 안전하지 않다. <br> 때문에, 요청 또는 메소드 스코프로 사용하는 것이 좋다. <br> SqlSession은 static 필드나 클래스의 인스턴스 필드로 지정하지 않고, 요청을 받을때마다 만들고 닫는 것이 중요하다. <br><br> · 데이터를 조작할 수 있는 CRUD 기능을 제공합니다. <br> · 트랜잭션 제어를 할 수 있는 기능을 제공합니다. <br> · JDBC 드라이버 클래스에 저장된 배치 수정구문을 지우는 flush 기능을 제공합니다. <br><br> · <T> T selectOne(String statement) <br> · <E> List<E> selectList(String statement) <br> · <T> Cursor<T> selectCursor(String statement) <br> · <K,V> Map<K,V> selectMap(String statement, String mapKey) <br> · int insert(String statement) <br> · int update(String statement) <br> · int delete(String statement) <br> · void commit() <br> · List<BatchResult> flushStatements() <br> · void rollback() |

<br><br>

### 10-1-3. MyBatis-Spring 컴포넌트 구조

<br>

![MyBatis 컴포넌트 구조](../images/mybatis002.png)

<br>

1. SqlSessionFactoryBean은 SqlSessionFactoryBuilder를 위해 SqlSessionFactory를 빌드하도록 요청합니다.
2. SessionFactoryBuilder는 SqlSessionFactory 생성을 위해 MyBatis 구성 파일을 읽습니다. 
3. SqlSessionFactoryBuilder는 MyBatis 구성 파일의 정의에 따라 SqlSessionFactory를 생성합니다. 따라서 생성된 SqlSessionFactory는 Spring DI 컨테이너에 의해 저장됩니다.
4. MapperFactoryBean은 안전한 SqlSession(SqlSessionTemplate) 및 스레드 안전 매퍼 개체(Mapper 인터페이스의 프록시 객체)를 생성합니 다. 따라서 생성되는 매퍼 객체는 스프링 DI 컨테이너에 의해 저장되며 서비스 클래스 등에 DI가 적용됩니다. 매퍼 개체는 안전한 SqlSession(SqlSessionTemplate)을 사용하여 스레드 안전 구현을 제공합니다.

<br>

| 구성 요소 | 설명 |
|------------------------|---------------------------------------------------------------------------|
| SqlSessionFactoryBean | SqlSessionFactory를 작성하고 Srping DI 컨테이너에 개체를 저장하는 구성 요소입니다. <br> 표준 MyBatis3에서 SqlSessionFactory는 MyBatis 구성 파일에 정의된 정보를 기반으로 합니다. <br> 그러나 SqlSessionFactoryBean을 사용하면 MyBatis 구성 파일이 없어도 SqlSessionFactory를 빌드할 수 있습니다. |
| MapperFactoryBean | Singleton Mapper 개체를 만들고 Spring DI 컨테이너에 개체를 저장하는 구성 요소입니다. <br> MyBatis3 표준 메커니즘에 의해 생성된 매퍼 객체는 스레드가 안전하지 않습니다.따라서 각 스레드에 대한 인스턴스를 할당해야 했습니다. MyBatis-Spring 구성 요소에 의해 생성된 매퍼 개체는 안전한 매퍼 개체를 생성할 수 있습니다. 따라서 서비스 등 싱글톤 구성요소에 DI를 적용할 수 있습니다. |
| SqlSessionTemplate | SqlSession 인터페이스를 구현하는 Singleton 버전의 SqlSession 구성 요소입니다. <br> 쓰레드에 안전하고 여러개의 DAO나 매퍼에서 공유할 수 있습니다. 커밋이나 롤백 등 트랜잭션과 세션의 생명주기를 관리해줍니다. |
| root-context.xml | 데이터베이스의 접속 주소 정보나 Mapping File의 경로 등의 고정된 환경정보를 설정합니다. |
| mybatis-config.xml | MyBatis를 통하여 오고 가는 데이터를 저장할 DTO에 대한 환경설정을 합니다. |
| xxxMapper.xml | MyBatis 명령에 해당하는 xml 태그가 있는 mapper 파일을 작성합니다. |


<br>

![MyBatis 주요 컴포넌트](../images/mybatis003.png)

- MyBatis는 개발자가 지정한 SQL, 저장프로시저, 몇가지 고급 매핑을 지원하는 퍼시스턴스 프레임워크
- JDBC로 처리하는 상당부분의 코드와 파라미터 설정 및 결과 매핑을 대신 처리해줌.


<br><br><br>

<div id="10-2"></div>

## 10-2. MyBatis 설정

<br><br>

### 10-2-1. MyBatis 라이브러리 의존성 등록

- MyBatis를 사용하기 위해 메이븐에 의존성 추가
- mybatis-x.x.x.jar 파일을 클래스패스에 두어 사용할 수도 있음

**pom.xml 파일에 MyBatis 내용 추가**

```xml
	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis</artifactId>
		<version>3.4.0</version>
	</dependency>

	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis-spring</artifactId>
		<version>1.3.2</version>
	</dependency>
```

<br><br>

### 10-2-2. MyBatis 의존성 추가 및 주입

- 마이바티스의 핵심이 되는 설정은 트랜잭션을 제어하기 위한 TransactionManager과 함께 데이터베이스 Connection 인스턴스를 가져오기 위한 DataSource를 포함합니다.
- 연동 DB 정보, mapper 위치 설정
- SQL을 수행하는 SqlSession 객체 생성 및 주입
- 트랜잭션 및 로깅 수행 설정과 주입


**src/main/webapp/WEB-INF/spring/root-context.xml 파일 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:jdbc="http://www.springframework.org/schema/jdbc"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-4.3.xsd
		http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring-1.2.xsd
		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">
	
	<!-- Root Context: defines shared resources visible to all other web components -->
	<!-- 데이터베이스 설정 -->
	<!-- spring-jdbc-5.0.8.RELEASE.jar 안의 드라이버매니저 연결 -->
	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
	<!-- 데이터 소스 및 드라이버 설정 : log4jdbc-log4j2-jdbc4-1.16.jar -->
		<property name="driverClassName" value="net.sf.log4jdbc.sql.jdbcapi.DriverSpy"></property>
	<!-- 연결 url, 사용자 아이디, 비밀번호 설정  -->
		<property name="url" value="jdbc:log4jdbc:oracle:thin:@localhost:1521:xe" />
		<property name="username" value="system" />
		<property name="password" value="1234"></property>
	</bean>
	<!-- sql을 대신할 my-batis 설정 : mybatis-spring-1.3.2.jar의 세션팩토리빈클래스 연결 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<!-- mybatis 설정파일 등록-->
		<property name="configLocation" value="classpath:/mybatis-config.xml"></property>
		<!-- sql처럼 데이터베이스와 자바 클래스를 데이터 연관을 지어줄 파일 위치와 이름 지정 -->
		<property name="mapperLocations" value="classpath:mappers/**/*Mapper.xml"></property>
	</bean>	
	<!-- SqlSession 객체 주입 -->
	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate" destroy-method="clearCache">
		<constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"></constructor-arg>
	</bean>
	
	<!-- 트랜잭션 및 DB 패키지 방안 및 각 종 로깅과 보안 설정 -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource" />
	</bean>
		
	<!-- @Transactional 어노테이션 처리 -->
	<tx:annotation-driven transaction-manager="transactionManager" />	
</beans>
```

<br><br>

### 10-2-3. MyBatis 설정

#### 10-2-3-1. package 로 설정

**src/main/resources/mybatis-config.xml 파일 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC 
"-//mybatis.org//DTD Config 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases>
		<package name="com.spring1" />
	</typeAliases>
</configuration>
```

<br><br>

#### 10-2-3-2. typeAlias 로 설정

- alias(별칭)이 중복되어서는 안되며, 사용자가 생성한 DTO나 VO 클래스로 하거나 아래에서 제시한 타입은 기본으로 제공하고 있어서 보통은 기본 타입이나 자바 타입의 경우는 설정하지 않는다.

| 별칭 | 매핑된 타입 |
|-----------------------------------|----------------------------------------|
| _byte	| byte |
| _char (since 3.5.10)	| char |
| _character (since 3.5.10)	| char |
| _long	| long |
| _short | short |
| _int | int |
| _integer | int |
| _double | double |
| _float | float |
| _boolean | boolean |
| string | String |
| byte | Byte |
| char (since 3.5.10) | Character |
| character (since 3.5.10) | Character |
| long | Long |
| short | Short |
| int | Integer |
| integer | Integer |
| double | Double |
| float | Float |
| boolean | Boolean |
| date | Date |
| decimal | BigDecimal |
| bigdecimal | BigDecimal |
| biginteger | BigInteger |
| object | Object |
| date[] | Date[] |
| decimal[] | BigDecimal[] |
| bigdecimal[] | BigDecimal[] |
| biginteger[]	BigInteger[] |
| object[] | Object[] |
| map | Map |
| hashmap | HashMap |
| list | List |
| arraylist | ArrayList |
| collection | Collection |
| iterator | Iterator |

<br>

**src/main/resources/mybatis-config.xml 파일 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC 
"-//mybatis.org//DTD Config 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases>
		<typeAlias type="com.spring1.dto.Board" alias="Board" />
		<typeAlias type="com.spring1.dto.Custom" alias="Custom" />
		<typeAlias type="com.spring1.dto.Sample" alias="Sample" />
	</typeAliases>
</configuration>
```

<br><br>

### 10-2.4. Mapper 파일 생성

**src/main/resources/mappers/boardMapper.xml 파일 생성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC 
"-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="board">

</mapper>
```

<br><br><br>

<div id="10-3"></div>

## 10-3. MyBatis 기본 문법

[MyBatis 메뉴얼](https://mybatis.org/mybatis-3/ko/getting-started.html)

<br><br>

### 10-3-0. 기본 태그

| 기본 태그 | 설명 |
|--------------|------------------------------------------------------------------------|
| cache | 해당 네임스페이스을 위한 캐시 설정 |
| cache-ref | 다른 네임스페이스의 캐시 설정에 대한 참조 |
| resultMap | 데이터베이스 결과데이터를 객체에 로드하는 방법을 정의하는 엘리먼트 |
| include | sql 태그로 정의된 부분을 해당 태그에 삽입할 경우 사용하는 엘리먼트 |
| sql | 다른 구문에서 재사용하기 위한 SQL 조각을 정의하는 엘리먼트 |
| insert | sql의 INSERT 구문을 매핑하는 엘리먼트 |
| update | sql의 UPDATE 구문을 매핑하는 엘리먼트 |
| delete | sql의 DELETE 구문을 매핑하는 엘리먼트 |
| select | sql의 SELECT 구문을 매핑하는 엘리먼트 |

<br><br>

**select 태그 구문에 적용할 수 있는 속성**

| 속성 | 설명 |
|-------------------|--------------------------------------------------------------------------|
| id | 구문을 찾기 위해 사용될 수 있는 네임스페이스내 유일한 구분자로서 절대 중복되서는 안됨 |
| parameterType | 구문에 전달될 파라미터의 패키지 경로를 포함한 전체 클래스명이나 별칭 |
| resultType | 구문에 의해 리턴되는 반환타입의 패키지 경로를 포함한 전체 클래스명이나 별칭. <br> collection인 경우 collection 타입 자체가 아닌 collection 이 포함된 타입이 될 수 있음. <br> resultType이나 resultMap을 사용함 |
| resultMap | 외부 resultMap 의 참조명. 결과맵은 마이바티스의 가장 강력한 기능 <br> resultType이나 resultMap을 사용함 |
| flushCache | true 로 셋팅하면 구문이 호출될때마다 로컬, 2nd 레벨 캐시가 지워질것이다(flush). 디폴트는 false임 |
| useCache | true 로 셋팅하면 구문의 결과가 2nd 레벨 캐시에 캐시 될 것이며, 디폴트는 true임
timeout	예외가 던져지기 전에 데이터베이스의 요청 결과를 기다리는 최대시간을 설정함. 디폴트는 셋팅하지 않는 것이고 드라이버에 따라 다소 지원되지 않을 수 있음 |
| fetchSize | 지정된 수만큼의 결과를 리턴하도록 하는 드라이버 힌트 형태의 값이며, 디폴트는 셋팅하지 않는 것이고 드라이버에 따라 다소 지원되지 않을 수 있음 |
| statementType | STATEMENT, PREPARED 또는 CALLABLE 중 하나를 선택할 수 있으며, 마이바티스에게 Statement, PreparedStatement 또는 CallableStatement를 사용하게 함. 디폴트는 PREPARED임 |
| resultSetType | FORWARD_ONLY, SCROLL_SENSITIVE, SCROLL_INSENSITIVE, DEFAULT(same as unset)중 하나를 선택할 수 있으며, 디폴트는 셋팅하지 않는 것이고 드라이버에 다라 다소 지원되지 않을 수 있음 |
| databaseId | 설정된 databaseIdProvider가 있는 경우 마이바티스는 databaseId 속성이 없는 모든 구문을 로드하거나 일치하는 databaseId와 함께 로드될 것임. 같은 구문에서 databaseId가 있거나 없는 경우 모두 있다면 뒤에 나온 것이 무시됨. |
| resultOrdered | 이 설정은 내포된 결과를 조회하는 구문에서만 적용이 가능하며, true로 설정하면 내포된 결과를 가져오거나 새로운 주요 결과 레코드를 리턴할때 함께 가져오도록 함. 이전의 결과 레코드에 대한 참조는 더 이상 발생하지 않음. 이 설정은 내포된 결과를 처리할때 조금 더 많은 메모리를 채우며, 디폴트값은 false 임 |
| resultSets | 여러 결과 세트에만 적용 가능합니다. 명령문에 의해 반환될 결과 집합을 나열하고 각 결과 집합에 이름을 지정하며, 이름은 쉼표로 구분됨 |
| affectData | 트랜잭션이 올바르게 제어되도록 데이터를 반환하는 INSERT, UPDATE 또는 DELETE 문을 작성할 때 이를 true로 설정하며, 기본값: false (since 3.5.12) |

<br><br>

**insert/update/delete 태그 구문에 적용할 수 있는 속성**

| 속성 | 설명 |
|-------------------|--------------------------------------------------------------------------|
| id | 구문을 찾기 위해 사용될 수 있는 네임스페이스내 유일한 구분자로서 절대 중복되서는 안됨 |
| parameterType | 구문에 전달될 파라미터의 패키지 경로를 포함한 전체 클래스명이나 별칭 |
| flushCache | true 로 셋팅하면 구문이 호출될때마다 로컬, 2nd 레벨 캐시가 지워질것이다(flush). 디폴트는 false임 |
| statementType | STATEMENT, PREPARED 또는 CALLABLE 중 하나를 선택할 수 있으며, 마이바티스에게 Statement, PreparedStatement 또는 CallableStatement를 사용하게 함. 디폴트는 PREPARED임 |
| timeout | 예외가 던져지기 전에 데이터베이스의 요청 결과를 기다리는 최대시간을 설정하며, 디폴트는 셋팅하지 않는 것이고 드라이버에 따라 다소 지원되지 않을 수 있음 |
| statementType | STATEMENT, PREPARED 또는 CALLABLE중 하나를 선택할 수 있으며, 마이바티스에게 Statement, PreparedStatement 또는 CallableStatement를 사용하게 함. 디폴트는 PREPARED 임 |
| useGeneratedKeys | insert, update에만 적용되며, 데이터베이스에서 내부적으로 생성한 키 (예를들어 MySQL또는 SQL Server와 같은 RDBMS의 자동 증가 필드)를 받는 JDBC getGeneratedKeys메소드를 사용하도록 설정하며, 디폴트는 false 임 |
| keyProperty | insert, update에만 적용되며, getGeneratedKeys 메소드나 insert 구문의 selectKey 하위 엘리먼트에 의해 리턴된 키를 셋팅할 프로퍼티를 지정. 디폴트는 셋팅하지 않는 것으로 여러개의 칼럼을 사용한다면 프로퍼티명에 콤마를 구분자로 나열할수 있음 |
| keyColumn | insert, update에만 적용되며, 생성키를 가진 테이블의 칼럼명을 셋팅. 키 칼럼이 테이블이 첫번째 칼럼이 아닌 데이터베이스(PostgreSQL 처럼)에서만 필요함. 여러 개의 칼럼을 사용한다면 프로퍼티명에 콤마를 구분자로 나열할수 있음 |
| databaseId | 설정된 databaseIdProvider가 있는 경우 마이바티스는 databaseId 속성이 없는 모든 구문을 로드하거나 일치하는 databaseId와 함께 로드될 것이며, 같은 구문에서 databaseId가 있거나 없는 경우 모두 있다면 뒤에 나온 것이 무시됨 |

<br><br>

**selectKey 태그 구문에 적용할 수 있는 속성**

| 속성 | 설명 |
|-------------------|--------------------------------------------------------------------------|
| keyProperty | selectKey구문의 결과가 셋팅될 대상 프로퍼티 |
| keyColumn | 리턴되는 결과셋의 칼럼명은 프로퍼티에 일치하며, 여러 개의 칼럼을 사용한다면 칼럼명의 목록은 콤마를 사용해서 구분 |
| resultType | 반환(결과)의 타입. 마이바티스는 이 기능을 제거할 수 있지만 추가하는게 문제가 되지는 않으며, 마이바티스는 String을 포함하여 키로 사용될 수 있는 간단한 타입을 허용 |
| order	| BEFORE 또는 AFTER를 셋팅할 수 있으며, BEFORE로 설정하면 키를 먼저 조회하고 그 값을 keyProperty 에 셋팅한 뒤 insert 구문을 실행하고, AFTER로 설정하면 insert 구문을 실행한 뒤 selectKey 구문을 실행함. 오라클과 같은 데이터베이스에서는 insert구문 내부에서 일관된 호출형태로 처리 |
| statementType	| 마이바티스는 Statement, PreparedStatement 그리고 CallableStatement을 매핑하기 위해 STATEMENT, PREPARED 그리고 CALLABLE 구문타입을 지원 |

<br><br>

**resultMap 태그 구문**

- resultMap엘리먼트는 많은 하위 엘리먼트를 가지며, 엘리먼트의 개념적인 뷰(conceptual view) 를 설정할 때 사용합니다.

| 속성 | 종류 | 설명 |
|-------------------|------------------|--------------------------------------------------------------------------|
| constructor  | 하위 엘리먼트 | 인스턴스화되는 클래스의 생성자에 결과를 삽입하기 위해 사용됨 <br> idArg - ID 인자. ID 와 같은 결과는 전반적으로 성능을 향상시킴 <br> arg - 생성자에 삽입되는 일반적인 결과 |
| result | 하위 엘리먼트 | 필드나 자바빈 프로퍼티에 삽입되는 일반적인 결과 |
| association | 하위 엘리먼트 | 복잡한 타입의 연관관계로서 많은 결과는 타입으로 나타냄 <br> 중첩된 결과 매핑 – resultMap 스스로의 연관관계 |
| collection | 하위 엘리먼트 | 복잡한 타입의 컬렉션 <br> 중첩된 결과 매핑 – resultMap 스스로의 연관관계
| discriminator | 하위 엘리먼트 | 사용할 resultMap 을 판단하기 위한 결과값을 사용 |
| case | 하위 엘리먼트 | 몇 가지 값에 기초한 결과 매핑 <br> 중첩된 결과 매핑 – 이 경우 또한 결과매핑 자체이고 이러한 동일한 엘리먼트를 많이 포함하거나 외부 resultMap을 참조할 수 있음 |
| id | 속성 | 결과매핑을 참조하기 위해 사용할 수 있는 값으로 네임스페이스에서 유일한 식별자 |
| type | 속성 | 패키지를 포함한 자바 클래스명이나 타입별칭 |
| autoMapping | 속성 | 마이바티스는 결과매핑을 자동매핑으로 처리할지 말지를 처리하며, 이 속성은 autoMappingBehavior 라는 전역설정을 덮으며, 디폴트는 unset임 |

<br><br>

### 10-3-1. 레코드 검색

```xml
<select id="selectPerson" parameterType="int" resultType="hashmap">
  SELECT * FROM PERSON WHERE ID = #{id}
</select>
```

<br><br>

### 10-3-2. 레코드 추가

```xml
<insert id="insertAuthor">
  insert into Author (id,username,password,email,bio)
  values (#{id},#{username},#{password},#{email},#{bio})
</insert>
<insert id="insertAuthor2" useGeneratedKeys="true" keyProperty="id">
  insert into Author (username,password,email,bio)
  values (#{username},#{password},#{email},#{bio})
</insert>
<insert id="insertAuthor3" useGeneratedKeys="true" keyProperty="id">
  insert into Author (username, password, email, bio) values
  <foreach item="item" collection="list" separator=",">
    (#{item.username}, #{item.password}, #{item.email}, #{item.bio})
  </foreach>
</insert>
<insert id="insertAuthor4">
  <selectKey keyProperty="id" resultType="int" order="BEFORE">
    select CAST(RANDOM()*1000000 as INTEGER) a from SYSIBM.SYSDUMMY1
  </selectKey>
  insert into Author
    (id, username, password, email,bio, favourite_section)
  values
    (#{id}, #{username}, #{password}, #{email}, #{bio}, #{favouriteSection,jdbcType=VARCHAR})
</insert>
```

<br><br>

### 10-3-3. 레코드 변경

```xml
<update id="updateAuthor">
  update Author set
    username = #{username},
    password = #{password},
    email = #{email},
    bio = #{bio}
  where id = #{id}
</update>
```

<br><br>

### 10-3-4. 레코드 삭제

```xml
<delete id="deleteAuthor">
  delete from Author where id = #{id}
</delete>
```

<br><br>

### 10-3-5. 중복 구문을 위한 sql과 include와 property 태그

**sql/include/propert 기본 문법**

```xml
<sql id="아이디">
	컬럼명1 [, 컬럼명2,....컬럼명n]
</sql>

<include refid="sql구문의 id" />

<property name="변수명" value="값"/>
```



<br>

**sql/include/propert 사용 예시**

```xml
<sql id="sometable">
  ${prefix}Table
</sql>

<sql id="someinclude">
  from
    <include refid="${include_target}"/>
</sql>

<select id="select" resultType="map">
  select
    field1, field2, field3
  <include refid="someinclude">
    <property name="prefix" value="Some"/>
    <property name="include_target" value="sometable"/>
  </include>
</select>
```

<br><br>

### 10-3-6. selectKey 의 사용

```xml
  <selectKey keyProperty="id" resultType="int" order="BEFORE">
    select CAST(RANDOM()*1000000 as INTEGER) a from SYSIBM.SYSDUMMY1
  </selectKey>
```

<br><br>

### 10-3-7. resultMap의 사용

```xml
  <resultMap type="org.apache.ibatis.submitted.rounding.User" id="usermap">
    <id column="id" property="id"/>
    <result column="name" property="name"/>
    <result column="funkyNumber" property="funkyNumber"/>
    <result column="roundingMode" property="roundingMode"/>
  </resultMap>

  <select id="getUser" resultMap="usermap">
    select * from users
  </select>
  <insert id="insert">
      insert into users (id, name, funkyNumber, roundingMode) values (
        #{id}, #{name}, #{funkyNumber}, #{roundingMode}
      )
  </insert>
```

```xml
<resultMap id="detailedBlogResultMap" type="Blog">
  <constructor>
    <idArg column="blog_id" javaType="int"/>
  </constructor>
  <result property="title" column="blog_title"/>
  <association property="author" javaType="Author">
    <id property="id" column="author_id"/>
    <result property="username" column="author_username"/>
    <result property="password" column="author_password"/>
    <result property="email" column="author_email"/>
    <result property="bio" column="author_bio"/>
    <result property="favouriteSection" column="author_favourite_section"/>
  </association>
  <collection property="posts" ofType="Post">
    <id property="id" column="post_id"/>
    <result property="subject" column="post_subject"/>
    <association property="author" javaType="Author"/>
    <collection property="comments" ofType="Comment">
      <id property="id" column="comment_id"/>
    </collection>
    <collection property="tags" ofType="Tag" >
      <id property="id" column="tag_id"/>
    </collection>
    <discriminator javaType="int" column="draft">
      <case value="1" resultType="DraftPost"/>
    </discriminator>
  </collection>
</resultMap>

<resultMap id="blogResult" type="Blog">
  <association property="author" column="author_id" javaType="Author" select="selectAuthor"/>
</resultMap>
<select id="selectBlog" resultMap="blogResult">
  SELECT * FROM BLOG WHERE ID = #{id}
</select>
<select id="selectAuthor" resultType="Author">
  SELECT * FROM AUTHOR WHERE ID = #{id}
</select>
```

```xml
<resultMap id="blogExample" type="Blog">
	<!-- id, result 하위 엘리먼트 -->
	<id property="id" column="post_id"/>
	<result property="subject" column="post_subject"/>
	<!-- constructor 하위 엘리먼트 -->
	<constructor>
	<idArg column="id" javaType="int" name="id" />
	<arg column="age" javaType="_int" name="age" />
	<arg column="username" javaType="String" name="username" />
	</constructor>
	<!-- association 하위 엘리먼트 -->
	<association property="author" column="blog_author_id" javaType="Author">
		<id property="id" column="author_id"/>
		<result property="username" column="author_username"/>
	</association>
	<!-- collection 하위 엘리먼트 -->
	<collection property="posts" ofType="domain.blog.Post">
		<id property="id" column="post_id"/>
		<result property="subject" column="post_subject"/>
		<result property="body" column="post_body"/>
	</collection>
</resultMap>

<resultMap id="blogResult" type="Blog">
  <id property="id" column="blog_id" />
  <result property="title" column="blog_title"/>
  <association property="author" column="blog_author_id" javaType="Author" resultMap="authorResult"/>
</resultMap>

<resultMap id="authorResult" type="Author">
  <id property="id" column="author_id"/>
  <result property="username" column="author_username"/>
  <result property="password" column="author_password"/>
  <result property="email" column="author_email"/>
  <result property="bio" column="author_bio"/>
</resultMap>

<resultMap id="blogResult" type="Blog">
  <id property="id" column="blog_id" />
  <result property="title" column="blog_title"/>
  <association property="author" javaType="Author">
    <id property="id" column="author_id"/>
    <result property="username" column="author_username"/>
    <result property="password" column="author_password"/>
    <result property="email" column="author_email"/>
    <result property="bio" column="author_bio"/>
  </association>
</resultMap>
```

<br><br><br>

<div id="10-4"></div>

## 10-4. MyBatis 동적 SQL 구현

| 태그 | 설명 |
|------------|------------------------------------------------------------------------------|
| if | 조건에 따라 여러 가지 방식의 문장을 실행할 수 있습니다. |
| choose, when, otherwise | 다단계의 조건으로 그 조건에 따라 해당 문장을 실행할 수 있습니다. |
| foreach | 배열 객체가 파라미터로 넘어올 경우 반복하여 처리할 수 있습니다. |
| where | · <where> 내부에는 조건을 표현할 수 있는 <if>나 <choose>가 사용될 수 있습니다. <br> · <where> 내부 코드가 추가되는 경우 동적으로 WHERE 키워드를 붙이고, 가장 앞에 해당되는 "AND"나 "OR"를 지워줍니다. |
| set | 동적으로 SET 키워드를 붙이고 필요없는 콤마를 제거합니다. |
| trim | · 접두사, 접미사를 추가하거나 오버라이딩하여 커스텀하게 동적 쿼리를 만들 수 있습니다.<br> · prefix : 실행될 쿼리의 가장 앞에 문자를 추가합니다. <br> · prefixOverrides : 실행될 쿼리의 가장 앞에 해당하는 문자가 있는 경우 지워줍니다. <br> · suffix : 실행될 쿼리의 가장 뒤에 문자를 추가합니다. <br> · suffixOverrides : 실행될 쿼리의 가장 뒤에 해당하는 문자가 있는 경우 지워줍니다. |
| selectKey | MyBatis 쿼리문을 실행하기전 <selectKey> 태그안에 쿼리를 먼저 수행하여 해당 조회된 결과를 파라미터로 사용할 수 있습니다. |
| bind | 엘리먼트 내부에서 해당 변수의 값이나 패턴을 바인딩시킬 때 사용합니다. |

<br><br>

### 10-4-1. 조건문 태그(if, choose, when, otherwise)

**if 기본 문법**

```xml
<if test="조건">
    조건이 참일 때 추가될 문장
</if>
```

<br>

**if 사용 예**

```xml
<!-- 학생 ID로 학생 정보 조회 -->
<select id="getStudentInfo" parameterType="String" resultType="hashMap">
    SELECT *
    FROM STUDENT
    WHERE USE_YN = 'Y'
    <if test='studentId != null and studentId != ""'>
        AND STUDENT_ID = #{studentId}
    </if> 
</select>
```

<br>

**choose, when, otherwise 기본 문법**

```xml
<choose>
    <when test="조건1">
        조건1이 참일 때 추가될 문장
    </when>
    <when test="조건2">
        조건1이 거짓이고, 조건2가 참일 때 추가될 문장
    </when>
    <otherwise>
        조건1, 조건2 모두 거짓일 때 실행할 문장
    </otherwise>
</choose>
```

<br>

**choose, when, otherwise 사용 예시**

```xml
<!-- 검색 기준에 따른 값으로 게시판 정보 조회 -->
<select id="getStudentInfo" parameterType="hashMap" resultType="hashMap">
    SELECT *
    FROM BOARD
    WHERE USE_YN = 'Y'
    <choose>
        <when test='"writer".equals(searchType)'>
            AND WRITER = #{searchValue}
        </when>
        <when test='"content".equals(searchType)'>
            AND CONTENT = #{searchValue}
        </when>
        <otherwise>
            AND TITLE = #{searchValue}
        </otherwise>
    </choose>
</select>
```

<br><br>

### 10-4-2. 반복문 태그(foreach)

**foreach 사용 가능한 속성**

| 속성 | 설명 |
|----------------|------------------------------------------------------------------------|
| collection | 컬렉션 파라미터(Map, List, Set, 배열, .. 등 반복가능한 객체) |
| item | 현재 반복되는 값을 저장할 변수 |
| index | 현재 반복되는 인덱스 값을 저장할 변수 |
| open | 쿼리가 실행될 때 앞에 추가될 접두사(시작문자열) |
| close | 쿼리가 실행될 때 끝에 추가될 접미사(종료문자열) |
| separator | 컬렉션이 반복될 때 추가될 문자(구분자 문자열) |

<br>

**foreach 기본 문법**

```xml
<foreach collection="컬렉션 변수" item="항목" index="인덱스" open="시작문자열" close="종료문자열" separator="구분자">
    #{item}
</foreach>
```

<br>

**foreach 사용 예시**

```xml
<!-- List<HashMap<String, Object> params = new ArrayList<HashMap<String, Object>();
 HashMap<String, Object>에는 studentId 키값으로 존재 -->
<select id="getStudentInfoList" parameterType="hashMap" resultType="hashMap">
    SELECT *
    FROM STUDENT
    WHERE STUDENT_ID IN
    <foreach collection="params" item="item" open="(" separator="," close=")">
        #{item.studentId}
    </foreach>
</select>

<!-- List<String> params = new ArrayList<String>(); -->
<select id="getStudentInfoList" parameterType="hashMap" resultType="hashMap">
    SELECT *
    FROM STUDENT
    WHERE STUDENT_ID IN
    <foreach collection="params" item="item" open="(" separator="," close=")">
        #{item}
    </foreach>
</select>
```

<br><br>

### 10-4-3. 기타 태그(where, set, trim, bind, selectKey)

**where 기본 문법**

```xml
<WHERE>
    <if test="조건">
        AND 컬럼명 = #{변수}
    </if>
    <if test="조건">
        AND 컬럼명 = #{변수}
    </if>
</WHERE>
```

<br>

**where 예시 코드**

```xml
<select id="getStudentInfo" parameterType="hashMap" resultType="hashMap">
    SELECT *
    FROM BOARD
    <where>
        <if test='title != null and title != ""'>
            AND TITLE = #{title}
        </if>
        <if test='writer != null and writer != ""'>
            AND WRITER = #{writer}
        </if>
        <if test='reportingDate != null and reportingDate != ""'>
            AND REPORTING_DATE = #{reportingDate}
        </if>
    </where>
</select>
```

<br><br>

<br>

**set 기본 문법**

```xml
<set>
    <if test="조건">
        컬럼명 = #{변수},
    </if>
    <if test="조건">
        컬럼명 = #{변수},
    </if>
</set>
```

<br>

**set 사용 예시**

```xml
<update id="updateStudentInfo">
    UPDATE
        STUDENT
    <set>
        <if test='age != null age != ""'>
            AGE = #{age},
        </if>
        <if test='name != null name != ""'>
            NAME = #{name},
        </if>
        <if test='phoneNumber != null phoneNumber != ""'>
            PHONE_NUMBER = #{phoneNumber},
        </if>
    </set>
</update>
```

<br>

**trim 기본 문법**

```xml
<trim prefix="문자열" prefixOverrides="문자열" suffix="문자열" suffixOverrides="문자열">
   실행될 쿼리
</trim>
```

<br>

**trim 사용 예시**

```xml
<select id="getStudentInfo" parameterType="hashMap" resultType="hashMap">
    SELECT *
    FROM BOARD
    <trim prefix="WHERE" prefixOverrides="AND|OR">
        <if test='title != null and title != ""'>
            AND TITLE = #{title}
        </if>
        <if test='writer != null and writer != ""'>
            AND WRITER = #{writer}
        </if>
        <if test='reportingDate != null and reportingDate != ""'>
            AND REPORTING_DATE = #{reportingDate}
        </if>
    </trim>
</select>
<update id="updateStudentInfo">
    UPDATE
        STUDENT
    <trim prefix="SET" prefixOverrides="," suffixOverrides=",">
        <if test='age != null age != ""'>
            AGE = #{age},
        </if>
        <if test='name != null name != ""'>
            NAME = #{name},
        </if>
        <if test='phoneNumber != null phoneNumber != ""'>
            PHONE_NUMBER = #{phoneNumber},
        </if>
    </trim>
</update>
```

**selectKey 의 속성**

| 속성 | 설명 |
|--------------|----------------------------------------------------------------------|
| resultType | 결과의 타입 |
| keyProperty | selectKey 구문의 결과가 세팅될 대상 프로퍼티
| keyColumn | 리턴되는 결과셋의 컬럼명은 프로퍼티에 일치, 여러개의 컬럼을 사용하는 경우 콤마를 사용해서 구분 |
| order | 쿼리 수행 순서(BEFORE, AFTER) |

<br>

**selectKey 기본 문법**

```xml
<!-- 1. 단일 프로퍼티(BEFORE)
 #{프로퍼티명} 으로 바인드 변수 사용할 수 있음 -->
<selectKey keyProperty="프로퍼티명" resultType="string" order="BEFORE">
    SELECT 컬럼명
    FROM 테이블명
    WHERE 조건
</selectKey>

<!-- 1. 단일 프로퍼티(AFTER)
 JAVA단에서 AFTER로 보낸 값을 사용할 수 있음, 바인드변수 불가 -->
<selectKey keyProperty="프로퍼티명" resultType="string" order="AFTER">
    SELECT 컬럼명
    FROM 테이블명
    WHERE 조건
</selectKey>

<!-- 2. 다중 프로퍼티 -->
<selectKey keyColumn="컬럼명, 컬럼명" keyProperty="변수명, 변수명" resultType="hashMap" order="BEFORE">
    SELECT 컬럼명, 컬럼명
    FROM 테이블명
    WHERE 조건
</selectKey>
```

**selectKey 사용 예시**

```xml
<!-- 입력하기전 특정키값을 조회하여 처리 -->
<insert id="insert">
    <selectKey keyProperty="boardId" resultType="string" order="BEFORE">
        SELECT MAX(BOARD_ID) + 1 AS boardId
        FROM BOARD
    </selectKey>
    INSERT INTO BOARD (
        BOARD_ID,
        TITLE,
        CONTENT
    ) VALUES (
        #{boardId},
        #{title},
        #{content}
    )
</insert>

<!-- 방금 입력한 값의 특정값 리턴 -->
<insert id="insertSurveyInfo" parameterType="Board">
    INSERT INTO BOARD (
        BOARD_ID,
        TITLE,
        CONTENT
    ) VALUES (
        #{boardId},
        #{title},
        #{content}
    )
    <selectKey keyProperty="boardId" resultType="string" order="AFTER">
        SELECT LAST_INSERT_ID()
    </selectKey>        
</insert>

<!-- selectKey 여러개 사용 -->
<insert id="insert">
    <selectKey keyColumn="boardId, seq" keyProperty="boardId, seq" resultType="hashMap" order="BEFORE">
        SELECT MAX(BOARD_ID) + 1 AS boardId,
               boardSeq.NEXTVAL AS seq
        FROM BOARD
    </selectKey>
    INSERT INTO BOARD (
        SEQ,
        BOARD_ID,
        TITLE,
        CONTENT
    ) VALUES (
        #{seq},
        #{boardId},
        #{title},
        #{content}
    )
</insert>
```

<br>

**bind 기본 문법**

```xml
<bind name="변수명" value="값" />
```

<br>

**bind 사용 예시**

```xml
	<select id="getBoard" parameterType="Integer" resultType="com.spring.dto.Board">
		<bind name="pattern" value="5" />
		select * from board where 
		bno = #{pattern}
	</select>
	<!-- bind 태그 예시 -->
	<select id="getBoard2" parameterType="String" resultType="com.spring.dto.Board">
		<bind name="pattern" value="'%' + keyword + '%'" />
		select * from board where 
		title like #{pattern}
	</select>
```

<br><br>

### 10-4-4. MyBatis에서 게시판의 페이징 처리 기법

**DTO 작성**

```java
package com.spring1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {

    private int page;             // 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
    private String keyword;       // 검색 키워드
    private String searchType;    // 검색 유형

    public SearchDTO() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }

    public int getOffset() {
        return (page - 1) * recordSize;
    }
}
```

<br>

**BoardDTO 수정**

```java
    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    List<Board> findAll(SearchDTO params);

    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    int count(SearchDTO params);
```

<br>

**boardMapper.xml 수정**

```xml
    <!-- 게시글 리스트 조회 -->
    <select id="findAll" parameterType="com.spring1.dto.SearchDTO" resultType="com.spring1.dto.Board">
        SELECT
            <include refid="postColumns" />
        FROM
            tb_post
        WHERE
            delete_yn = 0
        ORDER BY
            id DESC
        LIMIT #{offset}, #{recordSize}
    </select>
    
    
    <!-- 게시글 수 카운팅 -->
    <select id="count" parameterType="com.spring1.dto.SearchDTO" resultType="int">
        SELECT
            COUNT(*)
        FROM
            tb_post
        WHERE
            delete_yn = 0
    </select>
```
