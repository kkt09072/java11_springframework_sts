<nav id="quick" style="">
	<label for="chap1" style="color:black;font-size:28px;">14. Open API 와 API 용용 및 부가 기능</label>
	<input type="radio" name="chap" id="chap1" style="display:none;">
	<ul class="menu">
		<li><a href="#14-1" style="color:black;font-size:20px;text-decoration:none;">14-1. 스프링프레임워크 이메일 보내기 설정</a></li>
		<li><a href="#14-2" style="color:black;font-size:20px;text-decoration:none;">14-2. 스프링프레임워크 이메일 보내기 Controller 구현하기</a></li>
        <li><a href="#14-3" style="color:black;font-size:20px;text-decoration:none;">14-3. 파일 업로드 구현</li>
        <li><a href="#14-4" style="color:black;font-size:20px;text-decoration:none;">14-4. Editor Board 구현</li>
        <li><a href="#14-5" style="color:black;font-size:20px;text-decoration:none;">14-5. 결제 Api 구현</li>
        <li><a href="#14-6" style="color:black;font-size:20px;text-decoration:none;">14-6. Chat Api 구현</li>
        <li><a href="#14-7" style="color:black;font-size:20px;text-decoration:none;">14-7. 비속어 필터링 구현</li>
        <li><a href="#14-8" style="color:black;font-size:20px;text-decoration:none;">14-8. 상품 이미지 Api 구현</li>
        <li><a href="#14-9" style="color:black;font-size:20px;text-decoration:none;">14-9. Jsoup을 이용한 웹 크롤링 구현</li>
        <li><a href="#14-10" style="color:black;font-size:20px;text-decoration:none;">14-10. 차트 Api 구현</li>
        <li><a href="#14-11" style="color:black;font-size:20px;text-decoration:none;">14-11. PDF 생성 구현</li>
        <li><a href="#14-12" style="color:black;font-size:20px;text-decoration:none;">14-12. 기상청 날씨 Api 구현</li>
        <li><a href="#14-13" style="color:black;font-size:20px;text-decoration:none;">14-13. Chat GPT Api 구현</li>
        <li><a href="#14-14" style="color:black;font-size:20px;text-decoration:none;">14-14. Api 제작 및 구현</li>
	</ul>
</nav>

<div id="14"></div>

# 14. Open API 와 API 용용 및 부가 기능

<div id="14-1"></div>

## 14-1. 스프링프레임워크 이메일 보내기 설정

- 스프링프레임워크에서 메일서버(SMTP Server)를 이용하여 메일을 전송하는 방법에 대해서 알아보고자 합니다. SMTP는 Simple Mail Transfer Protocol로서 전자 메일 전송을 위한 표준 프로토콜입니다. 따라서 SMTP Server란 이메일을 송신하는 서버입니다. (메일서버는 SMTP Server, POP3 Server가 있고 POP3 Server는 이메일을 수신하는 서버입니다.)
- 메일 전송 기능을 구현하기 위해선 아래의 4가지가 필요합니다.
  1. pom.xml 설정
  2. mailSender Bean 등록(root-context.xml 도는 servlet-context.xml)
  3. Controller 메서드 추가
  4. 메일 계정 보안 설정

- 메일서버(SMTP Server)를 통해 메일을 전송할 때 다음과 같은 두 가지 방법이 있습니다.
     첫 번째, 메일 서버가 릴레이(relay)를 허용하는 경우 
          ※릴레이(Relay) : 외부(외부 네트워크)에서 해당 메일서버(smtp 서버)를 경유해서 외부로 메일을 보내는 것을 의미함.
        - 이 방식은 계정이 존재하지 않더라도 메일을 발송할 수 있습니다. 
        - 릴레이(relay)를 허용하는 경우 잘못된 방식(타인에 의한 스팸메일)으로 악용 될 수 있습니다. 
        - 따라서 특정 IP에서만 릴레이가 되도록 하는게 일반적입니다.

     두 번째, 메일 서버에 계정 인증 후 메일을 전송하는 방법

위 두 가지 방법 선택은 "2.mailSender Bean 등록"에서 이루어집니다. 
아래의 자세한 설명에서 어떻게 설정해야 하는지 설명은 하지만 주 방식은 계정 인증 후 메일을 전송하는 방법으로 설명을 진행하고자 합니다.

### 14-1-1. 메일 계정 보안 설정
 
- SMTP Server를 통해서 메일 전송 기능을 구현하기 위해선 사용하고자 하는 메일의 SMTP 보안 단계를 낮추어야 합니다. 네이버의 경우 로그인시 휴대폰승인을 통한 보안 단계까지 설정이 되어 있을 경우 아래의 설정만으로는 부족합니다. 

#### 14-1-1-1. 구글 이메일 설정

   1) 구글 메인페이지 오른쪽 상단에 자신의 계정을 클릭하고 아래의 그림과 같이 "Google 계정 관리"를 클릭합니다.

![구글메일설정](../images/mail001.png)

		그림 1-1
 

   2) 왼쪽 목록중 [보안]을 클릭합니다.

![구글메일설정](../images/mail002.png)

		그림 1-2
 
   3) 보안 항목에서 "보안 수준이 낮은 앱의 액세스"를 찾은 후 "액세스 사용 설정(권장하지 않음)"을 클릭합니다.

![구글메일설정](../images/mail003.png)

		그림 1-3
 

4) 보안 수준이 낮은 앱 허용을 사용함으로 변경합니다. 

![구글메일설정](../images/mail004.png)

		그림 1-4
 
![구글메일설정](../images/mail005.png)

		그림 1-5
 

<br><br>

#### 14-1-1-2. 네이버 이메일 설정

   1) 네이버 메일 페이지 들어간 후 왼쪽 최하단에 "환결 설정"을 클릭합니다.

![네이버메일설정](../images/mail006.png)

		그림 1-6
 
   2) 상단 목록 중 "POP3/IMAP 설정"을 클릭합니다.

![네이버메일설정](../images/mail007.png)

		그림 1-7
 
   3) "POP3/SMTP 사용"을 '사용함'으로 체크 후 확인버튼을 클릭합니다.

![네이버메일설정](../images/mail008.png)

		그림 1-8
 

<br><br>

#### 14-1-1-3. 다음 이메일 설정

   1) 다음 메일페이지 들어간 후 왼쪽 목록 중 제일 아래에 "환결 설정"을 클릭합니다.

![다음메일설정](../images/mail011.png)

		그림 1-9
 
   2) 상단 목록 중 "IMAP/POP3"을 클릭합니다.

![다음메일설정](../images/mail012.png)

		그림 1-10
 
   3) "IMAP / SMTP 사용"을 '사용함'으로 체크 후 저장을 클릭합니다.

![다음메일설정](../images/mail013.png)

		그림 1-11
 

<br><br><br>

### 14-1-2. pom.xml 설정

#### 14-1-2-1 mail 라이브러리를 추가해줍니다.

   - 버전은 자신에게 맞는 버전을 선택하시면 됩니다.
   - (글쓰는 현재 가장 최신 버전은 1.5.0-b01이지만 아직 안정적이지 않은 거 같아서 가장 많이 사용한 1.4.7을 선택하였습니다.)

```xml
<!-- https://mvnrepository.com/artifact/javax.mail/mail -->
<dependency>
    <groupId>javax.mail</groupId>
    <artifactId>mail</artifactId>
    <version>1.4.7</version>
</dependency>
```

![라이브러리추가](../images/mail021.png)

		그림 2-1

- java에서 메일을 보내기 위해 필요로한 라이브러리입니다.
- 실제 전송되는 메일 객체를 가집니다.
- 주요 클래스로 Session, Message, Address, Authenticator, Tranport 등이 잇습니다.
- 해당 라이브러리만으로도 메일 전송 기능이 구현 가능합니다. 하지만 개발자가 모든 세팅을 직접 해주어야 하기 때문에 번거롭습니다.
- 여기서는 spring-context-support 라이브러리를 사용할 것이기 때문에, mail 라이브러리에선 javax.mail.internet.MimeMessage만 사용됩니다. 

<br><br>

#### 14-1-2-2 spring-context-support 라이브러리를 추가합니다.
 
```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-context-support -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
    <version>${org.springframework-version}</version>
</dependency>
```

![라이브러리추가](../images/mail022.png)

			그림 2-2
 
- JavaMailSender 인터페이스(JavaMailSenderImpl)를 사용하기 위해 필요로 합니다. mail 라이브러리를 일일이 세팅을 할 필요 없이 몇 가지의 설정(MailSender Bean설정)만으로도 사용될 수 있도록 도와줍니다.
- JavaMailSender 인터페이스는 MIME 형식의 메일을 보낼 수 있도록 해줍니다.(javax.mail.MimeMessage 사용 가능)
- 스프링 프레임워크에서는 기본적으로 JavaMailSender 인터페이스와 비슷한 역할을 하는 메인 인터페이스 MainSender가 있습니다. 하지만 MainSender는 SimpleMailMessage만 사용 가능합니다. (javax.mail.MimeMessage 사용 불가능)

* SimpleMailMessage는 단순한 텍스트 메시지만 사용할 수 있고, MimeMessage는 텍스트와 더불어 이미지와 같은 첨부파일을 같이 메시지에 포함시킬 수 있습니다.

<br><br><br> 

### 14-1-3. 이메일로 파일을 보내기 위한 Bean 등록과 mailSender Bean 등록

- servlet-context.xml에 아래의 코드를 추가해줍니다.
  
```xml
	<!-- 멀티파트 리졸버 설정 -->
	<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="maxUploadSize" value="10485760" /> <!-- 10mb 제한 -->
		<beans:property name="defaultEncoding" value="UTF-8" />
	</beans:bean>
	
	<!-- 멀티파트 업로드 디렉토리 지정 -->
	<beans:bean id="uploadPath" class="java.lang.String">
		<beans:constructor-arg value="D:\kim\springframework\study14\src\main\webapp\resources\upload"></beans:constructor-arg>
	</beans:bean>
```  

<br><br>

- Bean 등록은 root-context.xml에 설정해주시면 됩니다. 릴레이를 허용하는 서버를 사용한 경우의 빈 설정과 아이디/비밀번호로 인증 후 발송하는 빈 설정은 구글 / 네이버 / 다음 순으로 알아보겠습니다. 

<br><br>

#### 14-1-3-1. 메일 서버가 릴레이(relay)를 허용하는 경우

- root-context.xml에 아래의 코드를 추가해줍니다.

```xml 
<bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
 <property name="host" value="stmp 도메인 주소"/> 
</bean>
```

- host의 value값은 아래의 링크에서 '2. 사용 가능한 메일서버 찾기(nslookup)'을 참고하셔서 해당 주소를 입력하시면 됩니다.

<br><br>
 
#### 14-1-3-2. 메일 서버에 계정 인증 후 메일을 전송하는 방법
 
- host, port, username, password <property> 태그는 각 메일서버에 맞게 설정해주시면 됩니다. 각 메일서버에서 가장 크게 다른 점은 name이 javaMailProperties인 <property> 태그입니다. 해당 태그는 각 메일서버에서 사용 중인 보안 프로토콜을 인증하기 위해 사용되는 코드가 삽입됩니다. 

<br><br>

**gmail**
  - root-context.xml에 아래의 코드를 추가해줍니다.

```xml
<!-- gmail설정 -->
<bean id="mailSender2" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
    <property name="host" value="smtp.gmail.com" />
    <property name="port" value="587" />
    <property name="username" value="your_email"/> <!-- 자신의 이메일 아이디 -->
    <property name="password" value="your_password!"/> <!-- 자신의 비밀번호 -->
    <property name="defaultEncoding" value="utf-8" />
    <!-- 보안연결 TLS과 관련된 설정 -->
    <property name="javaMailProperties">
        <props>
            <prop key="mail.smtp.starttls.enable">true</prop>
            <prop key="mail.smtp.auth">true </prop>
            <prop key="mail.transport.protocol">smtp</prop>
            <prop key="mail.debug">true</prop>
            <prop key="mail.smtp.ssl.trust">smtp.gmail.com</prop>
            <prop key="mail.smtp.ssl.protocols">TLSv1.2</prop>
        </props>
    </property>
</bean>
```
 
- host : "smtp.gmail.com" 입력
- port : "587" 입력
- username : 자신의 이메일 아이디 입력
- password : 자신의 이메일 비밀번호 입력
- javaMailProperties인 <property> 태그에 삽입된 코드는 TLS 인증 코드입니다.

* SSL이란 웹사이트와 브라우저 사이에서 전송되는 데이터를 암호화하여 인터넷 연결을 보안을 유지하는 표준 기술입니다. TLS는 더 강력한 버전의 SSL입니다. TLS 도한 SSL이라 불리기도 합니다. 

<br><br>

**naver**

- root-context.xml에 아래의 코드를 추가해줍니다.

