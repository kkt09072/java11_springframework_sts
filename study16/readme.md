<nav id="quick" style="">
	<label for="chap16" style="color:black;font-size:28px;">16. Open API 와 API 용용 및 부가 기능3</label>
	<input type="radio" name="chap" id="chap16" style="display:none;">
	<ul class="menu">
                <li><a href="#16-1" style="color:black;font-size:20px;text-decoration:none;">16-1. Chat GPT Api 구현</a></li>
                <li><a href="#16-2" style="color:black;font-size:20px;text-decoration:none;">16-2. 스케줄러를 활용한 예약 및 알림 구현</a></li>
                <li><a href="#16-3" style="color:black;font-size:20px;text-decoration:none;">16-3. Google Sheet를 이용한 스프레드시트 기능 구현</a></li>
                <li><a href="#16-4" style="color:black;font-size:20px;text-decoration:none;">16-4. 멀티 채팅방 기능 구현</a></li>
                <li><a href="#16-5" style="color:black;font-size:20px;text-decoration:none;">16-5. 쪽지함 기능 구현</a></li>
                <li><a href="#16-6" style="color:black;font-size:20px;text-decoration:none;">16-6. RabbitMQ 메신저 기능 구현</a></li>
                <li><a href="#16-7" style="color:black;font-size:20px;text-decoration:none;">16-7. 음력/양력 달력 기능 구현</a></li>
                <li><a href="#16-8" style="color:black;font-size:20px;text-decoration:none;">16-8. Drag&Drop 업로드 게시판</a></li>
	</ul>
</nav>    

# 16. Open API 와 API 용용 및 부가 기능3

<div id="16-1"></div>

## 16-1. Chat GPT Api 구현

### 16-1-1. HttpClient 주요 패키지 및 클래스

- Apache HttpClient 라이브러리는 HTTP 요청을 생성하고 보내는 데 사용되는 강력하고 유연한 Java 라이브러리입니다. 이를 통해 HTTP 클라이언트 애플리케이션을 쉽게 작성할 수 있으며, 다양한 기능과 유연한 구성 옵션을 제공합니다. HttpClient 라이브러리는 HTTP/1.1, HTTP/2 지원, 연결 풀링, 쿠키 관리, 인증 등을 포함한 많은 기능을 제공합니다.

<br><br><br>

#### 16-1-1-1. org.apache.http.client 패키지

- 이 패키지는 클라이언트 쪽 HTTP 기능을 제공합니다.

| 클래스 및 인터페이스 | 설명 |
|---------------|-------------------------------------------------------------------------------------------------|
| HttpClient | HTTP 요청을 보내기 위한 메인 인터페이스. <br> 메소드 : <br> CloseableHttpResponse execute(HttpUriRequest request): HTTP 요청을 실행하고 응답을 반환. <br> CloseableHttpResponse execute(HttpUriRequest request, HttpContext context): 컨텍스트를 사용하여 HTTP 요청을 실행하고 응답을 반환. |
| HttpClients | HttpClient 객체를 생성하기 위한 팩토리 클래스. <br> 메소드 : <br> static CloseableHttpClient createDefault(): 기본 설정으로 CloseableHttpClient 인스턴스를 생성. <br> static CloseableHttpClient createSystem(): 시스템 기본값을 사용하여 CloseableHttpClient 인스턴스를 생성. |

<br><br><br>

#### 16-1-1-2. org.apache.http.client.methods

- 이 패키지는 HTTP 요청을 나타내는 클래스들을 포함합니다.

| 클래스 및 인터페이스 | 설명 |
|---------------|-------------------------------------------------------------------------------------------------|
| HttpGet | HTTP GET 요청을 나타내는 클래스. <br> 생성자 : <br> HttpGet(String uri): 지정된 URI로 GET 요청을 생성. <br> 메소드 : <br> HttpUriRequest에서 상속된 메소드.
| HttpPost |HTTP POST 요청을 나타내는 클래스. <br> 생성자 : <br> HttpPost(String uri): 지정된 URI로 POST 요청을 생성. <br> 메소드 : <br> HttpEntityEnclosingRequestBase에서 상속된 메소드로, 엔티티 설정 메소드 포함. |

<br><br><br>

#### 16-1-1-3. org.apache.http.impl.client

- 이 패키지는 HttpClient 인터페이스의 구현체를 포함합니다.

| 클래스 및 인터페이스 | 설명 |
|---------------|-------------------------------------------------------------------------------------------------|
| CloseableHttpClient | 설명: 클라이언트를 닫을 수 있는 HttpClient의 확장 인터페이스. <br> 메소드 : <br> CloseableHttpResponse execute(HttpUriRequest request): HTTP 요청을 실행하고 응답을 반환. <br> void close(): 클라이언트를 닫아 자원을 해제. |
| HttpClients | HttpClient 구현체를 생성하기 위한 유틸리티 클래스. <br> 메소드 : <br> static CloseableHttpClient createDefault(): 기본 설정으로 CloseableHttpClient 인스턴스를 생성. |

<br><br><br>

#### 16-1-1-4. org.apache.http

- 이 패키지는 HTTP 프로토콜의 핵심 클래스와 인터페이스를 포함합니다.

| 클래스 및 인터페이스 | 설명 |
|---------------|-------------------------------------------------------------------------------------------------|
| HttpResponse | HTTP 응답을 나타내는 인터페이스. <br> 메소드 : <br> StatusLine getStatusLine(): 응답의 상태 줄을 반환. <br> HttpEntity getEntity(): 응답의 엔티티를 반환. |
| HttpEntity | HTTP 메시지의 엔티티를 나타내는 인터페이스. <br> 메소드 : <br> InputStream getContent(): 엔티티 콘텐츠를 읽을 수 있는 입력 스트림을 반환. <br> long getContentLength(): 엔티티의 콘텐츠 길이를 반환. |

<br><br><br>

#### 16-1-1-5. HttpClient 테스트 코드

**com.spring1.test.HttpClientTest 작성**

```
package com.spring1.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {
    public static void main(String[] args) {
        // HttpClient 생성
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // GET 요청 생성
            HttpGet request = new HttpGet("https://www.naver.com");

            // 요청 실행
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // 응답 상태 코드 확인
                System.out.println(response.getStatusLine().getStatusCode());

                // 응답 엔티티 확인 및 출력
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

<br><br><br><br>

### 16-1-2. 의존성 라이브러리 추가

**pom.xml 수정**

```xml
        <!-- JSON processing -->
        <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-databind</artifactId>
                <version>2.9.6</version>
        </dependency>

        <!-- HttpClient for API calls -->
        <dependency>
                <groupId>org.apache.httpcomponents</groupId>
                <artifactId>httpclient</artifactId>
                <version>4.5.6</version>
        </dependency>
```

<br><br><br>

### 16-1-3. 서비스 클래스 작성

**com.spring1.service.ChatGPTService 작성**

```java
package com.spring1.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChatGPTService {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String MODEL = "gpt-3.5-turbo-16k"; // 사용할 모델 명시

    public String getResponse(String prompt) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL);

        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + API_KEY);

        StringEntity requestEntity = new StringEntity(
               "{\"model\":\"" + MODEL + "\", \"prompt\":\"" + prompt + "\", \"max_tokens\":150}"
        );
        httpPost.setEntity(requestEntity);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            String jsonResponse = EntityUtils.toString(response.getEntity());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            return rootNode.path("choices").get(0).path("text").asText();
        }
    }
}
```

<br><br><br>

<div style="font-size:32px;color:red">아래 openai 개발자 등록 사이트로 이동하여 가입 후 개발자 등록을 하여야 합니다.</div>

[openai 개발자 등록 URL](https://platform.openai.com/docs/overview)

로그인 후 `https://platform.openai.com/settings/organization/general` 페이지에서 메뉴 `[Create project]` 를 눌러 프로젝트를 추가하고, 

상단 메뉴의 `[프로젝트명]` 링크를 클릭하고, `[Organization Overview]` 메뉴를 클릭한 후 왼쪽의 `[API Keys]` 메뉴에서 오른쪽 상단의 `[+ Create new secret key]` 버튼을 눌러 새로운 API key를 생성하고, 그 곳에 나타난 키를 새로 만들 ChatGPTService 클래스의 API_KEY 상수 필드의 값으로 지정하면 됨.

<br><br><br>

### 16-1-4. 컨트롤러 클래스 작성

**com.spring1.controller.ChatBotController**

```java
package com.spring1.controller;

import com.spring1.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatBotController {

    @Autowired
    private ChatGPTService chatGPTService;

    @GetMapping("/chatbot/home")
    public String index() {
        return "chatbot/home";
    }

    @PostMapping("/chatbot/chat")
    public String chat(@RequestParam("prompt") String prompt, Model model) {
        try {
            String response = chatGPTService.getResponse(prompt);
            model.addAttribute("response", response);
        } catch (Exception e) {
            model.addAttribute("response", "Error: " + e.getMessage());
        }
        return "chatbot/home";
    }
}
```

<br><br><br>

### 16-1-5. View(JSP) 작성

**src/main/webapp/WEB-INF/views/home.jsp 수정**

```jsp
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
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/chatbot/home">Chat GPT</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/chatbot/home.jsp 작성**

```jsp
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
    <title>ChatBot</title>
</head>
<body>
    <h1>ChatBot</h1>
    <form method="post" action="${path2}/chatbot/chat">
        <label for="prompt">Enter your prompt:</label>
        <input type="text" id="prompt" name="prompt" required/>
        <button type="submit">Send</button>
    </form>
    <h2>Response:</h2>
    <p>${response}</p>
