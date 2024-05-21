<nav id="quick" style="position:fixed;top:150px;right:100px;width:200px;height:auto;overflow:hidden;z-index:999;background-color:rgba(255,255,255,0.75);">
	<label for="chap1" style="color:black;font-size:28px;">14. Open API 와 API 용용 및 부가 기능</label>
	<input type="radio" name="chap" id="chap1" style="display:none;">
	<ul class="menu">
		<li><a href="#14-1" style="color:black;font-size:20px;text-decoration:none;">14-1. 스프링프레임워크 이메일 보내기 설정</a></li>
		<li><a href="#14-2" style="color:black;font-size:20px;text-decoration:none;">14-2. Controller 작성하기</a></li>
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
- 스프링 프레임웍에서는 기본적으로 JavaMailSender 인터페이스와 비슷한 역할을 하는 메인 인터페이스 MainSender가 있습니다. 하지만 MainSender는 SimpleMailMessage만 사용 가능합니다. (javax.mail.MimeMessage 사용 불가능)

* SimpleMailMessage는 단순한 텍스트 메시지만 사용할 수 있고, MimeMessage는 텍스트와 더불어 이미지와 같은 첨부파일을 같이 메시지에 포함시킬 수 있습니다.

<br><br><br> 

### 14-1-3. mailSender Bean 등록

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
 <!-- 보안연결 TLS과 관련된 설정 -->
     <property name="javaMailProperties">
    <props>
       <prop key="mail.smtp.auth">true</prop>
       <prop key="mail.smtp.starttls.enable">true</prop>
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

- host : "smtp.daum.net" 입력
- port : "465" 입력
- username : 자신의 이메일 아이디 입력
- password : 자신의 이메일 비밀번호 입력
- javaMailProperties인 <property>태그에 삽입된 코드는 네이버와 동일하게 SSL 인증 코드입니다.

<br><br><br><br>

<div id="14-2"></div>

## 14-2. Controller 메서드 추가
 
- MimeMessage 객체를 직접 생성하여 메일을 발송하는 방법과 MimeMessagePreparator 를 사용해서 메일을 전송하는 방법이 있습니다. 두가지 코드 모두 알아보겠습니다.

- 보내는 이 이메일, 받는 이 이메일, 메일 제목, 메일 내용 4가지의 데이터를 입력해주어야하는데 해당 데이터들을 VO객체에 담아서도 가능하고 Controller 메서드 내에 직접 삽입 하는 것도 가능합니다. 직접 삽입 방식으로 진행하겠습니다.

- 메일전송을 위해 필요로한 정보를 세팅하였던 "mailSender" Bean을 인젝션(의존성 주입) 하여 사용하겠습니다. 


```java
@Autowired
private JavaMailSender mailSender;
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
    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
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

#### 14-2-2-2. 이미지

- 이미지를 첨부하는 방법은 html 허용을 설정했기때문에 이미지 태그(<img>)를 사용합니다. 예를 들면 다음과 같습니다. 
 
```java
String content = "메일 테스트 내용" + "<img src=\"이미지 경로\">";
``` 

```java
    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public void sendMailTest() throws Exception{
        
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
    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public void sendMailTest() throws Exception{
        
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

<br><br><br> 

### 14-2-3. 메일 전송 테스트

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