```xml
<!-- navermail설정 -->
<bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
 <property name="host" value="smtp.naver.com"/> <!-- 메이서버 호스트 -->
 <property name="port" value="465"/> <!-- 메이서버 포트번호 -->
 <property name="username" value="your_email"/> <!-- 자신의 이메일 아이디 -->
 <property name="password" value="your_password"/> <!-- 자신의 비밀번호 -->
   <!-- 보안연결 SSL과 관련된 설정 -->
 <property name="javaMailProperties">
  <props>
  <prop key="mail.smtp.auth">true</prop>
  <prop key="mail.smtp.starttls.enable">true</prop>
  <prop key="mail.smtps.checkserveridentity">true</prop>
  <prop key="mail.smtps.ssl.trust">*</prop>
  <prop key="mail.debug">true</prop>
  <prop key="mail.smtp.socketFactory.class">javax.net.ssl.SSLSocketFactory</prop>
  </props>
 </property>
</bean>
```
- host : "smtp.naver.com" 입력
- port : "465" 입력
- username : 자신의 이메일 아이디 입력
- password : 자신의 이메일 비밀번호 입력
- javaMailProperties인 <property> 태그에 삽입된 코드는 SSL 인증 코드입니다.

<br><br>
 
**daum**

- root-context.xml에 아래의 코드를 추가해줍니다.

```xml
<!-- daum mail설정 -->
<bean id="mailSender3" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
        <property name="host" value="smtp.daum.net"/> <!-- 메이서버 호스트 -->
        <property name="port" value="465"/> <!-- 메일서버 포트번호 -->
        <property name="username" value="your_email"/> <!-- 자신의 이메일 아이디 -->
        <property name="password" value="your_password"/> <!-- 자신의 비밀번호 -->
        <property name="defaultEncoding" value="utf-8" />
        <!-- 보안연결 SSL과 관련된 설정 -->
        <property name="javaMailProperties">
            <props>
                <prop key="mail.smtp.auth">true</prop>
                <prop key="mail.smtp.starttls.enable">true</prop>
                <prop key="mail.smtps.checkserveridentity">true</prop>
                <prop key="mail.smtps.ssl.trust">*</prop>
                <prop key="mail.debug">true</prop>
                <prop key="mail.smtp.socketFactory.class">javax.net.ssl.SSLSocketFactory</prop>
            </props>
        </property>
</bean>
```

- host : "smtp.daum.net" 입력
- port : "465" 입력
- username : 자신의 이메일 아이디 입력
- password : 자신의 이메일 비밀번호 입력
- javaMailProperties인 <property>태그에 삽입된 코드는 네이버와 동일하게 SSL 인증 코드입니다.

<br><br><br>

**email 폴더 만들기**

src/main/webapp/WEB-INF/views에 email 폴더 만들기

<br><br>

**servlet-context.xml의 내용 추가**

```xml
    <!-- 생략 -->
    <resources mapping="/email/**" location="/WEB-INF/views/email" />
    <!-- 생략 -->
```

<br><br><br><br>

<div id="14-2"></div>

## 14-2. Controller / View(jsp) 작성
 
- MimeMessage 객체를 직접 생성하여 메일을 발송하는 방법과 MimeMessagePreparator 를 사용해서 메일을 전송하는 방법이 있습니다. 두가지 코드 모두 알아보겠습니다.

- 보내는 이 이메일, 받는 이 이메일, 메일 제목, 메일 내용 4가지의 데이터를 입력해주어야하는데 해당 데이터들을 VO객체에 담아서도 가능하고 Controller 메서드 내에 직접 삽입 하는 것도 가능합니다. 직접 삽입 방식으로 진행하겠습니다.

- 메일전송을 위해 필요로한 정보를 세팅하였던 "mailSender" Bean을 인젝션(의존성 주입) 하여 사용하겠습니다. 


```java
	@Autowired
	private JavaMailSenderImpl mailSender; //네이버 메일
	
	@Autowired
	private JavaMailSenderImpl mailSender2; //지메일
	
	@Autowired
	private JavaMailSenderImpl mailSender3; //다음 메일

    @Autowired
	private String uploadPath;
```

<br><br>

### 14-2-1. MimeMessage 객체를 직접 생성하여 메일을 발송하는 방법

이 방식을 사용하기 위해 import 되는 클래스들은 아래와 같습니다. 
 
```java
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
```

*MimeMessage 대신 SimpleMailMessage를 사용할 수도 있습니다. 둘의 차이점은 MimeMessage의 경우 멀티파트 데이터를 처리 할 수 있고 SimpleMailMessage는 단순한 텍스트 데이터만 전송이 가능합니다. 

<br>

Controller에 메서드는 아래와 같이 작성하면됩니다. 
 
```java
    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public void sendMailTest() throws Exception{
        
        String subject = "test 메일";
        String content = "메일 테스트 내용";
        String from = "보내는이 아이디@도메인주소";
        String to = "받는이 아이디@도메인주소";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            
            /*
             * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            
            mailHelper.setFrom(from);
            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true는 html을 사용하겠다는 의미입니다.
            
            /*
             * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
             */
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
``` 

<br><br>

- MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8") 에서 true는 멀티파트 메세지를 사용하겠다는 의미입니다. 단순한 텍스트 메세지만 사용하신다면 아래의 코드를 사용하시면됩니다.
- mailHelper.setText(content, true) 에서 true는 html을 사용한다는 의미입니다. html을 사용하게되면 이미지를 첨부 할 수 있는 img 태그를 사용 할 수 있습니다. 
- 단순한 텍스트만 사용할 시엔 mailHelper.setText(content)로 사용하시면 됩니다. 
- 보내는이(from)은 반드시 있어야합니다. mailSender 빈에서 아이디를 기입하였지만 이는 SMTP 사용 권한을 얻어 오는 역할을 수행합니다.


* 메일 주소에 보내는이와 메일주소를 모두 표기를 원한다면 아래와 같이 작성하시면 됩니다.
 
```java
mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
```

<br><br>

### 14-2-2. MimeMessagePreparator를 사용해서 메일을 전송하는 방법
 
- 해당 방식은 MimeMessage를 직접 객체화 하지 않고 MimeMessagePreparator 클래스를 사용하는 방식입니다. 이방식을 사용하기위해 import 되는 클래스는 다음과 같습니다.

- 14-2-1 과 다른점은 import org.springframework.mail.javamail.MimeMessagePreparator 가 추가적으로 추가 된다는 점입니다.
 
```java
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
```

- Controller에 메서드는 다음과 같이 작성 하시면 됩니다. 
- new MimeMessageHelper(mail,true,"UTF-8") / mailHelper.setText(content, true) 는 14-2-1 설명과 동일합니다.

 
```java
    @RequestMapping(value = "/sendMail2", method = RequestMethod.GET)
    public void sendMailTest2() throws Exception{
 
        String subject = "test 메일";
        String content = "메일 테스트 내용.";
        String from = "보내는이 아이디@도메인주소";
        String to = "받는이 아이디@도메인주소";
        
        
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            
            public void prepare(MimeMessage mimeMessage) throws Exception{
                final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                
                mailHelper.setFrom(from);
                mailHelper.setTo(to);
                mailHelper.setSubject(subject);
                mailHelper.setText(content, true);
 
            }
            
        };
        
        try {    
            mailSender.send(preparator);
            
        } catch(Exception e) {
            e.printStackTrace();
        }        
        
    }
```
 
<br><br><br>

### 14-2-2. 텍스트 / 이미지 / 업로드 전송
 
- 설명하기 앞서 이미지 / 업로드 전송을 위해선 멀티파트 메세지를 허용해야하며 html 허용을 설정해 주어야 합니다.해당 설명은 14-2-1 의 Controller 메서드 코드를 기반으로 진행하겠습니다. 


```java
// 멀티파트 메세지 허용
MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
 
// html 
mailHelper.setText(content, true);
```

<br><br> 

#### 14-2-2-1. 단순 텍스트 

- 14-2-1 코드 예시와 같이 메세지 내용을 단순한 텍스트로 전송하는 것입니다. 

```java
@RequestMapping(value = "/sendMail2", method = RequestMethod.GET)
    public void sendMailTest2() throws Exception{
        
        String subject = "test 메일";
        String content = "메일 테스트 내용";
        String from = "보내는이 아이디@도메인주소";
        String to = "받는이 아이디@도메인주소";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            
            /*
             * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            
            mailHelper.setFrom(from);
            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true는 html을 사용하겠다는 의미입니다.
            
            /*
             * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
             */
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
```

<br><br> 

#### 14-2-2-2. 이미지

- 이미지를 첨부하는 방법은 html 허용을 설정했기때문에 이미지 태그(<img>)를 사용합니다. 예를 들면 다음과 같습니다. 
 
```java
String content = "메일 테스트 내용" + "<img src=\"이미지 경로\">";
``` 

```java
    @RequestMapping(value = "/sendMail3", method = RequestMethod.GET)
    public void sendMailTest3() throws Exception{
        
        String subject = "test 메일";
        String content = "메일 테스트 내용" + "<img src=\"email_test_img.jpg\">";
        String from = "보내는이 아이디@도메인주소";
        String to = "받는이 아이디@도메인주소";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
```
 
<br><br>

#### 14-2-2-3. 업로드

- 업로드 된 파일을 전송하기 위해선 추가적인 클래스 import가 필요로합니다.
 
```java
import org.springframework.core.io.FileSystemResource;
import java.io.File;
```
 
추가해주어야할 코드는 다음과 같습니다.

```java
FileSystemResource file = new FileSystemResource(new File("경로\업로드할파일.형식")); 
helper.addAttachment("업로드파일.형식", file);
```
 
```java
    @RequestMapping(value = "/sendMail4", method = RequestMethod.GET)
    public void sendMailTest4() throws Exception{
        
        String subject = "test 메일";
        String content = "메일 테스트 내용" + "<img src=\"email_test_img.jpg\">";
        String from = "보내는이 아이디@도메인주소";
        String to = "받는이 아이디@도메인주소";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            
            FileSystemResource file = new FileSystemResource(new File("D:\\test.txt")); 
            mailHelper.addAttachment("업로드파일.형식", file);
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
```

<br><br>

### 14-2-3. 인증코드 발송

```java
	@Autowired
	private CustomService customService;

    @RequestMapping("findAuth")
    @ResponseBody
    public Map findAuth(@ModelAttribute("vo") Custom vo, Model model) {
        
        Map map = new HashMap();
        
        //사용자가 작성한 아이디를 기준으로 존재하는 사용자인지 확인한다.(id는 회원가입시 중복 체크를 했기 때문에 유니크하다.)
        Custom isUser = customService.getCustom(vo.getId());
        
        if(isUser != null) {//회원가입이 되어있는, 존재하는 사용자라면
            Random r = new Random();
            int num = r.nextInt(999999); //랜덤 난수 
            
            StringBuilder sb = new StringBuilder();
            
            // DB에 저장된 email            입력받은 email
            if(isUser.getEmail().equals(vo.getEmail())) {//이메일 정보 또한 동일하다면 
        
                String setFrom = "kkt09072@naver.com";//발신자 이메일
                String tomail = isUser.getEmail();//수신자 이메일
                String title = "비밀번호 변경 인증 이메일입니다.";
                sb.append(String.format("안녕하세요 %s님\n", isUser.getName()));
                sb.append(String.format("비밀번호 찾기(변경) 인증번호는 %d입니다.", num));
                String content = sb.toString();
                
                try {
                    MimeMessage msg = mailSender.createMimeMessage();
                    MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "utf-8");
                    msgHelper.setFrom(setFrom);
                    msgHelper.setTo(tomail);
                    msgHelper.setSubject(title);
                    msgHelper.setText(content);
                    mailSender.send(msg);                    //메일 전송                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                //성공적으로 메일을 보낸 경우
                map.put("status", true);
                map.put("num", num);
                map.put("m_idx", isUser.getId());                
            }
        }
		return map;
    }   
```

<br><br><br> 

### 14-2-4. 메일 전송 테스트

- 테스트 클래스를 통해 Controller 메서드에 추가했던 코드들을 테스트 해보겠습니다. 테스트 클래스를 생성 후 아래의 코드를 작성하였습니다. mailSendTest(), mailSendTest2()가 있는데 둘 중 하나를 사용하시면 됩니다. 
 
```java
import java.io.File;
 
import javax.mail.internet.MimeMessage;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MailTestController {
 
    @Autowired
    JavaMailSenderImpl mailSender;
    
    // MimeMessage 객체를 직접 생성하여 메일을 발송하는 방법
    @Test
    public void mailSendTest() throws Exception{
        
        String subject = "test 메일";
        String content = "메일 테스트 내용" + "<img src=\"email_test_img.jpg\">";
        String from = "보내는이 아이디@도메인주소";
        String to = "받는이 아이디@도메인주소";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
            
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            
            FileSystemResource file = new FileSystemResource(new File("D:\\test.txt")); 
            mailHelper.addAttachment("test.txt", file);            
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // MimeMessagePreparator를 사용해서 메일을 전송하는 방법
    @Test
    public void mailSendTest2() throws Exception{
        
        String subject = "test 메일";
        String content = "메일 테스트 내용" + "<img src=\"email_test_img.jpg\">";
        String from = "보내는이 아이디@도메인주소";
        String to = "받는이 아이디@도메인주소";
        
        try {
            final MimeMessagePreparator preparator = new MimeMessagePreparator() {
                
                public void prepare(MimeMessage mimeMessage) throws Exception{
                    final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    
                    mailHelper.setFrom(from);
                    mailHelper.setTo(to);
                    mailHelper.setSubject(subject);
                    mailHelper.setText(content, true);
                    
                    FileSystemResource file = new FileSystemResource(new File("D:\\test.txt")); 
                    mailHelper.addAttachment("test.txt", file);
                    
                }
                
            };
            
            mailSender.send(preparator);
            
        } catch(Exception e) {
            e.printStackTrace();
        }      
    }
}
``` 