</body>
</html>
```

<br><br><hr><br><br>

<div id="16-2"></div>

## 16-2. 스케줄러를 활용한 예약 및 알림 구현

- Quartz Scheduler는 주로 다음과 같은 주요 인터페이스와 클래스로 구성되어 있습니다. 이들은 작업(Job), 트리거(Trigger), 스케줄러(Scheduler)를 정의하고 관리하는 역할을 합니다.

### 16-2-1. 스케줄러의 주요 클래스와 인터페이스

#### 16-2-1-1. Scheduler

- Scheduler 인터페이스는 스케줄링 엔진의 주요 API를 정의합니다.

| 메소드 | 설명 |
|---------------------------------------------------------|--------------------------------------------------|
| void start() | 스케줄러를 시작합니다. |
| void standby(): 스케줄러를 일시 중지합니다. |
| void shutdown(): 스케줄러를 종료합니다. |
| boolean isStarted() | 스케줄러가 시작되었는지 여부를 반환합니다. |
| boolean isShutdown() | 스케줄러가 종료되었는지 여부를 반환합니다. |
| Date scheduleJob(JobDetail jobDetail, Trigger trigger) | 주어진 JobDetail과 Trigger를 사용하여 Job을 스케줄링합니다. |
| void addJob(JobDetail jobDetail, boolean replace) | Job을 추가합니다. |
| boolean deleteJob(JobKey jobKey) | 주어진 JobKey로 Job을 삭제합니다. |
| boolean unscheduleJob(TriggerKey triggerKey) | 주어진 TriggerKey로 트리거를 삭제합니다. |

<br><br>

#### 16-2-1-2. Job

- Job 인터페이스는 작업을 정의합니다.

| 메소드 | 설명 |
|---------------------------------------------------------|--------------------------------------------------|
| void execute(JobExecutionContext context) | Job이 실행될 때 호출되는 메소드입니다. JobExecutionContext는 실행 환경을 제공합니다. |


<br><br>

#### 16-2-1-3. JobDetail

- JobDetail 클래스는 Job 인스턴스에 대한 메타데이터를 포함합니다.

| 종류 | 멤버명 | 설명 |
|---------|---------------------------------------------------------|--------------------------------------------------|
| 생성자 | JobDetail() | 기본 생성자. |
|   | JobDetail(JobKey key, String description, Class<? extends Job> jobClass, boolean durable, boolean shouldRecover) | JobDetail을 초기화하는 생성자. |
| 메소드 | JobKey getKey() | Job의 키를 반환합니다. |
|   | String getDescription() | Job의 설명을 반환합니다. |
|   | Class<? extends Job> getJobClass() | Job의 클래스를 반환합니다. |
|   | boolean isDurable() | Job이 내구성 있는지 여부를 반환합니다. |
|   | boolean requestsRecovery() | Job이 복구를 요청하는지 여부를 반환합니다. |

<br><br>

#### 16-2-1-4. Trigger

- Trigger 인터페이스는 Job이 실행될 시기를 정의합니다.

| 메소드 | 설명 |
|---------------------------------------------------------|--------------------------------------------------|
| Date getStartTime() | 트리거의 시작 시간을 반환합니다. |
| Date getEndTime() | 트리거의 종료 시간을 반환합니다. |
| Date getNextFireTime() | 트리거의 다음 실행 시간을 반환합니다. |
| Date getPreviousFireTime() | 트리거의 이전 실행 시간을 반환합니다. |
| TriggerKey getKey() | 트리거의 키를 반환합니다. |
| String getDescription() | 트리거의 설명을 반환합니다. |


<br><br>

#### 16-2-1-5. TriggerBuilder

- TriggerBuilder 클래스는 트리거를 빌드하기 위한 빌더 패턴을 제공합니다.


| 종류 | 멤버명 | 설명 |
|---------|---------------------------------------------------------------|--------------------------------------------|
| 생성자 | TriggerBuilder() | 기본 생성자. |
| 메소드 | TriggerBuilder<T> withIdentity(String name) | 트리거의 이름을 설정합니다. |
|   | TriggerBuilder<T> withIdentity(String name, String group) | 트리거의 이름과 그룹을 설정합니다. |
|   | TriggerBuilder<T> startAt(Date startTime) | 트리거의 시작 시간을 설정합니다. |
|   | TriggerBuilder<T> endAt(Date endTime) | 트리거의 종료 시간을 설정합니다. |
|   | TriggerBuilder<T> withSchedule(ScheduleBuilder<?> scheduleBuilder) | 트리거의 스케줄을 설정합니다. |
|   | Trigger build() | 트리거를 빌드합니다. |

<br><br>

#### 16-2-1-6. CronTrigger

- CronTrigger 인터페이스는 Cron 표현식을 사용하여 Job의 스케줄을 정의합니다.

| 메소드 | 설명 |
|---------------------------------------------------------|--------------------------------------------------|
| String getCronExpression() | Cron 표현식을 반환합니다. |
| TimeZone getTimeZone() | 트리거의 타임존을 반환합니다. |

<br><br>

#### 16-2-1-7. CronScheduleBuilder
 
- CronScheduleBuilder 클래스는 Cron 표현식을 기반으로 트리거 스케줄을 빌드하기 위한 빌더 패턴을 제공합니다.

| 메소드 | 설명 |
|---------------------------------------------------------------|-----------------------------------------------|
| static CronScheduleBuilder cronSchedule(String cronExpression) | Cron 표현식을 사용하여 CronScheduleBuilder를 생성합니다. |
| CronScheduleBuilder inTimeZone(TimeZone timezone): 타임존을 설정합니다. |
| CronScheduleBuilder withMisfireHandlingInstructionDoNothing() | 미스파이어 처리를 설정합니다. |
| CronScheduleBuilder withMisfireHandlingInstructionIgnoreMisfires() | 미스파이어를 무시하도록 설정합니다. |

<br><br><br>

### 16-2-1. 프로젝트 설정 

**pom.xml에 의존성 라이브러리 추가**

```xml 
<!-- Quartz Scheduler dependencies -->
    <dependency>
        <groupId>org.quartz-scheduler</groupId>
        <artifactId>quartz</artifactId>
        <version>2.3.0</version>
    </dependency>
```

<br><br>

**src/main/resources/spring-quartz.xml 작성**

```xml
<!--  -->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- Quartz Scheduler Factory Bean -->
    <bean id="schedulerFactoryBean" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <property name="jobDetails">
            <list>
                <ref bean="reservationJobDetail" />
                <ref bean="birthdayNotificationJobDetail" />
            </list>
        </property>
        <property name="triggers">
            <list>
                <ref bean="reservationTrigger" />
                <ref bean="birthdayNotificationTrigger" />
            </list>
        </property>
    </bean>

    <!-- Reservation Job Detail -->
    <bean id="reservationJobDetail" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
        <property name="jobClass" value="com.spring1.jobs.ReservationJob" />
        <property name="durability" value="true" />
    </bean>

    <!-- Reservation Trigger -->
    <bean id="reservationTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <property name="jobDetail" ref="reservationJobDetail" />
        <property name="cronExpression" value="0 0 9 * * ?" />
    </bean>

    <!-- Birthday Notification Job Detail -->
    <bean id="birthdayNotificationJobDetail" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
        <property name="jobClass" value="com.spring1.jobs.BirthdayNotificationJob" />
        <property name="durability" value="true" />
    </bean>

    <!-- Birthday Notification Trigger -->
    <bean id="birthdayNotificationTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <property name="jobDetail" ref="birthdayNotificationJobDetail" />
        <property name="cronExpression" value="0 0 10 * * ?" />
    </bean>

</beans>
```

<br><br>

**src/main/resources/mail-config.xml 작성**

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
        <property name="host" value="smtp.spring1.com" />
        <property name="port" value="587" />
        <property name="username" value="your-email@spring1.com" />
        <property name="password" value="your-email-password" />
        <property name="javaMailProperties">
            <props>
                <prop key="mail.transport.protocol">smtp</prop>
                <prop key="mail.smtp.auth">true</prop>
                <prop key="mail.smtp.starttls.enable">true</prop>
                <prop key="mail.debug">true</prop>
            </props>
        </property>
    </bean>

</beans>
```

<br><br>

**src/main/webapp/WEB-INF/spring/root-context.xml 수정**

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/mvc
                           http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <mvc:annotation-driven />
    <context:component-scan base-package="com.spring1" />
    <!-- 중간 생략 -->
    <import resource="classpath:spring-quartz.xml" />
    <import resource="classpath:mail-config.xml" />
</beans>
```

<br><br><br>

### 16-2-2. 도메인(DTO) 작성

**com.spring1.model.Reservation 작성**

```java
package com.spring1.model;

import java.util.Date;

public class Reservation {
    private String email;
    private Date date;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
```

<br><br>

**com.spring1.model.Birthday 작성**

```java
package com.spring1.model;

import java.util.Date;

public class Birthday {
    private String email;
    private Date date;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
```

<br><br><br>

### 16-2-3. Job 구현 클래스 작성

- 예약 알림과 생일 알림을 담당할 Job 클래스를 작성합니다.

**com.spring1.jobs.ReservationJob 작성**

```java
package com.spring1.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class ReservationJob implements Job {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 예약 알림 로직 구현
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@spring1.com");
        message.setSubject("Reservation Reminder");
        message.setText("This is a reminder for your reservation.");

        mailSender.send(message);
    }
}
```

<br><br>

**com.spring1.jobs.BirthdayNotificationJob 작성**

```java
package com.spring1.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class BirthdayNotificationJob implements Job {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 생일 알림 로직 구현
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@spring1.com");
        message.setSubject("Birthday Notification");
        message.setText("Happy Birthday!");

        mailSender.send(message);
    }
}
```

<br><br><br>

### 16-2-4. 서비스 작성

**com.spring1.service.NotificationService 작성**

```java
package com.spring1.service;

import com.spring1.model.Reservation;
import com.spring1.model.Birthday;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private Scheduler scheduler;

    public void scheduleReservation(Reservation reservation) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(ReservationJob.class)
                    .withIdentity("reservationJob", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("reservationTrigger", "group1")
                    .startAt(reservation.getDate())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withMisfireHandlingInstructionFireNow())
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void scheduleBirthday(Birthday birthday) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(BirthdayNotificationJob.class)
                    .withIdentity("birthdayJob", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("birthdayTrigger", "group1")
                    .startAt(birthday.getDate())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withMisfireHandlingInstructionFireNow())
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
```

<br><br><br>

### 16-2-5. 컨트롤러 작성

**com.spring1.controller.NotificationController 작성**

```java
package com.spring1.controller;

import com.spring1.model.Reservation;
import com.spring1.model.Birthday;
import com.spring1.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/schedule")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/reservation")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "schedule/reservationForm";
    }

    @PostMapping("/reservation")
    public String submitReservation(@ModelAttribute Reservation reservation, Model model) {
        notificationService.scheduleReservation(reservation);
        model.addAttribute("message", "Reservation scheduled successfully!");
        return "schedule/result";
    }

    @GetMapping("/birthday")
    public String showBirthdayForm(Model model) {
        model.addAttribute("birthday", new Birthday());
        return "schedule/birthdayForm";
    }

    @PostMapping("/birthday")
    public String submitBirthday(@ModelAttribute Birthday birthday, Model model) {
        notificationService.scheduleBirthday(birthday);
        model.addAttribute("message", "Birthday notification scheduled successfully!");
        return "schedule/result";
    }
}
```

<br><br><br>

### 16-2-6. View(JSP) 작성

**src/main/webapp/WEB-INF/views/home.jsp 수정**

```jsp
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
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/chatbot/home">Chat GPT</a></li>
        <li><a href="${path2 }/schedule/reservationForm">예약하기</a></li>
	</ul>
</body>
</html>
```


**src/main/webapp/WEB-INF/views/schedule/reservationForm.jsp 작성**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Form</title>
</head>
<body>
    <h2>Reservation Form</h2>
    <form:form method="post" modelAttribute="reservation">
        <table>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Date:</td>
                <td><form:input path="date" type="date" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/schedule/birthdayForm.jsp 작성**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Birthday Form</title>
</head>
<body>
    <h2>Birthday Form</h2>
    <form:form method="post" modelAttribute="birthday">
        <table>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Date:</td>
                <td><form:input path="date" type="date" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/schedule/result.jsp 작성**

```jsp
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
    <title>Result</title>
</head>
<body>
    <h2>Result</h2>
    <p>${message}</p>
    <a href="${path2}/schedule/reservation">Back to Reservation Form</a>
    <br/>
    <a href="${path2}/schedule/birthday">Back to Birthday Form</a>
