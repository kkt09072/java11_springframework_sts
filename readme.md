<nav id="quick" style="position:fixed;top:150px;right:100px;width:200px;height:auto;overflow:hidden;z-index:999;background-color:rgba(255,255,255,0.75);">
	<label for="chap1" style="color:black;font-size:28px;">1. sts 설치</label>
	<input type="radio" name="chap" id="chap1" style="display:none;">
	<ul class="menu">
		<li><a href="#1-1" style="color:black;font-size:20px;text-decoration:none;">1-1. sts 다운로드 및 설치</a></li>
		<li><a href="#1-2" style="color:black;font-size:20px;text-decoration:none;">1-2. Lombok 설치</a></li>
		<li><a href="#1-3" style="color:black;font-size:20px;text-decoration:none;">1-3. sts 설정</a></li>
		<li><a href="#1-4" style="color:black;font-size:20px;text-decoration:none;">1-4. Dynamic Web Project 를 Maven Project로 변환하기</a></li>
		<li><a href="#1-5" style="color:black;font-size:20px;text-decoration:none;">1-5. 프로젝트 환경 설정하기</a></li>
		<li><a href="#1-6" style="color:black;font-size:20px;text-decoration:none;">1-6. sts에 Spring Legacy Project 템플릿 추가하기</a></li>
		<li><a href="#1-7" style="color:black;font-size:20px;text-decoration:none;">1-7. sts에서 Spring Legacy Project 바로 추가하기</a></li>
		<li><a href="#1-8" style="color:black;font-size:20px;text-decoration:none;">1-8. Spring Legacy Project 환경 설정하기</a></li>
	</ul>
	<hr>
	<label for="chap2" style="color:black;font-size:28px;">2. 애플리케이션 개발</label>
	<input type="radio" name="chap" id="chap2" style="display:none;">
	<ul class="menu">
		<li><a href="#2-1" style="color:black;font-size:20px;text-decoration:none;">2-1. 애플리케이션 설정하기</a></li>
		<li><a href="#2-2" style="color:black;font-size:20px;text-decoration:none;">2-2. 영속 계층 개발작업하기</a></li>
		<li><a href="#2-3" style="color:black;font-size:20px;text-decoration:none;">2-3. Service 작성하기</a></li>
		<li><a href="#2-4" style="color:black;font-size:20px;text-decoration:none;">2-4. Controller 작성하기</a></li>
		<li><a href="#2-5" style="color:black;font-size:20px;text-decoration:none;">2-5. View(jsp) 작성하기</a></li>
	</ul>
	<hr>
	<label for="chap3" style="color:black;font-size:28px;">3. DI (Dependency Injection)와 IoC(Invert Of Control)</label>
	<input type="radio" name="chap" id="chap3" style="display:none;">
	<ul class="menu">
		<li><a href="#3-1" style="color:black;font-size:20px;text-decoration:none;">3-1. 의존성 주입(Dependency Injection)</a></li>
		<li><a href="#3-2" style="color:black;font-size:20px;text-decoration:none;">3-2. 의존성 주입 방법</a></li>
		<li><a href="#3-3" style="color:black;font-size:20px;text-decoration:none;">3-3. 의존성 빈 설정 방법</a></li>
		<li><a href="#3-4" style="color:black;font-size:20px;text-decoration:none;">3-4. 스프링프레임워크의 DI와 IoC</a></li>
	</ul>	
</nav>

<div id="1"></div>

# 1. sts 설치

<div id="1-1"> <a href="#quick">목차로</a> </div>

## 1-1. sts 다운로드 및 설치