- 테스트 결과입니다. 

![이메일테스트결과](../images/mail031.png)

			그림 2-1-1

<br><br><br>

### 14-2-5. 이메일 전체 소스 코드

**pom.xml 에 필요한 라이브러리 등록**

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>spring1</groupId>
  <artifactId>spring1</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>study10</name>
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
				
		<!-- aspectjweaver -->
		<dependency>
		  <groupId>org.aspectj</groupId>
		  <artifactId>aspectjweaver</artifactId>
		  <version>${org.aspectj-version}</version>
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
		
		<!-- org.json.simple JSON 라이브러리 -->
	    <dependency>
	      <groupId>org.apache.clerezza.ext</groupId>
	      <artifactId>org.json.simple</artifactId>
	      <version>0.4</version>
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
		    <version>6.2.0.Final</version>
		</dependency>
		<dependency>
		    <groupId>javax.xml.bind</groupId>
		    <artifactId>jaxb-api</artifactId>
		    <version>2.3.0</version>
		</dependency>
		
		<dependency>
		    <groupId>javax.annotation</groupId>
		    <artifactId>javax.annotation-api</artifactId>
		    <version>1.2</version>
		</dependency>
		
	    <!-- 스프링 암호화 라이브러리 -->
	    <dependency>
	      <groupId>org.springframework.security</groupId>
	      <artifactId>spring-security-core</artifactId>
	      <version>5.4.0</version>
	    </dependency>
	    <dependency>
	      <groupId>org.springframework.security</groupId>
	      <artifactId>spring-security-web</artifactId>
	      <version>5.4.0</version>
	    </dependency>
	    <dependency>
	      <groupId>org.springframework.security</groupId>
	      <artifactId>spring-security-config</artifactId>
	      <version>5.4.0</version>
	    </dependency>
	    <dependency>
	      <groupId>org.springframework.security</groupId>
	      <artifactId>spring-security-taglibs</artifactId>
	      <version>5.4.0</version>
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

#### 14-2-5-1. root-context.xml

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
	
	<!-- navermail설정 -->
	<bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
	 <property name="host" value="smtp.naver.com"/> <!-- 메이서버 호스트 -->
	 <property name="port" value="465"/> <!-- 메일서버 포트번호 -->
	 <property name="username" value="your_email"/> <!-- 자신의 이메일 아이디 -->
	 <property name="password" value="your_password"/> <!-- 자신의 비밀번호 -->
	 <property name="defaultEncoding" value="utf-8" />
	   <!-- 보안연결 SSL과 관련된 설정 -->
	 <property name="javaMailProperties">
	  <props>
	  <prop key="mail.smtp.auth">true</prop>
	  <prop key="mail.smtp.starttls.enable">true</prop>
	  <prop key="mail.smtps.checkserveridentity">true</prop>
	  <prop key="mail.smtps.ssl.trust">*</prop>
	  <prop key="mail.debug">true</prop>
	  <prop key="mail.smtp.socketFactory.class">javax.net.ssl.SSLSocketFactory</prop>
	  </props>
	 </property>
	</bean>
	
	<!-- gmail설정 -->
	<bean id="mailSender2" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
	     <property name="host" value="smtp.gmail.com" />
	     <property name="port" value="587" />
		 <property name="username" value="your_email"/> <!-- 자신의 이메일 아이디 -->
		 <property name="password" value="your_password!"/> <!-- 자신의 비밀번호 -->
		 <property name="defaultEncoding" value="utf-8" />
		 <!-- 보안연결 TLS과 관련된 설정 -->
	     <property name="javaMailProperties">
	     <props>
	        <prop key="mail.smtp.starttls.enable">true</prop>
			<prop key="mail.smtp.auth">true </prop>
			<prop key="mail.transport.protocol">smtp</prop>
			<prop key="mail.debug">true</prop>
			<prop key="mail.smtp.ssl.trust">smtp.gmail.com</prop>
			<prop key="mail.smtp.ssl.protocols">TLSv1.2</prop>
	     </props>
	     </property>
	</bean>

	<!-- daum email설정 -->
	<bean id="mailSender3" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
		 <property name="host" value="smtp.daum.net"/> <!-- 메이서버 호스트 -->
		 <property name="port" value="465"/> <!-- 메일서버 포트번호 -->
		 <property name="username" value="your_email"/> <!-- 자신의 이메일 아이디 -->
		 <property name="password" value="your_password"/> <!-- 자신의 비밀번호 -->
		 <property name="defaultEncoding" value="utf-8" />
		 <!-- 보안연결 SSL과 관련된 설정 -->
		 <property name="javaMailProperties">
			  <props>
				  <prop key="mail.smtp.auth">true</prop>
				  <prop key="mail.smtp.starttls.enable">true</prop>
				  <prop key="mail.smtps.checkserveridentity">true</prop>
				  <prop key="mail.smtps.ssl.trust">*</prop>
				  <prop key="mail.debug">true</prop>
				  <prop key="mail.smtp.socketFactory.class">javax.net.ssl.SSLSocketFactory</prop>
			  </props>
		 </property>
	</bean>
			
</beans>
```

<br><br>

#### 14-2-5-2. servlet-context.xml

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
	<resources mapping="/test/**" location="/WEB-INF/views/test" />
	<resources mapping="/test2/**" location="/WEB-INF/views/test2" />
	<resources mapping="/api/**" location="/WEB-INF/views/api" />
	<resources mapping="/ajax/**" location="/WEB-INF/views/ajax" />
	<resources mapping="/ajax2/**" location="/WEB-INF/views/ajax2" />
	<resources mapping="/custom/**" location="/WEB-INF/views/custom" />
	<resources mapping="/admin/**" location="/WEB-INF/views/admin" />
	<resources mapping="/email/**" location="/WEB-INF/views/email" />
		
	<!-- 리졸버에 대한 접두사와 접미사 설정 -->
	<!-- @Controller가 렌더링하기 위해 선택한 뷰를 /WEB-INF/views 디렉터리의 .jsp 리소스로 확인합니다. -->
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
	
	<!-- 멀티파트 리졸버 설정 -->
	<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="maxUploadSize" value="10485760" /> <!-- 10mb 제한 -->
		<beans:property name="defaultEncoding" value="UTF-8" />
	</beans:bean>
	
	<!-- 멀티파트 업로드 디렉토리 지정 -->
	<beans:bean id="uploadPath" class="java.lang.String">
		<beans:constructor-arg value="D:\kim\springframework\study14\src\main\webapp\resources\upload"></beans:constructor-arg>
	</beans:bean>
	
	<!-- Spring Validator 지정 -->
	<annotation-driven validator="validator"/>
	<beans:bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean" />
	
	<!-- 기본 메인 패키지 및 컨트롤러 패키지 설정 -->
	<context:component-scan base-package="com.spring1" />

	<beans:bean id="adminIntercepter" class="com.spring1.filter.AdminInterceptor" />
    <interceptors>
        <interceptor>
            <mapping path="/admin/**"/>
            <beans:ref bean="adminIntercepter" />
        </interceptor>
    </interceptors>
	
</beans:beans>
```

<br><br>

#### 14-2-5-3. 컨트롤러(Controller)

**com.spring1.controller.EmailController**