</body>
</html>
```


<br><br><hr><br><br>

<div id="16-3"></div>

## 16-3. Google Sheet를 이용한 스프레드시트 기능 구현

### 16-3-1. Google API 라이브러리

- Google API Client, Google OAuth Client Jetty, and Google API Services Sheets 라이브러리는 Google API와 통합된 Java 애플리케이션을 개발할 때 사용되는 중요한 라이브러리입니다.

<br><br>

#### 16-3-1-1. google-api-client

**패키지: com.google.api.client**

- 이 패키지는 Google API 클라이언트 라이브러리를 포함합니다.

<br><br>

**주요 클래스**

| 클래스 및 인터페이스 | 설명 |
|---------------------|--------------------------------------------------------|
| GoogleApiClient | Google API 클라이언트를 설정하고 관리하는 주요 클래스입니다. |
| GoogleClientRequestInitializer | 클라이언트 요청을 초기화하는 인터페이스입니다. |
| HttpTransport | HTTP 전송을 관리하는 클래스입니다. |

<br><br>

**GoogleApiClient 클래스**

| 종류 | 멤버명 | 설명 |
|---------|-----------------------------|---------------------------------------------------------------------|
| 생성자 | GoogleApiClient.Builder | 빌더 패턴을 사용하여 GoogleApiClient 인스턴스를 생성합니다. |
| 메소드 | connect() | Google API 클라이언트와의 연결을 시작합니다. |
|   | disconnect() | Google API 클라이언트와의 연결을 끊습니다. |
|   | isConnected() | 클라이언트가 연결되어 있는지 확인합니다. |

<br><br>

**GoogleClientRequestInitializer 인터페이스**

| 메소드 | 설명 |
|--------------------------------------------------------|-----------------------------|
| initialize(AbstractGoogleClientRequest<?> request) | 주어진 요청을 초기화합니다. |

<br><br>

**HttpTransport 클래스**

| 종류 | 멤버명 | 설명 |
|---------|-----------------------------|---------------------------------------------------------------------|
| 생성자 | HttpTransport() | 새로운 HttpTransport 인스턴스를 생성합니다. |
| 메소드 | buildRequest(String method, String url) | HTTP 요청을 생성합니다. |


<br><br><br>

#### 16-3-1-2. google-oauth-client-jetty

**패키지: com.google.api.client.extensions.jetty.auth.oauth2**

- 이 패키지는 Jetty 서버와 함께 Google OAuth 2.0 클라이언트를 구현하는 데 사용됩니다.

<br><br>

**주요 클래스:**

- LocalServerReceiver: 로컬 서버 리시버를 구현하여 OAuth 2.0 인증 코드를 수신합니다.

<br><br>

**LocalServerReceiver 클래스**

| 종류 | 멤버명 | 설명 |
|---------|-----------------------------|---------------------------------------------------------------------|
| 생성자 | LocalServerReceiver() | 기본 생성자. |
|  | LocalServerReceiver.Builder() | 빌더 패턴을 사용하여 LocalServerReceiver 인스턴스를 생성합니다. |
| 메소드 | getRedirectUri() | OAuth 2.0 리디렉션 URI를 반환합니다. |
| waitForCode() | OAuth 2.0 인증 코드를 대기합니다. |
| stop() | 서버를 중지합니다. |

<br><br><br>

#### 16-3-1-3. google-api-services-sheets

**패키지: com.google.api.services.sheets.v4**

- 이 패키지는 Google Sheets API와 상호작용하기 위한 클래스들을 포함합니다.

<br><br>

**주요 클래스**

| 클래스 및 인터페이스 | 설명 |
|---------------------|--------------------------------------------------------|
| Sheets | Google Sheets API 클라이언트를 나타내는 클래스입니다. |
| SheetsRequest | Sheets API 요청을 나타내는 추상 클래스입니다. |
| SheetsRequestInitializer | Sheets API 요청을 초기화하는 클래스입니다. |

<br><br>

**Sheets 클래스**


| 종류 | 멤버명 | 설명 |
|---------|------------------------------------------------------|----------------------------------------------|
| 생성자 | Sheets(NetHttpTransport transport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) | Sheets API 클라이언트를 초기화합니다. |
| 메소드 | spreadsheets() | Sheets.Spreadsheets 객체를 반환하여 스프레드시트 관련 작업을 수행할 수 있습니다. |

<br><br>

**Sheets.Spreadsheets 클래스**

| 메소드 | 설명 |
|---------------------|--------------------------------------------------------|
| values() | Sheets.Spreadsheets.Values 객체를 반환하여 값 관련 작업을 수행할 수 있습니다. |

<br><br>

**Sheets.Spreadsheets.Values 클래스**

| 메소드 | 설명 |
|----------------------------------------------------------------|--------------------------------------------------------|
| get(String spreadsheetId, String range) | 특정 스프레드시트와 범위의 값을 가져옵니다.
| update(String spreadsheetId, String range, ValueRange body) | 특정 스프레드시트와 범위의 값을 업데이트합니다. |


<br><br>

**SheetsRequest 클래스**

| 종류 | 멤버명 | 설명 |
|---------|---------------------------------------------------------------------------------------|------------------------|
| 생성자 | SheetsRequest(Sheets client, String method, String uriTemplate, Object content, Class<T> responseClass) | 요청을 초기화합니다. |
| 메소드 | setDisableGZipContent(boolean disableGZipContent) | GZip 콘텐츠 압축을 비활성화합니다. |

<br><br>

**SheetsRequestInitializer 클래스**

| 메소드 | 설명 |
|----------------------------------------------------------------|--------------------------------------------------------|
| initialize(SheetsRequest<?> request) | 주어진 요청을 초기화합니다. |


<br><br><br>

#### 16-3-1-4. 작업 프로세스

1. Google Sheets API 설정: Google Cloud Platform에서 프로젝트를 생성하고 Google Sheets API를 활성화한 후, OAuth 2.0 클라이언트 ID를 생성해야 합니다.
2. Spring MVC 프로젝트 설정: Spring MVC 프로젝트를 설정하고 필요한 의존성을 추가합니다.
3. Google Sheets API 통합: Google Sheets API 클라이언트를 사용하여 스프레드시트와 상호 작용할 수 있도록 설정합니다.
4. Controller, Service, Repository 구현: 각 계층을 구현하여 Google Sheets와 상호작용할 수 있도록 합니다.


<br><br><br><br>

### 16-3-2. 프로젝트 설정

#### 16-3-2-1. Google Sheets API 설정

**Google Cloud Platform에서 프로젝트를 생성하고 Google Sheets API를 활성화합니다. OAuth 2.0 클라이언트 ID를 생성하고 JSON 파일을 다운로드합니다.**

<br><br><br>

#### 16-3-2-2. 의존성 라이브러리 추가

**pom.xml에 의존성 라이브러리 추가**

```xml
    <!-- Google Sheets API dependencies -->
    <dependency>
        <groupId>com.google.api-client</groupId>
        <artifactId>google-api-client</artifactId>
        <version>1.34.1</version> <!-- 최신 버전으로 업데이트 -->
    </dependency>
    <dependency>
        <groupId>com.google.oauth-client</groupId>
        <artifactId>google-oauth-client-jetty</artifactId>
        <version>1.34.1</version> <!-- 최신 버전으로 업데이트 -->
    </dependency>
    <dependency>
        <groupId>com.google.apis</groupId>
        <artifactId>google-api-services-sheets</artifactId>
        <version>v4-rev612-1.25.0</version>
    </dependency>
    <dependency>
        <groupId>com.google.http-client</groupId>
        <artifactId>google-http-client-jackson2</artifactId>
        <version>1.34.1</version> <!-- 최신 버전으로 업데이트 -->
    </dependency>
```

<br><br><br>

### 16-3-3. 서비스 작성

**com.spring1.service.GoogleSheetsService 작성**

```java
package com.spring1.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

@Service
public class GoogleSheetsService {

    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = GoogleSheetsService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public Sheets getSheetsService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = getCredentials(HTTP_TRANSPORT);
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
```

<br><br><br>


### 16-3-4. 컨트롤러 작성

**com.spring1.controller.SheetsController 작성**

```java
package com.spring1.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.spring1.service.GoogleSheetsService;

@Controller
public class SheetsController {
   
    @Autowired
    private GoogleSheetsService googleSheetsService;

    /*
    1. credentials.json 파일을 프로젝트의 리소스 경로에 배치합니다.
	2. 필요한 권한을 Google API 콘솔에서 설정하고 OAuth 2.0 클라이언트 ID를 생성합니다.
	3. tokens 디렉토리가 올바르게 설정되었는지 확인합니다.
    */
    