**(1) 다운로드 페이지로 이동** ☞ [sts다운로드링크](https://github.com/spring-attic/toolsuite-distribution/wiki/Spring-Tool-Suite-3)

![STS다운로드페이지](./images/stsdownload1.png)

**(2) 아래와 같은 화면이 나올 때까지 스크롤합니다.**

![STS다운로드페이지2](./images/stsdownload2.png)

<br>

![STS다운로드페이지3](./images/stsdownload3.png)

<br>

**(3) 압축 파일을 원하는 폴더로 이동후에 이름을 sts.zip으로 변경하고, 아래 그림과 같이 진행합니다.**

![STS설치1](./images/stsinstall01.png)

<br>

**(4) 압축풀기가 끝나면 아래 그림과 같이 \sts\sts-bundle\sts-3.9.18RELEASE 폴더로 이동합니다.**

![STS설치2](./images/stsinstall02.png)

<br><br>

<div id="1-2"><a href="#quick">목차로</a></div>

## 1-2. Lombok 설치

<span style="color:red;font-weight:bold;font-size:24px">※ Lombok을 활용하면, DTO 클래스에 getter/setter/toString/Constructor 의 정의가 필요 없게 되어 프로젝트 진행시 개발 시간을 단축시킬 수 있습니다.</span> 

**(1) DTO 생성을 위한 Lombok.jar를 설치하기 위해 mvnrepository 페이지로 이동합니다.**

![STS설치3](./images/stsinstall03_1.png)

<br>

**(2) Lombok을 검색하여 찾아 해당 카테고리를 클릭하고, 상세 페이지로 이동합니다.**

![STS설치3](./images/stsinstall03_2.png)

<br>

**(3) Lombok의 Archive 목록에서 원하는 버전을 클릭하여 선택합니다.**

![STS설치3](./images/stsinstall03_3.png)

<br>

**(4) jar 파일을 다운로드합니다.**

![STS설치3](./images/stsinstall03_4.png)

<br>

**(5) download 폴더로 이동하여 다운로드된 lombok-1.18.22.jar파일을 찾아 파일의 이름을 lombok.jar로 변경합니다.**

![STS설치](./images/stsinstall04.png)

<br>

**(6) lombok.jar 파일을 잘라내기하여 sts가 설치된 폴더로 붙여넣기 합니다.**

![STS설치](./images/stsinstall05.png)

<br>

**(7) cmd(명령 프롬프트)를 관리자모드로 실행합니다.**

![STS설치](./images/stsinstall06.png)

<br>

**(8) 해당 디렉토리로 이동 후에 java의 jar 명령으로 lombok.jar의 압축을 풀면서 실행을 시킵니다.**

![STS설치](./images/stsinstall07.png)

<br>

**(9) lombok 설치 화면에서 [Specify location] 버튼을 클릭합니다.**

![STS설치](./images/stsinstall08.png)

<br>

**(10) sts가 설치된 디렉토리 및 STS.exe 파일의 경로를 지정합니다.**

![STS설치](./images/stsinstall09.png)

<br>

**(11) 경로지정이 끝나면 설치를 진행합니다.**

![STS설치](./images/stsinstall10.png)

<br>

**(12) 설치가 모두 마치면, Lombok 설치를 종료합니다.**

![STS설치](./images/stsinstall11.png)

<br><br><br>

<div id="1-3"><a href="#quick">목차로</a></div>

## 1-3. sts 설정

**(1) sts의 바로가기 아이콘을 바탕화면에 만들어 실행합니다.**

![STS설치](./images/stsinstall12.png)

<br>

**(2) 프로젝트의 디렉토리인 워크스페이스 디렉토리를 지정합니다.**

![STS설치](./images/stsinstall12_1.png)

<br>

**(3) Dash 보드 패널을 닫습니다.**

![STS설치](./images/stsinstall13.png)

<br>

**(4) [Windows]-[Properties] 메뉴를 통하여 환경 설정 화면으로 이동합니다.**

![STS설치](./images/stsinstall14.png)

<span style="color:red;font-weight:bold;font-size:24px">※ 워크스페이스를 변경시에는 환경 설정을 다시 해야합니다.</span> 

<br>

**(5) 워크스페이스의 인코딩을 설정합니다.**

![STS설치](./images/stsinstall15.png)

<br>

**(6) 자바 컴파일러를 설정합니다.**

![STS설치](./images/stsinstall16.png)

<br>

**(7) JRE를 설정합니다.**

![STS설치](./images/stsinstall17.png)

<br>

**(8) CSS의 인코딩 방식을 설정합니다.**

![STS설치](./images/stsinstall18.png)

<br>

**(9) HTML의 인코딩 방식을 설정합니다.**

![STS설치](./images/stsinstall19.png)

<br>

**(10) JSP의 인코딩 방식을 설정합니다.**

![STS설치](./images/stsinstall20.png)

<br>

**(11) JSP 템플릿을 수정하도록 합니다.**

![STS설치](./images/stsinstall21.png)

<span style="color:red;font-weight:bold;font-size:24px">※ JSP 템플릿을 수정하게 되면, 매번 번거롭게 하는 일들을 줄일 수가 있습니다.</span> 

<br>

**(12) JSP 템플릿의 내용을 수정합니다.** 

![STS설치](./images/stsinstall22.png)

아래는 템플릿에 추가할 내용입니다.

```html
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="title" content="애플리케이션 제목">
    <meta name="application-name" content="애플리케이션 이름">
    <meta name="author" content="애플리케이션 제작자 이름">
    <meta name="description" content="애플리케이션 설명">
    <meta name="keywords" content="애플리케이션 검색어 열거">
    <meta property="og:url" content="애플리케이션 URL" />
    <meta property="og:type" content="website" />
    <meta property="og:title" content="애플리케이션 제목" />
    <meta property="og:description" content="애플리케이션 설명" />
    <meta property="og:image" content="애플리케이션 메인 이미지 경로" />
```

<br>

**(13) Server 를 설정합니다.**

![STS설치](./images/stsinstall23.png)

<br>

**(14) 사용할 Server로 Tomcat Server 를 지정합니다.**

![STS설치](./images/stsinstall24.png)

<span style="color:red;font-weight:bold;font-size:24px">※ Create a new local server 항목을 체크하지 않으면, Servers 패널에 나타나지 않으니 꼭 체크해 두시기 바랍니다.</span> 

<br>

**(15) 기존에 설치된 tomcat 9을 Server 로 지정합니다.**

![STS설치](./images/stsinstall25.png)

<br>

**만약, tomcat이 설치되지 않았다면, 아래 작업을 먼저 진행하고, (15)번 작업을 진행하시기 바랍니다.**

![STS설치](./images/stsinstall25_1.png)

**아파치톰캣 다운로드 페이지** [TOMCAT9](https://tomcat.apache.org/download-90.cgi)

**다운로드 받은 apache-tomcat-9.0.88-windows-x64.zip 파일을 원하는 곳으로 이동 후 압축을 해제합니다.**

<br>

**(16) 불필요한 VMWare Server를 제거하고, sts 설정을 종료합니다.**

![STS설치](./images/stsinstall26.png)

<br><br><br>

<div id="1-4"><a href="#quick">목차로</a></div>

## 1-4. Dynamic Web Project 를 Maven Project로 변환하기

**(1) Dynamic Web Project 를 새롭게 추가합니다.**

![STS설치](./images/stsinstall27.png)

<br>

**(2) Project 의 기본 정보를 입력하고 설정합니다.**

![STS설치](./images/stsinstall28.png)

<br>

**(3) Project 에서 활용될 디렉토리 설정과 배포할 파일이 존재할 출력 디렉토리를 지정합니다.**

![STS설치](./images/stsinstall29.png)

<br>

**(4) Project 에서 사용할 웹 모듈과 설정 방식을 지정합니다.**

![STS설치](./images/stsinstall30.png)

<span style="color:red;font-weight:bold;font-size:24px">※ Create web.xml deployment descriptor 항목을 체크하지 않으면, 수동으로 만드는 수고를 해야 하니 꼭 체크하시기 바랍니다.</span> 

<br>

**(5) sts에서 Dynamic Web Project를 추가하게 되면, JSP/Servlet 개발 환경에 적합한 화면으로 변경하겠다는 메시지가 출력됩니다.**

![STS설치](./images/stsinstall31.png)

<br><br>

**(6) Dynamic Web Project를 Maven Project 로 변환합니다.**

![STS설치](./images/stsinstall32.png)

<br>

**(7) Maven Project 로 변환시 프로젝트의 그룹아이디와 저작아이디 그리고, 패키징 방식 등을 지정합니다.**

![STS설치](./images/stsinstall33.png)

<br><br><br>

<div id="1-5"><a href="#quick">목차로</a></div>

## 1-5. 프로젝트 환경 설정하기

**(1) 프로젝트 환경을 설정창을 불러옵니다.**

![STS설치](./images/stsinstall34.png)

<br>

**(2) 프로젝트의 인코딩 방식을 지정합니다.**

![STS설치](./images/stsinstall35.png)

<br>

**(3) 프로젝트에서 사용할 자바 컴파일러를 설정합니다.**

![STS설치](./images/stsinstall36.png)

<br>

**(4) 프로젝트의 프론트엔드/백엔드 웹 환경을 설정합니다.**

![STS설치](./images/stsinstall37.png)

<br>

**(5) 프로젝트에서 사용할 JRE 환경을 지정합니다.**

![STS설치](./images/stsinstall38.png)

<br>

**(6) 프로젝트에서 사용할 JRE 환경을 불러옵니다.**

![STS설치](./images/stsinstall39.png)

<br>

**(7) 프로젝트에서 사용할 JRE 환경을 선택합니다.**

![STS설치](./images/stsinstall40.png)

<br>

**(8) 프로젝트에서 사용하지 않는 JRE 환경을 제거하고, 환경 설정을 종료합니다.**

![STS설치](./images/stsinstall41.png)

<br>

**(9) 프로젝트 개발작업을 시작합니다.**

![STS설치](./images/stsinstall42.png)


<span style="color:red;font-weight:bold;font-size:24px"> ※ 위와 같이 Dynamic Web Project에서 Spring Legacy Project 변환하여 작성하게 되면, 모두 수동 설정 작업을 해야 하므로, 상당히 설정에 너무 시간이 소요된다. 그래서 이번에는 아래 화면들은 Spring Legacy Project 직접 만드는 것이 편합니다.</span>

<br><br><br>

<div id="1-6"><a href="#quick">목차로</a></div>

## 1-6. sts에 Spring Legacy Project 템플릿 추가하기

<br>

**(1) Spring Legacy Project 를 새롭게 추가합니다.**

![STS설치](./images/stsinstall43.png)

<br>

**(2) 현재는 Spring Legacy Project 템플릿이 없는 상황입니다.**

![STS설치](./images/stsinstall44.png)

**(3) 새로운 템플릿 추가작업을 위해 sts를 종료합니다.**

<br>

### 그런데 위에서 본바와 같이 2024년 되자 sts에서 Spring Legacy Project 를 만들 수 있는 템플릿이 사라졌다. 그래서 별도의 템플릿 추가 작업을 진행하여 프로젝트를 생성해야 합니다.

<br>

**https-content.xml 다운로드 링크** ☞
<a href="https-content.xml" download>https-content.xml 다운로드 받기</a>

<br>

**(4) Spring Legacy Project 템플릿 추가 작업을 진행합니다.**

![STS설치](./images/stsinstall45.png)

<br>

**(5) sts를 다시 재기동합니다.**

<br>

**(6) Spring Legacy Project 를 새롭게 추가합니다.**

![STS설치](./images/stsinstall45.png)

<br>

**(7) 화면 처럼 없던 템플릿이 추가된 것을 확인합니다.**

![STS설치](./images/stsinstall46.png)

<br><br><br>

<div id="1-7"><a href="#quick">목차로</a></div>

## 1-7. sts에서 Spring Legacy Project 바로 추가하기

**(1) 메뉴에서 Spring Legacy Project를 새롭게 추가합니다.**

![프로젝트시작](./images/stsinstall43.png)

<br>

**(2) 프로젝트의 이름을 입력하고, Spring MVC Project를 선택합니다.**

![프로젝트시작](./images/stsinstall47.png)

<br>

**(3) 메시지 창에서 [Yes] 버튼을 누릅니다.**

![프로젝트시작](./images/stsinstall48.png)

<br>

**(4) 프로젝트의 이름을 확인하고, Spring MVC Project를 선택합니다.**

![프로젝트시작](./images/stsinstall47.png)

<br>

**(5) 메인 패키지의 이름을 입력합니다.**

![프로젝트시작](./images/stsinstall49.png)

<br>

**(6) 추가된 프로젝트의 내용을 상세하게 확인합니다.**

![프로젝트시작](./images/stsinstall50.png)

<br><br><br>

<div id="1-8"><a href="#quick">목차로</a></div>

## 1-8. Spring Legacy Project 환경 설정하기

**(1) 프로젝트 환경을 설정창을 불러옵니다.**

![프로젝트설정](./images/stsinstall34.png)

<br>

**(2) 프로젝트의 인코딩 방식을 지정합니다.**

![프로젝트설정](./images/stsinstall35.png)

<br>

**(3) 프로젝트에서 사용할 자바 컴파일러를 설정합니다.**

![프로젝트설정](./images/stsinstall36.png)

<br>

**(4) 프로젝트의 프론트엔드/백엔드 웹 환경을 설정합니다.**

![프로젝트설정](./images/stsinstall37.png)

<br>

**(5) 프로젝트에서 사용할 JRE 환경을 지정합니다.**

![프로젝트설정](./images/stsinstall38.png)

<br>

**(6) 프로젝트에서 사용할 JRE 환경을 불러옵니다.**

![프로젝트설정](./images/stsinstall39.png)

<br>

**(7) 프로젝트에서 사용할 JRE 환경을 선택합니다.**

![프로젝트설정](./images/stsinstall40.png)

<br>

**(8) 프로젝트에서 사용하지 않는 JRE 환경을 제거하고, 환경 설정을 종료합니다.**

![프로젝트설정](./images/stsinstall41.png)

<br><br><hr><br><br>

<div id="2"></div>

# 2. 애플리케이션 개발

<div id="2-1"><a href="#quick">목차로</a></div>

## 2-1. 애플리케이션 설정하기

<div id="2-1-1"></div>

### 2-1-1. 프로젝트 관리자 역할을 하는 POM.xml 의존성 등록하기

**아래 화면과 같이 pom.xml 파일을 열고 작성을 완료하도록 합니다.**

#### 2-1-1-1. 오라클의 예

![프로젝트설정](./images/pom.xml.png)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>spring1</groupId>
  <artifactId>spring1</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
  <properties>
  	<java-version>11</java-version>
  	<org.springframework-version>5.0.8.RELEASE</org.springframework-version>
  	<org.aspectj-version>1.8.10</org.aspectj-version>
  	<org.slf4j-version>1.7.25</org.slf4j-version>
  </properties>
  <dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- SLF4j를 위해 Commons Logging을 제외시킴 -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				 </exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
				
		<!-- AspectJ : 관점지향형(AOP) 기능 제공 라이브러리 -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>	
		
		<!-- Logging : 모든 자원의 접속 로그를 기록하는 라이브러리 -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.15</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<scope>runtime</scope>
		</dependency>

		<!-- @Inject : 의존성 주입 라이브러리 -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
		
		<!-- JSP/Servlet 라이브러리 -->
		<dependency>
		    <groupId>javax.servlet</groupId>
		    <artifactId>javax.servlet-api</artifactId>
		    <version>4.0.1</version>
		    <scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		
		<!-- Test : junit 테스트 라이브러리 -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13</version>
			<scope>test</scope>
		</dependency>  
		  
		<!--  스프링 테스트 라이브러리 추가 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>5.0.8.RELEASE</version>
		</dependency>
		
		<!-- war 배포 및 패키징 라이브러리 추가 -->
		<dependency>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-war-plugin</artifactId>
			<version>3.2.0</version>
		</dependency>    
		
		<!--  getter, setter, constructer를 자동 생성해주는 라이브러리 -->
		<dependency>
		    <groupId>org.projectlombok</groupId>
		    <artifactId>lombok</artifactId>
		    <version>1.18.22</version>
		    <scope>provided</scope>
		</dependency>

		<!--  log4jdbc-log4j2-jdbc4 : DB 접속로그를 기록하는 라이브러리 -->
		<dependency>
		    <groupId>org.bgee.log4jdbc-log4j2</groupId>
		    <artifactId>log4jdbc-log4j2-jdbc4</artifactId>
		    <version>1.16</version>
		</dependency>
		
				
		<!-- 스프링 트랜잭션 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-tx</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- 스프링 jdbc 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- commons-dbcp : 자바 웹 DB 연결 공용 라이브러리 -->
		<dependency>
		    <groupId>commons-dbcp</groupId>
		    <artifactId>commons-dbcp</artifactId>
		    <version>1.4</version>
		</dependency>
		
		<!--  오라클 jdbc 라이브러리 -->
		<dependency>
		    <groupId>com.oracle.database.jdbc</groupId>
		    <artifactId>ojdbc11</artifactId>
		    <version>21.1.0.0</version>
		</dependency>
		
		<!-- MySQL jdbc 라이브러리 -->
<!-- 		<dependency>
		    <groupId>mysql</groupId>
		    <artifactId>mysql-connector-java</artifactId>
		    <version>8.0.31</version>
		</dependency> -->

		<!-- MariaDB jdbc 라이브러리 -->
<!-- 		<dependency>
		    <groupId>org.mariadb.jdbc</groupId>
		    <artifactId>mariadb-java-client</artifactId>
		    <version>3.1.0</version>
		</dependency> -->

		<!-- SQL 구문을 XML로 쉽게 구현하기 위한 MyBatis 라이브러리 -->
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
		
		<!-- 구글 JSON -->
		<dependency>
		    <groupId>com.google.code.gson</groupId>
		    <artifactId>gson</artifactId>
		    <version>2.7</version>
		</dependency>
		<dependency>
		    <groupId>org.jsoup</groupId>
		    <artifactId>jsoup</artifactId>
		    <version>1.12.1</version>
		</dependency>
		<dependency>
		    <groupId>org.json</groupId>
		    <artifactId>json</artifactId>
		    <version>20200518</version>
		</dependency>
		
		<!-- jackson 라이브러리 -->
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.9.4</version>
		</dependency>
		
		<dependency>
		    <groupId>org.codehaus.jackson</groupId>
		    <artifactId>jackson-mapper-asl</artifactId>
		    <version>1.9.13</version>
		</dependency>
		
				<!-- 파일 첨부 및 업로드 라이브러리 -->		
		<dependency>
		    <groupId>commons-fileupload</groupId>
		    <artifactId>commons-fileupload</artifactId>
		    <version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.4</version>
		</dependency>
		<!--  이미지 편집 라이브러리 -->
		<dependency>
		    <groupId>org.imgscalr</groupId>
		    <artifactId>imgscalr-lib</artifactId>
		    <version>4.0</version>
		</dependency>
		
		<!-- 자바 이메일 기본 라이브러리 -->
		<dependency>
		    <groupId>javax.mail</groupId>
		    <artifactId>javax.mail-api</artifactId>
		    <version>1.4.7</version>
		</dependency>
		
		<!-- 이메일 및 자원에 대한 외부 송출 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-context-support</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>

		<!-- java의 validation 라이브러리 -->		
		<dependency>
		    <groupId>javax.validation</groupId>
		    <artifactId>validation-api</artifactId>
		    <version>2.0.1.Final</version>
		</dependency>
		<!-- 폼 검증을 애노테이션으로 검증하도록 하는 hibernate 라이브러리 -->
		<dependency>
		    <groupId>org.hibernate</groupId>
		    <artifactId>hibernate-annotations</artifactId>
		    <version>3.5.6-Final</version>
		</dependency> 
		<!--  hibernate Validator 라이브러리 -->
		<dependency>
		    <groupId>org.hibernate.validator</groupId>
		    <artifactId>hibernate-validator</artifactId>
		    <version>6.0.8.Final</version>
		</dependency>
		<dependency>
		    <groupId>javax.xml.bind</groupId>
		    <artifactId>jaxb-api</artifactId>
		    <version>2.3.0</version>
		</dependency>
  </dependencies>
  <build>
    <plugins>
        <plugin>
            <artifactId>maven-eclipse-plugin</artifactId>
            <version>2.9</version>
            <configuration>
                <additionalProjectnatures>
                    <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
                </additionalProjectnatures>
                <additionalBuildcommands>
                    <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
                </additionalBuildcommands>
                <downloadSources>true</downloadSources>
                <downloadJavadocs>true</downloadJavadocs>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.5.1</version>
            <configuration>
                <source>11</source>
                <target>11</target>
                <compilerArgument>-Xlint:all</compilerArgument>
                <showWarnings>true</showWarnings>
                <showDeprecation>true</showDeprecation>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>1.2.1</version>
            <configuration>
                <mainClass>org.test.int1.Main</mainClass>
            </configuration>
        </plugin>
    </plugins>
  </build>
</project>
```

<br>

#### 2-1-1-1. MySQL의 예

![프로젝트설정](./images/pom.xml2.png)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.spring1</groupId>
  <artifactId>myapp</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
  <properties>
  	<java-version>11</java-version>
  	<org.springframework-version>5.0.8.RELEASE</org.springframework-version>
  	<org.aspectj-version>1.8.10</org.aspectj-version>
  	<org.slf4j-version>1.7.25</org.slf4j-version>
  </properties>
  <dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				 </exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
				
		<!-- AspectJ : 관점지향형(AOP) 기능 제공 라이브러리 -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>	
		
		<!-- Logging : 모든 자원의 접속 로그를 기록하는 라이브러리 -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.15</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<scope>runtime</scope>
		</dependency>

		<!-- @Inject : 의존성 주입 라이브러리 -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
		
		<!-- JSP/Servlet 라이브러리 -->
		<dependency>
		    <groupId>javax.servlet</groupId>
		    <artifactId>javax.servlet-api</artifactId>
		    <version>4.0.1</version>
		    <scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		
		<!-- Test : junit 테스트 라이브러리 -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13</version>
			<scope>test</scope>
		</dependency>  
		  
		<!--  스프링 테스트 라이브러리 추가 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>5.0.8.RELEASE</version>
		</dependency>
		
		<!-- war 배포 및 패키징 라이브러리 추가 -->
		<dependency>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-war-plugin</artifactId>
			<version>3.2.0</version>
		</dependency>    
		
		<!--  getter, setter, constructer를 자동 생성해주는 라이브러리 -->
		<dependency>
		    <groupId>org.projectlombok</groupId>
		    <artifactId>lombok</artifactId>
		    <version>1.18.22</version>
		    <scope>provided</scope>
		</dependency>

		<!--  log4jdbc-log4j2-jdbc4 : DB 접속로그를 기록하는 라이브러리 -->
		<dependency>
		    <groupId>org.bgee.log4jdbc-log4j2</groupId>
		    <artifactId>log4jdbc-log4j2-jdbc4</artifactId>
		    <version>1.16</version>
		</dependency>
		
				
		<!-- 스프링 트랜잭션 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-tx</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- 스프링 jdbc 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- commons-dbcp : 자바 웹 DB 연결 공용 라이브러리 -->
		<dependency>
		    <groupId>commons-dbcp</groupId>
		    <artifactId>commons-dbcp</artifactId>
		    <version>1.4</version>
		</dependency>
		
		<!--  오라클 jdbc 라이브러리 -->
<!-- 		<dependency>
		    <groupId>com.oracle.database.jdbc</groupId>
		    <artifactId>ojdbc11</artifactId>
		    <version>21.1.0.0</version>
		</dependency> -->
		
		<!-- MySQL jdbc 라이브러리 -->
 		<dependency>
		    <groupId>mysql</groupId>
		    <artifactId>mysql-connector-java</artifactId>
		    <version>8.0.31</version>
		</dependency>

		<!-- MariaDB jdbc 라이브러리 -->
<!-- 		<dependency>
		    <groupId>org.mariadb.jdbc</groupId>
		    <artifactId>mariadb-java-client</artifactId>
		    <version>3.1.0</version>
		</dependency> -->

		<!-- SQL 구문을 XML로 쉽게 구현하기 위한 MyBatis 라이브러리 -->
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
		
		<!-- 구글 JSON -->
		<dependency>
		    <groupId>com.google.code.gson</groupId>
		    <artifactId>gson</artifactId>
		    <version>2.7</version>
		</dependency>
		<dependency>
		    <groupId>org.jsoup</groupId>
		    <artifactId>jsoup</artifactId>
		    <version>1.12.1</version>
		</dependency>
		<dependency>
		    <groupId>org.json</groupId>
		    <artifactId>json</artifactId>
		    <version>20200518</version>
		</dependency>
		
		<!-- jackson 라이브러리 -->
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.9.4</version>
		</dependency>
		
		<dependency>
		    <groupId>org.codehaus.jackson</groupId>
		    <artifactId>jackson-mapper-asl</artifactId>
		    <version>1.9.13</version>
		</dependency>
		
				<!-- 파일 첨부 및 업로드 라이브러리 -->		
		<dependency>
		    <groupId>commons-fileupload</groupId>
		    <artifactId>commons-fileupload</artifactId>
		    <version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.4</version>
		</dependency>
		<!--  이미지 편집 라이브러리 -->
		<dependency>
		    <groupId>org.imgscalr</groupId>
		    <artifactId>imgscalr-lib</artifactId>
		    <version>4.0</version>
		</dependency>
		
		<!-- 자바 이메일 기본 라이브러리 -->
		<dependency>
		    <groupId>javax.mail</groupId>
		    <artifactId>javax.mail-api</artifactId>
		    <version>1.4.7</version>
		</dependency>
		
		<!-- 이메일 및 자원에 대한 외부 송출 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-context-support</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>

		<!-- java의 validation 라이브러리 -->		
		<dependency>
		    <groupId>javax.validation</groupId>
		    <artifactId>validation-api</artifactId>
		    <version>2.0.1.Final</version>
		</dependency>
		<!-- 폼 검증을 애노테이션으로 검증하도록 하는 hibernate 라이브러리 -->
		<dependency>
		    <groupId>org.hibernate</groupId>
		    <artifactId>hibernate-annotations</artifactId>
		    <version>3.5.6-Final</version>
		</dependency> 
		<!--  hibernate Validator 라이브러리 -->
		<dependency>
		    <groupId>org.hibernate.validator</groupId>
		    <artifactId>hibernate-validator</artifactId>
		    <version>6.0.8.Final</version>
		</dependency>
		<dependency>
		    <groupId>javax.xml.bind</groupId>
		    <artifactId>jaxb-api</artifactId>
		    <version>2.3.0</version>
		</dependency>
  </dependencies>
  <build>
    <plugins>
        <plugin>
            <artifactId>maven-eclipse-plugin</artifactId>
            <version>2.9</version>
            <configuration>
                <additionalProjectnatures>
                    <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
                </additionalProjectnatures>
                <additionalBuildcommands>
                    <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
                </additionalBuildcommands>
                <downloadSources>true</downloadSources>
                <downloadJavadocs>true</downloadJavadocs>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.5.1</version>
            <configuration>
                <source>11</source>
                <target>11</target>
                <compilerArgument>-Xlint:all</compilerArgument>
                <showWarnings>true</showWarnings>
                <showDeprecation>true</showDeprecation>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>1.2.1</version>
            <configuration>
                <mainClass>org.test.int1.Main</mainClass>
            </configuration>
        </plugin>
    </plugins>
  </build>
</project>
```

<br>

#### 2-1-1-3. MariaDB의 예

![프로젝트설정](./images/pom.xml3.png)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>spring1</groupId>
  <artifactId>spring1</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>spring3</name>
  <packaging>war</packaging>
  <properties>
  	<java-version>11</java-version>
  	<org.springframework-version>5.0.8.RELEASE</org.springframework-version>
  	<org.aspectj-version>1.8.10</org.aspectj-version>
  	<org.slf4j-version>1.7.25</org.slf4j-version>
  </properties>
  <dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				 </exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
				
		<!-- AspectJ : 관점지향형(AOP) 기능 제공 라이브러리 -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>	
		
		<!-- Logging : 모든 자원의 접속 로그를 기록하는 라이브러리 -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.15</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<scope>runtime</scope>
		</dependency>

		<!-- @Inject : 의존성 주입 라이브러리 -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
		
		<!-- JSP/Servlet 라이브러리 -->
		<dependency>
		    <groupId>javax.servlet</groupId>
		    <artifactId>javax.servlet-api</artifactId>
		    <version>4.0.1</version>
		    <scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		
		<!-- Test : junit 테스트 라이브러리 -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13</version>
			<scope>test</scope>
		</dependency>  
		  
		<!--  스프링 테스트 라이브러리 추가 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>5.0.8.RELEASE</version>
		</dependency>
		
		<!-- war 배포 및 패키징 라이브러리 추가 -->
		<dependency>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-war-plugin</artifactId>
			<version>3.2.0</version>
		</dependency>    
		
		<!--  getter, setter, constructer를 자동 생성해주는 라이브러리 -->
		<dependency>
		    <groupId>org.projectlombok</groupId>
		    <artifactId>lombok</artifactId>
		    <version>1.18.22</version>
		    <scope>provided</scope>
		</dependency>

		<!--  log4jdbc-log4j2-jdbc4 : DB 접속로그를 기록하는 라이브러리 -->
		<dependency>
		    <groupId>org.bgee.log4jdbc-log4j2</groupId>
		    <artifactId>log4jdbc-log4j2-jdbc4</artifactId>
		    <version>1.16</version>
		</dependency>
		
				
		<!-- 스프링 트랜잭션 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-tx</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- 스프링 jdbc 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- commons-dbcp : 자바 웹 DB 연결 공용 라이브러리 -->
		<dependency>
		    <groupId>commons-dbcp</groupId>
		    <artifactId>commons-dbcp</artifactId>
		    <version>1.4</version>
		</dependency>
		
		<!--  오라클 jdbc 라이브러리 -->
<!-- 		<dependency>
		    <groupId>com.oracle.database.jdbc</groupId>
		    <artifactId>ojdbc11</artifactId>
		    <version>21.1.0.0</version>
		</dependency> -->
		
		<!-- MySQL jdbc 라이브러리 -->
<!-- 		<dependency>
		    <groupId>mysql</groupId>
		    <artifactId>mysql-connector-java</artifactId>
		    <version>8.0.31</version>
		</dependency> -->

		<!-- MariaDB jdbc 라이브러리 -->
 		<dependency>
		    <groupId>org.mariadb.jdbc</groupId>
		    <artifactId>mariadb-java-client</artifactId>
		    <version>3.1.0</version>
		</dependency>

		<!-- SQL 구문을 XML로 쉽게 구현하기 위한 MyBatis 라이브러리 -->
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
		
		<!-- 구글 JSON -->
		<dependency>
		    <groupId>com.google.code.gson</groupId>
		    <artifactId>gson</artifactId>
		    <version>2.7</version>
		</dependency>
		<dependency>
		    <groupId>org.jsoup</groupId>
		    <artifactId>jsoup</artifactId>
		    <version>1.12.1</version>
		</dependency>
		<dependency>
		    <groupId>org.json</groupId>
		    <artifactId>json</artifactId>
		    <version>20200518</version>
		</dependency>
		
		<!-- jackson 라이브러리 -->
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.9.4</version>
		</dependency>
		
		<dependency>
		    <groupId>org.codehaus.jackson</groupId>
		    <artifactId>jackson-mapper-asl</artifactId>
		    <version>1.9.13</version>
		</dependency>
		
				<!-- 파일 첨부 및 업로드 라이브러리 -->		
		<dependency>
		    <groupId>commons-fileupload</groupId>
		    <artifactId>commons-fileupload</artifactId>
		    <version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.4</version>
		</dependency>
		<!--  이미지 편집 라이브러리 -->
		<dependency>
		    <groupId>org.imgscalr</groupId>
		    <artifactId>imgscalr-lib</artifactId>
		    <version>4.0</version>
		</dependency>
		
		<!-- 자바 이메일 기본 라이브러리 -->
		<dependency>
		    <groupId>javax.mail</groupId>
		    <artifactId>javax.mail-api</artifactId>
		    <version>1.4.7</version>
		</dependency>
		
		<!-- 이메일 및 자원에 대한 외부 송출 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-context-support</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>

		<!-- java의 validation 라이브러리 -->		
		<dependency>
		    <groupId>javax.validation</groupId>
		    <artifactId>validation-api</artifactId>
		    <version>2.0.1.Final</version>
		</dependency>
		<!-- 폼 검증을 애노테이션으로 검증하도록 하는 hibernate 라이브러리 -->
		<dependency>
		    <groupId>org.hibernate</groupId>
		    <artifactId>hibernate-annotations</artifactId>
		    <version>3.5.6-Final</version>
		</dependency> 
		<!--  hibernate Validator 라이브러리 -->
		<dependency>
		    <groupId>org.hibernate.validator</groupId>
		    <artifactId>hibernate-validator</artifactId>
		    <version>6.0.8.Final</version>
		</dependency>
		<dependency>
		    <groupId>javax.xml.bind</groupId>
		    <artifactId>jaxb-api</artifactId>
		    <version>2.3.0</version>
		</dependency>
  </dependencies>
  <build>
    <plugins>
        <plugin>
            <artifactId>maven-eclipse-plugin</artifactId>
            <version>2.9</version>
            <configuration>
                <additionalProjectnatures>
                    <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
                </additionalProjectnatures>
                <additionalBuildcommands>
                    <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
                </additionalBuildcommands>
                <downloadSources>true</downloadSources>
                <downloadJavadocs>true</downloadJavadocs>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.5.1</version>
            <configuration>
                <source>11</source>
                <target>11</target>
                <compilerArgument>-Xlint:all</compilerArgument>
                <showWarnings>true</showWarnings>
                <showDeprecation>true</showDeprecation>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>1.2.1</version>
            <configuration>
                <mainClass>org.test.int1.Main</mainClass>
            </configuration>
        </plugin>
    </plugins>
  </build>
</project>
```

<br>

#### 2-1-1-4.PostGres DB 의 예

![PostGres DB](./images/pom.xml4.png)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.spring1</groupId>
  <artifactId>myapp</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>spring4</name>
  <packaging>war</packaging>
  <properties>
  	<java-version>11</java-version>
  	<org.springframework-version>5.0.8.RELEASE</org.springframework-version>
  	<org.aspectj-version>1.8.10</org.aspectj-version>
  	<org.slf4j-version>1.7.25</org.slf4j-version>
  </properties>
  <dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				 </exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
				
		<!-- AspectJ : 관점지향형(AOP) 기능 제공 라이브러리 -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>	
		
		<!-- Logging : 모든 자원의 접속 로그를 기록하는 라이브러리 -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.15</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<scope>runtime</scope>
		</dependency>

		<!-- @Inject : 의존성 주입 라이브러리 -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
		
		<!-- JSP/Servlet 라이브러리 -->
		<dependency>
		    <groupId>javax.servlet</groupId>
		    <artifactId>javax.servlet-api</artifactId>
		    <version>4.0.1</version>
		    <scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		
		<!-- Test : junit 테스트 라이브러리 -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13</version>
			<scope>test</scope>
		</dependency>  
		  
		<!--  스프링 테스트 라이브러리 추가 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>5.0.8.RELEASE</version>
		</dependency>
		
		<!-- war 배포 및 패키징 라이브러리 추가 -->
		<dependency>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-war-plugin</artifactId>
			<version>3.2.0</version>
		</dependency>    
		
		<!--  getter, setter, constructer를 자동 생성해주는 라이브러리 -->
		<dependency>
		    <groupId>org.projectlombok</groupId>
		    <artifactId>lombok</artifactId>
		    <version>1.18.22</version>
		    <scope>provided</scope>
		</dependency>

		<!--  log4jdbc-log4j2-jdbc4 : DB 접속로그를 기록하는 라이브러리 -->
		<dependency>
		    <groupId>org.bgee.log4jdbc-log4j2</groupId>
		    <artifactId>log4jdbc-log4j2-jdbc4</artifactId>
		    <version>1.16</version>
		</dependency>
		
				
		<!-- 스프링 트랜잭션 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-tx</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- 스프링 jdbc 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- commons-dbcp : 자바 웹 DB 연결 공용 라이브러리 -->
		<dependency>
		    <groupId>commons-dbcp</groupId>
		    <artifactId>commons-dbcp</artifactId>
		    <version>1.4</version>
		</dependency>
		
		<!--  오라클 jdbc 라이브러리 -->
<!-- 		<dependency>
		    <groupId>com.oracle.database.jdbc</groupId>
		    <artifactId>ojdbc11</artifactId>
		    <version>21.1.0.0</version>
		</dependency> -->
		
		<!-- MySQL jdbc 라이브러리 -->
<!--   		<dependency>
		    <groupId>mysql</groupId>
		    <artifactId>mysql-connector-java</artifactId>
		    <version>8.0.31</version>
		</dependency> -->

		<!-- MariaDB jdbc 라이브러리 -->
<!-- 		<dependency>
		    <groupId>org.mariadb.jdbc</groupId>
		    <artifactId>mariadb-java-client</artifactId>
		    <version>3.1.0</version>
		</dependency> -->

		<!-- PostGres DB jdbc 라이브러리 -->
 		<dependency>
		    <groupId>org.postgresql</groupId>
		    <artifactId>postgresql</artifactId>
		    <version>42.2.0</version>
		</dependency>

		<!-- SQL 구문을 XML로 쉽게 구현하기 위한 MyBatis 라이브러리 -->
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
		
		<!-- 구글 JSON -->
		<dependency>
		    <groupId>com.google.code.gson</groupId>
		    <artifactId>gson</artifactId>
		    <version>2.7</version>
		</dependency>
		<dependency>
		    <groupId>org.jsoup</groupId>
		    <artifactId>jsoup</artifactId>
		    <version>1.12.1</version>
		</dependency>
		<dependency>
		    <groupId>org.json</groupId>
		    <artifactId>json</artifactId>
		    <version>20200518</version>
		</dependency>
		
		<!-- jackson 라이브러리 -->
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.9.4</version>
		</dependency>
		
		<dependency>
		    <groupId>org.codehaus.jackson</groupId>
		    <artifactId>jackson-mapper-asl</artifactId>
		    <version>1.9.13</version>
		</dependency>
		
				<!-- 파일 첨부 및 업로드 라이브러리 -->		
		<dependency>
		    <groupId>commons-fileupload</groupId>
		    <artifactId>commons-fileupload</artifactId>
		    <version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.4</version>
		</dependency>
		<!--  이미지 편집 라이브러리 -->
		<dependency>
		    <groupId>org.imgscalr</groupId>
		    <artifactId>imgscalr-lib</artifactId>
		    <version>4.0</version>
		</dependency>
		
		<!-- 자바 이메일 기본 라이브러리 -->
		<dependency>
		    <groupId>javax.mail</groupId>
		    <artifactId>javax.mail-api</artifactId>
		    <version>1.4.7</version>
		</dependency>
		
		<!-- 이메일 및 자원에 대한 외부 송출 라이브러리 -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-context-support</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>

		<!-- java의 validation 라이브러리 -->		
		<dependency>
		    <groupId>javax.validation</groupId>
		    <artifactId>validation-api</artifactId>
		    <version>2.0.1.Final</version>
		</dependency>
		<!-- 폼 검증을 애노테이션으로 검증하도록 하는 hibernate 라이브러리 -->
		<dependency>
		    <groupId>org.hibernate</groupId>
		    <artifactId>hibernate-annotations</artifactId>
		    <version>3.5.6-Final</version>
		</dependency> 
		<!--  hibernate Validator 라이브러리 -->
		<dependency>
		    <groupId>org.hibernate.validator</groupId>
		    <artifactId>hibernate-validator</artifactId>
		    <version>6.0.8.Final</version>
		</dependency>
		<dependency>
		    <groupId>javax.xml.bind</groupId>
		    <artifactId>jaxb-api</artifactId>
		    <version>2.3.0</version>
		</dependency>
  </dependencies>
  <build>
    <plugins>
        <plugin>
            <artifactId>maven-eclipse-plugin</artifactId>
            <version>2.9</version>
            <configuration>
                <additionalProjectnatures>
                    <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
                </additionalProjectnatures>
                <additionalBuildcommands>
                    <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
                </additionalBuildcommands>
                <downloadSources>true</downloadSources>
                <downloadJavadocs>true</downloadJavadocs>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.5.1</version>
            <configuration>
                <source>11</source>
                <target>11</target>
                <compilerArgument>-Xlint:all</compilerArgument>
                <showWarnings>true</showWarnings>
                <showDeprecation>true</showDeprecation>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>1.2.1</version>
            <configuration>
                <mainClass>org.test.int1.Main</mainClass>
            </configuration>
        </plugin>
    </plugins>
  </build>
</project>
```


<br><br>

<div id="2-1-2"><a href="#quick">목차로</a></div>

### 2-1-2. 웹 환경설정하기 - web.xml

**프로젝트이름\src\main\webapp\WEB-INF\web.xml 파일을 열고, 웹 컨테이너와 필터, 서블릿 요청 처리, 외부 자원 등을 어떻게 할지 전반적인 웹 환경을 설정합니다.**

![웹환경설정](./images/web.xml.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee https://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

	<!-- 모든 서블릿과 필터가 공유하는 루트 스프링 컨테이너의 정의 : 외부 자원에 대한 환경 설정 -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param>
	
	<!-- 모든 서블릿과 필터가 공유하는 Spring 컨테이너를 생성합니다. -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<!-- 요청 처리 서블릿 설정 : 접근 권한 설정, 리소스 경로 설정-->
	<servlet>
		<servlet-name>appServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>
				/WEB-INF/spring/appServlet/servlet-context.xml
			</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
		
	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

    <!-- 한글깨짐 방지 --> 
    <filter> 
        <filter-name>encodingFilter</filter-name> 
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param> 
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

<br><br>

<div id="2-1-3"><a href="#quick">목차로</a></div>

### 2-1-3. 외부 자원 환경설정하기 - root-context.xml

**프로젝트이름\\src\main\webapp\WEB-INF\spring\root-context.xml 파일을 열고, 외부 자원인 데이터베이스, 트랜잭션, 네트워크 등의 환경을 설정합니다.**

#### 2-1-3-1. 오라클의 예

![외부자원환경설정](./images/root-context.xml.png)

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
	
	<!-- naver/daum/google 메일 서버 설정 -->
	
</beans>
```

<br>

#### 2-1-3-2. MySQL의 예

![외부자원환경설정](./images/root-context.xml2.png)

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
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>
	<!-- 연결 url, 사용자 아이디, 비밀번호 설정  -->
		<property name="url" value="jdbc:mysql://localhost:3306/company?serverTimezone=UTC" />
		<property name="username" value="root" />
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
	
	<!-- naver/daum/google 메일 서버 설정 -->
	
</beans>
```

<br>

#### 2-1-3-3. MariaDB의 예

![외부자원환경설정](./images/root-context.xml3.png)

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
		<property name="driverClassName" value="org.mariadb.jdbc.Driver"></property>
	<!-- 연결 url, 사용자 아이디, 비밀번호 설정  -->
		<property name="url" value="jdbc:mariadb://localhost:3308/company?autoReconnect=true&amp;useSSL=false" />
		<property name="username" value="root" />
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
	
	<!-- naver/daum/google 메일 서버 설정 -->
	
</beans>
```

<br><br>

#### 2-1-3-4. PostGres의 예

![PostGres DB](./images/root-context.xml4.png)

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
		<property name="driverClassName" value="org.postgresql.Driver"></property>
	<!-- 연결 url, 사용자 아이디, 비밀번호 설정  -->
		<property name="url" value="jdbc:postgresql://localhost:5432/company" />
		<property name="username" value="postgres" />
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
	
	<!-- naver/daum/google 메일 서버 설정 -->
	
</beans>
```

<br><br>

<div id="2-1-4"><a href="#quick">목차로</a></div>

### 2-1-4. 리소스 및 뷰 리졸버와 기본 패키지 설정하기 - servlet-context.xml

**프로젝트이름\src\main\webapp\WEB-INF\spring\appServlet\servlet-context.xml 파일을 열고, 외부 리소스의 접근 권한을 설정합니다.**

![리소스접근권한환경설정](./images/servlet-context.xml.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:beans="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

	<!-- DispatcherServlet 의 요청 처리 인프라를 정의 -->
	
	<!-- Spring MVC @Controller 프로그래밍 모델을 활성화합니다. -->
	<annotation-driven />
	
	<!--  접근 자원에 대한 권한 설정 -->
	<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
	<resources mapping="/resources/**" location="/resources/" />
	<resources mapping="/data/**" location="/data/" />
	
	<resources mapping="/include/**" location="/WEB-INF/views/include" />
	<resources mapping="/board/**" location="/WEB-INF/views/board" />
	<resources mapping="/member/**" location="/WEB-INF/views/member" />
	<resources mapping="/reservate/**" location="/WEB-INF/views/reservate" />
	<resources mapping="/qna/**" location="/WEB-INF/views/qna" />
	<resources mapping="/databank/**" location="/WEB-INF/views/databank" />
	<resources mapping="/util/**" location="/WEB-INF/views/util" />
	<resources mapping="/sample/**" location="/WEB-INF/views/sample" />
	<resources mapping="/check/**" location="/WEB-INF/views/check" />
	<resources mapping="/user/**" location="/WEB-INF/views/user" />
	<resources mapping="/free/**" location="/WEB-INF/views/free" />

	<!-- 리졸버에 대한 접두사와 접미사 설정 -->
	<!-- @Controller가 렌더링하기 위해 선택한 뷰를 /WEB-INF/views 디렉터리의 .jsp 리소스로 확인합니다. -->
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
	
	<!-- 멀티파트 리졸버 설정 -->
	<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="maxUploadSize" value="10485760" /> <!-- 10mb 제한 -->
	</beans:bean>
	
	<!-- 멀티파트 업로드 디렉토리 지정 -->
	<beans:bean id="uploadPath" class="java.lang.String">
		<beans:constructor-arg value="D:\kim\springframework\spring1\src\main\webapp\resources\upload"></beans:constructor-arg>
	</beans:bean>
		
	<!-- 기본 메인 패키지 및 컨트롤러 패키지 설정 -->
	<context:component-scan base-package="com.spring1" />

</beans:beans>
```

<br>

**리소스 디렉토리 생성하기**

![리소스 디렉토리](./images/directory1.png)

생성할 디렉토리 이름 목록

```
프로젝트이름\src\main\webapp\data
프로젝트이름\src\main\webapp\WEB-INF\views\include
프로젝트이름\src\main\webapp\WEB-INF\views\board
프로젝트이름\src\main\webapp\WEB-INF\views\member
프로젝트이름\src\main\webapp\WEB-INF\views\reservate
프로젝트이름\src\main\webapp\WEB-INF\views\qna
프로젝트이름\src\main\webapp\WEB-INF\views\databank
프로젝트이름\src\main\webapp\WEB-INF\views\util
프로젝트이름\src\main\webapp\WEB-INF\views\sample
프로젝트이름\src\main\webapp\WEB-INF\views\check
프로젝트이름\src\main\webapp\WEB-INF\views\user
프로젝트이름\src\main\webapp\WEB-INF\views\free
```

<br><br>

<div id="2-1-5"><a href="#quick">목차로</a></div>

### 2-1-5. SqlMapper 마이바티스 설정하기 - mybatis-config.xml

**프로젝트이름\src\main\resources\mybatis-config.xml 파일을 새로 생성하고, Mapper에 대한 내용을 설정하도록 합니다.**

![맵퍼환경설정](./images/mybatis-config.xml.png)

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

<div id="2-1-6"><a href="#quick">목차로</a></div>

### 2-1-6. logger 설정하기 - log4j.xml, log4jdbc.log4j2.properties, logback.xml

**프로젝트이름\src\main\resources\log4j.xml을 열고 어떤 객체에 대한 로그를 기록할지 설정하도록 합니다.**

![로거설정](./images/log4j.xml.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE log4j:configuration PUBLIC "-//APACHE//DTD LOG4J 1.2//EN" "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

	<!-- Appenders -->
	<appender name="console" class="org.apache.log4j.ConsoleAppender">
		<param name="Target" value="System.out" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%-5p: %c - %m%n" />
		</layout>
	</appender>
	<!-- level의 지정가능한 value:FATAL<ERROR<WARN<INFO<DEBUG<TRACE -->
	<!-- FATAL : 심각한 시스템 이상 내용 표시 -->
	<!-- ERROR : 요청에 대한 문제 발생시 표시 -->
	<!-- WARN : 처리는 가능하지만 경고성 메시지 발신 표시 -->
	<!-- INFO : 정보성 메시지 표시 -->
	<!-- DEBUG : 실행 내용에 대한 설명을 표시 -->
	<!-- TRACE : 실행 내용이나 추적할 경로 등을 표시 -->
	<!-- Application Loggers -->
	<logger name="com.spring1.myapp">
		<level value="info" />
	</logger>
	
	<logger name="com.spring1.controller">
		<level value="info" />
	</logger>
	
	<logger name="com.spring1.dao">
		<level value="info" />
	</logger>

	<logger name="com.spring1.dto">
		<level value="info" />
	</logger>
	
	<logger name="com.spring1.service">
		<level value="info" />
	</logger>
	
	<!-- 3rdparty Loggers -->
	<logger name="org.springframework.core">
		<level value="info" />
	</logger>
	
	<logger name="org.springframework.beans">
		<level value="info" />
	</logger>
	
	<logger name="org.springframework.context">
		<level value="info" />
	</logger>

	<logger name="org.springframework.web">
		<level value="info" />
	</logger>

	<!-- Root Logger -->
	<root>
		<priority value="warn" />
		<appender-ref ref="console" />
	</root>
	
</log4j:configuration>

```

<br>

**프로젝트이름\src\main\resources\log4jdbc.log4j2.properties 파일을 새로 만들어 로거특성정보를 저장합니다.**

![로거특성파일](./images/log4jdbc.log4j2.properties.png)

```properties
log4jdbc.spylogdelegator.name=net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator
```

<br>

**프로젝트이름\src\main\resources\logback.xml 파일을 작성하여 로그정보를 주기적으로 백업할 내용을 설정합니다.**

![로그백업설정](./images/logback.xml.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- log4jdbc-log4j2 -->
	<logger name="jdbc.sqlonly"        level="DEBUG"/>
    <logger name="jdbc.sqltiming"      level="INFO"/>
    <logger name="jdbc.audit"          level="WARN"/>
    <logger name="jdbc.resultset"      level="ERROR"/>
    <logger name="jdbc.resultsettable" level="ERROR"/>
    <logger name="jdbc.connection"     level="INFO"/>
    
	<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
		<layout class="ch.qos.logback.classic.PatternLayout">
			<pattern>%d{HH:mm:ss.SSS} [%thread] %-4level [%logger.%method:%line]-
				%msg%n</pattern>
		</layout>
	</appender>

	<appender name="LOGFILE"
		class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>/WEB-INF/logback.log</file>
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<fileNamePattern>logback.%d{yyyy-MM-dd}.log</fileNamePattern>
			<!-- 30일 지난 파일은 삭제한다. -->
			<maxHistory>30</maxHistory>
		</rollingPolicy>
		<encoder>
			<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-4level [%logger.%method:%line]
				- %msg %n</pattern>
		</encoder>
	</appender>

	<!-- 로그의 레벨( 지정된 로그 레벨 이상만 수집 ) : DEBUG < INFO < WARN < ERROR < FATAL -->
	<logger name="myweb" additivity="false">
		<level value="INFO" />
		<appender-ref ref="LOGFILE" />
		<appender-ref ref="CONSOLE" />
	</logger>

	<root>
		<level value="INFO" />
		<appender-ref ref="CONSOLE" />
	</root>

</configuration>
```

<br><br><br>

<div id="2-2"><a href="#quick">목차로</a></div>

## 2-2. 영속 계층 개발작업하기

<div id="2-2-1"><a href="#quick">목차로</a></div>

### 2-2-1. 테이블 정의와 더미 데이터 추가 작업

**SQL Developer 를 실행하고, 오라클을 접속한 후 샘플(sample) 테이블을 작성합니다.**

![SQLDEVELOPER1](./images/sqldeveloper1.png)

```sql
create table sample (num int, title varchar2(50), res TIMESTAMP default sysdate);

select * from sample;

insert into sample values (1, '샘플1', default);
insert into sample values (2, '샘플2', default);
insert into sample values (3, '샘플3', default);

commit;
```

<br><br>

<div id="2-2-2"><a href="#quick">목차로</a></div>

### 2-2-2. SqlMapper(MyBatis xml file) 작성하기

**프로젝트이름\src\main\resources\mappers\sampleMapper.xml 파일을 새로 생성하고, sample에 대한 sql 명령과 그에 해당하는 xml태그를 작성하도록 합니다.**

![샘플맵퍼](./images/sampleMapper.xml.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC 
"-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="sample">
	<select id="getSampleList" resultType="com.spring1.dto.Sample">
		select * from sample
	</select>	
	<select id="getSample" resultType="com.spring1.dto.Sample">
		select * from sample where num=#{num}
	</select>	
	<insert id="insSample">
		insert into sample values (#{num}, #{title}, #{res})
	</insert>
	<update id="upSample">
		update sample set title=#{title} where num=#{num}
	</update>
	<delete id="delSample">
		delete from sample where num=#{num}
	</delete>
</mapper>
```

**프로젝트에 작성할 각종 패키지를 생성합니다.**

![패키지생성](./images/package1.png)

```
프로젝트이름\src\main\java\com\프로젝트이름\controller
프로젝트이름\src\main\java\com\프로젝트이름\dao
프로젝트이름\src\main\java\com\프로젝트이름\dto
프로젝트이름\src\main\java\com\프로젝트이름\myapp
프로젝트이름\src\main\java\com\프로젝트이름\service
프로젝트이름\src\main\java\com\프로젝트이름\test
프로젝트이름\src\main\java\com\프로젝트이름\util
```

<br><br>

<div id="2-2-3"><a href="#quick">목차로</a></div>

### 2-2-3. Repository(DAO) 작성하기

**프로젝트이름\src\main\java\com\프로젝트이름\dao\SampleDAO.java 작성**

![DAO작성](./images/SampleDAO.java.png)

```java
package com.spring1.dao;

import java.util.List;

import com.spring1.dto.Sample;

public interface SampleDAO {
	public List<Sample> getSampleList();
	public Sample getSample(int num);
	public void insSample(Sample sample);
	public void upSample(Sample sample);
	public void delSample(Sample sample);
}
```

<br>

**프로젝트이름\src\main\java\com\프로젝트이름\dao\SampleDAOImpl.java 작성**

![DAO작성](./images/SampleDAOImple.java.png)

```java
package com.spring1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring1.dto.Sample;

@Repository
public class SampleDAOImpl implements SampleDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Sample> getSampleList() {
		return sqlSession.selectList("sample.getSampleList");
	}

	@Override
	public Sample getSample(int num) {
		return sqlSession.selectOne("sample.getSample", num);
	}

	@Override
	public void insSample(Sample sample) {
		sqlSession.insert("sample.insSample", sample);
	}

	@Override
	public void upSample(Sample sample) {
		sqlSession.update("sample.upSample", sample);		
	}

	@Override
	public void delSample(Sample sample) {
		sqlSession.delete("sample.delSample", sample);		
	}
}

```

<br><br>

<div id="2-3"><a href="#quick">목차로</a></div>

## 2-3. Service 작성하기

**프로젝트이름\src\main\java\com\프로젝트이름service\SampleService.java 작성**

![서비스작성](./images/SampleService.java.png)

```java
package com.spring1.service;

import java.util.List;

import com.spring1.dto.Sample;

public interface SampleService {
	public List<Sample> getSampleList();
	public Sample getSample(int num);
	public void insSample(Sample sample);
	public void upSample(Sample sample);
	public void delSample(Sample sample);
}
```

<br>

**프로젝트이름\src\main\java\com\프로젝트이름\service\SampleServiceImpl.java 작성**

![서비스작성](./images/SampleServiceImpl.java.png)

```java
package com.spring1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring1.dao.SampleDAO;
import com.spring1.dto.Sample;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleDAO sampleDAO;
	
	@Override
	public List<Sample> getSampleList() {
		return sampleDAO.getSampleList();
	}

	@Override
	public Sample getSample(int num) {
		return sampleDAO.getSample(num);
	}

	@Override
	public void insSample(Sample sample) {
		sampleDAO.insSample(sample);
	}

	@Override
	public void upSample(Sample sample) {
		sampleDAO.upSample(sample);		
	}

	@Override
	public void delSample(Sample sample) {
		sampleDAO.delSample(sample);		
	}	
}
```

<br><br>

<div id="2-4"><a href="#quick">목차로</a></div>

## 2-4. Controller 작성하기

**프로젝트이름\src\main\java\com\프로젝트이름\service\SampleController.java 작성**

```java
package com.spring1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring1.dto.Sample;
import com.spring1.service.SampleService;

@Controller
@RequestMapping("/sample/")
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping("list.do")
	public String getSampleList(Model model) {
		List<Sample> list = sampleService.getSampleList();
		model.addAttribute("list", list);
		return "sample/sampleList";
	}
}
```

<br>

**프로젝트이름\src\main\java\com\spring1\myapp\HomeController.java 수정하기**


```java
package com.spring1.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	//localhost:8091/spring1/ => /WEB-INF/views/home.jsp
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home~! The Client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formatDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formatDate);
		model.addAttribute("author", "김기태");
		model.addAttribute("company", "파람소프트");
		
		return "home";
	}
}
```

<br><br>

<div id="2-5"><a href="#quick">목차로</a></div>

## 2-5. View(jsp) 작성하기

**프로젝트이름\src\main\webapp\WEB-INF\views\home.jsp 수정하기**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<h2>${serverTime }</h2>
	<h2>${author }</h2>
	<h2>${company }</h2>
	<hr>
	<a href="${path2 }/sample/list.do">샘플 목록</a>	
</body>
</html>
```

<br>

**프로젝트이름\src\main\webapp\WEB-INF\views\sample\sampleList.jsp 작성**

![샘플목록](./images/sampleList.jsp.png)

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>샘플 목록</title>
</head>
<body>
	<c:forEach var="sample" items="${list}">
		<p>번호 : ${sample.num }</p>
		<p>제목 : ${sample.title }</p>
		<p>일시 : ${sample.res }</p>
		<hr>
	</c:forEach>
</body>
</html>
```

<br><br><hr><br><br>

<div id="3"><a href="#quick">목차로</a></div>

# 3. DI (Dependency Injection)와 IoC(Invert Of Control)

- 의존성 주입 : DI (Dependency Injection)
- 제어의 역전 : IoC(Invert Of Control)

<br>

<div id="3-1"><a href="#quick">목차로</a></div>

## 3-1. 의존성 주입(Dependency Injection)

```
의존성 주입(DI)은 스프링 프레임워크가 지원하는 핵심 기능 중 하나로서, 객체 사이의 의존관계가 
객체 자신이 아닌 외부에 의해 결정되는 디자인 패턴입니다. 스프링 IoC 컨테이너는 어떤 객체(A)가 
필요로 하는, 의존관계에 있는 다른 객체(B)를 직접 생성하여 A 객체로 주입(설정)하는 역할을 
담당합니다. 컴포넌트를 구성하는 객체의 생성과 의존관계의 연결 처리를 해당 객체가 아닌 컨테이너가 
대신하기 때문에 제어의 역전(Inversion of Control)이라고 합니다. 의존성 주입을 사용하면 종속성과 
결합도(coupling)가 낮아져 테스트가 용이해지고 재사용성, 유연성, 확장성을 향상시킬 수 있게 됩니다.
```

<br><br>

<div id="3-2"><a href="#quick">목차로</a></div>

## 3-2. 의존성 주입 방법

| 방법 | 설명 |
|-------------|------------------------------------------------------------|
| 생성자 주입<br>(Constructor Injection) | 생성자를 통해 의존관계를 주입하는 방법. <br> 생성자 호출 시점에 1회 호출되는 것이 보장된다. <br> 주입받은 객체가 변하지 않거나, 반드시 객체의 주입이 필요한 <br> 경우에 강제하기 위해 사용할 수 있다  |
| 필드 주입<br>(Feild Injection) | 필드의 선언시 @Autowired 의 애너테이션에 의해 주입되는 방법. <br> 재생성이 잘 되지 않아 현재는 가급적 사용하지 않는다.  |
| 수정자 주입<br>(Setter Injection) | 필드 값을 변경하는 setter 메소드를 통해서 의존 관계를 주입하는 방법. <br> 주입받는 객체가 변경될 가능성이 있는 경우에 사용할 수 있다. |

<br><br>

<div id="3-3"><a href="#quick">목차로</a></div>

## 3-3. 의존성 빈 설정 방법

| 방법 | 설명 |
|-------------|------------------------------------------------------------|
| XML 기반 설정 방식<br> (XML-based configuration)	| XML 파일을 사용하는 방법으로 <bean> 요소의 class 속성에 FQCN(Fully-Qualified Class Name)을 기술하면 빈이 정의된다. <constructor-arg>나 <property> 요소를 사용해 의존성을 주입한다.
| 자바 기반 설정 방식<br> (Java-based configuration) | 자바 클래스에 @Configuration 애너테이션을, 메서드에 @Bean 애너테이션을 사용해 빈을 정의하는 방법. 최근에는 스프링 기반 애플리케이션 개발에 자주 사용되고 특히 스프링 부트에서 이 방식을 많이 활용한다. |
| 애너테이션 기반 설정 방식 <br>(Annotation-based configuration) | @Component 같은 마커 애너테이션(Marker Annotation)이 부여된 클래스를 탐색해서(Component Scan) DI 컨테이너에 빈을 자동으로 등록하는 방법이다. |

<br><br>

<div id="3-3-1"><a href="#quick">목차로</a></div>

### 3-3-1. XML 주입 방법

#### 3-3-1-1. XML 템플릿 제작 방법

![XML 템플릿 제작](./images/xml_template01.png)

![XML 템플릿 제작](./images/xml_template02.png)

<br>

**기본 포맷 - 빈 등록 XML** 

![XML 템플릿 제작](./images/xml_template03.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```

![XML 템플릿 제작](./images/xml_template04.png)

<br>

**애노테이션 설정을 사용하기 위한 포맷**

![XML 템플릿 제작](./images/xml_template05.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd 
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
</beans>
```

<br>

**기본 XML 템플릿 수정**

![XML 템플릿 제작](./images/xml_template06.png)

![XML 템플릿 제작](./images/xml_template07.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```

<br><br>

#### 3-3-1-2. 기본 XML 주입 실습

**주입 작업 패턴**

인터페이스 생성 -> DTO 생성 -> 인터페이스를 멤버로 하는 DTO 생성 -> Beans xml 파일 작성 -> java 테스트 -> Controller/Service/DAO 에 적용

<br><br>

**빈 설정 예시**

```xml
<bean id="id" class="com.dto.BeanTest"></bean>
```

- id : 빈 이름(id) 설정 
- class : 빈 타입 설정
- scope : 빈의 scope 설정 (singleton/prototype)
- primary : true를 지정하여 같은 타입의 빈이 여러개 일때 우선적으로 사용할 빈 설정
- lazy-init : true를 지정하여 빈을 사용할 때 객체가 생성되도록 설정
- init-method : 빈 객체가 생성될때 호출할 메소드 설정
- destroy-method : 빈 객체가 소멸될때 호출할 메소드 설정
- autowire : 자동주입 설정 (no, byName, byType, constructor)

<br><br>

**/src/main/resources/injectionContext.xml 파일 작성**

![XML 템플릿 제작](./images/xml_template08.png)

![XML 템플릿 제작](./images/xml_template09.png)

![XML 템플릿 제작](./images/xml_template10.png)

![XML 템플릿 제작](./images/xml_template11.png)

![XML 템플릿 제작](./images/xml_template12.png)

![XML 템플릿 제작](./images/xml_template14.png)

![XML 템플릿 제작](./images/xml_template15.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans.xsd">
	<!-- 빈 객체 생성 NoArgument -->
	<bean id="store1" class="com.spring1.dto.Store"></bean>
	
	<!--		Store store1 = new Store();	 -->
	
	<!-- 생성자 주입 : All Argument -->
	<bean id="store2" class="com.spring1.dto.Store">
		<constructor-arg name="storeNum" value="1"></constructor-arg>
		<constructor-arg name="storeName" value="HomePlus"></constructor-arg>
	</bean> 
	
	<!--		Store store2 = new Store(1, "HomePlus");	 -->
	
	<!-- 생성자(Constructor) 주입2 : All Argument - value -->
	<bean id="store3" class="com.spring1.dto.Store">
		<constructor-arg name="storeNum">
			<value>2</value>
		</constructor-arg>
		<constructor-arg name="storeName">
			<value>KingMart</value>
		</constructor-arg>
	</bean>
	
	<!--		Store store3 = new Store(2, "KingMart");	-->
	
	<!--  수정자 주입(setter injection) -->		
	<bean id="store4" class="com.spring1.dto.Store">
		<property name="storeNum" value="3"></property>
		<property name="storeName" value="EMart"></property>
	</bean>		
	
	<!--
		Store store4 = new Store();
		store4.setStoreNum(1);
		store4.setStoreName("HomePlus"); 
	 -->
</beans>
```

<br>

**src/main/java/com/spring1/test/InjectionTest1.java 파일 작성**

![인젝션 테스트](./images/injectionTest1.png)

```java
package com.spring1.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring1.dto.Store;

public class InjectionTest1 {
	public static void main(String[] args) {
		ApplicationContext ctx1 = new GenericXmlApplicationContext("classpath:injectionContext.xml");

		Store store1 = ctx1.getBean("store1", Store.class);
		System.out.println("store1"+store1.toString());
		
		Store store2 = ctx1.getBean("store2", Store.class);
		System.out.println("store2"+store2.toString());
		
		Store store3 = ctx1.getBean("store3", Store.class);
		System.out.println("store3"+store3.toString());
		
		Store store4 = ctx1.getBean("store4", Store.class);
		System.out.println("store4"+store4.toString());
	}
}
```

![인젝션 테스트](./images/injectionTest1_run.png)

![인젝션 테스트](./images/injectionTest1_res.png)

<br><br>

#### 3-3-1-3. 객체 주입 실습

**com.spring1.dto.Product 작성**

```java
package com.spring1.dto;

public interface Product {

}
```

<br>

**com.spring1.dto.Pencil 작성**

```java
package com.spring1.dto;

public class Pencil implements Product {
	private String proName;
	private int price;
	public Pencil() { }
	public Pencil(String proName, int price) {
		super();
		this.proName = proName;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Pencil [proName=" + proName + ", price=" + price + "]";
	}
}
```

<br>

**com.spring1.dto.Shop 작성**

```java
package com.spring1.dto;

public class Shop {
	private String shopName;
	private Product product;
	public Shop() { }
	public Shop(String shopName, Product product) {
		super();
		this.shopName = shopName;
		this.product = product;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", product=" + product + "]";
	}
}
```

<br>

**/src/main/resources/injectionContext2.xml 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="pencil" class="com.spring1.dto.Pencil">
		<constructor-arg name="proName">
			<value>A001</value>
		</constructor-arg>
		<constructor-arg name="price" value="1000" />
	</bean>
	
	<!-- 생성자 주입 -->
	<bean id="shop1" class="com.spring1.dto.Shop">
		<constructor-arg name="shopName" value="Daeil"></constructor-arg>
		<constructor-arg name="product" ref="pencil"></constructor-arg>
	</bean>
	
	<!-- 수정자 주입 -->
	<bean id="shop2" class="com.spring1.dto.Shop">
		<property name="shopName" value="YoungPoong"></property>
		<property name="product" ref="pencil"></property>	
	</bean>
	
	<!-- 생성자 주입2 -->
	<bean id="shop3" class="com.spring1.dto.Shop">
		<constructor-arg name="shopName">
			<value>Kyobo</value>
		</constructor-arg>
		<constructor-arg name="product">
			<ref bean="pencil" />
		</constructor-arg>
	</bean>
</beans>
```

<br>

**com.spring1.test.InjectionTest2 작성**

```java
package com.spring1.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring1.dto.Shop;

public class InjectionTest2 {

	public static void main(String[] args) {
	  ApplicationContext ctx2 = new GenericXmlApplicationContext("classpath:injectionContext2.xml");
		
 	  Shop shop1 = ctx2.getBean("shop1", Shop.class);
	  System.out.println(shop1.toString());
		
	  Shop shop2 = (Shop) ctx2.getBean("shop2", Shop.class);
	  System.out.println(shop2.toString());
		
	  Shop shop3 = (Shop) ctx2.getBean("shop3", Shop.class);
	  System.out.println(shop3.toString());
	}

}
```

<br><br>

#### 3-3-1-4. List 컬렉션 주입 실습

**com.spring1.dto.Goods 작성**

```java
package com.spring1.dto;

public interface Goods {

}
```

<br>

**com.spring1.dto.Chair 작성**

```java
package com.spring1.dto;

public class Chair implements Goods {
	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Chair [price=" + price + "]";
	}
	
}
```

<br> <a href="#quick">목차로</a> <br>

**com.spring1.dto.Desk 작성**

```java
package com.spring1.dto;

public class Desk implements Goods {
	private double size;

	public Desk(double size) {
		super();
		this.size = size;
	}

	@Override
	public String toString() {
		return "Desk [size=" + size + "]";
	}
	
}
```

<br>

**com.spring1.dto.Shop 작성**

```java
package com.spring1.dto;

public class Shop {
	private String shopName;
	private Product product;
	public Shop() { }
	public Shop(String shopName, Product product) {
		super();
		this.shopName = shopName;
		this.product = product;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", product=" + product + "]";
	}
}
```

<br>

**src/main/resources/injectionContext3.xml 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="desk" class="com.spring1.dto.Desk">
		<constructor-arg name="size" value="10"></constructor-arg>
	</bean>
	<bean id="chair" class="com.spring1.dto.Chair">
		<property name="price" value="50000"></property>
	</bean>
	<bean id="market1" class="com.spring1.dto.Market">
		<property name="marketName" value="Sun"></property>
		<property name="goodsList">
			<list>
				<ref bean="chair" />
				<ref bean="desk" />
				<bean class="com.spring1.dto.Desk">
					<constructor-arg name="size" value="4"></constructor-arg>
				</bean>
			</list>
		</property>
	</bean>
	
</beans>
```

<br>

**com.spring1.test.InjectionTest3 작성**

```java
package com.spring1.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring1.dto.Market;

public class InjectionTest3 {

	public static void main(String[] args) {
		ApplicationContext ctx3 = new GenericXmlApplicationContext("classpath:injectionContext3.xml");
		
		Market market1 = ctx3.getBean("market1", Market.class);
		System.out.println(market1);
	}

}
```

<br> <a href="#quick">목차로</a> <br><br>


#### 3-3-1-5. Set 컬렉션 주입 실습

**com.spring1.dto.Fruits 작성**

```java
package com.spring1.dto;

public interface Fruits {

}
```

<br>

com.spring1.dto.Apple 작성

```java
package com.spring1.dto;

public class Apple implements Fruits {
	private int price;
	public Apple() {}
	public Apple(int price) {
		super();
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Apple [price=" + price + "]";
	}
	
}
```

<br>

**com.spring1.dto.Cherry 작성**

```java
package com.spring1.dto;

public class Cherry implements Fruits{
	private double amount;
	public Cherry() { }
	public Cherry(double amount) {
		super();
		this.amount = amount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Cherry [amount=" + amount + "]";
	}
	
}
```

<br>

**com.spring1.dto.Kiwi 작성**

```java
package com.spring1.dto;

public class Kiwi implements Fruits {
	private int price;
	public Kiwi() { } 
	public Kiwi(int price) {
		super();
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Kiwi [price=" + price + "]";
	}
}
```

<br>

**com.spring1.dto.Mango 작성**

```java
package com.spring1.dto;

public class Mango implements Fruits{
	private int size;
	public Mango() {}
	public Mango(int size) {
		super();
		this.size = size;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Mango [size=" + size + "]";
	}
}
```

<br>

**com.spring1.dto.Mart 작성**

```java
package com.spring1.dto;

import java.util.Set;

public class Mart {
	private String martName;
	private Set<Fruits> fr;
	public Mart() { }
	public Mart(String martName, Set<Fruits> fr) {
		super();
		this.martName = martName;
		this.fr = fr;
	}
	public String getMartName() {
		return martName;
	}
	public void setMartName(String martName) {
		this.martName = martName;
	}
	public Set<Fruits> getFr() {
		return fr;
	}
	public void setFr(Set<Fruits> fr) {
		this.fr = fr;
	}
	@Override
	public String toString() {
		return "Mart [martName=" + martName + ", frSet=" + fr + "]";
	}
	
}
```

<br>

**src/main/resources/injectionContext4.xml 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="apple" class="com.spring1.dto.Apple">
		<constructor-arg name="price" value="5000" />
	</bean>
	<bean id="mango" class="com.spring1.dto.Mango">
		<constructor-arg name="size" value="400" />
	</bean>
	<bean id="cherry" class="com.spring1.dto.Cherry">
		<constructor-arg name="amount" value="900" />
	</bean>
	<bean id="mart1" class="com.spring1.dto.Mart">
		<property name="martName" value="bigMart" />
		<property name="fr">
			<set>
				<ref bean="apple"/>
				<ref bean="mango"/>
				<ref bean="cherry"/>
				<bean class="com.spring1.dto.Kiwi">
					<constructor-arg name="price" value="5000"></constructor-arg>
				</bean>
			</set>
		</property>
	</bean>
</beans>
```

<br>

**com.spring1.test.InjectionTest4 작성**

```java
package com.spring1.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring1.dto.Mart;

public class InjectionTest4 {
	public static void main(String[] args) {
		ApplicationContext ctx4 = new GenericXmlApplicationContext("classpath:injectionContext4.xml");
		
		Mart mart1 = ctx4.getBean("mart1", Mart.class);
		System.out.println(mart1);
	}
}
```

<br><a href="#quick">목차로</a><br><br>

#### 3-3-1-6. Map 컬렉션 주입 실습

**com.spring1.dto.Warehouse 작성**

```java
package com.spring1.dto;

import java.util.Map;

public class Warehouse {
	private Map<String, Object> map;
	public Warehouse() {}
	public Warehouse(Map<String, Object> map) {
		super();
		this.map = map;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return "Store [map=" + map + "]";
	}
}
```

<br>

**src/main/resources/injectionContext5.xml 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="apple" class="com.spring1.dto.Apple">
		<constructor-arg name="price" value="5000" />
	</bean>
	<bean id="mango" class="com.spring1.dto.Mango">
		<constructor-arg name="size" value="400" />
	</bean>
	<bean id="cherry" class="com.spring1.dto.Cherry">
		<constructor-arg name="amount" value="900" />
	</bean>
	<bean id="ware2" class="com.spring1.dto.Warehouse">
		<property name="map">
			<map>
				<entry>
					<key><value>martName</value></key>
					<value>EMart</value>
				</entry>
				<entry key="no" value="1004" value-type="int"></entry>
				<entry>
					<key><value>apple</value></key>
					<ref bean="apple"/>
				</entry>
				<entry>
					<key><value>mango</value></key>
					<ref bean="mango"/>
				</entry>
				<entry>
					<key><value>cherry</value></key>
					<ref bean="cherry"/>
				</entry>
			</map>
		</property>
	</bean>
</beans>
```

<br>

**com.spring1.test.InjectionTest5 작성**

```java
package com.spring1.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring1.dto.Warehouse;

public class InjectionTest5 {

	public static void main(String[] args) {
		ApplicationContext ctx5 = new GenericXmlApplicationContext("classpath:injectionContext5.xml");
		
		Warehouse ware2 = ctx5.getBean("ware2", Warehouse.class);
		System.out.println(ware2);
	}

}
```

<br><br><br>

<div id="3-3-2"><a href="#quick">목차로</a></div>

### 3-3-2. Annotaion 주입 방법

![Annotaion 주입](./images/injectionAnnotation01.png)

![Annotaion 주입](./images/injectionAnnotation02.png)

![Annotaion 주입](./images/injectionAnnotation03.png)

![Annotaion 주입](./images/injectionAnnotation04.png)

![Annotaion 주입](./images/injectionAnnotation05.png)

![Annotaion 주입](./images/injectionAnnotation06.png)

<br><br>

#### 3-3-2-1. 객체 이름으로 주입

![Annotaion 주입](./images/injectionAnnotation06_1.png)

**src/main/resources/injectionAnnotaion.xml 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd 
       http://www.springframework.org/schema/context 
       http://www.springframework.org/schema/context/spring-context.xsd">

	<!-- autowired 어노테이션으로 주입시 객체 이름으로 찾아 주입  -->
	<bean id="obj1" class="com.spring1.test.InjectionAnnotation1" autowire="byName" />
	<bean id="storeBean1" class="com.spring1.dto.Store" />
	<bean id="storeBean2" class="com.spring1.dto.Store" />

    <context:annotation-config/>
</beans>
```

<br>

**com.spring1.test.InjectionAnnotation1 작성**

![Annotaion 주입](./images/injectionAnnotation07.png)

```java
package com.spring1.test;

import com.spring1.dto.Store;

public class InjectionAnnotation1 {
	
	private Store storeBean1;
	private Store storeBean2;
	public Store getStoreBean1() {
		return storeBean1;
	}
	public void setStoreBean1(Store storeBean1) {
		this.storeBean1 = storeBean1;
	}
	public Store getStoreBean2() {
		return storeBean2;
	}
	public void setStoreBean2(Store storeBean2) {
		this.storeBean2 = storeBean2;
	}
}
```

<br><br>

#### 3-3-2-2. 객체 타입으로 주입

![Annotaion 주입](./images/injectionAnnotation06_2.png)

**src/main/resources/injectionAnnotaion.xml 에 내용 추가**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd 
       http://www.springframework.org/schema/context 
       http://www.springframework.org/schema/context/spring-context.xsd">
	   
	<!-- autowired 어노테이션으로 주입시 객체 이름으로 찾아 주입  -->
	<bean id="obj1" class="com.spring1.test.InjectionAnnotation1" autowire="byName" />
	<bean id="storeBean1" class="com.spring1.dto.Store" />
	<bean id="storeBean2" class="com.spring1.dto.Store" />

	<!-- autowired 어노테이션으로 주입시 객체 타입으로 찾아 주입  -->
	<bean id="obj2" class="com.spring1.test.InjectionAnnotation2" autowire="byType" />
	<bean id="shopBean1" class="com.spring1.dto.Shop" />

    <context:annotation-config/>
</beans>
```

<br>


**com.spring1.test.InjectionAnnotation2 작성**

![Annotaion 주입](./images/injectionAnnotation08.png)

```java
package com.spring1.test;

import com.spring1.dto.Shop;

public class InjectionAnnotation2 {
	private Shop shopBean1;
	public Shop getShopBean1() {
		return shopBean1;
	}
	public void setShopBean1(Shop shopBean1) {
		this.shopBean1 = shopBean1;
	}
}
```

<br><br>

#### 3-3-2-3. 생성자로 주입

![Annotaion 주입](./images/injectionAnnotation06_3.png)

**src/main/resources/injectionAnnotaion.xml 에 내용 추가**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd 
       http://www.springframework.org/schema/context 
       http://www.springframework.org/schema/context/spring-context.xsd">
	   
	<!-- autowired 어노테이션으로 주입시 객체 이름으로 찾아 주입  -->
	<bean id="obj1" class="com.spring1.test.InjectionAnnotation1" autowire="byName" />
	<bean id="storeBean1" class="com.spring1.dto.Store" />
	<bean id="storeBean2" class="com.spring1.dto.Store" />

	<!-- autowired 어노테이션으로 주입시 객체 타입으로 찾아 주입  -->
	<bean id="obj2" class="com.spring1.test.InjectionAnnotation2" autowire="byType" />
	<bean id="shopBean1" class="com.spring1.dto.Shop" />

	<!-- autowired 어노테이션으로 주입시 생성자로 찾아 주입  -->
	<bean id="marketBean1" class="com.spring1.dto.Market" />
	<bean id="obj3" class="com.spring1.test.InjectionAnnotation3" autowire="constructor" />

    <context:annotation-config/>
</beans>
```

<br>


**com.spring1.test.InjectionAnnotation3 작성**

![Annotaion 주입](./images/injectionAnnotation09.png)

```java
package com.spring1.test;

import com.spring1.dto.Market;

public class InjectionAnnotation3 {
	private int data1;
	private String data2;
	private Market marketBean1;
	public InjectionAnnotation3(int data1, String data2, Market marketBean1) {
		super();
		this.data1 = data1;
		this.data2 = data2;
		this.marketBean1 = marketBean1;
	}
	public int getData1() {
		return data1;
	}
	public void setData1(int data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public Market getMarketBean1() {
		return marketBean1;
	}
	public void setMarketBean1(Market marketBean1) {
		this.marketBean1 = marketBean1;
	}
}
```

<br><br>

#### 3-3-2-4. 생성자로 주입 - 직접 필드 값 대입

![Annotaion 주입](./images/injectionAnnotation06_4.png)

**src/main/resources/injectionAnnotaion.xml 에 내용 추가**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd 
       http://www.springframework.org/schema/context 
       http://www.springframework.org/schema/context/spring-context.xsd">
	   
	<!-- autowired 어노테이션으로 주입시 객체 이름으로 찾아 주입  -->
	<bean id="obj1" class="com.spring1.test.InjectionAnnotation1" autowire="byName" />
	<bean id="storeBean1" class="com.spring1.dto.Store" />
	<bean id="storeBean2" class="com.spring1.dto.Store" />

	<!-- autowired 어노테이션으로 주입시 객체 타입으로 찾아 주입  -->
	<bean id="obj2" class="com.spring1.test.InjectionAnnotation2" autowire="byType" />
	<bean id="shopBean1" class="com.spring1.dto.Shop" />

	<!-- autowired 어노테이션으로 주입시 생성자로 찾아 주입  -->
	<bean id="marketBean1" class="com.spring1.dto.Market" />
	<bean id="obj3" class="com.spring1.test.InjectionAnnotation3" autowire="constructor" />

	<!-- autowired 어노테이션으로 주입시 생성자로 찾아 주입  -->
	<bean id="obj4" class="com.spring1.test.InjectionAnnotation4" autowire="constructor">
		<constructor-arg value="1000" index="0" type="int" />
		<constructor-arg value="문자열데이터" index="1" />
	</bean>

    <context:annotation-config/>
</beans>
```

<br><a href="#quick">목차로</a><br>


**com.spring1.test.InjectionAnnotation4 작성**

![Annotaion 주입](./images/injectionAnnotation10.png)

```java
package com.spring1.test;

public class InjectionAnnotation4 {
	private int data1;
	private String data2;
	public InjectionAnnotation4(int data1, String data2) {
		super();
		this.data1 = data1;
		this.data2 = data2;
	}
	public int getData1() {
		return data1;
	}
	public void setData1(int data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
}
```

<br><br>

#### 3-3-2-5. Autowired Annotation 주입 실습 

**com.spring1.test.InjectionAnnotationTest1 작성**

![Annotaion 주입](./images/injectionAnnotation11.png)

```java
package com.spring1.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring1.dto.Market;
import com.spring1.dto.Shop;
import com.spring1.dto.Store;

@Service
public class InjectionAnnotationTest1 {

	@Autowired
	private InjectionAnnotation1 obj1;
	
	@Autowired
	private InjectionAnnotation2 obj2;
	
	@Autowired
	private InjectionAnnotation3 obj3;
	
	@Autowired
	private InjectionAnnotation4 obj4;

	public Store getStore() { return obj1.getStoreBean1();	}
	
	public Shop getShop() {	return obj2.getShopBean1();	}
	
	public Market getMarket() {	return obj3.getMarketBean1();	}
	
	public int getData1() {	return obj4.getData1();	}
}
```

<br><br><br>

<div id="3-3-3"><a href="#quick">목차로</a></div>

### 3-3-3. Java Code 주입 방법

#### 3-3-3-1. 객체 주입 실습1

**com.spring1.vo.InjectionJava1 작성**

![Java 클래스 주입](./images/injectionJava01.png)

```java
package com.spring1.vo;

import com.spring1.dto.Store;

public class InjectionJava1 {
	private Store store;

	public InjectionJava1(Store store) {
		super();
		this.store = store;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
}
```

<br><br>

**com.spring1.test.InjectionJavaTest1 작성**

![Java 클래스 주입](./images/injectionJava02.png)

```java
package com.spring1.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring1.dto.Store;
import com.spring1.vo.InjectionJava1;

@Service
public class InjectionJavaTest1 {

	private InjectionJava1 injectionJava1;	//필드 주입

	//생성자(Constructor)로 주입
	@Autowired
	public InjectionJavaTest1(InjectionJava1 injectionJava1) {
		this.injectionJava1 = injectionJava1;
	}

	//수정자(setter) 주입
	@Autowired
	public void setInjectionJava1(InjectionJava1 injectionJava1) {
		this.injectionJava1 = injectionJava1;
	}

	public Store getStore() {
		return injectionJava1.getStore();
	}	
}
```

<br><br>



#### 3-3-3-2. 객체 주입 실습2

**com.spring1.vo.InjectionJava2 작성**

![JAVA주입실습](./images/injectionJava03.png)

```java
package com.spring1.vo;
public class InjectionJava2 {
	private int data1;
	private double data2;
	private String data3;
	public InjectionJava2(int data1) {
		this.data1 = data1;
		this.data2 = 0.0;
		this.data3 = null;
	}
	public InjectionJava2(double data2) {
		this.data1 = 0;
		this.data2 = data2;
		this.data3 = null;
	}
	public InjectionJava2(String data3) {
		this.data1 = 0;
		this.data2 = 0.0;
		this.data3 = data3;
	}
}
```

<br>

**com.spring1.test.InjectionJavaTest2 작성**

![JAVA주입실습](./images/injectionJava04.png)

```java
package com.spring1.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring1.vo.InjectionJava2;

@Service
public class InjectionJavaTest2 {

	//필드 주입
	private InjectionJava2 java2;

	//생성자 주입
	@Autowired
	public InjectionJavaTest2(InjectionJava2 java2) {
		this.java2 = java2;
	}

	//수정자 주입
	@Autowired
	public void setJava2(InjectionJava2 java2) {
		this.java2 = java2;
	}
	
	public InjectionJava2 getJava2() {
		return java2;
	}	
}
```

<div id="3-4"><a href="#quick">목차로</a></div>

## 3-4. 스프링프레임워크의 DI와 IoC

![스프링프레임워크의 DI와 IoC](./images/di_concept.jpg)

<br><br>

<div id="3-4-1"><a href="#quick">목차로</a></div>

### 3-4-1. 의존성 주입(DI) 관련 어노테이션

| 어노테이션 | 설명 |
|-------------------|------------------------------------------------------------|
| @Autowired | 변수 위에 설정하여 해당 타입의 객체를 찾아서 자동으로 할당.<br>org.springframework.beans.factory.annotation.Autowired |
| @Qualifier | 특정 객체의 이름을 이용하여 의존성을 주입할 때 사용. <br> org.springframework.beans.factory.annotation.Qualifier |
| @Inject | @Autowired 똑같다(Java 제공 어노테이션) <br>javax.annotation.Inject |
| @Resource | 해당 타입의 객체를 찾아서 자동으로 할당하고, 객체를 이용해 의존성을 주입할 떄 사용.<br> (@Autowired와 @Qualifier의 기능을 결합한것)<br>javax.annotation.Resource |

<br>

**@Autowired 주입 실습**

```java
package com.spring1.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring1.dao.SampleDAO;
import com.spring1.dao.SampleDAOImpl;

public class TestAutowired {
	
	@Autowired
	private SampleDAO sampleDAO;

	public TestAutowired() {
		this.sampleDAO = new SampleDAOImpl();
	}
}
```

<br><br>

**@Qualifier 주입 실습**

```java
package com.spring1.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.spring1.dao.SampleDAO;
import com.spring1.dao.SampleDAOImpl;

public class TestQualifier {

	@Autowired
	@Qualifier("testDAO")
	private SampleDAO sampleDAO;
	
	public TestQualifier() {
		this.sampleDAO = new SampleDAOImpl();
	}
}
```

<br><br>

**Inject 주입 실습 전에 반드시 pom.xml에 먼저 annoation-api를 의존성 등록하기**

<br><br>

```xml
	...중략....
	<dependencies>
	...중략....
		<dependency>
		    <groupId>javax.annotation</groupId>
		    <artifactId>javax.annotation-api</artifactId>
		    <version>1.2</version>
		</dependency>
  </dependencies>
```
<br><br>

**@Inject 주입 실습**

```java
package com.spring1.test;

import javax.inject.Inject;

import com.spring1.dao.SampleDAO;
import com.spring1.dao.SampleDAOImpl;

public class TestInject {

	@Inject
	private SampleDAO sampleDAO; 
	
	public TestInject() {
		this.sampleDAO = new SampleDAOImpl();
	}
}
```

<br><br>

**@Resource 주입 실습**

```java
package com.spring1.test;

import javax.annotation.Resource;

import com.spring1.dao.SampleDAO;
import com.spring1.dao.SampleDAOImpl;

public class TestResource {
	
	@Resource(name="testDAO")
	private SampleDAO sampleDAO;
	
	public TestResource() {
		this.sampleDAO = new SampleDAOImpl();
	}
}
```



<br><hr><br>