```java
package com.spring1.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring1.dto.Custom;
import com.spring1.service.CustomService;

@Controller
@RequestMapping("/email/")
public class EmailController {
	
	private static final Logger log = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private JavaMailSenderImpl mailSender; //네이버 메일
	
	@Autowired
	private JavaMailSenderImpl mailSender2; //지메일
	
	@Autowired
	private JavaMailSenderImpl mailSender3; //다음 메일

	@Autowired
	private String uploadPath;
	
	@Autowired
	private CustomService customService;
	
	@GetMapping("main")
	public String home(Model model) {
		return "email/home";
	}
	
	@GetMapping("email1")
	public String email1(Model model) {
		return "email/sendEmail1";
	}
	
	@GetMapping("email2")
	public String email2(Model model) {
		return "email/sendEmail2";
	}
	
	@GetMapping("email3")
	public String email3(Model model) {
		return "email/sendEmail3";
	}

	@GetMapping("email4")
	public String email4(Model model) {
		return "email/sendEmail4";
	}
	
	@GetMapping("email5")
	public String email5(Model model) {
		return "email/sendEmail5";
	}

	@GetMapping("email11")
	public String email11(Model model) {
		return "email/sendEmail11";
	}
	
	@GetMapping("email12")
	public String email12(Model model) {
		return "email/sendEmail12";
	}
	
	@GetMapping("email13")
	public String email13(Model model) {
		return "email/sendEmail13";
	}

	@GetMapping("email14")
	public String email14(Model model) {
		return "email/sendEmail14";
	}
	
	@GetMapping("email15")
	public String email15(Model model) {
		return "email/sendEmail15";
	}
	
	@GetMapping("email21")
	public String email21(Model model) {
		return "email/sendEmail21";
	}
	
	@GetMapping("email22")
	public String email22(Model model) {
		return "email/sendEmail22";
	}
	
	@GetMapping("email23")
	public String email23(Model model) {
		return "email/sendEmail23";
	}

	@GetMapping("email24")
	public String email24(Model model) {
		return "email/sendEmail24";
	}
	
	@GetMapping("email5")
	public String email25(Model model) {
		return "email/sendEmail25";
	}
	
	//여기부터는 네이버 이메일 보내기입니다.
	//1. MimeMessage 객체를 직접 생성하여 메일을 발송하는 방법
	@RequestMapping(value = "sendMail1", method = RequestMethod.POST)
    public String sendMailTest(HttpServletRequest req, Model model) throws Exception{
		String author = req.getParameter("author");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        boolean ps = false;
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            /*
             * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            mailHelper.setFrom(from);
            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true는 html을 사용하겠다는 의미입니다.            
            /*
             * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
             */
            mailSender.send(mail);
            ps = true;
        } catch(Exception e) {
        	ps= false;
            e.printStackTrace();
        } 

        model.addAttribute("ps", ps);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("name", author+"/"+name+"/"+tel);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        
        return "email/sendEmailOk1";
    }

	
	//2. MimeMessagePreparator를 사용해서 메일을 전송하는 방법
    @RequestMapping(value = "sendMail2", method = RequestMethod.GET)
    public String sendMail2(HttpServletRequest req, Model model) throws Exception{
		String author = req.getParameter("author");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String from = req.getParameter("from");
        String to = req.getParameter("to"); 
        boolean ps = false;
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception{
                final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");                
                mailHelper.setFrom(from);
                mailHelper.setTo(to);
                mailHelper.setSubject(subject);
                mailHelper.setText(content, true);
            }            
        };
        try {    
            mailSender.send(preparator);
            ps = true;
        } catch(Exception e) {
        	ps = false;
            e.printStackTrace();
        }
        
        model.addAttribute("ps", ps);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("name", author+"/"+name+"/"+tel);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        
        return "email/sendEmailOk2";
    }
    
    
    //3. 이미지를 내장하여 이메일 발송
    @RequestMapping(value = "sendMail3", method = RequestMethod.GET)
    public String sendMail3(MultipartHttpServletRequest multi, Model model) throws Exception{
    	
    	String root = multi.getSession().getServletContext().getRealPath("/");
    	String path = root + "resources/upload/";
    	boolean ps = false;
    	
    	File dir = new File(path);
    	
    	if(!dir.isDirectory()) {	//해당 디렉토리가 없는 경우 디렉토리 생성
    		dir.mkdir();
    	}
    	
    	Iterator<String> files = multi.getFileNames();
    	
    	String fileName_original = "";
    	String uploadFile = "";
    	MultipartFile mFile;
    	long size = 0L;
    	if(files.hasNext()) {
    		uploadFile = files.next();
    		mFile = multi.getFile(uploadFile);
    		fileName_original = mFile.getOriginalFilename();
    		try {
    			mFile.transferTo(new File(uploadPath+fileName_original));
    			size = mFile.getSize();
    			log.info("전송된 파일 이름 : "+fileName_original);
    			log.info("전송된 파일 크기 : "+mFile.getSize());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
		String author = multi.getParameter("author");
		String name = multi.getParameter("name");
		String tel = multi.getParameter("tel");
        String subject = multi.getParameter("subject");
        String content = multi.getParameter("content") + "<img src='"+fileName_original+"'>";
        String from = multi.getParameter("from");
        String to = multi.getParameter("to");
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            mailSender.send(mail);
            ps = true;
        } catch(Exception e) {
        	ps = false;
            e.printStackTrace();
        }
        
        model.addAttribute("ps", ps);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("name", author+"/"+name+"/"+tel);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        model.addAttribute("filename", fileName_original);
        model.addAttribute("size", size);
        
        return "email/sendEmailOk3";
    }

    
    //4. 파일을 첨부하여 이메일 발송
    @RequestMapping(value = "sendMail4", method = RequestMethod.GET)
    public String sendMail4(MultipartHttpServletRequest multi, Model model) throws Exception{
    	String root = multi.getSession().getServletContext().getRealPath("/");
    	String path = root + "resources/upload/";  
    	boolean ps = false;
    	
    	File dir = new File(path);
    	
    	if(!dir.isDirectory()) {	//해당 디렉토리가 없는 경우 디렉토리 생성
    		dir.mkdir();
    	}
    	
    	Iterator<String> files = multi.getFileNames();
    	String fileName_original = "";
    	String uploadFile = "";
    	MultipartFile mFile;
    	long size = 0L;
    	
    	if(files.hasNext()) {
    		uploadFile = files.next();
    		mFile = multi.getFile(uploadFile);
    		fileName_original = mFile.getOriginalFilename();
    		try {
    			mFile.transferTo(new File(uploadPath+fileName_original));
    			size = mFile.getSize();
    			log.info("전송된 파일 이름 : "+fileName_original);
    			log.info("전송된 파일 크기 : "+mFile.getSize());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
		String author = multi.getParameter("author");
		String name = multi.getParameter("name");
		String tel = multi.getParameter("tel");
        String subject = multi.getParameter("subject");
        String content = multi.getParameter("content");
        String from = multi.getParameter("from");
        String to = multi.getParameter("to");
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(uploadPath+fileName_original)); 
            mailHelper.addAttachment(fileName_original, file);
            mailSender.send(mail);
            ps = true;
        } catch(Exception e) {
        	ps = false;
            e.printStackTrace();
        }
        
        model.addAttribute("ps", ps);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("name", author+"/"+name+"/"+tel);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        model.addAttribute("filename", fileName_original);
        model.addAttribute("size", size);
        
        return "email/sendEmailOk4";
    }
    
    
    //5. 이메일 인증번호 보내기 
    @RequestMapping("findAuth")
    @ResponseBody
    public Map findAuth(@ModelAttribute("vo") Custom vo, Model model) {
        
        Map map = new HashMap();
        
        //사용자가 작성한 아이디를 기준으로 존재하는 사용자인지 확인한다.(id는 회원가입시 중복 체크를 했기 때문에 유니크하다.)
        Custom isUser = customService.getCustom(vo.getId());
        
        if(isUser != null) {//회원가입이 되어있는, 존재하는 사용자라면
            Random r = new Random();
            int num = r.nextInt(999999); //랜덤 난수 
            
            StringBuilder sb = new StringBuilder();
            
            // DB에 저장된 email            입력받은 email
            if(isUser.getEmail().equals(vo.getEmail())) {//이메일 정보 또한 동일하다면 
        
                String setFrom = "kkt09072@naver.com";//발신자 이메일
                String tomail = isUser.getEmail();//수신자 이메일
                String title = "비밀번호 변경 인증 이메일입니다.";
                sb.append(String.format("안녕하세요 %s님\n", isUser.getName()));
                sb.append(String.format("비밀번호 찾기(변경) 인증번호는 %d입니다.", num));
                String content = sb.toString();
                
                try {
                    MimeMessage msg = mailSender.createMimeMessage();
                    MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "utf-8");
                    msgHelper.setFrom(setFrom);
                    msgHelper.setTo(tomail);
                    msgHelper.setSubject(title);
                    msgHelper.setText(content);
                    mailSender.send(msg);                    //메일 전송                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                //성공적으로 메일을 보낸 경우
                map.put("status", true);
                map.put("num", num);
                map.put("m_idx", isUser.getId());                
            }
        }
		return map;
    }    
    

    /* 여기 부터는 Gmail 입니다. */
	//1. MimeMessage 객체를 직접 생성하여 메일을 발송하는 방법
	@RequestMapping(value = "sendMail11", method = RequestMethod.POST)
    public String sendMailTest11(HttpServletRequest req, Model model) throws Exception{
		String author = req.getParameter("author");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        boolean ps = false;
        try {
            MimeMessage mail = mailSender2.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            /*
             * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            mailHelper.setFrom(from);
            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true는 html을 사용하겠다는 의미입니다.            
            /*
             * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
             */
            mailSender2.send(mail);
            ps = true;
        } catch(Exception e) {
        	ps= false;
            e.printStackTrace();
        } 

        model.addAttribute("ps", ps);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("name", author+"/"+name+"/"+tel);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        
        return "email/sendEmailOk11";
    }

	
	//2. MimeMessagePreparator를 사용해서 메일을 전송하는 방법
    @RequestMapping(value = "sendMail12", method = RequestMethod.GET)
    public String sendMail12(HttpServletRequest req, Model model) throws Exception{
		String author = req.getParameter("author");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String from = req.getParameter("from");
        String to = req.getParameter("to"); 
        boolean ps = false;
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception{
                final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");                
                mailHelper.setFrom(from);
                mailHelper.setTo(to);
                mailHelper.setSubject(subject);
                mailHelper.setText(content, true);
            }            
        };
        try {    
            mailSender2.send(preparator);
            ps = true;
        } catch(Exception e) {
        	ps = false;
            e.printStackTrace();
        }
        
        model.addAttribute("ps", ps);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("name", author+"/"+name+"/"+tel);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        
        return "email/sendEmailOk12";
    }
    
    
    //3. 이미지를 내장하여 이메일 발송
    @RequestMapping(value = "sendMail13", method = RequestMethod.GET)
    public String sendMail13(MultipartHttpServletRequest multi, Model model) throws Exception{
    	
    	String root = multi.getSession().getServletContext().getRealPath("/");
    	String path = root + "resources/upload/";
    	boolean ps = false;
    	
    	File dir = new File(path);
    	
    	if(!dir.isDirectory()) {	//해당 디렉토리가 없는 경우 디렉토리 생성
    		dir.mkdir();
    	}
    	
    	Iterator<String> files = multi.getFileNames();
    	
    	String fileName_original = "";
    	String uploadFile = "";
    	MultipartFile mFile;
    	long size = 0L;
    	if(files.hasNext()) {
    		uploadFile = files.next();
    		mFile = multi.getFile(uploadFile);
    		fileName_original = mFile.getOriginalFilename();
    		try {
    			mFile.transferTo(new File(uploadPath+fileName_original));
    			size = mFile.getSize();
    			log.info("전송된 파일 이름 : "+fileName_original);
    			log.info("전송된 파일 크기 : "+mFile.getSize());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
		String author = multi.getParameter("author");
		String name = multi.getParameter("name");
		String tel = multi.getParameter("tel");
        String subject = multi.getParameter("subject");
        String content = multi.getParameter("content") + "<img src='"+fileName_original+"'>";
        String from = multi.getParameter("from");
        String to = multi.getParameter("to");
        
        try {
            MimeMessage mail = mailSender2.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            mailSender2.send(mail);
            ps = true;
        } catch(Exception e) {
        	ps = false;
            e.printStackTrace();
        }
        
        model.addAttribute("ps", ps);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("name", author+"/"+name+"/"+tel);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        model.addAttribute("filename", fileName_original);
        model.addAttribute("size", size);
        
        return "email/sendEmailOk13";
    }

    
    //4. 파일을 첨부하여 이메일 발송
    @RequestMapping(value = "sendMail14", method = RequestMethod.GET)
    public String sendMail14(MultipartHttpServletRequest multi, Model model) throws Exception{
    	String root = multi.getSession().getServletContext().getRealPath("/");
    	String path = root + "resources/upload/";  
    	boolean ps = false;
    	
    	File dir = new File(path);
    	
    	if(!dir.isDirectory()) {	//해당 디렉토리가 없는 경우 디렉토리 생성
    		dir.mkdir();
    	}
    	
    	Iterator<String> files = multi.getFileNames();
    	String fileName_original = "";
    	String uploadFile = "";
    	MultipartFile mFile;
    	long size = 0L;
    	
    	if(files.hasNext()) {
    		uploadFile = files.next();
    		mFile = multi.getFile(uploadFile);
    		fileName_original = mFile.getOriginalFilename();
    		try {
    			mFile.transferTo(new File(uploadPath+fileName_original));
    			size = mFile.getSize();
    			log.info("전송된 파일 이름 : "+fileName_original);
    			log.info("전송된 파일 크기 : "+mFile.getSize());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
		String author = multi.getParameter("author");
		String name = multi.getParameter("name");
		String tel = multi.getParameter("tel");
        String subject = multi.getParameter("subject");
        String content = multi.getParameter("content");
        String from = multi.getParameter("from");
        String to = multi.getParameter("to");
        
        try {
            MimeMessage mail = mailSender2.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(uploadPath+fileName_original)); 
            mailHelper.addAttachment(fileName_original, file);
            mailSender2.send(mail);
            ps = true;
        } catch(Exception e) {
        	ps = false;
            e.printStackTrace();
        }
        
        model.addAttribute("ps", ps);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("name", author+"/"+name+"/"+tel);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        model.addAttribute("filename", fileName_original);
        model.addAttribute("size", size);
        
        return "email/sendEmailOk14";
    }
    
    
    //5. 이메일 인증번호 보내기 
    @RequestMapping("findAuth2")
    @ResponseBody
    public Map findAuth2(@ModelAttribute("vo") Custom vo, Model model) {
        
        Map map = new HashMap();
        
        //사용자가 작성한 아이디를 기준으로 존재하는 사용자인지 확인한다.(id는 회원가입시 중복 체크를 했기 때문에 유니크하다.)
        Custom isUser = customService.getCustom(vo.getId());
        
        if(isUser != null) {//회원가입이 되어있는, 존재하는 사용자라면
            Random r = new Random();
            int num = r.nextInt(999999); //랜덤 난수 
            
            StringBuilder sb = new StringBuilder();
            
            // DB에 저장된 email            입력받은 email
            if(isUser.getEmail().equals(vo.getEmail())) {//이메일 정보 또한 동일하다면 
        
                String setFrom = "kkt09072@gmail.com";//발신자 이메일
                String tomail = isUser.getEmail();//수신자 이메일
                String title = "비밀번호 변경 인증 이메일입니다.";
                sb.append(String.format("안녕하세요 %s님\n", isUser.getName()));
                sb.append(String.format("비밀번호 찾기(변경) 인증번호는 %d입니다.", num));
                String content = sb.toString();
                
                try {
                    MimeMessage msg = mailSender2.createMimeMessage();
                    MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "utf-8");
                    msgHelper.setFrom(setFrom);
                    msgHelper.setTo(tomail);
                    msgHelper.setSubject(title);
                    msgHelper.setText(content);
                    mailSender2.send(msg);                    //메일 전송                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                //성공적으로 메일을 보낸 경우
                map.put("status", true);
                map.put("num", num);
                map.put("m_idx", isUser.getId());                
            }
        }
		return map;
    }    
    
    
    /* 여기 부터는 Daum Email 입니다. */
    //1. MimeMessage 객체를 직접 생성하여 메일을 발송하는 방법
  	@RequestMapping(value = "sendMail21", method = RequestMethod.POST)
      public String sendMailTest21(HttpServletRequest req, Model model) throws Exception{
  		String author = req.getParameter("author");
  		String name = req.getParameter("name");
  		String tel = req.getParameter("tel");
          String subject = req.getParameter("subject");
          String content = req.getParameter("content");
          String from = req.getParameter("from");
          String to = req.getParameter("to");
          boolean ps = false;
          try {
              MimeMessage mail = mailSender3.createMimeMessage();
              MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
              // true는 멀티파트 메세지를 사용하겠다는 의미
              /*
               * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
               * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
               */
              mailHelper.setFrom(from);
              // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
              // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
              //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
              mailHelper.setTo(to);
              mailHelper.setSubject(subject);
              mailHelper.setText(content, true);
              // true는 html을 사용하겠다는 의미입니다.            
              /*
               * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
               */
              mailSender3.send(mail);
              ps = true;
          } catch(Exception e) {
          	ps= false;
              e.printStackTrace();
          } 

          model.addAttribute("ps", ps);
          model.addAttribute("from", from);
          model.addAttribute("to", to);
          model.addAttribute("name", author+"/"+name+"/"+tel);
          model.addAttribute("subject", subject);
          model.addAttribute("content", content);
          
          return "email/sendEmailOk21";
      }

  	
  	//2. MimeMessagePreparator를 사용해서 메일을 전송하는 방법
      @RequestMapping(value = "sendMail22", method = RequestMethod.GET)
      public String sendMail22(HttpServletRequest req, Model model) throws Exception{
  		String author = req.getParameter("author");
  		String name = req.getParameter("name");
  		String tel = req.getParameter("tel");
          String subject = req.getParameter("subject");
          String content = req.getParameter("content");
          String from = req.getParameter("from");
          String to = req.getParameter("to"); 
          boolean ps = false;
          final MimeMessagePreparator preparator = new MimeMessagePreparator() {
              public void prepare(MimeMessage mimeMessage) throws Exception{
                  final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");                
                  mailHelper.setFrom(from);
                  mailHelper.setTo(to);
                  mailHelper.setSubject(subject);
                  mailHelper.setText(content, true);
              }            
          };
          try {    
              mailSender3.send(preparator);
              ps = true;
          } catch(Exception e) {
          	ps = false;
              e.printStackTrace();
          }
          
          model.addAttribute("ps", ps);
          model.addAttribute("from", from);
          model.addAttribute("to", to);
          model.addAttribute("name", author+"/"+name+"/"+tel);
          model.addAttribute("subject", subject);
          model.addAttribute("content", content);
          
          return "email/sendEmailOk22";
      }
      
      
      //3. 이미지를 내장하여 이메일 발송
      @RequestMapping(value = "sendMail23", method = RequestMethod.GET)
      public String sendMail23(MultipartHttpServletRequest multi, Model model) throws Exception{
      	
      	String root = multi.getSession().getServletContext().getRealPath("/");
      	String path = root + "resources/upload/";
      	boolean ps = false;
      	
      	File dir = new File(path);
      	
      	if(!dir.isDirectory()) {	//해당 디렉토리가 없는 경우 디렉토리 생성
      		dir.mkdir();
      	}
      	
      	Iterator<String> files = multi.getFileNames();
      	
      	String fileName_original = "";
      	String uploadFile = "";
      	MultipartFile mFile;
      	long size = 0L;
      	if(files.hasNext()) {
      		uploadFile = files.next();
      		mFile = multi.getFile(uploadFile);
      		fileName_original = mFile.getOriginalFilename();
      		try {
      			mFile.transferTo(new File(uploadPath+fileName_original));
      			size = mFile.getSize();
      			log.info("전송된 파일 이름 : "+fileName_original);
      			log.info("전송된 파일 크기 : "+mFile.getSize());
      		} catch (Exception e) {
      			e.printStackTrace();
      		}
      	}
  		String author = multi.getParameter("author");
  		String name = multi.getParameter("name");
  		String tel = multi.getParameter("tel");
          String subject = multi.getParameter("subject");
          String content = multi.getParameter("content") + "<img src='"+fileName_original+"'>";
          String from = multi.getParameter("from");
          String to = multi.getParameter("to");
          
          try {
              MimeMessage mail = mailSender3.createMimeMessage();
              MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
              mailHelper.setFrom(from);
              mailHelper.setTo(to);
              mailHelper.setSubject(subject);
              mailHelper.setText(content, true);
              mailSender3.send(mail);
              ps = true;
          } catch(Exception e) {
          	ps = false;
              e.printStackTrace();
          }
          
          model.addAttribute("ps", ps);
          model.addAttribute("from", from);
          model.addAttribute("to", to);
          model.addAttribute("name", author+"/"+name+"/"+tel);
          model.addAttribute("subject", subject);
          model.addAttribute("content", content);
          model.addAttribute("filename", fileName_original);
          model.addAttribute("size", size);
          
          return "email/sendEmailOk23";
      }

      
      //4. 파일을 첨부하여 이메일 발송
      @RequestMapping(value = "sendMail24", method = RequestMethod.GET)
      public String sendMail24(MultipartHttpServletRequest multi, Model model) throws Exception{
      	String root = multi.getSession().getServletContext().getRealPath("/");
      	String path = root + "resources/upload/";  
      	boolean ps = false;
      	
      	File dir = new File(path);
      	
      	if(!dir.isDirectory()) {	//해당 디렉토리가 없는 경우 디렉토리 생성
      		dir.mkdir();
      	}
      	
      	Iterator<String> files = multi.getFileNames();
      	String fileName_original = "";
      	String uploadFile = "";
      	MultipartFile mFile;
      	long size = 0L;
      	
      	if(files.hasNext()) {
      		uploadFile = files.next();
      		mFile = multi.getFile(uploadFile);
      		fileName_original = mFile.getOriginalFilename();
      		try {
      			mFile.transferTo(new File(uploadPath+fileName_original));
      			size = mFile.getSize();
      			log.info("전송된 파일 이름 : "+fileName_original);
      			log.info("전송된 파일 크기 : "+mFile.getSize());
      		} catch (Exception e) {
      			e.printStackTrace();
      		}
      	}
  		String author = multi.getParameter("author");
  		String name = multi.getParameter("name");
  		String tel = multi.getParameter("tel");
          String subject = multi.getParameter("subject");
          String content = multi.getParameter("content");
          String from = multi.getParameter("from");
          String to = multi.getParameter("to");
          
          try {
              MimeMessage mail = mailSender3.createMimeMessage();
              MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
              mailHelper.setFrom(from);
              mailHelper.setTo(to);
              mailHelper.setSubject(subject);
              mailHelper.setText(content, true);
              FileSystemResource file = new FileSystemResource(new File(uploadPath+fileName_original)); 
              mailHelper.addAttachment(fileName_original, file);
              mailSender3.send(mail);
              ps = true;
          } catch(Exception e) {
          	ps = false;
              e.printStackTrace();
          }
          
          model.addAttribute("ps", ps);
          model.addAttribute("from", from);
          model.addAttribute("to", to);
          model.addAttribute("name", author+"/"+name+"/"+tel);
          model.addAttribute("subject", subject);
          model.addAttribute("content", content);
          model.addAttribute("filename", fileName_original);
          model.addAttribute("size", size);
          
          return "email/sendEmailOk24";
      }
      
      
      //5. 이메일 인증번호 보내기 
      @RequestMapping("findAuth3")
      @ResponseBody
      public Map findAuth3(@ModelAttribute("vo") Custom vo, Model model) {
          
          Map map = new HashMap();
          
          //사용자가 작성한 아이디를 기준으로 존재하는 사용자인지 확인한다.(id는 회원가입시 중복 체크를 했기 때문에 유니크하다.)
          Custom isUser = customService.getCustom(vo.getId());
          
          if(isUser != null) {//회원가입이 되어있는, 존재하는 사용자라면
              Random r = new Random();
              int num = r.nextInt(999999); //랜덤 난수 
              
              StringBuilder sb = new StringBuilder();
              
              // DB에 저장된 email            입력받은 email
              if(isUser.getEmail().equals(vo.getEmail())) {//이메일 정보 또한 동일하다면 
          
                  String setFrom = "kkt090724@daum.net";//발신자 이메일
                  String tomail = isUser.getEmail();//수신자 이메일
                  String title = "비밀번호 변경 인증 이메일입니다.";
                  sb.append(String.format("안녕하세요 %s님\n", isUser.getName()));
                  sb.append(String.format("비밀번호 찾기(변경) 인증번호는 %d입니다.", num));
                  String content = sb.toString();
                  
                  try {
                      MimeMessage msg = mailSender3.createMimeMessage();
                      MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "utf-8");
                      msgHelper.setFrom(setFrom);
                      msgHelper.setTo(tomail);
                      msgHelper.setSubject(title);
                      msgHelper.setText(content);
                      mailSender3.send(msg);                    //메일 전송                    
                  } catch (Exception e) {
                      System.out.println(e.getMessage());
                  }
                  
                  //성공적으로 메일을 보낸 경우
                  map.put("status", true);
                  map.put("num", num);
                  map.put("m_idx", isUser.getId());                
              }
          }
  		return map;
      }    
}
```