    @GetMapping("/sheets")
    public String getSheetsData(Model model) {
        try {
            Sheets sheetsService = googleSheetsService.getSheetsService();
            // Google Sheets API 호출 예시
            String spreadsheetId = "your_spreadsheet_id";
            String range = "Sheet1!A1:D10";
            var response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
            var values = response.getValues();
            
            model.addAttribute("values", values);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return "sheet/sheets";
    }

    @PostMapping("/sheets/save")
    public String saveData(@RequestParam("spreadsheetId") String spreadsheetId,
                           @RequestParam("range") String range,
                           @RequestParam("values") String values) throws IOException, GeneralSecurityException {
        Sheets sheetsService = googleSheetsService.getSheetsService();
        ValueRange body = new ValueRange().setValues(Collections.singletonList(
                Arrays.asList(values.split(","))));
        UpdateValuesResponse result = sheetsService.spreadsheets().values()
                .update(spreadsheetId, range, body)
                .setValueInputOption("RAW")
                .execute();
        return "redirect:/sheets";
    }

    @PostMapping("/sheets/load")
    public String loadData(@RequestParam("spreadsheetId") String spreadsheetId,
                           @RequestParam("range") String range,
                           Model model) throws IOException, GeneralSecurityException {
        Sheets sheetsService = googleSheetsService.getSheetsService();
        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        model.addAttribute("values", values);
        return "sheet/sheets";
    }
}
```

<br><br><br>

### 16-3-5. View(JSP) 작성

**src/main/webapp/WEB-INF/views/home.jsp 수정**

```jsp
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
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/chatbot/home">Chat GPT</a></li>
        <li><a href="${path2 }/schedule/reservationForm">예약하기</a></li>
        <li><a href="${path2 }/sheets">스프레드시트 작업하기</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/sheet/sheets.jsp 작성**

```jsp
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
    <title>Google Sheets Integration</title>
</head>
<body>
<h2>Google Sheets Integration</h2>

<form action="${path2 }/sheets/save" method="post">
    <label for="spreadsheetId">Spreadsheet ID:</label>
    <input type="text" id="spreadsheetId" name="spreadsheetId"><br>
    <label for="range">Range:</label>
    <input type="text" id="range" name="range"><br>
    <label for="values">Values (comma separated):</label>
    <input type="text" id="values" name="values"><br>
    <button type="submit">Save</button>
</form>

<form action="${path2 }/sheets/load" method="post">
    <label for="spreadsheetId">Spreadsheet ID:</label>
    <input type="text" id="spreadsheetId" name="spreadsheetId"><br>
    <label for="range">Range:</label>
    <input type="text" id="range" name="range"><br>
    <button type="submit">Load</button>
</form>

<h3>Loaded Data</h3>
<table border="1">
    <c:forEach var="row" items="${values}">
        <tr>
            <c:forEach var="cell" items="${row}">
                <td>${cell}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
```

<br><br><hr><br><br>


<div id="16-4"></div>

## 16-4. 멀티 채팅방 기능 구현

### 16-4-1. 관련 라이브러리와 패키지

- spring-messaging 라이브러리는 메시징 애플리케이션을 만들 때 유용한 다양한 구성 요소들을 제공하는 라이브러리입니다. 
- 이 라이브러리는 특히 WebSocket과 STOMP와 같은 메시징 프로토콜을 지원하는 데 중점을 두고 있습니다. 

<br><br>

#### 16-4-1-1. org.springframework.messaging

- spring-messaging 라이브러리에 속하는 패키지

<br>

**1. Message 인터페이스**

- 메시지 자체를 표현하는 인터페이스. 페이로드(payload)와 헤더(headers)를 포함함.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| getPayload() | 페이로드를 반환. |
| getHeaders() | 메시지 헤더를 반환. |

<br><br>

**2. MessageHeaders 클래스**

- 메시지의 헤더 정보를 저장.

| 종류 | 멤버명 | 설명 |
|---------|-----------------------------------------------------------------------------|------------------------------|
| 생성자 | MessageHeaders(Map<String, Object> headers) | 주어진 헤더 정보를 사용해 객체를 생성. |
| 메소드 | getId() | 메시지 ID를 반환. |
| getTimestamp() | 타임스탬프를 반환. |
| get(String key) | 주어진 키에 해당하는 헤더 값을 반환. |

<br><br>

**3. MessageHandler 인터페이스**

- 메시지를 처리하는 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| handleMessage(Message<?> message) | 주어진 메시지를 처리. |

<br><br>

**4. MessageChannel 인터페이스**

- 메시지를 전송하는 채널을 나타내는 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| send(Message<?> message) | 메시지를 전송. |
| send(Message<?> message, long timeout) | 지정된 타임아웃 내에 메시지를 전송. |

<br><br>

**5. SubscribableChannel 인터페이스**

- 메시지 핸들러를 구독할 수 있는 채널 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| subscribe(MessageHandler handler) | 메시지 핸들러를 구독. |
| unsubscribe(MessageHandler handler) | 메시지 핸들러 구독을 해지. |

<br><br>

#### 16-4-1-2. org.springframework.messaging.converter

- org.springframework.messaging의 하위 패키지

<br>

**1. MessageConverter 인터페이스**

- 객체와 메시지 간의 변환을 담당하는 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| fromMessage(Message<?> message, Class<?> targetClass) | 메시지를 주어진 클래스의 객체로 변환. |
| toMessage(Object payload, MessageHeaders headers) | 객체를 메시지로 변환. |

<br><br>

**2. SimpleMessageConverter 클래스**

- 기본적인 메시지 변환을 제공하는 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| fromMessage(Message<?> message, Class<?> targetClass) | 메시지를 변환. |
| toMessage(Object payload, MessageHeaders headers) | 객체를 메시지로 변환. |

<br><br><br>

#### 16-4-1-3. org.springframework.messaging.handler

- org.springframework.messaging의 하위 패키지

<br>

**1. AbstractMessageHandler 클래스**
 
- 메시지 핸들러의 기본 구현을 제공하는 추상 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| handleMessage(Message<?> message) | 메시지를 처리. |
| getRequiredHeaders() | 필수 헤더를 반환. |
| getLogger() | 로거를 반환. |

<br><br>


**2. InvocableHandlerMethod 클래스**

- 메시지를 처리하기 위해 호출 가능한 메서드를 캡슐화하는 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| invoke(Message<?> message, Object... providedArgs) | 메시지를 처리하기 위해 메서드를 호출. |

<br><br><br>

#### 16-4-1-4. org.springframework.messaging.simp

- org.springframework.messaging의 하위 패키지

<br>

**1. SimpMessageSendingOperations 인터페이스**

- 간단한 메시지 전송 작업을 정의하는 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| convertAndSend(String destination, Object payload) | 주어진 목적지로 메시지를 변환하여 전송. |
| convertAndSendToUser(String user, String destination, Object payload) | 특정 사용자에게 메시지를 변환하여 전송. |

<br><br>

**2. SimpMessageHeaderAccessor 클래스**

- 메시지 헤더에 접근하기 위한 클래스.

| 생성자 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| SimpMessageHeaderAccessor() | 기본 생성자. |

<br>

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| getSessionId() | 세션 ID를 반환. |
| getUser() | 사용자 정보를 반환. |
| create(SimpMessageType messageType, Map<String, Object> headers) | 새로운 헤더 인스턴스를 생성. |

<br><br><br>

#### 16-4-1-5. org.springframework.messaging.simp.stomp

- org.springframework.messaging의 하위 패키지

<br>

**1. StompSession 인터페이스**

- STOMP 프로토콜의 세션을 나타내는 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| send(String destination, Object payload) | 지정된 목적지로 메시지를 전송. |
| subscribe(String destination, StompFrameHandler handler) | 지정된 목적지에 구독을 설정. |
| disconnect() | 세션을 종료. |

<br><br>

**2. StompFrameHandler 인터페이스**

-: STOMP 프레임을 처리하기 위한 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| getPayloadType(StompHeaders headers) | 페이로드의 타입을 반환. |
| handleFrame(StompHeaders headers, Object payload) | 프레임을 처리. |

<br><br><br>

#### 16-4-1-6. org.springframework.web.socket

- spring-websocket 라이브러리는 웹 소켓 통신을 구현하기 위한 다양한 구성 요소들을 제공합니다.

**1. WebSocketHandler 인터페이스**

- 클라이언트로부터의 웹 소켓 메시지를 처리하기 위한 핸들러 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| handleMessage(WebSocketSession session, WebSocketMessage<?> message) | 웹 소켓 메시지를 처리. | 
| afterConnectionEstablished(WebSocketSession session) | 웹 소켓 연결이 수립된 후 호출. | 
| afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) | 웹 소켓 연결이 종료된 후 호출. | 

<br><br>

**2. WebSocketSession 인터페이스**

- 웹 소켓 연결을 나타내는 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| sendMessage(WebSocketMessage<?> message) | 웹 소켓으로 메시지를 보냄. | 
| close() | 웹 소켓 연결을 닫음. |

<br><br>

**3. WebSocketHandlerAdapter 클래스**

- WebSocketHandler를 지원하기 위한 어댑터 클래스.

| 메소드 | 설명 |
|--------------------------------------------------------------------------------------|--------------------------------|
| handleMessage(WebSocketSession session, WebSocketMessage<?> message, WebSocketHandler handler) | 웹 소켓 메시지를 처리. |

<br><br><br>

#### 16-4-1-7. org.springframework.web.socket.adapter

- org.springframework.web.socket의 하위 패키지

<br>

**1. WebSocketHandlerAdapter 클래스**

- WebSocketHandler를 지원하기 위한 어댑터 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| handleMessage(WebSocketSession session, WebSocketMessage<?> message, WebSocketHandler handler) | 웹 소켓 메시지를 처리. |

<br><br><br>

#### 16-4-1-8. org.springframework.web.socket.client

- org.springframework.web.socket의 하위 패키지

<br>

**1. WebSocketClient 인터페이스**

- 웹 소켓 클라이언트를 나타내는 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| doHandshake(WebSocketHandler webSocketHandler, String uri) | 웹 소켓 연결을 수립. |

<br><br><br>


#### 16-4-1-9. org.springframework.web.socket.config

- org.springframework.web.socket의 하위 패키지

<br>

**1. WebSocketConfigurer 인터페이스**

- 웹 소켓 구성을 위한 인터페이스.
- 
| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| registerWebSocketHandlers(WebSocketHandlerRegistry registry) | 웹 소켓 핸들러를 등록. |


<br><br><br>

#### 16-4-1-10. org.springframework.web.socket.handler

- org.springframework.web.socket의 하위 패키지

<br>

**1. WebSocketHandlerDecorator 클래스**

- WebSocketHandler를 위한 데코레이터 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| handleMessage(WebSocketSession session, WebSocketMessage<?> message) | 웹 소켓 메시지를 처리. |

<br><br><br>

#### 16-4-1-11. org.springframework.web.socket.messaging

- org.springframework.web.socket의 하위 패키지

<br>

**1. SubProtocolWebSocketHandler 클래스**

- 웹 소켓 서브 프로토콜을 처리하는 핸들러.
 
| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| getProtocolHandler(String protocol) | 지정된 서브 프로토콜의 핸들러를 반환. |

<br><br><br>

#### 16-4-1-12. org.sockjs.client

- SockJS는 브라우저와 서버 간의 신뢰성 있는 양방향 통신을 제공하는 라이브러리이며, sockjs-client는 이를 클라이언트 측에서 구현하기 위한 라이브러리입니다. 

<br>

**1. SockJs 클래스**

- SockJS 클라이언트를 나타내는 주요 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| connect(url: string, options?: object) | SockJs: 지정된 URL에 대한 SockJS 연결을 생성. |
| close(): void | SockJS 연결을 종료. |

<br><br>

**2. SockJs.EventListener 인터페이스**

- SockJS 이벤트 리스너를 정의하는 인터페이스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| onOpen(): void: 연결이 열린 경우 호출.
| onClose(): void: 연결이 닫힌 경우 호출.
| onMessage(message: string): void: 메시지를 수신한 경우 호출.

<br><br>

**3. SockJs.CloseCode 클래스**

- SockJS 연결 종료 코드를 정의하는 클래스.

| 필드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| NORMAL_CLOSURE: number | 정상적인 연결 종료 코드. |

<br><br><br>

#### 16-4-1-13. org.sockjs.client.transport

- org.sockjs.client 패키지의 하위 패키지

<br>

**1. WebSocketTransport 클래스**

- WebSocket 전송을 담당하는 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| send(payload: string): void | WebSocket으로 메시지를 보냄. |

<br><br>

**2. XhrTransport 클래스**

- XHR 전송을 담당하는 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| send(payload: string): void | XHR로 메시지를 보냄. |

<br><br>

**3. EventSourceTransport 클래스**

- EventSource 전송을 담당하는 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| send(payload: string): void | EventSource로 메시지를 보냄. |

<br><br>

**4. HtmlFileTransport 클래스**

- HTML 파일 전송을 담당하는 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| send(payload: string): void | HTML 파일로 메시지를 보냄. |

<br><br>

**5. IframeTransport 클래스**

- IFrame 전송을 담당하는 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| send(payload: string): void | IFrame으로 메시지를 보냄. |

<br><br><br>

#### 16-4-1-14. org.sockjs.client.transport.websocket

- org.sockjs.client 패키지의 하위 패키지

<br>

**1. WebSocketClient 클래스**

- WebSocket 클라이언트를 나타내는 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| connect(url: string) | WebSocket: 지정된 URL에 대한 WebSocket 연결을 생성. |

<br><br><br>

#### 16-4-1-15. org.sockjs.client.transports

- org.sockjs.client 패키지의 하위 패키지

<br>

**1. Transports 클래스**

- SockJS 전송을 관리하는 클래스.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| get(id: string): Transport | 지정된 ID에 대한 전송 객체를 반환. |

<br><br><br>


#### 16-4-1-16. org.springframework.messaging.simp.stomp

- stomp-websocket 라이브러리는 Spring Framework에서 STOMP(간단한 메시징 프로토콜) 프로토콜을 사용하여 WebSocket을 통해 클라이언트와 서버 간의 양방향 통신을 제공하는 데 사용됩니다. 이 라이브러리는 메시징 애플리케이션을 개발할 때 유용하며, 주로 웹 소켓을 기반으로하는 실시간 애플리케이션에서 사용됩니다.

<br>

**1. StompSession 클래스**

- STOMP(Session Transmission Protocol) 세션을 나타내는 클래스입니다. STOMP 클라이언트와 상호 작용할 수 있도록 지원합니다.

| 생성자 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| StompSession(StompConnectionHandler handler) | 주어진 StompConnectionHandler를 사용하여 StompSession을 생성합니다. |

<br>

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
subscribe(String destination, StompFrameHandler handler): 주어진 대상(destination)에 대한 구독을 설정합니다.
send(String destination, Object payload): 주어진 대상(destination)에 메시지를 전송합니다.
disconnect(): STOMP 세션을 종료하고 연결을 닫습니다.

<br><br>

**2. StompClient 인터페이스**

- STOMP 서버와 통신하기 위한 클라이언트를 나타내는 인터페이스입니다.

| 메소드 | 설명 |
|----------------------------------------------------|---------------------------------------------------------------|
| connect(String url, StompSessionHandler handler) | 주어진 URL에 대한 연결을 설정하고 StompSession을 생성합니다. |
| setTaskScheduler(TaskScheduler taskScheduler) | 연결을 위한 TaskScheduler를 설정합니다. |

<br><br>

**3 StompSessionHandler 인터페이스**

- STOMP 세션 이벤트를 처리하기 위한 핸들러 인터페이스입니다.

| 메소드 | 설명 |
|------------------------------------------------------------------------|------------------------------------------|
| afterConnected(StompSession session, StompHeaders connectedHeaders) | 연결이 수립된 후 호출됩니다. |
| handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) | 예외가 발생한 경우 호출됩니다. |
| handleTransportError(StompSession session, Throwable exception) | 전송 오류가 발생한 경우 호출됩니다. |

<br><br>

**4. StompFrameHandler 인터페이스**

- STOMP 프레임을 처리하기 위한 핸들러 인터페이스입니다.

| 메소드 | 설명 |
|--------------------------------------------------------|--------------------------------------------------|
| handleFrame(StompHeaders headers, Object payload) | 프레임을 처리합니다. |

<br><br>

**5. StompHeaders 클래스**

- STOMP 프레임의 헤더를 나타내는 클래스입니다.

| 메소드 | 설명 |
|------------------------------------------|-----------------------------------------------------------------------|
| setDestination(String destination) | 대상(destination) 헤더를 설정합니다. |
| setContentType(String contentType) | 콘텐츠 유형(content-type) 헤더를 설정합니다. |

<br><br>

**6. StompCommand 열거형**

- STOMP 프레임의 명령을 나타내는 열거형입니다. 예를 들어, CONNECT, SEND, SUBSCRIBE 등의 명령을 정의합니다.

<br><br><br><br>

### 16-4-2. 의존성 라이브러리 추가

**pom.xml에 라이브러리 추가**

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-messaging</artifactId>
    <version>5.0.8.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-websocket</artifactId>
    <version>5.0.8.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>sockjs-client</artifactId>
    <version>1.0.2</version>
</dependency>
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>stomp-websocket</artifactId>
    <version>2.3.3</version>
</dependency>
```

<br><br><br>

### 16-4-3. 웹소켓 설정

**com.spring1.config.WebSocketConfig 작성**

```java
package com.spring1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
}
```

<br><br><br>

### 16-4-4. 컨트롤러 작성

**com.spring1.controller.ChatController 작성**

```java
package com.spring1.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatHome(Model model){
        return "chat/home";
    }

    @MessageMapping("/chat/{roomId}/sendMessage")
    @SendTo("/topic/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, Message message) {
        return message;
    }
}
```

<br><br><br>

### 16-4-5. View(JSP) 작성

**src/main/webapp/WEB-INF/views/home.jsp 수정**

```jsp
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
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/chatbot/home">Chat GPT</a></li>
        <li><a href="${path2 }/schedule/reservationForm">예약하기</a></li>
        <li><a href="${path2 }/sheets">스프레드시트 작업하기</a></li>
        <li><a href="${path2 }/chat/home">멀티채팅방</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/chat/home.jsp 작성**

```jsp
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
    <title>Spring Chat Application</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/sockjs/1.1.4/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body>
    <h1>Chat Room</h1>
    <div id="messages"></div>
    <input type="text" id="messageInput" placeholder="Enter message"/>
    <button onclick="sendMessage()">Send</button>

    <script>
        var socket = new SockJS('/chat');
        var stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/roomId', function (message) {
                showMessage(JSON.parse(message.body).content);
            });
        });

        function sendMessage() {
            var messageInput = document.getElementById('messageInput');
            stompClient.send("${path2}/chat/roomId/sendMessage", {}, JSON.stringify({'content': messageInput.value}));
            messageInput.value = '';
        }

        function showMessage(message) {
            var messages = document.getElementById('messages');
            var messageElement = document.createElement('div');
            messageElement.textContent = message;
            messages.appendChild(messageElement);
        }
    </script>
</body>
</html>
```

<br><br><hr><br><br>

<div id="16-5"></div>

## 16-5. 쪽지 기능 구현

- Springframework 5에 사용하여 쪽지보내기, 쪽지읽기, 쪽지삭제, 쪽지 관리함 기능이 있는 웹 애플리케이션을 제작합니다.

<br>

### 16-5-1. Model(DTO) 작성

- Message.java 모델 클래스를 작성합니다.

**com.spring1.messageapp.model.Message**

```java
package com.spring1.messageapp.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Long id;
    private String sender;
    private String receiver;
    private String subject;
    private String body;
    private Date timestamp;
}
```

<br><br><br>

### 16-5-2. Service 작성

**com.spring1.messageapp.service.MessageService**

- MessageService.java 서비스 클래스를 작성합니다.

```java
package com.spring1.messageapp.service;

import com.spring1.messageapp.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private List<Message> messages = new ArrayList<>();
    private Long nextId = 1L;

    public void sendMessage(Message message) {
        message.setId(nextId++);
        message.setTimestamp(new java.util.Date());
        messages.add(message);
    }

    public List<Message> getInbox(String receiver) {
        return messages.stream()
                .filter(m -> m.getReceiver().equals(receiver))
                .collect(Collectors.toList());
    }

    public List<Message> getSentMessages(String sender) {
        return messages.stream()
                .filter(m -> m.getSender().equals(sender))
                .collect(Collectors.toList());
    }

    public Message readMessage(Long id) {
        return messages.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteMessage(Long id) {
        messages.removeIf(m -> m.getId().equals(id));
    }
}
```

<br><br><br>

### 16-5-3. Controller 작성

**com.spring1.messageapp.controller.MessageController 작성**

```java
package com.spring1.messageapp.controller;

import com.spring1.messageapp.model.Message;
import com.spring1.messageapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/compose")
    public String composeMessageForm(Model model) {
        model.addAttribute("message", new Message());
        return "messages/compose";
    }

    @PostMapping("/compose")
    public String composeMessageSubmit(@ModelAttribute Message message, Principal principal) {
        message.setSender(principal.getName());
        messageService.sendMessage(message);
        return "redirect:/messages/sent";
    }

    @GetMapping("/inbox")
    public String inbox(Model model, Principal principal) {
        model.addAttribute("messages", messageService.getInbox(principal.getName()));
        return "messages/inbox";
    }

    @GetMapping("/sent")
    public String sentMessages(Model model, Principal principal) {
        model.addAttribute("messages", messageService.getSentMessages(principal.getName()));
        return "messages/sent";
    }

    @GetMapping("/{id}")
    public String readMessage(@PathVariable Long id, Model model) {
        model.addAttribute("message", messageService.readMessage(id));
        return "messages/message";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return "redirect:/messages/inbox";
    }
}
```

<br><br><br>

### 16-5-4. Views(JSP) 작성

**src/main/webapp/WEB-INF/views/home.jsp 수정**

```jsp
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
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/chatbot/home">Chat GPT</a></li>
        <li><a href="${path2 }/schedule/reservationForm">예약하기</a></li>
        <li><a href="${path2 }/sheets">스프레드시트 작업하기</a></li>
        <li><a href="${path2 }/chat/home">멀티채팅방</a></li>
        <li><a href="${path2 }/messages/compose">쪽지 보내기</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messages/compose.jsp 작성**

```jsp
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
    <title>Compose Message</title>
</head>
<body>
    <h1>Compose Message</h1>
    <form action="${path2}/messages/compose" method="post">
        <label for="receiver">To:</label>
        <input type="text" id="receiver" name="receiver"><br>
        <label for="subject">Subject:</label>
        <input type="text" id="subject" name="subject"><br>
        <label for="body">Message:</label>
        <textarea id="body" name="body"></textarea><br>
        <input type="submit" value="Send">
    </form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messages/inbox.jsp 작성**

```jsp
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
    <title>Inbox</title>
</head>
<body>
    <h1>Inbox</h1>
    <table border="1">
        <tr>
            <th>From</th>
            <th>Subject</th>
            <th>Date</th>
        </tr>
        <c:forEach var="message" items="${messages}">
            <tr>
                <td>${message.sender}</td>
                <td><a href="${path2}/messages/${message.id}">${message.subject}</a></td>
                <td>${message.timestamp}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messages/sent.jsp 작성**

```jsp
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
    <title>Sent Messages</title>
</head>
<body>
    <h1>Sent Messages</h1>
    <table border="1">
        <tr>
            <th>To</th>
            <th>Subject</th>
            <th>Date</th>
        </tr>
        <c:forEach var="message" items="${messages}">
            <tr>
                <td>${message.receiver}</td>
                <td>${message.subject}</td>
                <td>${message.timestamp}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messages/message.jsp 작성**

```jsp
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
    <title>Message</title>
</head>
<body>
    <h1>Message</h1>
    <p><strong>From:</strong> ${message.sender}</p>
    <p><strong>To:</strong> ${message.receiver}</p>
    <p><strong>Subject:</strong> ${message.subject}</p>
    <p>${message.body}</p>
    <form action="${path2}/messages/delete/${message.id}" method="post">
        <input type="submit" value="Delete">
    </form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messages/delete.jsp 작성**

```jsp
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
    <title>Delete Message</title>
</head>
<body>
    <h1>Delete Message</h1>
    <p>Message deleted successfully.</p>
    <a href="${path2}/messages/inbox">Back to Inbox</a>