<br><br>

#### 14-2-5-4. 이메일 발송 View(jsp)

**src/main/webapp/WEB-INF/views/home.jsp 작성**

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
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/email/main">이메일 보내기 실습</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/home.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기 실습</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기 실습</h3>
			<ul>
				<li><h3>네이버 이메일 보내기</h3></li>
				<li><a href="${path2 }/email/email1">1. MimeMessage 객체 메일 발송</a></li>
				<li><a href="${path2 }/email/email2">2. MimeMessagePreparator 이용하여 메일 발송</a></li>
				<li><a href="${path2 }/email/email3">3. 이미지를 내장하여 이메일 발송</a></li>
				<li><a href="${path2 }/email/email4">4. 파일을 첨부하여 이메일 발송</a></li>
				<li><a href="${path2 }/email/email5">5. 이메일 인증번호 보내기</a></li>
				<li><hr></li>
				<li><h3>지메일 보내기</h3></li>
				<li><a href="${path2 }/email/email11">1. MimeMessage 객체 메일 발송</a></li>
				<li><a href="${path2 }/email/email12">2. MimeMessagePreparator 이용하여 메일 발송</a></li>
				<li><a href="${path2 }/email/email13">3. 이미지를 내장하여 이메일 발송</a></li>
				<li><a href="${path2 }/email/email14">4. 파일을 첨부하여 이메일 발송</a></li>
				<li><a href="${path2 }/email/email15">5. 이메일 인증번호 보내기</a></li>
				<li><hr></li>
				<li><h3>다음 이메일 보내기</h3></li>
				<li><a href="${path2 }/email/email21">1. MimeMessage 객체 메일 발송</a></li>
				<li><a href="${path2 }/email/email22">2. MimeMessagePreparator 이용하여 메일 발송</a></li>
				<li><a href="${path2 }/email/email23">3. 이미지를 내장하여 이메일 발송</a></li>
				<li><a href="${path2 }/email/email24">4. 파일을 첨부하여 이메일 발송</a></li>
				<li><a href="${path2 }/email/email25">5. 이메일 인증번호 보내기</a></li>
				<li><hr></li>
			</ul>
			<hr>
			<a href="${path2 }">홈으로</a>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail1.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 이메일 보내기</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">네이버 이메일 보내기</h3>
			<form action="${path2 }/email/sendEmail1" method="post">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk1.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email1" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk2.jsp 작성**

```java
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail2.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 이메일 보내기2</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">네이버 이메일 보내기2</h3>
			<form action="${path2 }/email/sendEmail2" method="post">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk2.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기2 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기2 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email2" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail3.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 이메일 보내기3</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">네이버 이메일 보내기3 - 이미지 포함하여</h3>
			<form action="${path2 }/email/sendEmail3" method="post" encType="multipart/form-data">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
						<tr>
							<th><label for="file">이미지</label></th>
							<td>
								<input type="file" name="file" id="file" accept="image/*" class="form-control" required />
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk3.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기3 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기3 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						<tr>
							<th><label for="filename">파일 이름</label></th>
							<td>
								${filename }
							</td>
						</tr>
						<tr>
							<th><label for="size">파일 크기</label></th>
							<td>
								${size } Byte
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email3" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail4.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 이메일 보내기4</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">네이버 이메일 보내기4 - 파일 첨부 전송</h3>
			<form action="${path2 }/email/sendEmail4" method="post" encType="multipart/form-data">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
						<tr>
							<th><label for="file">첨부 파일</label></th>
							<td>
								<input type="file" name="file" id="file" class="form-control" required />
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk4.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기4 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기4 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						<tr>
							<th><label for="filename">파일 이름</label></th>
							<td>
								${filename }
							</td>
						</tr>
						<tr>
							<th><label for="size">파일 크기</label></th>
							<td>
								${size } Byte
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email4" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail5.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 이메일 보내기5</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">네이버 이메일 보내기5 - 인증키 발송</h3>
			<form action="${path2 }/email/findAuth" method="post">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>	
						<tr>
							<th><label for="to">이메일</label></th>
							<td>
								<input type="hidden" name="from" id="from" class="form-control" value="kkt09072@naver.com" maxlength="100" required>
								<input type="email" name="to" id="to" class="form-control" maxlength="100">
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 인증</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```