</body>
</html>
```

<br><br><hr><br><br>

<div id="16-6"></div>

## 16-6. RabbitMQ를 활용한 메신저 기능 구현

### 16-6-1. spring-rabbit 라이브러리

- spring-rabbit 라이브러리는 RabbitMQ를 Spring 프레임워크와 통합하는 데 사용되는 라이브러리입니다.

<br>

#### 16-6-1-1. org.springframework.amqp.core

- RabbitMQ 코어 요소를 정의하는 핵심 패키지로, 메시지, 교환, 큐 등을 다룹니다.

<br>

**주요 클래스 및 인터페이스:**

- Message: RabbitMQ 메시지를 나타내는 클래스입니다. 페이로드, 헤더, 속성 등을 포함합니다.
- Exchange: RabbitMQ의 교환을 나타내는 인터페이스입니다. 다양한 유형의 교환을 정의합니다.
- Queue: RabbitMQ의 큐를 나타내는 클래스입니다. 메시지를 저장하는 장소로 사용됩니다.
- Binding: 교환과 큐 간의 바인딩을 나타내는 클래스입니다. 교환에 전달된 메시지가 어떤 큐에 전달될지를 결정합니다.
- AmqpAdmin: RabbitMQ 코어 개체를 생성하고 관리하는 데 사용되는 인터페이스입니다.
- 기타: ExchangeType, QueueBuilder 등의 유틸리티 클래스와 인터페이스가 있습니다.

<br><br>

#### 16-6-1-2. org.springframework.amqp.rabbit.core

- RabbitMQ와 상호 작용하기 위한 핵심 클래스를 제공합니다.

**주요 클래스 및 인터페이스:**

- RabbitTemplate: RabbitMQ와 통신하기 위한 중심 클래스입니다. 메시지를 보내고 받는 데 사용됩니다.
- RabbitAdmin: RabbitMQ의 관리 작업을 수행하기 위한 클래스입니다.
- SimpleRabbitListenerContainerFactory: 간단한 리스너 컨테이너 팩토리 클래스입니다. 리스너 컨테이너를 설정하고 생성합니다.
- MessageListener: RabbitMQ 메시지를 처리하기 위한 인터페이스입니다. 리스너 컨테이너에서 구현됩니다.
- MessageListenerAdapter: 메시지 리스너를 POJO로 변환하는 어댑터 클래스입니다.
- 기타: ConnectionFactory, RabbitTemplateConfigurer 등의 유틸리티 클래스가 있습니다.

<br><br>

#### 16-6-1-3. org.springframework.amqp.rabbit.annotation

- 애너테이션 기반의 RabbitMQ 메시지 핸들링을 지원합니다.

**주요 클래스 및 인터페이스**

- RabbitListener: RabbitMQ 메시지를 처리하는 메서드에 대한 애너테이션입니다.
- RabbitListenerConfigurer: 런타임에 RabbitListener 구성을 구성하는 데 사용되는 인터페이스입니다.
- RabbitListenerEndpointRegistrar: RabbitListener에 대한 구성을 등록하는 클래스입니다.
- 기타: QueueBinding, ExchangeBinding 등의 애너테이션도 있습니다.

<br><br>

#### 16-6-1-4. org.springframework.amqp.rabbit.config

- RabbitMQ를 구성하는 데 사용되는 클래스를 제공합니다.

**주요 클래스 및 인터페이스**

- SimpleRabbitListenerContainerFactory: 간단한 리스너 컨테이너 팩토리 클래스입니다.
- RabbitListenerContainerFactory: 리스너 컨테이너를 생성하는 인터페이스입니다.
- RabbitListenerEndpointRegistrar: RabbitListener에 대한 구성을 등록하는 클래스입니다.
- 기타: AbstractRabbi

<br><br><br>

### 16-6-2. amqp-client 라이브러리

- amqp-client 라이브러리는 RabbitMQ와 통신하기 위한 Java 클라이언트 라이브러리입니다. 이 라이브러리는 RabbitMQ와 상호 작용하는 데 필요한 핵심 기능을 제공합니다. 각 패키지의 구성, 역할, 클래스, 인터페이스, 생성자, 메소드, 필드, 매개변수, 반환타입 등에 대해 자세히 설명하겠습니다.

<br>

#### 16-6-2-1. com.rabbitmq.client

- RabbitMQ 클라이언트의 핵심 패키지로, RabbitMQ와 통신하는 데 필요한 클래스와 인터페이스를 제공합니다.

**주요 클래스 및 인터페이스**

- ConnectionFactory: RabbitMQ와의 연결을 생성하는 클래스입니다. 주요 메소드는 newConnection()입니다.
- Connection: RabbitMQ와의 연결을 나타내는 인터페이스입니다. 주요 메소드는 createChannel()입니다.
- Channel: RabbitMQ 채널을 나타내는 인터페이스입니다. 메시지를 발행하고 소비하는 데 사용됩니다.
- QueueingConsumer: 메시지를 수신하는 데 사용되는 기본 소비자입니다. 주요 메소드는 nextDelivery()입니다.
- Message: RabbitMQ 메시지를 나타내는 클래스입니다. 페이로드, 속성 등을 포함합니다.
- Envelope: RabbitMQ 메시지에 대한 메타데이터를 나타내는 클래스입니다.
- 기타: Address, ShutdownSignalException 등의 유틸리티 클래스가 있습니다.

<br><br>

#### 16-6-2-2. com.rabbitmq.client.impl

- RabbitMQ 클라이언트의 내부 구현을 포함하는 패키지입니다.

**주요 클래스 및 인터페이스**

- AMQImpl: RabbitMQ 프로토콜의 구현을 제공하는 클래스입니다. 내부적으로 사용됩니다.
- AMQChannel: RabbitMQ 채널의 구현을 제공하는 클래스입니다.
- AMQConnection: RabbitMQ 연결의 구현을 제공하는 클래스입니다.
- AMQMessage: RabbitMQ 메시지의 구현을 제공하는 클래스입니다.
- AMQShortString: RabbitMQ의 짧은 문자열을 나타내는 클래스입니다.
- 기타: AMQImplBody 등의 내부 구현 클래스가 있습니다.

<br><br>

#### 16-6-2-3. com.rabbitmq.client.impl.nio

- RabbitMQ 클라이언트의 NIO(비동기 입출력) 구현을 포함하는 패키지입니다.

**주요 클래스 및 인터페이스**

- NioParams: NIO 연결 설정을 관리하는 클래스입니다.
- SocketChannelFrameHandler: 소켓 채널의 프레임 핸들링을 담당하는 클래스입니다.
- SocketFrameHandler: 소켓 프레임 핸들링을 담당하는 인터페이스입니다.
- NioParams.SocketChannelWrapper: 소켓 채널 래퍼 클래스입니다.
- NioParams.Callback: NIO 콜백 인터페이스입니다.
- 기타: NioHelper, NioLoop, NioQueue, NioQueueEntry 등의 클래스가 있습니다.

<br><br>

#### 16-6-2-4. com.rabbitmq.client.impl.recovery

- RabbitMQ 클라이언트의 장애 복구 구현을 포함하는 패키지입니다.

**주요 클래스 및 인터페이스**

- RecoveryAwareChannelN.java: 복구 가능한 채널을 나타내는 클래스입니다.
- AutomaticRecoveryProvider: 자동 복구 기능을 제공하는 클래스입니다.
- TopologyRecoveryRetryHandler: 토폴로지 복구 재시도 핸들러를 나타내는 클래스입니다.
- 기타: RecoveryAwareChannelN, RecoveryDelayHandler 등의 클래스가 있습니다.


<br><br>

- RabbitMQ를 활용하여 Spring 5.0.8 기반의 메신저 웹 애플리케이션을 작성하려면 메시지 큐를 통해 메시지를 비동기적으로 처리하는 기능을 추가해야 합니다. 이 애플리케이션은 사용자가 메시지를 보내고, RabbitMQ를 통해 큐에 전달하고, 소비자가 큐에서 메시지를 읽어 처리하는 구조로 구성됩니다.

<br><br>

**프로젝트 구조**

- 프로젝트 구조는 다음과 같이 설정합니다:

```tree
src
└── main
    ├── java
    │   └── com.spring1.messageapp
    │       ├── config
    │       │   └── RabbitConfig.java
    │       ├── controller
    │       │   └── MessengerController.java
    │       ├── model
    │       │   └── Messenger.java
    │       └── service
    │           ├── MessengerService.java
    │           └── RabbitMQConsumer.java
    └── resources
        └── application.properties
    └── webapp
        ├── WEB-INF
        │   └── views
        │       └── messenger
        │           ├── compose.jsp
        │           ├── inbox.jsp
        │           ├── sent.jsp
        │           ├── message.jsp
        │           └── delete.jsp
        ├── dispatcher-servlet.xml
        └── web.xml
```

<br><br><br>

### 16-6-3. 의존성 라이브러리 추가(Maven dependencies)

**pom.xml에 RabbitMQ 의존성 라이브러리를 추가**

```xml
    <dependency>
        <groupId>org.springframework.amqp</groupId>
        <artifactId>spring-rabbit</artifactId>
        <version>2.0.2.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>com.rabbitmq</groupId>
        <artifactId>amqp-client</artifactId>
        <version>5.2.0</version>
    </dependency>
```

<br><br><br>

### 16-6-4. RabbitMQ Configuration

**com.spring1.messageapp.config.RabbitConfig.java 설정 파일 작성**

```java
package com.spring1.messageapp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue messageQueue() {
        return new Queue("messageQueue", true);
    }
}
```

<br><br><br>

### 16-6-5. Model(DTO) 작성

**com.spring1.messageapp.model.Message 모델 클래스 작성**

```java
package com.spring1.messageapp.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Messenger implements Serializable {
    private Long id;
    private String sender;
    private String receiver;
    private String subject;
    private String body;
    private Date timestamp;
}
```

<br><br><br>

### 16-6-6. Service 작성

**com.spring1.messageapp.service.MessengerService 메신저 서비스 클래스 작성**

```java
package com.spring1.messageapp.service;

import com.spring1.messageapp.model.Messenger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessengerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessenger(Messenger message) {
        message.setTimestamp(new java.util.Date());
        rabbitTemplate.convertAndSend("messageQueue", message);
    }
}
```

<br><br>

**com.spring1.messageapp.servic.RabbitMQConsumer 소비자 클래스 작성**

```java
package com.spring1.messageapp.service;

import com.spring1.messageapp.model.Messenger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RabbitMQConsumer {

    private List<Messenger> messages = new ArrayList<>();

    @RabbitListener(queues = "messageQueue")
    public void receiveMessenger(Messenger message) {
        messages.add(message);
    }

    public List<Messenger> getInbox(String receiver) {
        return messages.stream()
                .filter(m -> m.getReceiver().equals(receiver))
                .collect(Collectors.toList());
    }

    public List<Messenger> getSentMessengers(String sender) {
        return messages.stream()
                .filter(m -> m.getSender().equals(sender))
                .collect(Collectors.toList());
    }

    public Messenger readMessenger(Long id) {
        return messages.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteMessenger(Long id) {
        messages.removeIf(m -> m.getId().equals(id));
    }
}
```

<br><br><br>

### 16-6-7. Controller 작성

MessengerController.java 컨트롤러 클래스를 작성합니다.

```java
package com.spring1.messageapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring1.messageapp.model.Messenger;
import com.spring1.messageapp.service.MessengerService;
import com.spring1.messageapp.service.RabbitMQConsumer;

@Controller
@RequestMapping("/messenger")
public class MessengerController {

    @Autowired
    private MessengerService messageService;

    @Autowired
    private RabbitMQConsumer rabbitMQConsumer;

    @GetMapping("/compose")
    public String composeMessengerForm(Model model) {
        model.addAttribute("message", new Messenger());
        return "messenger/compose";
    }

    @PostMapping("/compose")
    public String composeMessengerSubmit(@ModelAttribute Messenger message, Principal principal) {
        message.setSender(principal.getName());
        messageService.sendMessenger(message);
        return "redirect:/messenger/sent";
    }

    @GetMapping("/inbox")
    public String inbox(Model model, Principal principal) {
        model.addAttribute("messages", rabbitMQConsumer.getInbox(principal.getName()));
        return "messenger/inbox";
    }

    @GetMapping("/sent")
    public String sentMessengers(Model model, Principal principal) {
        model.addAttribute("messages", rabbitMQConsumer.getSentMessengers(principal.getName()));
        return "messenger/sent";
    }

    @GetMapping("/{id}")
    public String readMessenger(@PathVariable Long id, Model model) {
        model.addAttribute("message", rabbitMQConsumer.readMessenger(id));
        return "messenger/message";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessenger(@PathVariable Long id) {
        rabbitMQConsumer.deleteMessenger(id);
        return "redirect:/messenger/inbox";
    }
}
```

<br><br><br>

### 16-6-8. View(JSP) 작성

각 JSP 파일을 작성합니다.

**src/main/webapp/WEB-INF/views/home.jsp 수정**

```jsp
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
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/chatbot/home">Chat GPT</a></li>
        <li><a href="${path2 }/schedule/reservationForm">예약하기</a></li>
        <li><a href="${path2 }/sheets">스프레드시트 작업하기</a></li>
        <li><a href="${path2 }/chat/home">멀티채팅방</a></li>
        <li><a href="${path2 }/messages/compose">쪽지 보내기</a></li>
        <li><a href="${path2 }/messenger/compose">RabbitMQ 메신저</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messenger/compose.jsp 작성**

```jsp
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
    <title>Compose Message</title>
</head>
<body>
    <h1>Compose Message</h1>
    <form action="${path2}/messenger/compose" method="post">
        <label for="receiver">To:</label>
        <input type="text" id="receiver" name="receiver"><br>
        <label for="subject">Subject:</label>
        <input type="text" id="subject" name="subject"><br>
        <label for="body">Message:</label>
        <textarea id="body" name="body"></textarea><br>
        <input type="submit" value="Send">
    </form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messenger/inbox.jsp 작성**


```jsp
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
    <title>Inbox</title>
</head>
<body>
    <h1>Inbox</h1>
    <table>
        <tr>
            <th>From</th>
            <th>Subject</th>
            <th>Date</th>
        </tr>
        <c:forEach var="message" items="${messages}">
            <tr>
                <td>${message.sender}</td>
                <td><a href="${path2}/messenger/${message.id}">${message.subject}</a></td>
                <td>${message.timestamp}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messenger/sent.jsp 작성**

```jsp
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
    <title>Sent Messages</title>
</head>
<body>
    <h1>Sent Messages</h1>
    <table>
        <tr>
            <th>To</th>
            <th>Subject</th>
            <th>Date</th>
        </tr>
        <c:forEach var="message" items="${messages}">
            <tr>
                <td>${message.receiver}</td>
                <td>${message.subject}</td>
                <td>${message.timestamp}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messenger/message.jsp 작성**

```jsp
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
    <title>Message</title>
</head>
<body>
    <h1>Message</h1>
    <p><strong>From:</strong> ${message.sender}</p>
    <p><strong>To:</strong> ${message.receiver}</p>
    <p><strong>Subject:</strong> ${message.subject}</p>
    <p>${message.body}</p>
    <form action="${path2}/messenger/delete/${message.id}" method="post">
        <input type="submit" value="Delete">
    </form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/messenger/delete.jsp 작성**


```jsp
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
    <title>Delete Message</title>
</head>
<body>
    <h1>Delete Message</h1>
    <p>Message deleted successfully.</p>
    <a href="${path2}/messenger/inbox">Back to Inbox</a>
</body>
</html>
```

<br><br><br>

### 16-6-9. Application Properties

**application.properties 파일에 RabbitMQ 설정 추가**

```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```



<br><br><hr><br><br>

<div id="16-7"></div>

## 16-7. 음력/양력 달력 기능 구현

- euckr-calendar 라이브러리의 주요 기능은 음력과 양력 간의 변환입니다. 
- 이 라이브러리는 한국의 음력 달력을 기반으로 하여, 특정 날짜를 음력에서 양력으로 또는 양력에서 음력으로 변환하는 기능을 제공합니다.

<br>

### 16-7-1. com.github.euckr 라이브러리

#### 16-7-1-1. LunarCalendar 클래스

- 이 클래스는 음력 달력을 다루는 주요 클래스입니다.
- 음력과 양력 변환, 음력 날짜 계산 등의 기능을 구현할 때 사용합니다.

**LunarCalendar 클래스의 멤버**

| 종류 | 멤버명 | 설명 |
|--------|----------------------------------------|----------------------------------------------------------------|
| 생성자 | LunarCalendar(Date date) | 주어진 양력 날짜를 기반으로 음력 날짜 객체를 생성합니다. <br> 매개변수: date - 변환할 양력 날짜 (java.util.Date) <br> 반환 타입: 없음 (생성자) |
|   | LunarCalendar(int lunarYear, int lunarMonth, int lunarDay, boolean leapMonth) | 주어진 음력 날짜를 기반으로 음력 날짜 객체를 생성합니다. <br> 매개변수: <br> lunarYear - 음력 연도 (int) <br> lunarMonth - 음력 월 (int) <br> lunarDay - 음력 일 (int) <br> leapMonth - 윤달 여부 (boolean) <br> 반환 타입: 없음 (생성자) |
| 메소드 | Date getSolar() | 현재 음력 날짜 객체를 양력 날짜로 변환하여 반환합니다. <br> 매개변수: 없음 <br> 반환 타입: java.util.Date - 양력 날짜 |
|   | int getLunarYear() | 음력 연도를 반환합니다. <br> 매개변수: 없음 <br> 반환 타입: int - 음력 연도 |
|   | int getLunarMonth() | 음력 월을 반환합니다. <br> 매개변수: 없음 <br> 반환 타입: int - 음력 월 |
|   | int getLunarDay() | 음력 일을 반환합니다. <br> 매개변수: 없음 <br> 반환 타입: int - 음력 일 |
|   | boolean isLeapMonth() | 윤달 여부를 반환합니다. <br> 매개변수: 없음 <br> 반환 타입: boolean - 윤달 여부 |

<br><br>

**프로젝트 구조**

```
src/main/java
    └── com/spring1/controller
        └── CalendarController.java
    └── com/spring1/service
        └── CalendarService.java
    └── com/spring1/util
        └── LunarCalendarUtil.java
src/main/resources
    └── application.properties
src/main/webapp/WEB-INF
    └── views
        └── calendar.jsp
        └── reservationResult.jsp
    └── web.xml
src/main/webapp
    └── index.jsp
```


<br><br><br>

### 16-7-2. 의존성 라이브러리 추가

**pom.xml에 calendar 라이브러리 추가**

```xml
<dependencies>
    <!-- other-library -->
    <dependency>
        <groupId>com.github.usingsky</groupId>
        <artifactId>KoreanLunarCalendar</artifactId>
        <version>0.3.1</version>
    </dependency>
</dependencies>
<repositories>
  ...
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

<br><br><br>


### 16-7-3. 음력과 양력 변환 유틸리티 클래스

- euckr-calendar 라이브러리를 사용하여 음력과 양력을 변환하는 유틸리티 클래스를 작성합니다.

**com.spring1.util.LunarCalendarUtil 유틸리티 클래스 작성**

```java
package com.spring1.util;

import com.github.usingsky.calendar.KoreanLunarCalendar;

public class LunarCalendarUtil {

    public static String solarToLunar(int year, int month, int day) {
        KoreanLunarCalendar calendar = KoreanLunarCalendar.getInstance();
        calendar.setLunarDate(year - 1900, month - 1, day, false);
        return calendar.getLunarIsoFormat();
    }

    public static String lunarToSolar(int year, int month, int day) {
    	KoreanLunarCalendar calendar = KoreanLunarCalendar.getInstance();
    	calendar.setSolarDate(year, month, day);
        return calendar.getSolarIsoFormat();
    }
}
```

<br><br><br>

### 16-7-4. Service 작성

**com.spring1.service.CalendarService.java 작성**

```java
package com.spring1.service;

import com.spring1.util.LunarCalendarUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CalendarService {

    public Map<String, String> getSolarCalendar() {
        Map<String, String> solarCalendar = new HashMap<>();
        solarCalendar.put("2024-01-01", "양력 2024-01-01");
        solarCalendar.put("2024-01-02", "양력 2024-01-02");
        return solarCalendar;
    }

    public Map<String, String> getLunarCalendar() {
        Map<String, String> lunarCalendar = new HashMap<>();
        lunarCalendar.put("2024-01-01", "음력 " + LunarCalendarUtil.solarToLunar(2024, 1, 1));
        lunarCalendar.put("2024-01-02", "음력 " + LunarCalendarUtil.solarToLunar(2024, 1, 2));
        return lunarCalendar;
    }

    public boolean isReservationAvailable(String date) {
        return !date.equals("2024-01-01");
    }
}
```

<br><br><br>

### 16-7-5. Controller 작성

**com.spring1.controller.CalendarController 작성**

```java
package com.spring1.controller;

import com.spring1.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String showCalendar(Model model) {
        model.addAttribute("solarCalendar", calendarService.getSolarCalendar());
        model.addAttribute("lunarCalendar", calendarService.getLunarCalendar());
        return "calendar";
    }

    @RequestMapping(value = "/checkReservation", method = RequestMethod.GET)
    public String checkReservation(@RequestParam("date") String date, Model model) {
        boolean available = calendarService.isReservationAvailable(date);
        model.addAttribute("date", date);
        model.addAttribute("available", available);
        return "reservationResult";
    }
}
```

<br><br><br>


### 16-7-6. View(JSP) 작성

**src/main/webapp/WEB-INF/views/calendar.jsp 작성**

- 양력 및 음력 달력을 표시하고 날짜 선택 기능을 구현합니다.

```jsp
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
    <title>Calendar</title>
</head>
<body>
    <h1>Solar Calendar</h1>
    <table border="1">
        <tr>
            <th>Date</th>
            <th>Description</th>
        </tr>
        <c:forEach var="entry" items="${solarCalendar}">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>

    <h1>Lunar Calendar</h1>
    <table border="1">
        <tr>
            <th>Date</th>
            <th>Description</th>
        </tr>
        <c:forEach var="entry" items="${lunarCalendar}">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>Check Reservation</h2>
    <form action="checkReservation" method="get">
        <label for="date">Date:</label>
        <input type="text" id="date" name="date">
        <input type="submit" value="Check">
    </form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/reservationResult.jsp 작성**

- 예약 가능 여부 결과를 표시합니다.

```jsp
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
    <title>Reservation Result</title>
</head>
<body>
    <h1>Reservation Result</h1>
    <p>Date: ${date}</p>
    <c:choose>
        <c:when test="${available}">
            <p>Reservation is available.</p>
        </c:when>
        <c:otherwise>
            <p>Reservation is not available.</p>
        </c:otherwise>
    </c:choose>
    <a href="calendar">Back to Calendar</a>
</body>
</html>
```

<br><br><hr><br><br>

<div id="16-8"></div>

## 16-8. Drag&Drop 업로드 게시판

<div style="font-size:40px; color:red">이 섹션만 MariaDB를 활용하였습니다.</div>

### 16-8-1. 의존성 라이브러리 추가

**pom.xml에 MyBatis 및 MariaDB 라이브러리 추가**

```xml
<!-- Spring Web MVC -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.0.8.RELEASE</version>
</dependency>

<!-- MyBatis -->
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

<dependency>
    <groupId>org.mariadb.jdbc</groupId>
    <artifactId>mariadb-java-client</artifactId>
    <version>3.1.0</version>
</dependency>

<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc11</artifactId>
    <version>21.1.0.0</version>
</dependency>
```

<br><br><br>

### 16-8-2. 서블릿 설정

**src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml 설정**

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
	<resources mapping="/fileboard/**" location="/WEB-INF/views/fileboard" />

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
		<beans:constructor-arg value="D:\kim\springframework\spring16\src\main\webapp\resources\upload"></beans:constructor-arg>
	</beans:bean>
		
	<!-- 기본 메인 패키지 및 컨트롤러 패키지 설정 -->
	<context:component-scan base-package="com.spring1" />

</beans:beans>
```

<br><br><br>

### 16-8-3. 루트 컨텍스트 설정

**src/main/webapp/WEB-INF/spring/root-context.xml 설정**

```xml
<!-- root-context.xml -->
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
</beans>
```

<br><br><br>

### 16-8-3. MyBatis 설정

**src/main/resources/mybatis-config.xml 작성**

```xml
<!-- mybatis-config.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC 
"-//mybatis.org//DTD Config 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <!-- Underscore to CamelCase mapping -->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
	<typeAliases>
		<package name="com.spring1" />
	</typeAliases>
</configuration>
```

<br><br><br>

### 16-8-4. MariaDB 테이블 생성 SQL 구문

```sql
CREATE TABLE fileboard (
    no INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    filename VARCHAR(255),
    resdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

<br><br><br>

### 16-8-5. Fileboard 클래스

**com.spring1.domain.Fileboard 작성**

```java
package com.spring1.domain;

public class Fileboard {
    private int no;
    private String title;
    private String content;
    private String filename;
    private Timestamp resdate;

    // Getters and Setters
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Timestamp getResdate() {
        return resdate;
    }

    public void setResdate(Timestamp resdate) {
        this.resdate = resdate;
    }
}
```

<br><br><br>

### 16-8-6. Mapper.xml 작성

**src/main/resources/mappers/fileboardMapper.xml 작성**

```xml
<!-- FileboardMapper.xml -->
<mapper namespace="com.spring1.mapper.FileboardMapper">
    <select id="getList" resultType="com.spring1.domain.Fileboard">
        SELECT * FROM fileboard ORDER BY no DESC LIMIT #{pageSize} OFFSET #{offset}
    </select>

    <select id="getTotalCount" resultType="int">
        SELECT COUNT(*) FROM fileboard
    </select>

    <!-- Other existing queries -->
</mapper>
```

<br><br><br>

### 16-8-7. Repository (Mapper Interface) 작성

**com.spring1.mapper.FileboardMapper 작성**

```java
package com.spring1.mapper;

import com.spring1.domain.Fileboard;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileboardMapper {

    @Select("SELECT * FROM fileboard ORDER BY no DESC LIMIT #{pageSize} OFFSET #{offset}")
    List<Fileboard> getList(@Param("pageSize") int pageSize, @Param("offset") int offset);

    @Select("SELECT COUNT(*) FROM fileboard")
    int getTotalCount();

    @Select("SELECT * FROM fileboard WHERE no = #{no}")
    Fileboard getDetail(int no);

    @Insert("INSERT INTO fileboard (title, content, filename) VALUES (#{title}, #{content}, #{filename})")
    void insertFileboard(Fileboard fileboard);

    @Update("UPDATE fileboard SET title = #{title}, content = #{content}, filename = #{filename} WHERE no = #{no}")
    void updateFileboard(Fileboard fileboard);

    @Delete("DELETE FROM fileboard WHERE no = #{no}")
    void deleteFileboard(int no);
}
```

<br><br><br>

### 16-8-8. Service 작성

**com.spring1.service.FileboardService 작성**

```java
package com.spring1.service;

import com.spring1.domain.Fileboard;
import com.spring1.mapper.FileboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileboardService {

    @Autowired
    private FileboardMapper fileboardMapper;

    public List<Fileboard> getList(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return fileboardMapper.getList(pageSize, offset);
    }

    public int getTotalCount() {
        return fileboardMapper.getTotalCount();
    }

    public Fileboard getDetail(int no) {
        return fileboardMapper.getDetail(no);
    }

    public void saveFileboard(Fileboard fileboard, MultipartFile file) {
        if (!file.isEmpty()) {
            String filename = saveFile(file);
            fileboard.setFilename(filename);
        }
        fileboardMapper.insertFileboard(fileboard);
    }

    public void updateFileboard(Fileboard fileboard, MultipartFile file) {
        if (!file.isEmpty()) {
            String filename = saveFile(file);
            fileboard.setFilename(filename);
        }
        fileboardMapper.updateFileboard(fileboard);
    }

    public void deleteFileboard(int no) {
        fileboardMapper.deleteFileboard(no);
    }

    private String saveFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + "_" + originalFilename;

        try {
            file.transferTo(new File("path/to/your/upload/directory/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }
}
```


<br><br><br>

### 16-8-9. Controller 작성

**com.spring1.controller.FileboardController 작성**

```java
package com.spring1.controller;

import com.spring1.domain.Fileboard;
import com.spring1.service.FileboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileboardController {

    @Autowired
    private FileboardService fileboardService;

    @GetMapping("/fileboard/list")
    public String getList(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;
        List<Fileboard> list = fileboardService.getList(page, pageSize);
        int totalCount = fileboardService.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "fileboard/list";
    }

    @GetMapping("/fileboard/detail/{no}")
    public String getDetail(@PathVariable int no, Model model) {
        Fileboard fileboard = fileboardService.getDetail(no);
        model.addAttribute("fileboard", fileboard);
        return "fileboard/detail";
    }

    @GetMapping("/fileboard/post")
    public String postForm() {
        return "fileboard/post";
    }

    @PostMapping("/fileboard/postPro")
    public String postPro(@RequestParam("file") MultipartFile file, Fileboard fileboard) {
        fileboardService.saveFileboard(fileboard, file);
        return "redirect:/fileboard/list";
    }

    @GetMapping("/fileboard/modify/{no}")
    public String modifyForm(@PathVariable int no, Model model) {
        Fileboard fileboard = fileboardService.getDetail(no);
        model.addAttribute("fileboard", fileboard);
        return "fileboard/modify";
    }

    @PostMapping("/fileboard/modifyPro")
    public String modifyPro(@RequestParam("file") MultipartFile file, Fileboard fileboard) {
        fileboardService.updateFileboard(fileboard, file);
        return "redirect:/fileboard/list";
    }

    @PostMapping("/fileboard/deletePro/{no}")
    public String deletePro(@PathVariable int no) {
        fileboardService.deleteFileboard(no);
        return "redirect:/fileboard/list";
    }
}
```

<br><br><br>

### 16-8-10. View (JSP) 작성

**src/main/webapp/WEB-INF/views/fileboard/list.jsp 목록 페이지 작성**

```jsp
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
    <title>게시글 목록</title>
</head>
<body>
<h2>게시글 목록</h2>
<a href="/fileboard/post">새 글 작성</a>
<table border="1">
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성일시</th>
        <th>파일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.no}</td>
            <td><a href="${path2}/fileboard/detail/${item.no}">${item.title}</a></td>
            <td>${item.resdate}</td>
            <td><a href="${path2}/files/${item.filename}">${item.filename}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <c:if test="${currentPage > 1}">
        <a href="${path2}/fileboard/list?page=${currentPage - 1}">이전</a>
    </c:if>
    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="${path2}/fileboard/list?page=${i}">${i}</a>
    </c:forEach>
    <c:if test="${currentPage < totalPages}">
        <a href="${path2}/fileboard/list?page=${currentPage + 1}">다음</a>
    </c:if>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileboard/detail.jsp 상세보기 페이지 작성**

```jsp
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
    <title>게시글 상세보기</title>
</head>
<body>
<h2>게시글 상세보기</h2>
<a href="${path2}/fileboard/list">목록으로</a>
<p>번호: ${fileboard.no}</p>
<p>제목: ${fileboard.title}</p>
<p>내용: ${fileboard.content}</p>
<p>파일: <a href="${path2}/files/${fileboard.filename}">${fileboard.filename}</a></p>
<p>작성일시: ${fileboard.resdate}</p>
<a href="${path2}/fileboard/modify/${fileboard.no}">수정</a>
<form action="${path2}/fileboard/deletePro/${fileboard.no}" method="post">
    <input type="submit" value="삭제">
</form>
</body>
</html>
```


<br><br>


**src/main/webapp/WEB-INF/views/fileboard/post.jsp 글 작성 페이지 작성**

```jsp
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
    <title>새 글 작성</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        #drop-area {
            border: 2px dashed #ccc;
            border-radius: 20px;
            width: 300px;
            height: 200px;
            margin: 20px auto;
            text-align: center;
            font-size: 24px;
            line-height: 200px;
        }
        .highlight {
            border-color: #000;
        }
    </style>
</head>
<body>
<h2>새 글 작성</h2>
<form id="postForm" enctype="multipart/form-data">
    <p>제목: <input type="text" name="title"></p>
    <p>내용: <textarea name="content"></textarea></p>
    <p>파일:</p>
    <div id="drop-area">Drag & Drop files here</div>
    <input type="file" id="file" name="file" style="display: none;">
    <input type="submit" value="작성">
</form>

<script>
    $(document).ready(function () {
        var dropArea = $("#drop-area");

        // 드래그 앤 드롭 이벤트 처리
        dropArea.on("dragenter", function (e) {
            e.preventDefault();
            e.stopPropagation();
            dropArea.addClass("highlight");
        });

        dropArea.on("dragleave", function (e) {
            e.preventDefault();
            e.stopPropagation();
            dropArea.removeClass("highlight");
        });

        dropArea.on("dragover", function (e) {
            e.preventDefault();
            e.stopPropagation();
        });

        dropArea.on("drop", function (e) {
            e.preventDefault();
            e.stopPropagation();
            dropArea.removeClass("highlight");

            var files = e.originalEvent.dataTransfer.files;
            if (files.length > 0) {
                $("#file")[0].files = files;
                handleFiles(files);
            }
        });

        // 파일 선택 시 파일 이름 표시
        $("#file").on("change", function () {
            var files = this.files;
            handleFiles(files);
        });

        // 파일 처리
        function handleFiles(files) {
            var fileList = $("<ul></ul>");
            $.each(files, function (index, file) {
                var listItem = $("<li></li>").text(file.name);
                fileList.append(listItem);
            });
            dropArea.html(fileList);
        }

        // 폼 제출 처리
        $("#postForm").on("submit", function (e) {
            e.preventDefault();
            var formData = new FormData(this);
            $.ajax({
                url: "${path2}/fileboard/postPro",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    window.location.href = "${path2}/fileboard/list";
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        });
    });
</script>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileboard/modify.jsp 글 수정 페이지 작성**

```jsp
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
    <title>게시글 수정</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        #drop-area {
            border: 2px dashed #ccc;
            border-radius: 20px;
            width: 300px;
            height: 200px;
            margin: 20px auto;
            text-align: center;
            font-size: 24px;
            line-height: 200px;
        }
        .highlight {
            border-color: #000;
        }
    </style>
</head>
<body>
<h2>게시글 수정</h2>
<form id="modifyForm" enctype="multipart/form-data">
    <input type="hidden" name="no" value="${fileboard.no}">
    <p>제목: <input type="text" name="title" value="${fileboard.title}"></p>
    <p>내용: <textarea name="content">${fileboard.content}</textarea></p>
    <p>파일:</p>
    <div id="drop-area">Drag & Drop files here</div>
    <input type="file" id="file" name="file" style="display: none;">
    <input type="submit" value="수정">
</form>

<script>
    $(document).ready(function () {
        var dropArea = $("#drop-area");

        // 드래그 앤 드롭 이벤트 처리
        dropArea.on("dragenter", function (e) {
            e.preventDefault();
            e.stopPropagation();
            dropArea.addClass("highlight");
        });

        dropArea.on("dragleave", function (e) {
            e.preventDefault();
            e.stopPropagation();
            dropArea.removeClass("highlight");
        });

        dropArea.on("dragover", function (e) {
            e.preventDefault();
            e.stopPropagation();
        });

        dropArea.on("drop", function (e) {
            e.preventDefault();
            e.stopPropagation();
            dropArea.removeClass("highlight");

            var files = e.originalEvent.dataTransfer.files;
            if (files.length > 0) {
                $("#file")[0].files = files;
                handleFiles(files);
            }
        });

        // 파일 선택 시 파일 이름 표시
        $("#file").on("change", function () {
            var files = this.files;
            handleFiles(files);
        });

        // 파일 처리
        function handleFiles(files) {
            var fileList = $("<ul></ul>");
            $.each(files, function (index, file) {
                var listItem = $("<li></li>").text(file.name);
                fileList.append(listItem);
            });
            dropArea.html(fileList);
        }

        // 폼 제출 처리
        $("#modifyForm").on("submit", function (e) {
            e.preventDefault();
            var formData = new FormData(this);
            $.ajax({
                url: "${path2}/fileboard/modifyPro",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    window.location.href = "${path2}/fileboard/list";
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        });
    });
</script>
</body>
</html>
```


<br><br><hr><br><br>