<br><br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail11.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지메일 보내기</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">지메일 보내기</h3>
			<form action="${path2 }/email/sendEmail11" method="post">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk11.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email11" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail12.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지메일 보내기2</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">지메일 보내기2</h3>
			<form action="${path2 }/email/sendEmail12" method="post">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk12.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기2 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기2 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email12" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail13.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지메일 보내기3</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">지메일 보내기3 - 이미지 포함하여</h3>
			<form action="${path2 }/email/sendEmail13" method="post" encType="multipart/form-data">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
						<tr>
							<th><label for="file">이미지</label></th>
							<td>
								<input type="file" name="file" id="file" accept="image/*" class="form-control" required />
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk13.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기3 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기3 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						<tr>
							<th><label for="filename">파일 이름</label></th>
							<td>
								${filename }
							</td>
						</tr>
						<tr>
							<th><label for="size">파일 크기</label></th>
							<td>
								${size } Byte
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email13" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail14.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지메일 보내기4</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">지메일 보내기4 - 파일 첨부 전송</h3>
			<form action="${path2 }/email/sendEmail14" method="post" encType="multipart/form-data">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
						<tr>
							<th><label for="file">첨부 파일</label></th>
							<td>
								<input type="file" name="file" id="file" class="form-control" required />
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk14.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기4 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기4 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						<tr>
							<th><label for="filename">파일 이름</label></th>
							<td>
								${filename }
							</td>
						</tr>
						<tr>
							<th><label for="size">파일 크기</label></th>
							<td>
								${size } Byte
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email14" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail15.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지메일 보내기</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">지메일 보내기 - 인증키 발송</h3>
			<form action="${path2 }/email/findAuth2" method="post">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>	
						<tr>
							<th><label for="to">이메일</label></th>
							<td>
								<input type="hidden" name="from" id="from" class="form-control" value="kkt09072@naver.com" maxlength="100" required>
								<input type="email" name="to" id="to" class="form-control" maxlength="100">
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 인증</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail21.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다음 이메일 보내기</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">다음 이메일 보내기</h3>
			<form action="${path2 }/email/sendEmail21" method="post">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk21.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기1 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기1 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email11" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail22.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다음 이메일 보내기2</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">다음 이메일 보내기2</h3>
			<form action="${path2 }/email/sendEmail22" method="post">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk22.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기2 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기2 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email12" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail23.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다음 이메일 보내기3</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">다음 이메일 보내기3 - 이미지 포함하여</h3>
			<form action="${path2 }/email/sendEmail23" method="post" encType="multipart/form-data">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
						<tr>
							<th><label for="file">이미지</label></th>
							<td>
								<input type="file" name="file" id="file" accept="image/*" class="form-control" required />
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk23.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기3 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기3 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						<tr>
							<th><label for="filename">파일 이름</label></th>
							<td>
								${filename }
							</td>
						</tr>
						<tr>
							<th><label for="size">파일 크기</label></th>
							<td>
								${size } Byte
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email13" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail24.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다음 이메일 보내기4</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">다음 이메일 보내기4 - 파일 첨부 전송</h3>
			<form action="${path2 }/email/sendEmail24" method="post" encType="multipart/form-data">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								<input type="email" name="from" id="from" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								<input type="email" name="to" id="to" class="form-control" value="kkt09072@naver.com" maxlength="100" readonly>
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								<input type="text" name="subject" id="subject" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								<textarea name="content" id="content" rows="8" cols="80" class="form-control"></textarea>
							</td>
						</tr>
						<tr>
							<th><label for="file">첨부 파일</label></th>
							<td>
								<input type="file" name="file" id="file" class="form-control" required />
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 발송</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmailOk24.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기4 결과</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">이메일 보내기4 결과</h3>
				<table class="table">
					<tbody>
						<c:if test="${ps }">
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								${name }
							</td>
						</tr>
						<tr>
							<th><label for="from">작성자 이메일</label></th>
							<td>
								${from }
							</td>
						</tr>
						<tr>
							<th><label for="to">받는 사람 이메일</label></th>
							<td>
								${to }
							</td>
						</tr>
						<tr>
							<th><label for="subject">이메일 제목</label></th>
							<td>
								${subject }
							</td>
						</tr>
						<tr>
							<th><label for="content">이메일 내용</label></th>
							<td>
								${content }
							</td>
						</tr>
						<tr>
							<th><label for="filename">파일 이름</label></th>
							<td>
								${filename }
							</td>
						</tr>
						<tr>
							<th><label for="size">파일 크기</label></th>
							<td>
								${size } Byte
							</td>
						</tr>
						</c:if>
						<c:if test="${not ps }">
							<tr>
								<td colspan="2">이메일 보내기 실패</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<hr>
				<div class="btn-group">
				  <a href="${path2 }/email/email14" class="btn btn-secondary">이메일 폼으로 가기</a>
				  <a href="${path2 }" class="btn btn-secondary">홈으로</a>
				</div>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/email/sendEmail25.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다음 이메일 보내기</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">다음 메일 보내기 - 인증키 발송</h3>
			<form action="${path2 }/email/findAuth3" method="post">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="author">작성자 아이디</label></th>
							<td>
								<input type="text" name="author" id="author" class="form-control" maxlength="100" required>
							</td>
						</tr>
						<tr>
							<th><label for="name">작성자 이름</label></th>
							<td>
								<input type="text" name="name" id="name" class="form-control" maxlength="100" required>
							</td>
						</tr>	
						<tr>
							<th><label for="to">이메일</label></th>
							<td>
								<input type="hidden" name="from" id="from" class="form-control" value="kkt09072@naver.com" maxlength="100" required>
								<input type="email" name="to" id="to" class="form-control" maxlength="100">
							</td>
						</tr>
						<tr>
							<th><label for="tel">연락처</label></th>
							<td>
								<input type="tel" name="tel" id="tel" class="form-control" maxlength="100" required>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="btn-group">
				  <button type="submit" class="btn btn-secondary">이메일 인증</button>
				</div>
			</form>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br><hr><br><br>

## 14-3. 파일 업로드 구현

### 14-3-1. 의존성 라이브러리 등록

**pom.xml에 파일업로드 관련 라이브러리 등록**

```xml
    <!-- 생략 -->
    <!-- 서블릿 3.0이상 사용 가능한 파일 업로드 api-->
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.3.1</version>
    </dependency>
    
    <!-- commons-io -->
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.4</version>
    </dependency>
    <!-- 생략 -->
```

<br><br>

**pom.xml 전체 코드**

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>spring1</groupId>
  <artifactId>spring1</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>study10</name>
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
				
		<!-- aspectjweaver -->
		<dependency>
		  <groupId>org.aspectj</groupId>
		  <artifactId>aspectjweaver</artifactId>
		  <version>${org.aspectj-version}</version>
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
		
		<!-- org.json.simple JSON 라이브러리 -->
	    <dependency>
	      <groupId>org.apache.clerezza.ext</groupId>
	      <artifactId>org.json.simple</artifactId>
	      <version>0.4</version>
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
		    <version>6.2.0.Final</version>
		</dependency>
		<dependency>
		    <groupId>javax.xml.bind</groupId>
		    <artifactId>jaxb-api</artifactId>
		    <version>2.3.0</version>
		</dependency>
		
		<dependency>
		    <groupId>javax.annotation</groupId>
		    <artifactId>javax.annotation-api</artifactId>
		    <version>1.2</version>
		</dependency>
		
	    <!-- 스프링 암호화 라이브러리 -->
	    <dependency>
	      <groupId>org.springframework.security</groupId>
	      <artifactId>spring-security-core</artifactId>
	      <version>5.4.0</version>
	    </dependency>
	    <dependency>
	      <groupId>org.springframework.security</groupId>
	      <artifactId>spring-security-web</artifactId>
	      <version>5.4.0</version>
	    </dependency>
	    <dependency>
	      <groupId>org.springframework.security</groupId>
	      <artifactId>spring-security-config</artifactId>
	      <version>5.4.0</version>
	    </dependency>
	    <dependency>
	      <groupId>org.springframework.security</groupId>
	      <artifactId>spring-security-taglibs</artifactId>
	      <version>5.4.0</version>
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

<br><br><br>

### 14-3-2. 객체 등록 및 빈(Bean) 생성

**root-context.xml의 내용 추가**

```xml
    <!-- 생략 -->
    <beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
      <beans:property name="maxUploadSize" value="10485760" />
      <beans:property name="defaultEncoding" value="utf-8" />
   </beans:bean>
   <!-- 생략 -->
```

<br><br>

**root-context.xml 전체코드**

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
	
	<!-- navermail설정 -->
	<bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
	 <property name="host" value="smtp.naver.com"/> <!-- 메이서버 호스트 -->
	 <property name="port" value="465"/> <!-- 메일서버 포트번호 -->
	 <property name="username" value="your_email"/> <!-- 자신의 이메일 아이디 -->
	 <property name="password" value="your_password"/> <!-- 자신의 비밀번호 -->
	 <property name="defaultEncoding" value="utf-8" />
	   <!-- 보안연결 SSL과 관련된 설정 -->
	 <property name="javaMailProperties">
	  <props>
	  <prop key="mail.smtp.auth">true</prop>
	  <prop key="mail.smtp.starttls.enable">true</prop>
	  <prop key="mail.smtps.checkserveridentity">true</prop>
	  <prop key="mail.smtps.ssl.trust">*</prop>
	  <prop key="mail.debug">true</prop>
	  <prop key="mail.smtp.socketFactory.class">javax.net.ssl.SSLSocketFactory</prop>
	  </props>
	 </property>
	</bean>
	
	<!-- gmail설정 -->
	<bean id="mailSender2" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
	     <property name="host" value="smtp.gmail.com" />
	     <property name="port" value="587" />
		 <property name="username" value="your_email"/> <!-- 자신의 이메일 아이디 -->
		 <property name="password" value="your_password!"/> <!-- 자신의 비밀번호 -->
		 <property name="defaultEncoding" value="utf-8" />
		 <!-- 보안연결 TLS과 관련된 설정 -->
	     <property name="javaMailProperties">
	     <props>
	        <prop key="mail.smtp.starttls.enable">true</prop>
			<prop key="mail.smtp.auth">true </prop>
			<prop key="mail.transport.protocol">smtp</prop>
			<prop key="mail.debug">true</prop>
			<prop key="mail.smtp.ssl.trust">smtp.gmail.com</prop>
			<prop key="mail.smtp.ssl.protocols">TLSv1.2</prop>
	     </props>
	     </property>
	</bean>

	<!-- daum email설정 -->
	<bean id="mailSender3" class="org.springframework.mail.javamail.JavaMailSenderImpl"> 
		 <property name="host" value="smtp.daum.net"/> <!-- 메이서버 호스트 -->
		 <property name="port" value="465"/> <!-- 메일서버 포트번호 -->
		 <property name="username" value="your_email"/> <!-- 자신의 이메일 아이디 -->
		 <property name="password" value="your_password"/> <!-- 자신의 비밀번호 -->
		 <property name="defaultEncoding" value="utf-8" />
		 <!-- 보안연결 SSL과 관련된 설정 -->
		 <property name="javaMailProperties">
			  <props>
				  <prop key="mail.smtp.auth">true</prop>
				  <prop key="mail.smtp.starttls.enable">true</prop>
				  <prop key="mail.smtps.checkserveridentity">true</prop>
				  <prop key="mail.smtps.ssl.trust">*</prop>
				  <prop key="mail.debug">true</prop>
				  <prop key="mail.smtp.socketFactory.class">javax.net.ssl.SSLSocketFactory</prop>
			  </props>
		 </property>
	</bean>	
</beans>
```

<br><br><br>

### 14-3-3. Servlet을 통한 View의 접근 권한 열기

**fileupload 폴더 만들기**

src/main/webapp/WEB-INF/views에 fileupload 폴더 만들기

<br><br>

**servlet-context.xml의 내용 추가**

```xml
    <!-- 생략 -->
    <resources mapping="/fileupload/**" location="/WEB-INF/views/fileupload" />
    <!-- 생략 -->
```

<br><br>

**servlet-context.xml의 전체 코드**

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
	<resources mapping="/test/**" location="/WEB-INF/views/test" />
	<resources mapping="/test2/**" location="/WEB-INF/views/test2" />
	<resources mapping="/api/**" location="/WEB-INF/views/api" />
	<resources mapping="/ajax/**" location="/WEB-INF/views/ajax" />
	<resources mapping="/ajax2/**" location="/WEB-INF/views/ajax2" />
	<resources mapping="/custom/**" location="/WEB-INF/views/custom" />
	<resources mapping="/admin/**" location="/WEB-INF/views/admin" />
	<resources mapping="/email/**" location="/WEB-INF/views/email" />
	<resources mapping="/fileupload/**" location="/WEB-INF/views/fileupload" />
		
	<!-- 리졸버에 대한 접두사와 접미사 설정 -->
	<!-- @Controller가 렌더링하기 위해 선택한 뷰를 /WEB-INF/views 디렉터리의 .jsp 리소스로 확인합니다. -->
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
	
	<!-- 멀티파트 리졸버 설정 -->
	<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="maxUploadSize" value="10485760" /> <!-- 10mb 제한 -->
		<beans:property name="defaultEncoding" value="UTF-8" />
	</beans:bean>
	
	<!-- 멀티파트 업로드 디렉토리 지정 -->
	<beans:bean id="uploadPath" class="java.lang.String">
		<beans:constructor-arg value="D:\kim\springframework\study14\src\main\webapp\resources\upload"></beans:constructor-arg>
	</beans:bean>
	
	<!-- Spring Validator 지정 -->
	<annotation-driven validator="validator"/>
	<beans:bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean" />
	
	<!-- 기본 메인 패키지 및 컨트롤러 패키지 설정 -->
	<context:component-scan base-package="com.spring1" />

	<beans:bean id="adminIntercepter" class="com.spring1.filter.AdminInterceptor" />
    <interceptors>
        <interceptor>
            <mapping path="/admin/**"/>
            <beans:ref bean="adminIntercepter" />
        </interceptor>
    </interceptors>
	
</beans:beans>
```

<br><br><br>

### 14-3-4. 파일 업로드 VIEW(jsp) 파일 작성

**Form encType**

| 속성값 | 설명 |
|--------------------------------------|-----------------------------------------------------------------|
| application/x-www-form-urlencode | 기본값으로 모든 문자들은 서버로 보내기 전에 인코딩되어야 함을 지정 |
| multipart/form-data | 모든 문자를 인코딩하지 않으며, 이 방식은 `<form>` 요소가 파일이나 이미지를 서버로 전송시에 지정 |
| text/plain | 공백 문자는 "+" 기호로 변환하지만, 나머지 문자는 모두 인코딩되지 않을 경우 지정 |

<br><br>

**src/main/webapp/WEB-INF/views/home.jsp 내용 수정**

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
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/email/main">이메일 보내기 실습</a></li>
        <li><a href="${path2 }/fileupload/main">파일 업로드 실습</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/home.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 실습</title>
</head>
<body>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">파일 업로드 실습</h3>
			<ul>
				<li><h3>로컬 시스템에 업로드</h3></li>
				<li><a href="${path2 }/fileupload/upload">파일 업로드 테스트1 - 하나의 파일</a></li>
				<li><a href="${path2 }/fileupload/upload2">파일 업로드 테스트2 - 여러 파일</a></li>
				<li><a href="${path2 }/fileupload/upload3">파일 업로드 테스트3 - 자료실 파일 첨부</a></li>
				<li><a href="${path2 }/fileupload/upload4">파일 업로드 테스트4 - 상품 정보 여러 파일 첨부</a></li>
				<li><hr></li>
				<li><h3>서버 시스템에 업로드</h3></li>
				<li><a href="${path2 }/fileupload/upload5">파일 업로드 테스트5 - 하나의 파일</a></li>
				<li><a href="${path2 }/fileupload/upload6">파일 업로드 테스트6 - 여러 파일</a></li>
				<li><a href="${path2 }/fileupload/upload7">파일 업로드 테스트7 - 자료실 파일 첨부</a></li>
				<li><a href="${path2 }/fileupload/upload8">파일 업로드 테스트8 - 상품 정보 여러 파일 첨부</a></li>
				<li><hr></li>
				<li><h3>서버 시스템에 파일 변경/삭제</h3></li>
				<li><a href="${path2 }/fileupload/update">파일 업로드 테스트9 - 상품 정보 파일 변경</a></li>
				<li><a href="${path2 }/fileupload/delete">파일 업로드 테스트10 - 상품 이미지 삭제</a></li>
			</ul>
			<hr>
			<a href="${path2 }">홈으로</a>
		</div>
	</section>
</div>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUpload.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 업로드</title>
</head>
<body>
    <h2>파일 업로드</h2>

	<!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
	<form action="${path0}/fileupload/uploadPro" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" name="file">
		<input type="submit" value="전송">
	</form>
	
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUploadOk.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 업로드 성공</title>
</head>
<body>
    <h2>파일 업로드 성공</h2>

	<div>
		<ul>
			<li>${fileRealName }</li>
			<li>${size }</li>
			<li>${uuid }</li>
			<li>${uuidName }</li>
		</ul>
	</div>
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUpload2.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 업로드2</title>
</head>
<body>
    <h2>파일 업로드2</h2>
	
	<!-- 파일 두 개이상 붙히는거 -->
	<form action="${path0}/fileupload/uploadPro2" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" multiple="multiple" name="files"> 
		<input type="submit" value="전송">
	</form>
	
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUploadOk2.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 업로드 성공2</title>
</head>
<body>
    <h2>파일 업로드 성공2</h2>

    <c:choose>
        <c:when test="${not empty datas}">
            <c:forEach var="data" items="${datas}">
                <div class="item">
                    <ul>
                        <li>${data.name}</li>
                        <li>${data.size} Byte</li>
                    </ul>
                    <hr>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>업로드된 파일이 없습니다.</p>
        </c:otherwise>
    </c:choose>
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUpload3.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 및 글 업로드</title>
</head>
<body>
    <h2>파일 및 글 업로드</h2>
	
	<!-- 파일 두 개이상 붙히는거 -->
	<h2>파일 및 글 업로드</h2>
    <form action="${path0}/fileupload/uploadPro3" method="post" enctype="multipart/form-data">
        <label for="title">제목:</label>
        <input type="text" id="title" name="title"><br><br>
        <label for="content">내용:</label>
        <textarea id="content" name="content"></textarea><br><br>
        <label for="file">파일 선택:</label>
        <input type="file" id="file" name="file"><br><br>
        <button type="submit">업로드</button>
    </form>
	
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUploadOk3.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 글 업로드 성공</title>
</head>
<body>
    <h2>파일 글 업로드 성공</h2>
	<hr>
    <div class="item">
        <ul>
            <li>제목: ${title}</li>
		    <li>내용: ${content}</li>
		    <li>파일명: ${fileRealName}</li>
		    <li>파일 크기: ${size} bytes</li>
		    <li>저장된 파일 경로: ${uuidName}</li>
        </ul>
        <hr>
    </div>
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUpload4.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 정보 및 이미지 업로드</title>
</head>
<body>
    <h2>상품 정보 및 이미지 업로드</h2>
    <form action="${path0 }/fileupload/uploadPro4" method="post" enctype="multipart/form-data">
        <label for="productName">상품명:</label>
        <input type="text" id="productName" name="productName"><br><br>
        <label for="description">설명:</label>
        <textarea id="description" name="description"></textarea><br><br>
        <label for="price">가격:</label>
        <input type="number" id="price" name="price"><br><br>
        <label for="files">이미지 선택:</label>
        <input type="file" id="files" name="files" multiple><br><br>
        <button type="submit">업로드</button>
    </form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUploadOk4.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>상품 정보 업로드 성공</title>
</head>
<body>
    <h2>상품 정보 업로드 성공</h2>
    <p>상품명: ${product.name}</p>
    <p>설명: ${product.description}</p>
    <p>가격: ${product.price}</p>
    <h3>이미지 목록:</h3>
    <c:forEach var="image" items="${product.images}">
        <div class="item">
            <ul>
                <li>파일명: ${image.name}</li>
                <li>파일 크기: ${image.size} bytes</li>
            </ul>
            <hr>
        </div>
    </c:forEach>
    <a href="${path0}">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUpload5.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 업로드</title>
</head>
<body>
    <h2>파일 업로드</h2>

	<!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
	<form action="${path0}/fileupload/uploadPro5" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" name="file">
		<input type="submit" value="전송">
	</form>
	<hr>
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUploadOk5.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 업로드 성공</title>
</head>
<body>
    <h2>파일 업로드 성공</h2>

	<div>
		<ul>
			<li>${fileRealName }</li>
			<li>${size }</li>
			<li>${uuid }</li>
			<li>${uuidName }</li>
		</ul>
	</div>
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUpload6.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 업로드2</title>
</head>
<body>
    <h2>파일 업로드2</h2>
	
	<!-- 파일 두 개이상 붙히는거 -->
	<form action="${path0}/fileupload/uploadPro6" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" multiple="multiple" name="files"> 
		<input type="submit" value="전송">
	</form>
	<hr>
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUploadOk6.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 업로드 성공2</title>
</head>
<body>
    <h2>파일 업로드 성공2</h2>

    <c:choose>
        <c:when test="${not empty datas}">
            <c:forEach var="data" items="${datas}">
                <div class="item">
                    <ul>
                        <li>${data.name}</li>
                        <li>${data.size} Byte</li>
                    </ul>
                    <hr>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>업로드된 파일이 없습니다.</p>
        </c:otherwise>
    </c:choose>
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUpload7.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 및 글 업로드</title>
</head>
<body>
    <h2>파일 및 글 업로드</h2>
	
	<!-- 파일 두 개이상 붙히는거 -->
	<h2>파일 및 글 업로드</h2>
    <form action="${path0}/fileupload/uploadPro7" method="post" enctype="multipart/form-data">
        <label for="title">제목:</label>
        <input type="text" id="title" name="title"><br><br>
        <label for="content">내용:</label>
        <textarea id="content" name="content"></textarea><br><br>
        <label for="file">파일 선택:</label>
        <input type="file" id="file" name="file"><br><br>
        <button type="submit">업로드</button>
    </form>
    <hr>
    <a href="${path0}">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUploadOk7.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 글 업로드 성공</title>
</head>
<body>
    <h2>파일 글 업로드 성공</h2>
	<hr>
    <div class="item">
        <ul>
            <li>제목: ${title}</li>
		    <li>내용: ${content}</li>
		    <li>파일명: ${fileRealName}</li>
		    <li>파일 크기: ${size} bytes</li>
		    <li>저장된 파일 경로: ${uuidName}</li>
        </ul>
        <hr>
    </div>
	<a href="${path0 }">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUpload8.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 정보 및 이미지 업로드</title>
</head>
<body>
    <h2>상품 정보 및 이미지 업로드</h2>
    <form action="${path0 }/fileupload/uploadPro8" method="post" enctype="multipart/form-data">
        <label for="productName">상품명:</label>
        <input type="text" id="productName" name="productName"><br><br>
        <label for="description">설명:</label>
        <textarea id="description" name="description"></textarea><br><br>
        <label for="price">가격:</label>
        <input type="number" id="price" name="price"><br><br>
        <label for="files">이미지 선택:</label>
        <input type="file" id="files" name="files" multiple><br><br>
        <button type="submit">업로드</button>
    </form>
    <hr>
    <a href="${path0}">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUploadOk8.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>상품 정보 업로드 성공</title>
</head>
<body>
    <h2>상품 정보 업로드 성공</h2>
    <p>상품명: ${product.name}</p>
    <p>설명: ${product.description}</p>
    <p>가격: ${product.price}</p>
    <h3>이미지 목록:</h3>
    <c:forEach var="image" items="${product.images}">
        <div class="item">
            <ul>
                <li>파일명: ${image.name}</li>
                <li>파일 크기: ${image.size} bytes</li>
            </ul>
            <hr>
        </div>
    </c:forEach>
    <a href="${path0}">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/fileUploadUpdate.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 정보 및 이미지 변경</title>
</head>
<body>
    <h2>상품 정보 및 이미지 변경</h2>
    <form action="${path0}/fileupload/updatePro" method="post" enctype="multipart/form-data">
        <input type="hidden" name="productId" value="${product.id}">
        <label for="productName">상품명:</label>
        <input type="text" id="productName" name="productName" value="${product.name}"><br><br>
        <label for="description">설명:</label>
        <textarea id="description" name="description">${product.description}</textarea><br><br>
        <label for="price">가격:</label>
        <input type="number" id="price" name="price" value="${product.price}"><br><br>
        <label for="files">새로운 이미지 선택:</label>
        <input type="file" id="files" name="files" multiple><br><br>
        <h3>기존 이미지:</h3>
        <c:forEach var="image" items="${product.images}">
            <div>
                <img src="${path0}/resources/upload/${image.uuidName}" alt="${image.name}" style="width: 100px;">
                <input type="checkbox" name="deleteFiles" value="${image.uuidName}"> 삭제
            </div>
        </c:forEach>
        <button type="submit">업로드</button>
    </form>
    <hr>
    <a href="${path0}">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/updateSuccess.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>상품 정보 업데이트 성공</title>
</head>
<body>
    <h2>상품 정보 업데이트 성공</h2>
    <p>상품명: ${product.name}</p>
    <p>설명: ${product.description}</p>
    <p>가격: ${product.price}</p>
    <h3>이미지 목록:</h3>
    <c:forEach var="image" items="${product.images}">
        <div class="item">
            <ul>
                <li>파일명: ${image.name}</li>
                <li>파일 크기: ${image.size} bytes</li>
            </ul>
            <hr>
        </div>
    </c:forEach>
    <hr>
    <a href="${path0}">홈으로</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/fileupload/deleteFile.jsp 작성**

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이미지 삭제</title>
</head>
<body>
    <h3>기존 이미지:</h3>
    <hr>
	<c:forEach var="image" items="${product.images}">
	    <div>
	        <img src="${path0}/resources/uploads/${image.uuidName}" alt="${image.name}" style="width: 100px;">
	        <a href="${path0}/fileupload/deleteFile?fileName=${image.uuidName}">삭제</a>
	    </div>
	</c:forEach>
    <hr>
    <a href="${path0}">홈으로</a>
</body>
</html>
```

<br><br><br>

### 14-3-5. DTO 또는 VO 클래스 작성

**com.spring1.vo.UploadVO 작성**

```java
package com.spring1.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UploadVO {
	private String name;
	private MultipartFile file;
}
```

<br><br>

**com.spring1.vo.MultiUploadVO 작성**

```java
package com.spring1.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MultiUploadVO {
	private List<UploadVO> list;
}
```

<br><br>

**com.spring1.dto.BoardVO 작성**

```java
package com.spring1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	private int bno;
	private String title;
	private String content;
	private String author;
	private int hits;
	private String filename;
}
```

<br><br>

**com.spring1.dto.Product 작성**

```java
package com.spring1.dto;

import java.util.List;

import com.spring1.vo.UploadData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private String description;
    private double price;
    private List<UploadData> images;
}
```

<br><br><br>

### 14-3-6. 파일 업로드 컨트롤러 작성

**com.spring1.controller.FileUploadController 클래스 작성**

```java
package com.spring1.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring1.dto.Product;
import com.spring1.vo.UploadData;

@Controller
@RequestMapping("/fileupload/")
public class FileUploadController {
	
	private static final Logger log = LoggerFactory.getLogger(FileUploadController.class); 
	
	@GetMapping("main")
	public String home(Model model) {
		return "fileupload/home";
	}
	
	//업로드로 가는 메소드
	@GetMapping("upload")
	public String uploadForm(Model model) {
		return "fileupload/fileUpload";
	}
	
	@PostMapping("uploadPro")
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
		long size = file.getSize(); //파일 사이즈
		
		log.info("파일명 : {}", fileRealName);
		log.info("용량크기(byte) : {}", size);
		
		model.addAttribute("fileRealName", "파일명 : " + fileRealName);
		model.addAttribute("size", "용량크기(byte) : "+size);
		
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		String uploadFolder = "D:\\test\\upload";
		
		
		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */
		
		UUID uuid = UUID.randomUUID();
		log.info("UUID : {}", uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		
		log.info("생성된 고유문자열 : {}", uniqueName);
		log.info("확장자명 : {}", fileExtension);
		
		model.addAttribute("uuid", "uuid : "+uuid.toString());
		
		String uuidName = uploadFolder+"\\"+uniqueName + fileExtension;
		
		model.addAttribute("uuidName", "uuidName : "+uuidName);
		
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		
		File saveFile = new File(uuidName);  // 적용 후
		try {
			file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "fileupload/fileUploadOk";
	}
	
	@GetMapping("upload2")
	public String uploadForm2(Model model) {
		return "fileupload/fileUpload2";
	}
	
    @PostMapping("uploadPro2")
    public String upload2(MultipartHttpServletRequest files, Model model) {
        String uploadFolder = "D:\\test\\upload";
        List<MultipartFile> list = files.getFiles("files");
        
        List<UploadData> datas = new ArrayList<>();
        
        for (MultipartFile file : list) {
            String fileRealName = file.getOriginalFilename();
            long size = file.getSize();
            
            log.info("파일명: {}", fileRealName);
            log.info("파일 크기(byte): {}", size);
            
            datas.add(new UploadData(fileRealName, size));
            
            File saveFile = new File(uploadFolder + "\\" + fileRealName);
            try {
                file.transferTo(saveFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        
        model.addAttribute("datas", datas);
        return "fileupload/fileUploadOk2";
    }
    
    @GetMapping("upload3")
    public String uploadForm3(Model model) {
        return "fileupload/fileUpload3"; // HTML 폼이 있는 JSP 파일의 이름
    }
    
    @PostMapping("uploadPro3")
    public String upload3(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("file") MultipartFile file,
            Model model) {
        
        String fileRealName = file.getOriginalFilename();
        long size = file.getSize();
        
        log.info("제목: {}", title);
        log.info("내용: {}", content);
        log.info("파일명: {}", fileRealName);
        log.info("파일 크기(byte): {}", size);
        
        // 서버에 저장할 파일 이름 생성
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."));
        String uploadFolder = "D:\\test\\upload";
        
        UUID uuid = UUID.randomUUID();
        String uniqueName = uuid.toString().split("-")[0];
        String uuidName = uploadFolder + "\\" + uniqueName + fileExtension;
        
        // 파일 저장
        File saveFile = new File(uuidName);
        try {
            file.transferTo(saveFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        
        // 데이터 모델에 추가
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("fileRealName", fileRealName);
        model.addAttribute("size", size);
        model.addAttribute("uuidName", uuidName);
        
        return "fileupload/fileUploadOk3"; // 업로드 성공 페이지
    }
    
    @GetMapping("upload4")
    public String uploadForm4(Model model) {
        return "fileupload/fileUpload4"; // HTML 폼이 있는 JSP 파일의 이름
    }
    
    @PostMapping("uploadPro4")
    public String uploadProduct(
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            MultipartHttpServletRequest files,
            Model model) {

        String uploadFolder = "D:\\test\\upload";
        List<MultipartFile> fileList = files.getFiles("files");
        List<UploadData> uploadDatas = new ArrayList<>();

        for (MultipartFile file : fileList) {
            String fileRealName = file.getOriginalFilename();
            long size = file.getSize();

            log.info("파일명: {}", fileRealName);
            log.info("파일 크기(byte): {}", size);

            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."));
            UUID uuid = UUID.randomUUID();
            String uniqueName = uuid.toString().split("-")[0];
            String uuidName = uploadFolder + "\\" + uniqueName + fileExtension;

            uploadDatas.add(new UploadData(fileRealName, size));

            File saveFile = new File(uuidName);
            try {
                file.transferTo(saveFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }

        Product product = new Product(productName, description, price, uploadDatas);
        model.addAttribute("product", product);

        return "fileupload/fileUploadOk4"; // 업로드 성공 페이지
    }
    
    /* 여기까지는 로컬에 업로드하는 경우임 */
    
    
    /* 여기부터는 서버에 업로드하는 경우임 */
    
    @Autowired
    private ServletContext servletContext;
    
	@GetMapping("upload5")
	public String uploadForm5(Model model) {
		return "fileupload/fileUpload5";
	}
	
	@PostMapping("uploadPro5")
	public String upload5(@RequestParam("file") MultipartFile file, Model model) {
		String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
		long size = file.getSize(); //파일 사이즈
		String uploadFolder = servletContext.getRealPath("/resources/upload");
		
		log.info("파일명 : {}", fileRealName);
		log.info("용량크기(byte) : {}", size);
		
		model.addAttribute("fileRealName", "파일명 : " + fileRealName);
		model.addAttribute("size", "용량크기(byte) : "+size);
		
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		
		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */
		
		UUID uuid = UUID.randomUUID();
		log.info("UUID : {}", uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		
		log.info("생성된 고유문자열 : {}", uniqueName);
		log.info("확장자명 : {}", fileExtension);
		
		model.addAttribute("uuid", "uuid : "+uuid.toString());
		
		String uuidName = uploadFolder+"\\"+uniqueName + fileExtension;
		
		model.addAttribute("uuidName", "uuidName : "+uuidName);
		
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		
		File saveFile = new File(uuidName);  // 적용 후
		try {
			file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        /* 서비스 호출 영역 시작 */
        
        
        /* 서비스 호출 영역 끝 */
		
		return "fileupload/fileUploadOk5";
	}
	
	@GetMapping("upload6")
	public String uploadForm6(Model model) {
		return "fileupload/fileUpload6";
	}
	
    @PostMapping("uploadPro6")
    public String upload6(MultipartHttpServletRequest files, Model model) {
        String uploadFolder = servletContext.getRealPath("/resources/upload");
        List<MultipartFile> list = files.getFiles("files");
        
        List<UploadData> datas = new ArrayList<>();
        
        for (MultipartFile file : list) {
            String fileRealName = file.getOriginalFilename();
            long size = file.getSize();
            
            log.info("파일명: {}", fileRealName);
            log.info("파일 크기(byte): {}", size);
            
            datas.add(new UploadData(fileRealName, size));
            
            File saveFile = new File(uploadFolder + "\\" + fileRealName);
            try {
                file.transferTo(saveFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        
        /* 서비스 호출 영역 시작 */
        
        
        /* 서비스 호출 영역 끝 */
        
        model.addAttribute("datas", datas);
        return "fileupload/fileUploadOk6";
    }
    
    @GetMapping("upload7")
    public String uploadForm7(Model model) {
        return "fileupload/fileUpload7"; // HTML 폼이 있는 JSP 파일의 이름
    }
    
    @PostMapping("uploadPro7")
    public String upload7(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("file") MultipartFile file,
            Model model) {
        
        String fileRealName = file.getOriginalFilename();
        long size = file.getSize();
        
        log.info("제목: {}", title);
        log.info("내용: {}", content);
        log.info("파일명: {}", fileRealName);
        log.info("파일 크기(byte): {}", size);
        
        // 서버에 저장할 파일 이름 생성
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."));
        String uploadFolder = servletContext.getRealPath("/resources/upload");
        
        UUID uuid = UUID.randomUUID();
        String uniqueName = uuid.toString().split("-")[0];
        String uuidName = uploadFolder + "\\" + uniqueName + fileExtension;
        
        // 파일 저장
        File saveFile = new File(uuidName);
        try {
            file.transferTo(saveFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        
        // 데이터 모델에 추가
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("fileRealName", fileRealName);
        model.addAttribute("size", size);
        model.addAttribute("uuidName", uuidName);
        
        /* 서비스 호출 영역 시작 */
        
        
        /* 서비스 호출 영역 끝 */
        
        return "fileupload/fileUploadOk7"; // 업로드 성공 페이지
    }
    
    @GetMapping("upload8")
    public String uploadForm8(Model model) {
        return "fileupload/fileUpload8"; // HTML 폼이 있는 JSP 파일의 이름
    }
    
    @PostMapping("uploadPro8")
    public String upload8(
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            MultipartHttpServletRequest files,
            Model model) {

        String uploadFolder = servletContext.getRealPath("/resources/upload");
        List<MultipartFile> fileList = files.getFiles("files");
        List<UploadData> uploadDatas = new ArrayList<>();

        for (MultipartFile file : fileList) {
            String fileRealName = file.getOriginalFilename();
            long size = file.getSize();

            log.info("파일명: {}", fileRealName);
            log.info("파일 크기(byte): {}", size);

            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."));
            UUID uuid = UUID.randomUUID();
            String uniqueName = uuid.toString().split("-")[0];
            String uuidName = uploadFolder + "\\" + uniqueName + fileExtension;

            uploadDatas.add(new UploadData(fileRealName, size));

            File saveFile = new File(uuidName);
            try {
                file.transferTo(saveFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }

        Product product = new Product(productName, description, price, uploadDatas);
        model.addAttribute("product", product);
        
        /* 서비스 호출 영역 시작 */
        
        
        /* 서비스 호출 영역 끝 */
        return "fileupload/fileUploadOk8"; // 업로드 성공 페이지
    }
    
    
    //상품 정보 변경
    @GetMapping("update")
    public String updateForm9(@RequestParam("id") Long id, Model model) {
        // DB나 다른 저장소에서 기존 상품 정보를 불러옵니다.
        // 여기서는 예시로 더미 데이터를 사용합니다.
        List<UploadData> existingImages = List.of(
                new UploadData("image1.jpg", 12345L, "uuid1.jpg"),
                new UploadData("image2.jpg", 67890L, "uuid2.jpg")
        );
        Product product = new Product("Existing Product", "This is an existing product.", 999, existingImages);
        model.addAttribute("product", product);
        return "fileupload/fileUploadUpdate"; // 업데이트 폼이 있는 JSP 파일의 이름
    }
    
    //상품 정보 변경
    @PostMapping("updatePro")
    public String updateProduct(
            @RequestParam("productId") Long productId,
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam(value = "deleteFiles", required = false) String[] deleteFiles,
            MultipartHttpServletRequest files,
            Model model) {

        String uploadFolder = servletContext.getRealPath("/resources/upload");
        
        // 삭제할 파일 처리
        if (deleteFiles != null) {
            for (String fileName : deleteFiles) {
                File file = new File(uploadFolder + File.separator + fileName);
                if (file.exists()) {
                    file.delete();
                }
            }
        }

        // 새로 업로드할 파일 처리
        List<MultipartFile> fileList = files.getFiles("files");
        List<UploadData> uploadDatas = new ArrayList<>();

        for (MultipartFile file : fileList) {
            String fileRealName = file.getOriginalFilename();
            long size = file.getSize();

            log.info("파일명: {}", fileRealName);
            log.info("파일 크기(byte): {}", size);

            String fileExtension = file.getOriginalFilename().substring(fileRealName.lastIndexOf("."));
            UUID uuid = UUID.randomUUID();
            String uniqueName = uuid.toString().split("-")[0];
            String uuidName = uniqueName + fileExtension;

            uploadDatas.add(new UploadData(fileRealName, size, uuidName));

            File saveFile = new File(uploadFolder + File.separator + uuidName);
            try {
                file.transferTo(saveFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }

        Product product = new Product(productName, description, price, uploadDatas);
        model.addAttribute("product", product);

        /* 서비스 호출 영역 시작 */
        
        
        /* 서비스 호출 영역 끝 */
        
        return "fileupload/updateSuccess"; // 업데이트 성공 페이지
    }
    
    @GetMapping("delete")
    public String delete(Model model) {
    	//아래는 더미 이미지입니다. 실제는 서비스 클래스의 메소드를 호출하여 데이터를 불러와야 합니다.
    	List<UploadData> existingImages = List.of(
                new UploadData("image1.jpg", 12345L, "uuid1.jpg"),
                new UploadData("image2.jpg", 67890L, "uuid2.jpg")
        );
        Product product = new Product("Existing Product", "This is an existing product.", 999, existingImages);
        model.addAttribute("product", product);
    	return "fileupload/deleteFile";
    }
    
    
    @GetMapping("deleteFile")
    public String deleteFile(@RequestParam("fileName") String fileName) {
        String uploadFolder = servletContext.getRealPath("/resources/upload");
        File file = new File(uploadFolder + File.separator + fileName);
        if (file.exists()) {
            file.delete(); // 파일 삭제
        }
        
        /* 서비스 호출 영역 시작 */
        
        
        /* 서비스 호출 영역 끝 */
        
        return "redirect:/"; // 삭제 후 다시 메인으로 이동
    }   
}
```

<br><br><hr><br><br>

## 14-4. Editor Board 구현



<br><br><hr><br><br>

## 14-5. 결제 Api 구현

<br><br><hr><br><br>

## 14-6. Chat Api 구현

<br><br><hr><br><br>

## 14-7. 비속어 필터링 구현

<br><br><hr><br><br>

## 14-8. 상품 이미지 Api 구현

<br><br><hr><br><br>

## 14-9. Jsoup을 이용한 웹 크롤링 구현

<br><br><hr><br><br>

## 14-10. 차트 Api 구현

<br><br><hr><br><br>

## 14-11. PDF 생성 구현

<br><br><hr><br><br>

## 14-12. 기상청 날씨 Api 구현

<br><br><hr><br><br>

## 14-13. Chat GPT Api 구현

<br><br><hr><br><br>

## 14-14. Api 제작 및 구현

<br><br><hr><br><br>