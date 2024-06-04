<nav id="quick" style="">
	<label for="chap15" style="color:black;font-size:28px;">15. Open API 와 API 용용 및 부가 기능2</label>
	<input type="radio" name="chap" id="chap15" style="display:none;">
	<ul class="menu">
        <li><a href="#15-1" style="color:black;font-size:20px;text-decoration:none;">15-1. 비속어 필터링 구현</li>
        <li><a href="#15-2" style="color:black;font-size:20px;text-decoration:none;">15-2. 상품 이미지 Api 구현</li>
        <li><a href="#15-3" style="color:black;font-size:20px;text-decoration:none;">15-3. Jsoup을 이용한 웹 크롤링 구현</li>
        <li><a href="#15-4" style="color:black;font-size:20px;text-decoration:none;">15-4. 차트 Api 구현</li>
        <li><a href="#15-5" style="color:black;font-size:20px;text-decoration:none;">15-5. PDF 생성 및 편집기 구현</li>
        <li><a href="#15-6" style="color:black;font-size:20px;text-decoration:none;">15-6. 기상청 날씨 Api 구현</li>
	</ul>
</nav>

<div id="15"></div>

<div id="15-1"></div>

## 15-1. 비속어 필터링 구현

### 15-1-1. 비속어 필터링 인터셉터 작성

**com.spring1.interceptor.ProfanityFilterInterceptor 작성**

```java
package com.spring1.interceptor;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProfanityFilterInterceptor {

    private static final List<String> PROFANITIES = Arrays.asList("18년", "18놈", "18새끼", "ㄱㅐㅅㅐㄲl", "ㄱㅐㅈㅏ", "가슴만져", "가슴빨아", "가슴빨어", "가슴조물락", "가슴주물럭", "가슴쪼물딱", "가슴쪼물락", "가슴핧아", "가슴핧어", "강간", "개가튼년", "개가튼뇬", "개같은년", "개걸레", "개고치", "개너미", "개넘", "개년", "개놈", "개늠", "개똥", "개떵", "개떡", "개라슥", "개보지", "개부달", "개부랄", "개불랄", "개붕알", "개새", "개세", "개쓰래기", "개쓰레기", "개씁년", "개씁블", "개씁자지", "개씨발", "개씨블", "개자식", "개자지", "개잡년", "개젓가튼넘", "개좆", "개지랄", "개후라년", "개후라들놈", "개후라새끼", "걔잡년", "거시기", "걸래년", "걸레같은년", "걸레년", "걸레핀년", "게부럴", "게세끼", "게이", "게새끼", "게늠", "게자식", "게지랄놈", "고환", "공지", "공지사항", "귀두", "깨쌔끼", "난자마셔", "난자먹어", "난자핧아", "내꺼빨아", "내꺼핧아", "내버지", "내자지", "내잠지", "내조지", "너거애비", "노옴", "누나강간", "니기미", "니뿡", "니뽕", "니씨브랄", "니아범", "니아비", "니애미", "니애뷔", "니애비", "니할애비", "닝기미", "닌기미", "니미", "닳은년", "덜은새끼", "돈새끼", "돌으년", "돌은넘", "돌은새끼", "동생강간", "동성애자", "딸딸이", "똥구녁", "똥꾸뇽", "똥구뇽", "똥", "띠발뇬", "띠팔", "띠펄", "띠풀", "띠벌", "띠벨", "띠빌", "마스터", "막간년", "막대쑤셔줘", "막대핧아줘", "맛간년", "맛없는년", "맛이간년", "멜리스", "미친구녕", "미친구멍", "미친넘", "미친년", "미친놈", "미친눔", "미친새끼", "미친쇄리", "미친쇠리", "미친쉐이", "미친씨부랄", "미튄", "미티넘", "미틴", "미틴넘", "미틴년", "미틴놈", "미틴것", "백보지", "버따리자지", "버지구녕", "버지구멍", "버지냄새", "버지따먹기", "버지뚫어", "버지뜨더", "버지물마셔", "버지벌려", "버지벌료", "버지빨아", "버지빨어", "버지썰어", "버지쑤셔", "버지털", "버지핧아", "버짓물", "버짓물마셔", "벌창같은년", "벵신", "병닥", "병딱", "병신", "보쥐", "보지", "보지핧어", "보짓물", "보짓물마셔", "봉알", "부랄", "불알", "붕알", "붜지", "뷩딱", "븅쉰", "븅신", "빙띤", "빙신", "빠가십새", "빠가씹새", "빠구리", "빠굴이", "뻑큐", "뽕알", "뽀지", "뼝신", "사까시", "상년", "새꺄", "새뀌", "새끼", "색갸", "색끼", "색스", "색키", "샤발", "서버", "써글", "써글년", "성교", "성폭행", "세꺄", "세끼", "섹스", "섹스하자", "섹스해", "섹쓰", "섹히", "수셔", "쑤셔", "쉐끼", "쉑갸", "쉑쓰", "쉬발", "쉬방", "쉬밸년", "쉬벌", "쉬불", "쉬붕", "쉬빨", "쉬이발", "쉬이방", "쉬이벌", "쉬이불", "쉬이붕", "쉬이빨", "쉬이팔", "쉬이펄", "쉬이풀", "쉬팔", "쉬펄", "쉬풀", "쉽쌔", "시댕이", "시발", "시발년", "시발놈", "시발새끼", "시방새", "시밸", "시벌", "시불", "시붕", "시이발", "시이벌", "시이불", "시이붕", "시이팔", "시팍새끼", "시팔", "시팔넘", "시팔년", "시팔놈", "시팔새끼", "시펄", "실프", "십8", "십때끼", "십떼끼", "십버지", "십부랄", "십부럴", "십새", "십세이", "십셰리", "십쉐", "십자석", "십자슥", "십지랄", "십창녀", "십창", "십탱", "십탱구리", "십탱굴이", "십팔새끼", "ㅆㅂ", "ㅆㅂㄹㅁ", "ㅆㅂㄻ", "ㅆㅣ", "쌍넘", "쌍년", "쌍놈", "쌍눔", "쌍보지", "쌔끼", "쌔리", "쌕스", "쌕쓰", "썅년", "썅놈", "썅뇬", "썅늠", "쓉새", "쓰바새끼", "쓰브랄쉽세", "씌발", "씌팔", "씨가랭넘", "씨가랭년", "씨가랭놈", "씨발", "씨발년", "씨발롬", "씨발병신", "씨방새", "씨방세", "씨밸", "씨뱅가리", "씨벌", "씨벌년", "씨벌쉐이", "씨부랄", "씨부럴", "씨불", "씨불알", "씨붕", "씨브럴", "씨블", "씨블년", "씨븡새끼", "씨빨", "씨이발", "씨이벌", "씨이불", "씨이붕", "씨이팔", "씨파넘", "씨팍새끼", "씨팍세끼", "씨팔", "씨펄", "씨퐁넘", "씨퐁뇬", "씨퐁보지", "씨퐁자지", "씹년", "씹물", "씹미랄", "씹버지", "씹보지", "씹부랄", "씹브랄", "씹빵구", "씹뽀지", "씹새", "씹새끼", "씹세", "씹쌔끼", "씹자석", "씹자슥", "씹자지", "씹지랄", "씹창", "씹창녀", "씹탱", "씹탱굴이", "씹탱이", "씹팔", "아가리", "애무", "애미", "애미랄", "애미보지", "애미씨뱅", "애미자지", "애미잡년", "애미좃물", "애비", "애자", "양아치", "어미강간", "어미따먹자", "어미쑤시자", "영자", "엄창", "에미", "에비", "엔플레버", "엠플레버", "염병", "염병할", "염뵹", "엿먹어라", "오랄", "오르가즘", "왕버지", "왕자지", "왕잠지", "왕털버지", "왕털보지", "왕털자지", "왕털잠지", "우미쑤셔", "운디네", "운영자", "유두", "유두빨어", "유두핧어", "유방", "유방만져", "유방빨아", "유방주물럭", "유방쪼물딱", "유방쪼물럭", "유방핧아", "유방핧어", "육갑", "이그니스", "이년", "이프리트", "자기핧아", "자지", "자지구녕", "자지구멍", "자지꽂아", "자지넣자", "자지뜨더", "자지뜯어", "자지박어", "자지빨아", "자지빨아줘", "자지빨어", "자지쑤셔", "자지쓰레기", "자지정개", "자지짤라", "자지털", "자지핧아", "자지핧아줘", "자지핧어", "작은보지", "잠지", "잠지뚫어", "잠지물마셔", "잠지털", "잠짓물마셔", "잡년", "잡놈", "저년", "점물", "젓가튼", "젓가튼쉐이", "젓같내", "젓같은", "젓까", "젓나", "젓냄새", "젓대가리", "젓떠", "젓마무리", "젓만이", "젓물", "젓물냄새", "젓밥", "정액마셔", "정액먹어", "정액발사", "정액짜", "정액핧아", "정자마셔", "정자먹어", "정자핧아", "젖같은", "젖까", "젖밥", "젖탱이", "조개넓은년", "조개따조", "조개마셔줘", "조개벌려조", "조개속물", "조개쑤셔줘", "조개핧아줘", "조까", "조또", "족같내", "족까", "족까내", "존나", "존나게", "존니", "졸라", "좀마니", "좀물", "좀쓰레기", "좁빠라라", "좃가튼뇬", "좃간년", "좃까", "좃까리", "좃깟네", "좃냄새", "좃넘", "좃대가리", "좃도", "좃또", "좃만아", "좃만이", "좃만한것", "좃만한쉐이", "좃물", "좃물냄새", "좃보지", "좃부랄", "좃빠구리", "좃빠네", "좃빠라라", "좃털", "좆같은놈", "좆같은새끼", "좆까", "좆까라", "좆나", "좆년", "좆도", "좆만아", "좆만한년", "좆만한놈", "좆만한새끼", "좆먹어", "좆물", "좆밥", "좆빨아", "좆새끼", "좆털", "좋만한것", "주글년", "주길년", "쥐랄", "지랄", "지랼", "지럴", "지뢀", "쪼까튼", "쪼다", "쪼다새끼", "찌랄", "찌질이", "창남", "창녀", "창녀버지", "창년", "처먹고", "처먹을", "쳐먹고", "쳐쑤셔박어", "촌씨브라리", "촌씨브랑이", "촌씨브랭이", "크리토리스", "큰보지", "클리토리스", "트랜스젠더", "페니스", "항문수셔", "항문쑤셔", "허덥", "허버리년", "허벌년", "허벌보지", "허벌자식", "허벌자지", "허접", "허젚", "허졉", "허좁", "헐렁보지", "혀로보지핧기", "호냥년", "호로", "호로새끼", "호로자슥", "호로자식", "호로짜식", "호루자슥", "호모", "호졉", "호좁", "후라덜넘", "후장", "후장꽂아", "후장뚫어", "흐접", "흐젚", "흐졉", "bitch", "fuck", "fuckyou", "nflavor", "penis", "pennis", "pussy", "sex");

    public boolean containsProfanity(String content) {
        for (String profanity : PROFANITIES) {
            if (content.toLowerCase().contains(profanity)) {
                return true;
            }
        }
        return false;
    }
}
```

<br><br><br>


### 15-1-2. Controller 작성

**com.spring1.controller.SlangController 작성**

```java
package com.spring1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring1.interceptor.ProfanityFilterInterceptor;

@Controller
@RequestMapping("/slang/")
public class SlangController {
	
	@Autowired
	private ProfanityFilterInterceptor proFilter;
	
	@GetMapping("form")
	public String loadingForm(Model model) {
		return "slang/form";
	}
	
    @RequestMapping(value = "submitContent", method = RequestMethod.POST)
    public ModelAndView submitContent(String content) {
        ModelAndView modelAndView = new ModelAndView();
        
        // 여기서 content를 ProfanityFilterInterceptor에 전달하여 필터링
        boolean pass = proFilter.containsProfanity(content);
        
        String data = content;
        
        if(pass) {
        	data = "콘텐츠에 부적절한 언어가 포함되어 있습니다";
        }
        
        modelAndView.addObject("filteredContent", data); // 필터링된 content를 JSP에 전달
        modelAndView.setViewName("slang/filteredContent"); // 필터링된 content를 보여줄 JSP 페이지 지정
        
        return modelAndView;
    }
    
	@GetMapping("form2")
	public String ajaxForm(Model model) {
		return "slang/ajaxform";
	}
    
    @PostMapping("submitContent2")
    @ResponseBody
    public ResponseEntity<?> submitContent2(@RequestBody String content) {
        // 여기서 content를 ProfanityFilterInterceptor에 전달하여 필터링
        // 필터링된 content를 JSON 형태로 응답

        // 여기서 content를 ProfanityFilterInterceptor에 전달하여 필터링
        boolean pass = proFilter.containsProfanity(content);
        
        String filteredContent = content;
        
        if(pass) {
        	filteredContent = "콘텐츠에 부적절한 언어가 포함되어 있습니다";
        } else {
        	filteredContent = content;	
        }
    	 
        // JSON 형태로 응답
        return ResponseEntity.ok().body("{\"filteredContent\": \"" + filteredContent + "\"}");
    }   
}
```

<br><br><br>

### 15-1-3. View(JSP) 작성

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
	<h2>${company }</h2>
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/slang/form">비속어 필터링</a></li>
		<li><a href="${path2 }/slang/form2">비속어 필터링2</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/slang/form.jsp 작성**

```jsp
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
<title>비속어 필터링 입력 폼</title>
</head>
<body>
	<form name="form1" action="${path2 }/slang/submitContent" method="post">
		<h3>하고 싶은 말</h3>
		<textarea name="content" id="content" rows="6" cols="80">
		
		</textarea><br><br>
		<button type="submit">작성</button>
	</form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/slang/filteredContent.jsp 작성**

```jsp
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
<title>비속어 필터링 결과</title>
</head>
<body>
<body>
    <h1>비속어 필터링 결과</h1>
    <p>입력한 내용: ${filteredContent}</p>
    <a href="${path2 }/slang/form">입력 폼으로 이동</a><br>
    <a href="${path2 }">홈으로 이동</a>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/slang/ajaxForm.jsp 작성**

```jsp
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
	<title>AJAX 비속어 필터링</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h1>Ajax Submit Content</h1>
	
	<textarea id="content" rows="6" cols="80"></textarea>
	<br>
	<button id="submitBtn">Submit</button>
	<br>
	<div id="result"></div>
	<script>
	    $(document).ready(function () {
	        $("#submitBtn").click(function () {
	            var content = $("#content").val();
	
	            $.ajax({
	                type: "POST",
	                url: "${path2}/slang/submitContent2",
	                contentType: "text/plain; charset=UTF-8",
	                data: content,
	                dataType: "json",
	                success: function (response) {
	                    // 서버로부터 응답을 받으면 필터링된 내용을 표시
	                    console.log(response.filteredContent);
	                    $("#result").html(response.filteredContent);
	                },
	                error: function (xhr, status, error) {
	                    // 에러 처리
	                    $("#result").html("Error: " + xhr.responseText);
	                }
	            });
	        });
	    });
	</script>

</body>
</html>
```

<br><br>

<br><br><hr><br><br>

<div id="15-2"></div>

## 15-2. 상품 이미지 Api 구현

### 15-2-1. 이미지 및 파일 관련 라이브러리 

#### 15-2-1-1. Commons FileUpload

- Commons FileUpload는 파일 업로드 처리를 위한 라이브러리입니다. 

**1. org.apache.commons.fileupload.disk.DiskFileItemFactory**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | DiskFileItemFactory() |   |
|   | DiskFileItemFactory(int sizeThreshold, File repository) |   |
| 메서드 | setSizeThreshold(int sizeThreshold) | 메모리 내에 저장할 임시 파일의 최대 크기를 설정합니다. |
|   | setRepository(File repository) | 임시 파일을 저장할 디렉토리를 설정합니다. |
|   | createItem(String fieldName, String contentType, boolean isFormField, String fileName) | 새로운 FileItem 객체를 생성합니다. |

<br><br>

**2. org.apache.commons.fileupload.servlet.ServletFileUpload**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | ServletFileUpload() |   |
|   | ServletFileUpload(FileItemFactory fileItemFactory) |   |
| 메서드 | isMultipartContent(HttpServletRequest request) | 요청이 멀티파트 컨텐츠인지 확인합니다. |
|  | parseRequest(HttpServletRequest request) | 요청을 파싱하여 FileItem 객체의 리스트를 반환합니다. |
|  | setSizeMax(long sizeMax) | 업로드 가능한 전체 파일의 최대 크기를 설정합니다. |
|  | setFileSizeMax(long fileSizeMax) | 업로드 가능한 단일 파일의 최대 크기를 설정합니다. |

<br><br>

**3. org.apache.commons.fileupload.FileItem**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 메서드 | getName() | 업로드된 파일의 이름을 반환합니다. |
|   | getContentType() | 파일의 MIME 타입을 반환합니다. |
|   | isFormField() | 파일이 일반 폼 필드인지 확인합니다. |
|   | getSize() | 파일의 크기를 반환합니다. |
|   | getInputStream() | 파일 내용을 읽을 수 있는 InputStream을 반환합니다. |
|   | write(File file) | 파일을 지정한 디렉토리에 저장합니다. |


<br><br><br>

#### 15-2-1-2. Commons IO

- Commons IO는 I/O 관련 유틸리티를 제공하는 라이브러리입니다.

**1. org.apache.commons.io.FileUtils**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 메서드 | copyFile(File srcFile, File destFile) | 파일을 복사합니다. |
|   | copyFileToDirectory(File srcFile, File destDir) | 파일을 디렉토리로 복사합니다. |
|   | deleteQuietly(File file) | 파일을 조용히 삭제합니다. |
|   | readFileToString(File file, Charset encoding) | 파일 내용을 문자열로 읽어옵니다. |
|   | writeStringToFile(File file, String data, Charset encoding) | 문자열 데이터를 파일에 씁니다. |
|   | openInputStream(File file) | 파일을 읽기 위한 InputStream을 엽니다. |

<br><br>

**2. org.apache.commons.ioIOUtils**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 메서드 | copy(InputStream input, OutputStream output) | 입력 스트림을 출력 스트림으로 복사합니다. |
|   | toByteArray(InputStream input) | 입력 스트림의 내용을 바이트 배열로 반환합니다. |
|   | toString(InputStream input, Charset encoding) | 입력 스트림의 내용을 문자열로 반환합니다. |
|   | closeQuietly(Closeable closeable) | Closeable 객체를 조용히 닫습니다. |

<br><br><br>


#### 15-2-1-3. imgscalr-lib

- imgscalr는 이미지 크기 조정 및 다양한 이미지 처리를 위한 라이브러리입니다.

**1. org.imgscalr.Scalr**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 메서드 | resize(BufferedImage src, Method scalingMethod, Mode resizeMode, int targetWidth, int targetHeight) | 이미지를 지정한 크기로 리사이즈합니다. <br> 	- Method scalingMethod: 리사이즈 방법 (e.g., Scalr.Method.AUTOMATIC, Scalr.Method.ULTRA_QUALITY) <br> - Mode resizeMode: 리사이즈 모드 (e.g., Scalr.Mode.FIT_TO_WIDTH, Scalr.Mode.FIT_TO_HEIGHT) <br> - int targetWidth: 목표 너비 <br> - int targetHeight: 목표 높이 |
|   | resize(BufferedImage src, int targetWidth, int targetHeight) | 이미지를 지정한 크기로 리사이즈합니다. |
|   | crop(BufferedImage src, int x, int y, int width, int height) | 이미지를 지정한 좌표와 크기로 자릅니다. |
|   | rotate(BufferedImage src, Rotation rotation) | 이미지를 지정한 각도로 회전합니다. <br> - Rotation: 회전 각도 (e.g., Scalr.Rotation.CW_90, Scalr.Rotation.CW_180) |
|   | apply(BufferedImage src, BufferedImageOp... ops) | 주어진 필터를 이미지에 적용합니다. <br> - BufferedImageOp: Java2D API의 필터 (e.g., RescaleOp, ConvolveOp) |

<br><br><br>

#### 15-2-1-4. TwelveMonkeys ImageIO (imageio-core, imageio-jpeg)

- TwelveMonkeys ImageIO는 다양한 이미지 포맷을 지원하는 Java ImageIO 플러그인입니다. 

**1. com.twelvemonkeys.imageio.plugins.jpeg.JPEGImageReader**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | JPEGImageReader(ImageReaderSpi originatingProvider) |   |
| 메서드 | getWidth(int imageIndex) | 이미지의 너비를 반환합니다. |
|   | getHeight(int imageIndex) | 이미지의 높이를 반환합니다. |
|   | read(int imageIndex, ImageReadParam param) | 이미지를 읽고 BufferedImage 객체를 반환합니다. |
|   | getStreamMetadata() | 스트림 메타데이터를 반환합니다. |
|   | getImageMetadata(int imageIndex) | 이미지 메타데이터를 반환합니다. |

멤버 필드 : JPEGImageReader 클래스는 내부적으로 JPEG 이미지를 처리하는 데 필요한 필드를 가지고 있습니다.

<br><br>

**2. com.twelvemonkeys.imageio.plugins.jpeg.JPEGImageWriter**

- JPEGImageWriter 클래스는 내부적으로 JPEG 이미지를 작성하는 데 필요한 필드를 가지고 있습니다.

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | JPEGImageWriter(ImageWriterSpi originatingProvider) |   |
| 메서드 | write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) | 이미지를 작성합니다. |
|   | canWriteSequence() | 시퀀스 이미지를 작성할 수 있는지 여부를 반환합니다. |
|   | prepareWriteSequence(IIOMetadata streamMetadata) | 시퀀스 이미지를 작성하기 위한 준비를 합니다. |
|   | endWriteSequence() | 시퀀스 이미지 작성을 종료합니다. |

<br><br><br>

#### 15-2-1-5. JHLabs Filters

- JHLabs Filters는 다양한 이미지 필터를 제공하는 라이브러리입니다.

**1. com.jhlabs.image.BrightnessFilter**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | BrightnessFilter() |    |
| 메서드 | filter(BufferedImage src, BufferedImage dst) | 입력 이미지를 처리하여 밝기를 조절한 이미지를 반환합니다. |
|   | setBrightness(float brightness) | 밝기 값을 설정합니다. |
|   | getBrightness() | 현재 밝기 값을 반환합니다. |
| 멤버 필드 | brightness | 밝기 값을 저장하는 float 필드. |

<br><br>

**2. com.jhlabs.image.HSBAdjustFilter**

- HSBAdjustFilter는 이미지의 색상, 명도, 채도를 적용하는 라이브러리입니다.

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | HSBAdjustFilter() |   |
| 메서드 | filter(BufferedImage src, BufferedImage dst) | 입력 이미지를 처리하여 색상, 채도, 명도를 조절한 이미지를 반환합니다. |
|   | setHFactor(float hFactor) | 색상 값을 설정합니다. |
|   | setSFactor(float sFactor) | 채도 값을 설정합니다. |
|   | setBFactor(float bFactor) | 명도 값을 설정합니다. |
|   | getHFactor() | 현재 색상 값을 반환합니다. |
|   | getSFactor() | 현재 채도 값을 반환합니다. |
|   | getBFactor() | 현재 명도 값을 반환합니다. |
| 멤버 필드 | hFactor | 색상 값을 저장하는 float 필드. |
|   | sFactor | 채도 값을 저장하는 float 필드. |
|   | bFactor | 명도 값을 저장하는 float 필드. |


<br><br><br>

#### 15-2-1-6. 이미지 필터

- JHLabs 라이브러리에서 제공하는 필터는 아래와 같습니다.

| 결과 이미지 | 필터명 | 설명 |
|-------------|-----------------------|-----------------------------------------------|
| **색상 조정 필터** |    |   |
| ![ChannelMixFilter](../images/ChannelMixFilter.jpg) | ChannelMixFilter |  RGB 채널을 혼합합니다. |
| ![ContrastFilter](../images/ContrastFilter.jpg) | ContrastFilter |  밝기와 대비를 조정합니다. |
| ![CurvesFilter](../images/CurvesFilter.jpg) | CurvesFilter |  이미지에 조정 곡선 적용
| ![DiffusionFilter](../images/DiffusionFilter.jpg) | DiffusionFilter |  오류 확산 디더링
| ![DitherFilter](../images/DitherFilter.jpg) | DitherFilter |  순서가 지정된 디더링
| ![ExposureFilter](../images/ExposureFilter.jpg) | ExposureFilter |  이미지 노출 변경
| ![GainFilter](../images/GainFilter.jpg) | GainFilter |  게인 및 바이어스 조정
| ![GammaFilter](../images/GammaFilter.jpg) | GammaFilter |  이미지 감마 조정
| ![GrayFilter](../images/GrayFilter.jpg) | GrayFilter |  이미지를 회색으로 표시합니다. |
| ![GrayscaleFilter](../images/GrayscaleFilter.jpg) | GrayscaleFilter |  회색조로 변환합니다. |
| ![HSBAdjustFilter](../images/HSBAdjustFilter.jpg) | HSBAdjustFilter | 색조, 채도 및 밝기를 조정합니다. |
| ![InvertAlphaFilter](../images/InvertAlphaFilter.jpg) | InvertAlphaFilter |  알파 채널을 반전시킵니다. |
| ![InvertFilter](../images/InvertFilter.jpg) | InvertFilter |  이미지 색상을 반전시킵니다. |
| ![LevelsFilter](../images/LevelsFilter.jpg) | LevelsFilter |  이미지 레벨 조정 |
| ![LookupFilter](../images/LookupFilter.jpg) | LookupFilter |  조회 테이블을 사용하여 이미지 색상 변경 |
| ![MapColorsFilter](../images/blank.jpg) | MapColorsFilter |  색상 바꾸기 |
| ![MaskFilter](../images/MaskFilter.jpg) | MaskFilter |  채널 마스킹 |
| ![PosterizeFilter](../images/PosterizeFilter.jpg) | PosterizeFilter |  포스터화 |
| ![QuantizeFilter](../images/QuantizeFilter.jpg) | QuantizeFilter |  GIF 내보내기 등을 위해 이미지를 256색으로 양자화합니다. |
| ![RescaleFilter](../images/RescaleFilter.jpg) | RescaleFilter |  색상에 배율을 곱합니다. |
| ![RGBAdjustFilter](../images/RGBAdjustFilter.jpg) | RGBAdjustFilter | " 빨간색 |  녹색 및 파란색 레벨을 조정합니다. |"
| ![SolarizeFilter](../images/SolarizeFilter.jpg) | SolarizeFilter |  솔라리제이션 |
| ![ThresholdFilter](../images/ThresholdFilter.jpg) | ThresholdFilter |  임계값  |
| ![TritoneFilter](../images/TritoneFilter.jpg) | TritoneFilter |  삼중톤 이미지 만들기  |
| **왜곡 및 워핑 필터** |    |   |
| ![BicubicScaleFilter](../images/BicubicScaleFilter.jpg) | BicubicScaleFilter |  쌍삼차 보간을 사용한 크기 조정  |
| ![CircleFilter](../images/CircleFilter.jpg) | CircleFilter |  원 주위에 이미지 감싸기  |
| ![CropFilter](../images/CropFilter.jpg) | CropFilter |  이미지에서 영역 자르기  |
| ![DiffuseFilter](../images/DiffuseFilter.jpg) | DiffuseFilter |  이미지의 픽셀을 확산시킵니다. |
| ![DisplaceFilter](../images/DisplaceFilter.jpg) | DisplaceFilter |  유리 왜곡 효과  |
| ![DissolveFilter](../images/DissolveFilter.jpg) | DissolveFilter |  임의의 픽셀을 투명하게 만들어 이미지를 용해합니다. |
| ![FieldWarpFilter](../images/FieldWarpFilter.jpg) | FieldWarpFilter |  필드 워프 알고리즘을 사용하여 이미지 워프  |
| ![FlipFilter](../images/FlipFilter.jpg) | FlipFilter |  이미지 뒤집기 및 회전  |
| ![KaleidscopeFilter](../images/KaleidscopeFilter.jpg) | KaleidscopeFilter |  만화경 효과  |
| ![MarbleFilter](../images/MarbleFilter.jpg) | MarbleFilter |  마블링 효과  |
| ![MirrorFilter](../images/MirrorFilter.jpg) | MirrorFilter |  이미지 미러링  |
| ![OffsetFilter](../images/OffsetFilter.jpg) | OffsetFilter |  타일링을 위해 이미지 오프셋  |
| ![PerspectiveFilter](../images/PerspectiveFilter.jpg) | PerspectiveFilter |  원근 왜곡  |
| ![PinchFilter](../images/PinchFilter.jpg) | PinchFilter |  소용돌이 핀치 왜곡  |
| ![PolarFilter](../images/PolarFilter.jpg) | PolarFilter |  극좌표 간 변환  |
| ![RippleFilter](../images/RippleFilter.jpg) | RippleFilter |  잔물결 왜곡  |
| ![RotateFilter](../images/RotateFilter.jpg) | RotateFilter |  이미지 회전  |
| ![ScaleFilter](../images/ScaleFilter.jpg) | ScaleFilter |  영역 평균화를 사용하여 이미지 크기 조정  |
| ![ShearFilter](../images/ShearFilter.jpg) | ShearFilter |  이미지 자르기  |
| ![SphereFilter](../images/SphereFilter.jpg) | SphereFilter |  렌즈 왜곡  |
| ![SwimFilter](../images/SwimFilter.jpg) | SwimFilter | 수중, 왜곡 효과 |
| ![TileImageFilter](../images/TileImageFilter.jpg) | TileImageFilter |  이미지를 더 큰 이미지로 타일링  |
| ![TwirlFilter](../images/TwirlFilter.jpg) | TwirlFilter |  비틀어 이미지를 왜곡합니다. |
| ![WarpFilter](../images/WarpFilter.jpg) | WarpFilter |  일반 그리드 이미지 워프 |
| ![WaterFilter](../images/WaterFilter.jpg) | WaterFilter |  물 잔물결 시뮬레이션 |
| **효과 필터** |    |   |
| ![BlockFilter](../images/BlockFilter.jpg) | BlockFilter |  이미지를 모자이크하거나 픽셀화합니다. |
| ![BorderFilter](../images/BorderFilter.jpg) | BorderFilter |  테두리 추가 |
| ![ChromeFilter](../images/ChromeFilter.jpg) | ChromeFilter |  크롬 시뮬레이션 |
| ![ColorHalftoneFilter](../images/ColorHalftoneFilter.jpg) | ColorHalftoneFilter |  색상 하프톤 효과입니다. |
| ![CrystallizeFilter](../images/CrystallizeFilter.jpg) | CrystallizeFilter |  이미지를 스테인드 글라스처럼 보이게 만듭니다. |
| ![EmbossFilter](../images/EmbossFilter.jpg) | EmbossFilter |  단순 엠보싱 |
| ![FeedbackFilter](../images/FeedbackFilter.jpg) | FeedbackFilter |  비디오 피드백 효과 |
| ![HalftoneFilter](../images/HalftoneFilter.jpg) | HalftoneFilter |  단순 하프톤 |
| ![LightFilter](../images/LightFilter.jpg) | LightFilter |  범프 매핑된 이미지에서 조명 시뮬레이션 |
| ![NoiseFilter](../images/NoiseFilter.jpg) | NoiseFilter |  노이즈 추가 |
| ![PointillizeFilter](../images/PointillizeFilter.jpg) | PointillizeFilter |  이미지를 컬러 점으로 그립니다. |
| ![ShadowFilter](../images/ShadowFilter.jpg) | ShadowFilter |  그림자 만들기 |
| ![ShapeFilter](../images/ShapeFilter.jpg) | ShapeFilter |  조명용 범프 맵 생성 |
| ![StampFilter](../images/StampFilter.jpg) | StampFilter |  고무 도장 효과 |
| ![WeaveFilter](../images/WeaveFilter.jpg) | WeaveFilter |  직조된 이미지 효과 |
| 텍스처링 필터 |    |   |
| ![BrushedMetalFilter](../images/BrushedMetalFilter.jpg) | BrushedMetalFilter |  브러시드 금속 생성 |
| ![CausticsFilter](../images/CausticsFilter.jpg) | CausticsFilter |  수중 화선 시뮬레이션 |
| ![CellularFilter](../images/CellularFilter.jpg) | CellularFilter |  셀룰러 텍스처링 |
| ![CheckFilter](../images/CheckFilter.jpg) | CheckFilter |  체커보드 패턴 그리기 |
| ![FBMFilter](../images/FBMFilter.jpg) | FBMFilter |  프랙탈 브라운 모션 텍스처링 |
| ![FillFilter](../images/FillFilter.jpg) | FillFilter |  이미지를 색상으로 채웁니다. |
| ![FlareFilter](../images/FlareFilter.jpg) | FlareFilter |  렌즈 플레어 생성 |
| ![FourColorFilter](../images/FourColorFilter.jpg) | FourColorFilter |  4색 그라데이션 그리기
| ![GradientFilter](../images/GradientFilter.jpg) | GradientFilter | 방사형,  선형, 부채꼴 및 사각형 그라데이션 그리기  |
| ![PlasmaFilter](../images/PlasmaFilter.jpg) | PlasmaFilter |  플라즈마 생성 |
| ![TextureFilter](../images/TextureFilter.jpg) | TextureFilter |  Perlin 노이즈 텍스처링 |
| ![ScratchFilter](../images/ScratchFilter.jpg) | ScratchFilter |  선 또는 스크래치 렌더링 |
| ![SmearFilter](../images/SmearFilter.jpg) | SmearFilter |  페인팅 효과 |
| ![SparkleFilter](../images/SparkleFilter.jpg) | SparkleFilter |  반짝임 렌더링 |
| ![WoodFilter](../images/WoodFilter.jpg) | WoodFilter |  나무 질감 만들기 |
| 흐리게 하고 선명하게 하는 필터 |    |   |
| ![BlurFilter](../images/BlurFilter.jpg) | BlurFilter |  단순 흐림 |
| ![BoxBlurFilter](../images/BoxBlurFilter.jpg) | BoxBlurFilter |  상자 흐림 |
| ![BumpFilter](../images/BumpFilter.jpg) | BumpFilter |  가장자리 엠보싱 |
| ![ConvolveFilter](../images/ConvolveFilter.jpg) | ConvolveFilter |  일반 컨볼루션 |
| ![DespeckleFilter](../images/DespeckleFilter.jpg) | DespeckleFilter |  이미지 얼룩 제거 |
| ![GaussianFilter](../images/GaussianFilter.jpg) | GaussianFilter |  가우시안 블러 |
| ![GlowFilter](../images/GlowFilter.jpg) | GlowFilter |  이미지에 광선 추가 |
| ![HighPassFilter](../images/HighPassFilter.jpg) | HighPassFilter |  낮은 공간 주파수 제거 |
| ![LensBlurFilter](../images/LensBlurFilter.jpg) | LensBlurFilter |  카메라 렌즈 흐림 시뮬레이션 |
| ![MaximumFilter](../images/MaximumFilter.jpg) | MaximumFilter |  팽창 |
| ![MedianFilter](../images/MedianFilter.jpg) | MedianFilter |  노이즈 감소를 위한 중앙값 필터 |
| **침식 및 최소 필터** |    |   |
| ![MotionBlurFilter](../images/MotionBlurFilter.jpg) | MotionBlurFilter |  모션 블러 시뮬레이션 |
| ![OilFilter](../images/OilFilter.jpg) | OilFilter |  유화 효과 |
| ![RaysFilter](../images/RaysFilter.jpg) | RaysFilter |  광선 생성 |
| ![ReduceNoiseFilter](../images/ReduceNoiseFilter.jpg) | ReduceNoiseFilter |  이미지에서 노이즈 제거 |
| ![SharpenFilter](../images/SharpenFilter.jpg) | SharpenFilter |  간단한 선명화 |
| ![SmartBlurFilter](../images/SmartBlurFilter.jpg) | SmartBlurFilter |  주름을 펴기 위한 임계값 흐림 |
| ![UnsharpFilter](../images/UnsharpFilter.jpg) | UnsharpFilter |  언샵 마스킹으로 선명하게 하기 |
| ![VariableBlurFilter](../images/VariableBlurFilter.jpg) | VariableBlurFilter |  마스크에서 가져온 가변 반경을 사용한 블러링 |
| 가장자리 감지 |    |   |
| ![DoGFilter](../images/DoGFilter.jpg) | DoGFilter |  가우시안 차이를 통한 가장자리 감지 |
| ![EdgeFilter](../images/EdgeFilter.jpg) | EdgeFilter |  가장자리 감지 |
| ![LaplaceFilter](../images/LaplaceFilter.jpg) | LaplaceFilter |  Laplace 연산자에 의한 가장자리 감지 |
| 전환 |    |   |
| ![BlurTransition](../images/BlurTransition.jpg) | BlurTransition |  흐림 효과가 있는 전환 |
| ![GradientWipeFilter](../images/GradientWipeFilter.jpg) | GradientWipeFilter |  마스크를 사용하여 한 이미지를 다른 이미지로 디졸브 |
| **알파 채널 필터** |   |   |
| ![OpacityFilter](../images/OpacityFilter.jpg) | OpacityFilter |  이미지 불투명도 변경 |
| ![PremultiplyFilter](../images/blank.jpg) | PremultiplyFilter |  이미지를 미리 곱하기 |
| ![UnpremultiplyFilter](../images/blank.jpg) | UnpremultiplyFilter |  이미지의 사전 곱셈 해제 |
| **기타 필터** |   |   |
| ![CompoundFilter](../images/blank.jpg) | CompoundFilter |  두 개의 필터를 순서대로 적용합니다. |
| ![IteratedFilter](../images/blank.jpg) | IteratedFilter |  다른 필터 반복 |

<br><br><br><br>

### 15-2-2. 이미지 용량 줄이기 처리 실습

#### 15-2-2-1. 프로젝트 설정

- pom.xml 파일에 필요한 라이브러리를 추가합니다.

```xml
<dependencies>
    <!-- Spring dependencies -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.10</version>
    </dependency>

    <!-- Commons FileUpload -->
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.4</version>
    </dependency>

    <!-- Commons IO -->
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.8.0</version>
    </dependency>

    <!-- imgscalr-lib -->
    <dependency>
        <groupId>org.imgscalr</groupId>
        <artifactId>imgscalr-lib</artifactId>
        <version>4.2</version>
    </dependency>
</dependencies>
```

<br><br><br>

#### 15-2-2-2. Spring 설정

- servlet-context.xml에서 파일 업로드를 위한 설정을 추가합니다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:beans="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc" 
	xmlns:websocket="http://www.springframework.org/schema/websocket"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">


    <!-- 중간 생략 -->
    <!-- File upload resolver -->
    <beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <beans:property name="maxUploadSize" value="5000000" />
    </beans:bean>
    <!-- 중간 생략 -->

</beans:beans>
```

<br><br><br>

#### 15-2-2-3. 컨트롤러 구현

- 이미지 업로드 및 처리를 위한 컨트롤러를 작성합니다.

```java
package com.spring1.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/img")
public class ImageUploadController {

    @Autowired
    private ServletContext servletContext; 
    
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadForm() {
        return "img/uploadForm";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
    	
    	String UPLOAD_DIR = servletContext.getRealPath("/resources/upload/");
    	
        if (!file.isEmpty()) {
            try {
                // Save the original file
                String originalFileName = file.getOriginalFilename();
                String extension = FilenameUtils.getExtension(originalFileName);
                File originalFile = new File(UPLOAD_DIR + originalFileName);
                file.transferTo(originalFile);

                // Read the image
                BufferedImage image = ImageIO.read(originalFile);

                // Resize the image
                BufferedImage resizedImage = Scalr.resize(image, 250);

                // Save the resized image
                String resizedFileName = FilenameUtils.getBaseName(originalFileName) + "_resized." + extension;
                File resizedFile = new File(UPLOAD_DIR + resizedFileName);
                ImageIO.write(resizedImage, extension, resizedFile);

                model.addAttribute("message", "File uploaded and resized successfully!");
                model.addAttribute("originalFile", originalFileName);
                model.addAttribute("resizedFile", resizedFileName);
            } catch (IOException e) {
                model.addAttribute("message", "Failed to upload and resize file: " + e.getMessage());
            }
        } else {
            model.addAttribute("message", "File is empty!");
        }
        return "img/uploadResult";
    }
}
```

<br><br><br>

#### 15-2-2-4. 뷰 파일 작성

- 업로드 폼을 제공하는 uploadForm.jsp와 결과를 보여주는 uploadResult.jsp를 작성합니다.

**src/main/webapp/WEB-INF/views/img/uploadForm.jsp**

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <title>Image Upload</title>
</head>
<body>
    <h2>Upload an Image</h2>
    <form method="post" enctype="multipart/form-data" action="${path2 }/img/upload">
        <input type="file" name="file" />
        <input type="submit" value="Upload" />
    </form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/img/uploadResult.jsp**

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <title>Upload Result</title>
</head>
<body>
    <h2>${message}</h2>
    <c:if test="${not empty originalFile}">
        <p>Original File: <a href="${path2}/resources/upload/${originalFile}">${originalFile}</a></p>
        <p>Resized File: <a href="${path2}/resources/upload/${resizedFile}">${resizedFile}</a></p>
    </c:if>
</body>
</html>
```

<br>

- Spring Legacy MVC 프로젝트에서 commons-fileupload, commons-io, imgscalr-lib 라이브러리를 사용하여 이미지 처리 기능을 구현할 수 있습니다. 이 예제에서는 이미지 업로드, 이미지 크기 조정 및 결과를 웹 페이지에 표시하는 기능을 포함하고 있습니다. UPLOAD_DIR 경로는 실제 업로드 파일을 저장할 경로로 변경해야 합니다.


<br><br><br>


### 15-2-3. 이미지 편집 라이브러리 실습

- Spring Legacy 프로젝트에서 이미지의 채도, 명도, 색상 등을 조절할 수 있는 라이브러리를 사용하여 웹 애플리케이션을 구현하려면, 이미지 처리 라이브러리를 선택해야 합니다. imgscalr 라이브러리는 주로 이미지 크기 조정에 사용되며, 채도, 명도, 색상 조절 기능은 포함되어 있지 않습니다. 이를 위해, Java에서 이미지 처리에 자주 사용되는 TwelveMonkeys 이미지 라이브러리와 JHLabs 이미지 필터 라이브러리를 사용할 수 있습니다.

<br><br>

#### 15-2-3-1. 프로젝트 설정

- pom.xml 파일에 필요한 라이브러리를 추가합니다.

```xml
<dependencies>
    <!-- Spring dependencies -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.10</version>
    </dependency>

    <!-- Commons FileUpload -->
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.4</version>
    </dependency>

    <!-- Commons IO -->
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.8.0</version>
    </dependency>

    <!-- TwelveMonkeys ImageIO -->
    <dependency>
        <groupId>com.twelvemonkeys.imageio</groupId>
        <artifactId>imageio-core</artifactId>
        <version>3.6.2</version>
    </dependency>
    <dependency>
        <groupId>com.twelvemonkeys.imageio</groupId>
        <artifactId>imageio-jpeg</artifactId>
        <version>3.6.2</version>
    </dependency>

    <!-- JHLabs Image Filters -->
    <dependency>
        <groupId>com.jhlabs</groupId>
        <artifactId>filters</artifactId>
        <version>2.0.235</version>
    </dependency>
</dependencies>
```

<br><br><br>

#### 15-2-3-2. 컨트롤러 구현

- 이미지 업로드 및 처리를 위한 컨트롤러를 작성합니다.

```java
package com.spring1.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jhlabs.image.ContrastFilter;
import com.jhlabs.image.HSBAdjustFilter;

@Controller
@RequestMapping("/img")
public class ImageEditController {

	@Autowired
    private ServletContext servletContext; 

    @RequestMapping(value = "/upload2", method = RequestMethod.GET)
    public String showUploadForm() {
        return "img/uploadForm2";
    }

    @PostMapping("/uploadPro2")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("brightness") float brightness,
                                   @RequestParam("saturation") float saturation,
                                   @RequestParam("hue") float hue, Model model) throws IIOException {
    	
    	String UPLOAD_DIR = servletContext.getRealPath("/resources/upload/");
    	
        if (!file.isEmpty()) {
            try {
                // Save the original file
                String originalFileName = file.getOriginalFilename();
                String extension = FilenameUtils.getExtension(originalFileName);
                File originalFile = new File(UPLOAD_DIR + originalFileName);
                file.transferTo(originalFile);

                // Read the image
                BufferedImage image = ImageIO.read(originalFile);

                if (image == null) {
                    model.addAttribute("message", "Failed to read input file!");
                    return "img/uploadResult2";
                }

                // Adjust brightness
                ContrastFilter brightnessFilter = new ContrastFilter();
                brightnessFilter.setBrightness(brightness);
                //brightnessFilter.setLights(brightness);
                BufferedImage brightImage = brightnessFilter.filter(image, null);

                // Adjust hue, saturation
                HSBAdjustFilter hsbAdjustFilter = new HSBAdjustFilter();
                hsbAdjustFilter.setHFactor(hue);
                hsbAdjustFilter.setSFactor(saturation);
                BufferedImage editedImage = hsbAdjustFilter.filter(brightImage, null);

                // Save the edited image
                String editedFileName = FilenameUtils.getBaseName(originalFileName) + "_edited." + extension;
                File editedFile = new File(UPLOAD_DIR + editedFileName);
                ImageIO.write(editedImage, extension, editedFile);

                model.addAttribute("message", "File uploaded and edited successfully!");
                model.addAttribute("originalFile", originalFileName);
                model.addAttribute("editedFile", editedFileName);
            } catch (IOException e) {
                model.addAttribute("message", "Failed to upload and edit file: " + e.getMessage());
            }
        } else {
            model.addAttribute("message", "File is empty!");
        }
        return "img/uploadResult2";
    }
}
```

<br><br><br>

#### 15-2-3-3. 뷰 파일 작성

- 업로드 폼을 제공하는 uploadForm2.jsp와 결과를 보여주는 uploadResult2.jsp를 작성합니다.

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
	<h2>${company }</h2>
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/slang/form">비속어 필터링</a></li>
		<li><a href="${path2 }/slang/form2">비속어 필터링2</a></li>
		<li><a href="${path2 }/img/upload">이미지 업로드 스케일링</a></li>
		<li><a href="${path2 }/img/upload2">이미지 편집 업로드</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/img/uploadForm2.jsp**

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <title>Image Upload2</title>
</head>
<body>
    <h2>Upload an Image</h2>
    <form method="post" enctype="multipart/form-data" action="${path2}/img/uploadPro2">
        <label for="file">File:</label>
        <input type="file" name="file" id="file" /><br/>
        
        <label for="brightness">Brightness:</label>
        <input type="range" name="brightness" id="brightness" min="-1.0" max="1.0" step="0.1" value="0.0" /><br/>
        
        <label for="saturation">Saturation:</label>
        <input type="range" name="saturation" id="saturation" min="-1.0" max="1.0" step="0.1" value="0.0" /><br/>
        
        <label for="hue">Hue:</label>
        <input type="range" name="hue" id="hue" min="-1.0" max="1.0" step="0.1" value="0.0" /><br/>
        
        <input type="submit" value="Upload and Edit" />
    </form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/img/uploadResult2.jsp**


```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <title>Upload Result2</title>
</head>
<body>
    <h2>${message}</h2>
    <c:if test="${not empty originalFile}">
        <p>Original File: <a href="${path2}/resources/upload/${originalFile}">${originalFile}</a></p>
        <p>Edited File: <a href="${path2}/resources/upload/${editedFile}">${editedFile}</a></p>
    </c:if>
</body>
</html>
```

- Spring Legacy 프로젝트에서 commons-fileupload, commons-io, TwelveMonkeys, JHLabs 라이브러리를 사용하여 이미지의 채도, 명도, 색상 등을 조절하는 웹 애플리케이션을 구현할 수 있습니다. UPLOAD_DIR 경로는 실제 업로드 파일을 저장할 경로로 변경해야 합니다.

<br><br><hr><br><br>

<div id="15-3"></div>

## 15-3. Jsoup을 이용한 웹 크롤링 구현

### 15-3-1. Gson, Jsoup, json 라이브러리 

#### 15-3-1-1. Gson 라이브러리

**com.google.gson.Gson**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | Gson() | 기본 생성자. |
|   | Gson(GsonBuilder builder) | GsonBuilder 객체를 사용하여 Gson 객체를 생성합니다. |
| 메서드 | fromJson(String json, Class<T> classOfT) | JSON 문자열을 Java 객체로 변환합니다. <br> 파라미터: <br>
- String json: JSON 문자열. <br> - Class<T> classOfT: 변환할 Java 객체의 클래스 타입. <br> 반환 타입: T |
|   | toJson(Object src) | Java 객체를 JSON 문자열로 변환합니다. <br> - 파라미터 : Object src: JSON으로 변환할 Java 객체. <br> - 반환 타입: String |
|   | fromJson(Reader json, Type typeOfT) | Reader로부터 JSON을 읽고 Java 객체로 변환합니다. <br> 파라미터: <br> - Reader json: JSON 데이터를 읽을 Reader. <br> - Type typeOfT: 변환할 Java 객체의 타입. <br> 반환 타입: T |
| toJson(Object src, Appendable writer) | Java 객체를 JSON 문자열로 변환하고 Appendable에 작성합니다. <br> 파라미터: <br> - Object src: JSON으로 변환할 Java 객체. <br> - Appendable writer: JSON 문자열을 작성할 Appendable. <br> 반환 타입: void |

<br><br><br>

#### 15-3-1-2. Jsoup 라이브러리

**org.jsoup.Jsoup**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 메서드 | connect(String url) | URL에 연결하는 새로운 Connection 객체를 생성합니다. <br> 파라미터: String url: 연결할 URL. <br> 반환 타입: Connection |
|   | parse(String html) | HTML 문자열을 파싱하여 Document 객체를 생성합니다. <br> 파라미터: <br> - String html: 파싱할 HTML 문자열. <br> 반환 타입: Document |
|   | parse(File in, String charsetName) | 파일을 파싱하여 Document 객체를 생성합니다. <br> 파라미터: <br> - File in: 파싱할 파일. <br> - String charsetName: 사용될 문자 인코딩. <br> 반환 타입: Document |

<br><br>

**org.jsoup.nodes.Document**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 메서드 | select(String cssQuery) | 주어진 CSS 쿼리와 일치하는 요소를 찾습니다. <br> 파라미터: <br> String cssQuery: 찾을 CSS 쿼리. <br> 반환 타입: Elements |
|   | title() | 문서의 제목을 반환합니다. <br> 파라미터: 없음 <br> 반환 타입: String |
|   | body() | 문서의 본문 요소를 반환합니다. <br> 파라미터: 없음 <br> 반환 타입: Element |

<br><br>

**org.jsoup.nodes.Element**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 메서드 | text() | 요소의 텍스트 콘텐츠를 반환합니다. <br> 파라미터: 없음 <br> 반환 타입: String |
|   | html() | 요소의 HTML 콘텐츠를 반환합니다. <br> 파라미터: 없음 <br> 반환 타입: String |

<br><br>

**org.jsoup.select.Elements**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 메서드 | get(int index) | 주어진 인덱스의 요소를 반환합니다. <br> 파라미터: <br> int index: 가져올 요소의 인덱스. <br> 반환 타입: Element |

<br><br><br>

#### 15-3-1-3. org.json 라이브러리

**org.json.JSONObject**

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | JSONObject() | 빈 JSONObject 객체를 생성합니다. |
|   | JSONObject(String source) | 주어진 JSON 문자열을 파싱하여 JSONObject 객체를 생성합니다. <br> 파라미터: String source: JSON 문자열. | 
|   | JSONObject(Map<?, ?> map) | 주어진 맵을 사용하여 JSONObject 객체를 생성합니다. <br> 파라미터: Map<?, ?> map: 초기화에 사용할 맵. |
| 메서드 | put(String key, Object value) | 주어진 키와 값을 추가합니다. <br> 파라미터: <br> - String key: 키. <br> - Object value: 값. <br> 반환 타입: JSONObject |
|   | getString(String key) | 주어진 키에 해당하는 문자열 값을 반환합니다. <br> 파라미터: <br> String key: 가져올 값의 키. <br> 반환 타입: String |
|   | getInt(String key) | 주어진 키에 해당하는 정수 값을 반환합니다. <br> 파라미터: <br> String key: 가져올 값의 키. <br> 반환 타입: int |
|   | toString() | JSON 객체를 문자열로 변환하여 반환합니다. <br> 파라미터: 없음 <br> 반환 타입: String |

<br><br><br><br>

### 15-3-2. 웹 크롤링 구현

- 의존성을 사용하여 웹 페이지를 크롤링하고, JSON으로 변환한 후 Gson을 사용하여 JSON 데이터를 파싱하는 것을 구현하도록 합니다.

1. 프로젝트 설정 (pom.xml에 의존성 추가)
2. 웹 페이지 크롤링 (jsoup 사용)
3. JSON 데이터 변환 (org.json 사용)
4. JSON 파싱 (gson 사용)

<br>

#### 15-3-2-1. 라이브러리 추가

**pom.xml 수정**

```xml
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
</project>
```

<br><br><br>

#### 15-3-2-2. 웹 페이지 크롤링 (jsoup 사용)

- jsoup을 사용하여 웹 페이지의 데이터를 추출합니다.

```java
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebCrawlerTest {
    public static void main(String[] args) {
        String url = "https://www.spring1.com";  // 크롤링할 웹사이트의 URL

        try {
            Document doc = Jsoup.connect(url).get();

            // HTML 제목 태그를 찾습니다.
            Elements titles = doc.select("h1");

            for (Element title : titles) {
                System.out.println("Title: " + title.text());
            }

            // 기타 필요한 데이터를 크롤링할 수 있습니다.
            // 예: doc.select("css-selector");
            //doc.select("css-selector");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

<br><br><br>

#### 15-3-2-3. JSON 데이터 변환 (org.json 사용)

- 크롤링한 데이터를 JSON 객체로 변환합니다.

```java
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataToJsonTest {
    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("title", "Example Title");
        data.put("content", "This is an example content.");

        JSONObject json = new JSONObject(data);
        System.out.println(json.toString());
    }
}
```

<br><br><br>

#### 15-3-2-4. JSON 파싱 (gson 사용)

- JSON 데이터를 파싱하여 Java 객체로 변환합니다.

```java
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonParsingTest {
    public static void main(String[] args) {
        String jsonString = "{\"title\":\"Example Title\",\"content\":\"This is an example content.\"}";

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        String title = jsonObject.get("title").getAsString();
        String content = jsonObject.get("content").getAsString();

        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
    }
}
```

<br><br><br>

#### 15-3-2-5. 웹 크롤링 전체 코드

- 크롤링, JSON 변환, 파싱을 한 번에 수행하는 종합 예제입니다.

```java
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebCrawlingTotal {
    public static void main(String[] args) {
        String url = "https://www.moel.go.kr/info/realpolicy/realPolicyMain.do";  // 크롤링할 웹사이트의 URL

        try {
            // 1. 웹 페이지 크롤링
            Document doc = Jsoup.connect(url).get();
            Elements titles = doc.select("#contents_title");
            String titleText = titles.isEmpty() ? "No title found" : titles.get(0).text();
            Elements content = doc.select(".title2");

            List<String> contentData = new ArrayList<>();
            for (Element row : content) {
                contentData.add(row.text());
                log.info("내용 : {}", row.text());
            }

            // 2. JSON 데이터 변환
            Map<String, String> data = new HashMap<>();
            data.put("title", titleText);
            data.put("content", contentData);
            JSONObject json = new JSONObject(data);
            String jsonString = json.toString();
            System.out.println("JSON Data: " + jsonString);

            // 3. JSON 데이터 파싱
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
            String title1 = jsonObject.get("title").getAsString();
            String content1 = jsonObject.get("content").getAsString();

            System.out.println("Parsed Title: " + title1);
            System.out.println("Parsed Content: " + content1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

<br><br><br>

#### 15-3-2-6. 컨트롤러 작성

```java
package com.spring1.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;

@Controller
public class WebCrawlerController {

	private static final Logger log = LoggerFactory.getLogger(WebCrawlerController.class);
	
	@GetMapping("/crawl")
	public String crawl(Model model) {
	    String url = "https://www.moel.go.kr/info/realpolicy/realPolicyMain.do";
	    Map<String, Object> data = new HashMap<>();

	    try {
	        Document doc = Jsoup.connect(url).timeout(2000).get();
	        
	        log.info("문서 : {}", doc);
	        
	        Elements titles = doc.select("#contents_title");
	        String titleText = titles.isEmpty() ? "제목을 찾을 수 없습니다." : titles.get(0).text();
	        
	        log.info("문서 제목 : {}", titleText);
	        
	        // 데이터에 제목 추가
	        data.put("title", titleText);
	        
	        Elements comment = doc.select(".title2");
	        Elements numlist = doc.select(".num_list li");

	        if (comment != null) {
	            // 각 행의 데이터를 리스트로 저장
	            List<String> rowDataList = new ArrayList<>();
	            for (Element row : comment) {
	                rowDataList.add(row.text());
	                log.info("내용 : {}", row.text());
	            }
	            // 데이터에 행 데이터 추가
	            data.put("rowData", rowDataList);
	        } else {
	            System.out.println("class가 'title2'인 요소를 찾을 수 없습니다.");
	        }
	        
	        if (numlist != null) {
	            // 각 행의 데이터를 리스트로 저장
	        	
	            List<String> rowDataList2 = new ArrayList<>();
	            for (int i=0;i<numlist.size();i++) {
	            	Element row = numlist.get(i);
	                rowDataList2.add(row.text());
	                log.info("내용 : {}", row.text());
	            }
	            // 데이터에 행 데이터 추가
	            data.put("rowData2", rowDataList2);
	        } else {
	            System.out.println("class가 'num_list'인 요소를 찾을 수 없습니다.");
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	        data.put("error", "웹 사이트 크롤링에 실패했습니다.");
	    }

	    Gson gson = new Gson();
	    String jsonData = gson.toJson(data);

	    model.addAttribute("jsonData", jsonData);

	    return "crawler/crawling";
	}
}
```


<br><br><br>

#### 15-3-2-7. View(JSP) 작성

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
	<h2>${company }</h2>
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/slang/form">비속어 필터링</a></li>
		<li><a href="${path2 }/slang/form2">비속어 필터링2</a></li>
		<li><a href="${path2 }/img/upload">이미지 업로드 스케일링</a></li>
		<li><a href="${path2 }/img/upload2">이미지 편집 업로드</a></li>
		<li><a href="${path2 }/crawl">웹크롤링</a></li>
	</ul>
</body>
</html>
```

<br><br>


**src/main/webapp/WEB-INF/views/crawler/crawling.jsp 작성**

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <title>Crawling Result</title>
</head>
<body>
    <h2>Crawling Result</h2>
    <c:choose>
        <c:when test="${not empty jsonData}">
            <p>JSON Data:</p>
            <pre>${jsonData}</pre>
        </c:when>
        <c:otherwise>
            <p>No data found.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
```

- 위 소스코드는 Jsoup을 사용하여 웹 페이지에서 데이터를 크롤링하고, 이를 org.json을 사용하여 JSON 객체로 변환한 다음, Gson을 사용하여 JSON 데이터를 Java 객체로 파싱하여 결과를 View(jsp)로 보여줍니다.


<br><br><hr><br><br>

<div id="15-4"></div>

## 15-4. 차트 Api 구현

### 15-4-1. 구글 차트 사용법

#### 15-4-1-1. 구글 차트 라이브러리 불러오기

- 구글 차트를 사용하기 위해서는 먼저 구글 차트 라이브러리를 HTML 파일에 불러와야 합니다.

```html
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
```

<br><br><br>

2. 차트를 그릴 엘리먼트 생성
차트를 표시할 <div> 엘리먼트를 HTML 파일에 생성합니다. 이 엘리먼트에는 차트가 표시될 공간의 크기를 설정할 수 있습니다. 예를 들어:

```html
<div id="chart_div" style="width: 800px; height: 400px;"></div>
```

<br><br><br>

3. 데이터 준비하기

- 그리려는 차트의 종류에 맞는 데이터를 준비합니다. 데이터는 JavaScript의 배열이나 객체로 표현됩니다. 예를 들어, 꺾은 선 그래프를 그리려면 X축과 Y축의 데이터를 배열로 만들어야 합니다.

<br><br><br>

4. 차트 로드 및 그리기

- google.charts.load() 함수를 사용하여 구글 차트 라이브러리를 로드하고, google.charts.setOnLoadCallback() 함수를 사용하여 차트를 그릴 콜백 함수를 정의합니다. 그 후에 차트를 그리기 위한 데이터를 로드하고 그립니다.

```javascript
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
  var data = google.visualization.arrayToDataTable([
    ['Year', 'Sales', 'Expenses'],
    ['2013',  1000,      400],
    ['2014',  1170,      460],
    ['2015',  660,       1120],
    ['2016',  1030,      540]
  ]);

  var options = {
    title: 'Company Performance',
    curveType: 'function',
    legend: { position: 'bottom' }
  };

  var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

  chart.draw(data, options);
}
```

<br>

- 위의 코드에서 arrayToDataTable() 함수는 배열 형식의 데이터를 Google Visualization DataTable 객체로 변환하고, draw() 함수를 사용하여 그래프를 그립니다. 이 때, 첫 번째 매개변수로는 데이터가 담긴 DataTable 객체를, 두 번째 매개변수로는 옵션을 전달합니다.

<br><br><br><br>

### 15-4-2. 구글 차트 종류

#### 15-4-2-1. 꺾은 선 그래프 (Line Chart)

![그래프 예시](../images/chart01.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    // Google Charts 라이브러리 로드 완료 후 실행
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawCharts);

    function drawCharts() {
      // 차트 1 - 라인 차트
      var data1 = google.visualization.arrayToDataTable([
        ['연도', '매출', '비용'],
        ['2019', 1000, 600],
        ['2020', 1200, 700],
        ['2021', 1400, 800],
        ['2022', 1600, 900]
      ]);

      var options1 = {
        title: '매출과 비용 추이',
        width: 400,
        height: 300
      };

      var chart1 = new google.visualization.LineChart(document.getElementById('chart_div1'));
      chart3.draw(data1, options1);
    }
  </script>
</head>
<body>
  <!-- 그래프 -->
  <div id="chart_div1"></div>
</body>
</html>
```

**필수 항목** : X축 및 Y축 데이터.
**옵션** :
    - curveType: 곡선의 유형을 설정합니다. ('none' 또는 'function')
    - legend.position: 범례의 위치를 설정합니다. ('bottom', 'top', 'none' 등)

<br><br>

#### 15-4-2-2. 가로 막대 그래프 (Bar Chart)

![그래프 예시](../images/chart03.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    // Google Charts 라이브러리 로드 완료 후 실행
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawCharts);

    function drawCharts() {
	  // 차트 2 - 가로 막대 그래프
      var data2 = google.visualization.arrayToDataTable([
        ['도시', '인구'],
        ['서울', 9720846],
        ['부산', 3404423],
        ['인천', 2922274],
        ['대구', 2459744],
        ['대전', 1539470]
      ]);

      var options2 = {
        title: '대한민국 주요 도시의 인구',
        width: 400,
        height: 300,
        hAxis: { title: '인구' }
      };

      var chart2 = new google.visualization.BarChart(document.getElementById('chart_div2'));
      chart2.draw(data2, options2);
    }
  </script>
</head>
<body>
  <!-- 그래프 -->
  <div id="chart_div2"></div>
</body>
</html>
```

**필수 항목** : 막대의 높이를 나타내는 데이터 및 막대의 카테고리를 나타내는 레이블.
**옵션** :
    legend.position: 범례의 위치를 설정합니다.

<br><br>

#### 15-4-2-3. 세로 막대 그래프 (Column Chart)

![그래프 예시](../images/chart02.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    // Google Charts 라이브러리 로드 완료 후 실행
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawCharts);

    function drawCharts() {
      // 차트 3 - 세로 막대 그래프
      var data3 = google.visualization.arrayToDataTable([
        ['도시', '인구'],
        ['서울', 9720846],
        ['부산', 3404423],
        ['인천', 2922274],
        ['대구', 2459744],
        ['대전', 1539470]
      ]);

      var options3 = {
        title: '대한민국 주요 도시의 인구',
        width: 400,
        height: 300
      };

      var chart3 = new google.visualization.ColumnChart(document.getElementById('chart_div3'));
      chart3.draw(data3, options3);
    }
  </script>
</head>
<body>
  <!-- 그래프 -->
  <div id="chart_div3"></div>
</body>
</html>
```

**필수 항목**, **옵션**은 가로 막대 그래프와 유사하지만, 막대가 세로 방향으로 표시됩니다.

<br><br>

#### 15-4-2-4. 원형 그래프 (Pie Chart)

![그래프 예시](../images/chart04.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    // Google Charts 라이브러리 로드 완료 후 실행
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawCharts);

    function drawCharts() {
      // 차트 4 - 파이 차트
      var data4 = google.visualization.arrayToDataTable([
        ['과일', '판매량'],
        ['사과', 30],
        ['바나나', 50],
        ['딸기', 20],
        ['오렌지', 40]
      ]);

      var options4 = {
        title: '과일 판매 비율',
        width: 400,
        height: 300
      };

      var chart4 = new google.visualization.PieChart(document.getElementById('chart_div4'));
      chart4.draw(data4, options4);
    }
  </script>
</head>
<body>
  <!-- 그래프를 그릴 요소들 -->
  <div id="chart_div4"></div>
</body>
</html>
```

**필수 항목** : 각 부채꼴의 크기를 나타내는 데이터 및 각 부채꼴의 레이블.
**옵션** :
    legend.position: 범례의 위치를 설정합니다.

<br><br>

#### 15-4-2-5. 레이더(6축) 그래프 (Radar Chart)

![그래프 예시](../images/chart05.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    // Google Charts 라이브러리 로드 완료 후 실행
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawCharts);

    function drawCharts() {
	// 차트 5 - 레이다 그래프
      var data5 = google.visualization.arrayToDataTable([
        ['키', '몸무게'],
        [165, 68],
        [170, 75],
        [175, 80],
        [180, 88],
        [185, 95]
      ]);

      var options5 = {
        title: '키와 몸무게의 관계',
        width: 400,
        height: 400
      };

      var chart5 = new google.visualization.RadarChart(document.getElementById('chart_div5'));
      chart5.draw(data5, options5);
    }
  </script>
</head>
<body>
  <!-- 그래프 -->
  <div id="chart_div5" style="float: left;"></div>
</body>
</html>
```

**필수 항목** : 각 축에 해당하는 데이터 값.
**옵션** :
    레이더 그래프에는 일부 특정 옵션이 없을 수 있으며, 사용하는 라이브러리나 프레임워크에 따라 다릅니다.

<br><br>

#### 15-4-2-6. 분산형 그래프 (Scatter Chart)

![그래프 예시](../images/chart06.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    // Google Charts 라이브러리 로드 완료 후 실행
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawCharts);

    function drawCharts() {
	// 차트 6 - 산점도 그래프
      var data6 = google.visualization.arrayToDataTable([
        ['키', '몸무게'],
        [165, 68],
        [170, 75],
        [175, 80],
        [180, 88],
        [185, 95]
      ]);

      var options6 = {
        title: '키와 몸무게의 관계',
        width: 400,
        height: 300,
        hAxis: { title: '키 (cm)' },
        vAxis: { title: '몸무게 (kg)' }
      };

      var chart6 = new google.visualization.ScatterChart(document.getElementById('chart_div6'));
      chart6.draw(data6, options6);
    }
  </script>
</head>
<body>
  <!-- 그래프 -->
  <div id="chart_div6"></div>
</body>
</html>
```

**필수 항목** : 각 데이터 포인트의 X, Y 값을 나타내는 데이터.
**옵션** :
    legend.position: 범례의 위치를 설정합니다.

<br><br>

#### 15-4-2-7. 지도 차트 (Geo Chart)

![그래프 예시](../images/chart07.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load('current', {
    'packages': ['geochart'],
    // Note: you will need to get a mapsApiKey for your project.
    // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
    'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
    });
    google.charts.setOnLoadCallback(drawRegionsMap);

    function drawRegionsMap() {
    var data = google.visualization.arrayToDataTable([
        ['Country', 'Popularity'],
        ['Germany', 200],
        ['United States', 300],
        ['Brazil', 400],
        ['Canada', 500],
        ['France', 600],
        ['RU', 700]
    ]);

    var options = {
        colorAxis: {
        colors: ['#76C1FA', '#63CF72', '#F36368', '#FABA66']
        }
    };
    var chart = new google.visualization.GeoChart(document.getElementById('chart_div7'));

    chart.draw(data, options);
    }
  </script>
</head>
<body>
  <!-- 그래프 -->
  <div id="chart_div7"></div>
</body>
</html>
```

**필수 항목** : 각 지역의 데이터 값 또는 크기.
**옵션** :
    region: 표시할 지역을 설정합니다.
    displayMode: 지도의 표시 방법을 설정합니다. ('markers', 'regions', 'text' 등)

<br><br>

#### 15-4-2-8. 트리맵 차트 (Tree Map)

![그래프 예시](../images/chart08.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load('current', { 'packages': ['treemap'] });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'ID');
        data.addColumn('string', 'Parent');
        data.addColumn('number', 'Number Of Lines');
        data.addRows([
        // ['ID', 'Parent', 'number of lines']
            ['Shakespeare', null, 0],
            
            ['Adam', 'Shakespeare', 10],
            ['Amiens', 'Shakespeare', 10],
            ['Audrey', 'Shakespeare', 12],
            ['Celia', 'Shakespeare', 108],
            ['Charles', 'Shakespeare', 8],
            ['Corin', 'Shakespeare', 24],
            ['Duke', 'Shakespeare', 32],
            ['Frederick', 'Shakespeare', 20],
            ['Jaques (lord)', 'Shakespeare', 57],
            ['Le Beau', 'Shakespeare', 14],
            ['Oliver', 'Shakespeare', 37],
            ['Orlando', 'Shakespeare', 120],
            ['Phebe', 'Shakespeare', 23],
            ['Rosalind', 'Shakespeare', 201],
            ['Silvius', 'Shakespeare', 24],
            ['Touchstone', 'Shakespeare', 74],
            ['William', 'Shakespeare', 11],
            ['좋아 아주 잘 되고 있구마이!', 'Shakespeare', 230]

        ]);

        var tree = new google.visualization.TreeMap(document.getElementById('chart_div8'));

        var options = { // For v49 or before
            highlightOnMouseOver: true,
            maxDepth: 1,
            maxPostDepth: 2,
            minHighlightColor: '#8c6bb1',
            midHighlightColor: '#9ebcda',
            maxHighlightColor: '#edf8fb',
            minColor: '#009688',
            midColor: '#f7f7f7',
            maxColor: '#ee8100',
            headerHeight: 15,
            showScale: true,
            height: 500,
            useWeightedAverageForAggregation: true
        };

        var optionsV50 = { // For v50+
            enableHighlight: true,
            maxDepth: 1,
            maxPostDepth: 2,
            minHighlightColor: '#8c6bb1',
            midHighlightColor: '#9ebcda',
            maxHighlightColor: '#edf8fb',
            minColor: '#009688',
            midColor: '#f7f7f7',
            maxColor: '#ee8100',
            headerHeight: 15,
            showScale: true,
            height: 500,
            useWeightedAverageForAggregation: true,
            // Use click to highlight and double-click to drill down.
            eventsConfig: {
                highlight: ['click'],
                unhighlight: ['mouseout'],
                rollup: ['contextmenu'],
                drilldown: ['dblclick'],
            }
        };

        tree.draw(data, options);
        // Please uncomment the following line for v50+.
        // tree.draw(data, optionsV50);

    }
  </script>
</head>
<body>
  <!-- 그래프 -->
  <div id="chart_div8"></div>
</body>
</html>
```

**필수 항목** : 트리맵의 데이터 값 및 계층 구조.
**옵션** :
    maxDepth: 표시할 최대 깊이를 설정합니다.
    maxPostDepth: 최대 포스트 깊이를 설정합니다.

<br><br>

#### 15-4-2-9. 캘린더 차트 (Calendar Chart)

![그래프 예시](../images/chart09.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    // Google Charts 라이브러리 로드 완료 후 실행
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawCharts);

    function drawCharts() {
      // 차트 9 - 캘린다 그래프
        var data = google.visualization.arrayToDataTable([
          ['Location', 'Parent', 'Market trade volume (size)', 'Market increase/decrease (color)'],
          ['Global',    null,                 0,                               0],
          ['America',   'Global',             0,                               0],
          ['Europe',    'Global',             0,                               0],
          ['Asia',      'Global',             0,                               0],
          ['Australia', 'Global',             0,                               0],
          ['Africa',    'Global',             0,                               0],
          ['Brazil',    'America',            11,                              10],
          ['USA',       'America',            52,                              31],
          ['Mexico',    'America',            24,                              12],
          ['Canada',    'America',            16,                              -23],
          ['France',    'Europe',             42,                              -11],
          ['Germany',   'Europe',             31,                              -2],
          ['Sweden',    'Europe',             22,                              -13],
          ['Italy',     'Europe',             17,                              4],
          ['UK',        'Europe',             21,                              -5],
          ['China',     'Asia',               36,                              4],
          ['Japan',     'Asia',               20,                              -12],
          ['India',     'Asia',               40,                              63],
          ['Laos',      'Asia',               4,                               34],
          ['Mongolia',  'Asia',               1,                               -5],
          ['Israel',    'Asia',               12,                              24],
          ['Iran',      'Asia',               18,                              13],
          ['Pakistan',  'Asia',               11,                              -52],
          ['Egypt',     'Africa',             21,                              0],
          ['S. Africa', 'Africa',             30,                              43],
          ['Sudan',     'Africa',             12,                              2],
          ['Congo',     'Africa',             10,                              12],
          ['Zaire',     'Africa',             8,                               10]
        ]);

        tree = new google.visualization.TreeMap(document.getElementById('chart_div9'));

        tree.draw(data, {
          minColor: '#f00',
          midColor: '#ddd',
          maxColor: '#0d0',
          headerHeight: 15,
          fontColor: 'black',
          showScale: true
        });
    }
  </script>
</head>
<body>
  <!-- 그래프 -->
  <div id="chart_div9"></div>
</body>
</html>
```

**필수 항목** : 날짜와 해당 날짜의 데이터 값.
**옵션** :
    colorAxis: 날짜에 대한 색상 축을 설정합니다.
    title: 차트의 제목을 설정합니다.

<br><br>

#### 15-4-2-10. 직사각형 차트 (Histogram Chart)

![그래프 예시](../images/chart10.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
  <!-- Google Charts 라이브러리 로드 -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    // Google Charts 라이브러리 로드 완료 후 실행
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawCharts);

    function drawCharts() {
      // 차트 10 - 직사각형(Histogram) 그래프
        var data = google.visualization.arrayToDataTable([
          ['Dinosaur', 'Length'],
          ['Acrocanthosaurus (top-spined lizard)', 12.2],
          ['Albertosaurus (Alberta lizard)', 9.1],
          ['Allosaurus (other lizard)', 12.2],
          ['Apatosaurus (deceptive lizard)', 22.9],
          ['Archaeopteryx (ancient wing)', 0.9],
          ['Argentinosaurus (Argentina lizard)', 36.6],
          ['Baryonyx (heavy claws)', 9.1],
          ['Brachiosaurus (arm lizard)', 30.5],
          ['Ceratosaurus (horned lizard)', 6.1],
          ['Coelophysis (hollow form)', 2.7],
          ['Compsognathus (elegant jaw)', 0.9],
          ['Deinonychus (terrible claw)', 2.7],
          ['Diplodocus (double beam)', 27.1],
          ['Dromicelomimus (emu mimic)', 3.4],
          ['Gallimimus (fowl mimic)', 5.5],
          ['Mamenchisaurus (Mamenchi lizard)', 21.0],
          ['Megalosaurus (big lizard)', 7.9],
          ['Microvenator (small hunter)', 1.2],
          ['Ornithomimus (bird mimic)', 4.6],
          ['Oviraptor (egg robber)', 1.5],
          ['Plateosaurus (flat lizard)', 7.9],
          ['Sauronithoides (narrow-clawed lizard)', 2.0],
          ['Seismosaurus (tremor lizard)', 45.7],
          ['Spinosaurus (spiny lizard)', 12.2],
          ['Supersaurus (super lizard)', 30.5],
          ['Tyrannosaurus (tyrant lizard)', 15.2],
          ['Ultrasaurus (ultra lizard)', 30.5],
          ['Velociraptor (swift robber)', 1.8]]);

        var options = {
          title: 'Lengths of dinosaurs, in meters',
          legend: { position: 'none' },
        };

        var chart = new google.visualization.Histogram(document.getElementById('chart_div10'));
        chart.draw(data, options);

    }
  </script>
</head>
<body>
  <!-- 그래프 -->
  <div id="chart_div10"></div>
</body>
</html>
```

**필수 항목** : 데이터 범위와 해당 범위 내의 빈도.
**옵션** :
    legend.position: 범례의 위치를 설정합니다.

<br><br><br>

### 15-4-3. Spring Legacy Gson + Google Chart

#### 15-4-3-1. Controller 작성

```java
package com.spring1.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/chart)
public class ChartController {

    @GetMapping("/chart-data")
    @ResponseBody
    public String getChartData() {
        Map<String, Object> data = new HashMap<>();
        
        // Sample data for different charts
        data.put("lineChart", new int[]{10, 20, 30, 40, 50});
        data.put("barChart", new int[]{15, 25, 35, 45, 55});
        data.put("columnChart", new int[]{20, 30, 40, 50, 60});
        data.put("pieChart", new int[]{5, 15, 25, 35, 45});
        data.put("radarChart", new int[]{12, 22, 32, 42, 52, 62});
        data.put("scatterChart", new int[][]{{5, 3}, {10, 17}, {15, 7}, {20, 12}, {25, 19}});
        data.put("mapChart", new Object[][]{{"서울", 1000000}, {"부산", 800000}, {"인천", 700000}, {"대구", 600000}, {"광주", 500000}});
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    @GetMapping("/charts")
    public String getChartsPage(Model model) {
        return "chart/charts";
    }
}
```

<br><br><br>

#### 15-4-3-2. View(jsp) 작성

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
	<h2>${company }</h2>
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/slang/form">비속어 필터링</a></li>
		<li><a href="${path2 }/slang/form2">비속어 필터링2</a></li>
		<li><a href="${path2 }/img/upload">이미지 업로드 스케일링</a></li>
		<li><a href="${path2 }/img/upload2">이미지 편집 업로드</a></li>
        <li><a href="${path2 }/crawl">웹크롤링</a></li>
		<li><a href="${path2 }/chart/charts">구글 차트</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/chart/charts.jsp 작성**

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <title>Charts Example</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart', 'bar', 'line']});
        google.charts.setOnLoadCallback(drawCharts);

        function drawCharts() {
            fetch('chart/chart-data')
                .then(response => response.json())
                .then(data => {
                    drawLineChart(data.lineChart);
                    drawBarChart(data.barChart);
                    drawColumnChart(data.columnChart);
                    drawPieChart(data.pieChart);
                    drawRadarChart(data.radarChart);
                    drawScatterChart(data.scatterChart);
                    drawMapChart(data.mapChart);
                });
        }

        function drawLineChart(data) {
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn('number', 'X');
            dataTable.addColumn('number', 'Values');
            data.forEach((value, index) => dataTable.addRow([index + 1, value]));

            var options = {
                title: 'Line Chart',
                curveType: 'function',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('line_chart'));
            chart.draw(dataTable, options);
        }

        function drawBarChart(data) {
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn('string', 'X');
            dataTable.addColumn('number', 'Values');
            data.forEach((value, index) => dataTable.addRow([`Category ${index + 1}`, value]));

            var options = {
                title: 'Bar Chart',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.BarChart(document.getElementById('bar_chart'));
            chart.draw(dataTable, options);
        }

        function drawColumnChart(data) {
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn('string', 'X');
            dataTable.addColumn('number', 'Values');
            data.forEach((value, index) => dataTable.addRow([`Category ${index + 1}`, value]));

            var options = {
                title: 'Column Chart',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('column_chart'));
            chart.draw(dataTable, options);
        }

        function drawPieChart(data) {
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn('string', 'Category');
            dataTable.addColumn('number', 'Values');
            data.forEach((value, index) => dataTable.addRow([`Category ${index + 1}`, value]));

            var options = {
                title: 'Pie Chart',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.PieChart(document.getElementById('pie_chart'));
            chart.draw(dataTable, options);
        }

        function drawRadarChart(data) {
            // Google Charts doesn't have a built-in radar chart. A workaround is required
            // You can use other libraries like Chart.js for radar charts.
            // Here's a basic example using Chart.js:
            var ctx = document.getElementById('radar_chart').getContext('2d');
            new Chart(ctx, {
                type: 'radar',
                data: {
                    labels: ['A', 'B', 'C', 'D', 'E', 'F'],
                    datasets: [{
                        label: 'Radar Chart',
                        data: data,
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderColor: 'rgba(54, 162, 235, 1)'
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        r: {
                            beginAtZero: true
                        }
                    }
                }
            });
        }

        function drawScatterChart(data) {
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn('number', 'X');
            dataTable.addColumn('number', 'Y');
            data.forEach(point => dataTable.addRow(point));

            var options = {
                title: 'Scatter Chart',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.ScatterChart(document.getElementById('scatter_chart'));
            chart.draw(dataTable, options);
        }

        function drawMapChart(data) {
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn('string', 'City');
            dataTable.addColumn('number', 'Population');
            dataTable.addRows(data);

            var options = {
                region: 'KR',
                displayMode: 'markers',
                colorAxis: {colors: ['green', 'blue']}
            };

            var chart = new google.visualization.GeoChart(document.getElementById('map_chart'));
            chart.draw(dataTable, options);
        }
    </script>
</head>
<body>
    <h1>Google Charts</h1>
    <hr>
    <div id="line_chart" style="width: 900px; height: 500px;"></div>
    <div id="bar_chart" style="width: 900px; height: 500px;"></div>
    <div id="column_chart" style="width: 900px; height: 500px;"></div>
    <div id="pie_chart" style="width: 900px; height: 500px;"></div>
    <div id="radar">
        <canvas id="radar_chart" style="width: 900px; height: 500px;"></canvas>
    </div>    
    <div id="scatter_chart" style="width: 900px; height: 500px;"></div>
    <div id="map_chart" style="width: 900px; height: 500px;"></div>
</body>
</html>
```

<br><br><hr><br><br>

<div id="15-5"></div>

## 15-5. PDF 생성 및 편집기 구현

### 15-5-1. Apache PDFBox 란?

- Apache PDFBox는 PDF 문서를 생성, 편집 및 처리하기 위한 Java 라이브러리입니다. 
- PDF 문서의 다양한 요소를 다루기 위한 다양한 클래스들이 있습니다. 

<br>

#### 15-5-1-1. PDDocument

**org.apache.pdfbox.pdmodel.PDDocument**

- 기능: PDF 문서를 나타냅니다. 페이지 추가, 저장, 닫기 등의 작업을 수행합니다.

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | PDDocument() | 새로운 비어있는 PDF 문서를 생성합니다. |
|   | PDDocument(InputStream inputStream) | 주어진 InputStream에서 PDF 문서를 로드합니다. |
| 메소드 | addPage(PDPage page) | 새로운 페이지를 문서에 추가합니다. |
|   | save(OutputStream outputStream) | 문서를 지정된 OutputStream으로 저장합니다. |
|   | close() | 문서를 닫습니다. |


<br><br>

#### 15-5-1-2. PDPage

**org.apache.pdfbox.pdmodel.PDPage**

- 기능: PDF 문서의 페이지를 나타냅니다. 텍스트, 이미지 등의 콘텐츠를 추가할 수 있습니다.

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | PDPage() | 새로운 비어있는 페이지를 생성합니다. |
| 메소드 | addAnnotation(PDAnnotation annotation) | 주석을 페이지에 추가합니다. |
| getMediaBox() | 페이지의 미디어 박스(페이지의 크기 및 배치를 정의하는 상자)를 반환합니다. |
| getContents() | 페이지의 콘텐츠 스트림을 반환합니다. |

<br><br>

#### 15-5-1-3. PDPageContentStream

**org.apache.pdfbox.pdmodel.PDPageContentStream**

- 기능: PDF 페이지에 텍스트, 이미지 등의 콘텐츠를 추가합니다.

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | PDPageContentStream(PDDocument document, PDPage page) | 주어진 문서 및 페이지에 대한 새로운 콘텐츠 스트림을 생성합니다. |
| 메소드 | beginText() | 텍스트 추가를 시작합니다. |
|   | setFont(PDFont font, float fontSize) | 현재 폰트를 설정합니다. |
|   | showText(String text) | 텍스트를 추가합니다. |
|   | newLineAtOffset(float tx, float ty) | 텍스트 시작 위치 설정 |
|   | drawImage(PDImageXObject image, float x, float y) | 이미지 추가 |

<br><br>

#### 15-5-1-4. PDPageTree

**org.apache.pdfbox.pdmodel.pdmodel.PDPageTree**

- 기능: 페이지를 트리 구조로 관리합니다. PDDocument 내의 모든 페이지에 대한 접근을 제공합니다.

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | PDPageTree() | 새로운 빈 페이지 트리를 생성합니다. |
| 메소드 | add(PDPage page) | 페이지를 트리에 추가합니다. |
|   | get(int index) | 지정된 인덱스의 페이지를 반환합니다. |

<br><br>

#### 15-5-1-5. PDFont

**org.apache.pdfbox.pdmodel.font.PDFont**

- 기능: PDF 문서의 글꼴을 나타냅니다.

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | PDFBox는 여러 가지 글꼴 유형을 지원합니다. 예를 들어, PDType1Font 클래스를 사용하여 기본 PostScript 글꼴을 나타낼 수 있습니다. |
| 메소드 | PDType1Font.HELVETICA | 헬베티카 글꼴을 나타냅니다. |

<br><br>

#### 15-5-1-6. PDImageXObject

- 기능: PDF 페이지에 이미지를 추가합니다.

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | PDImageXObject.createFromFile(String fileName, PDDocument document) |  |
| 메소드 | 이미지 객체로서 주로 PDF 페이지에 추가되는 데 사용됩니다. |  |


<br><br>

#### 15-5-1-7. PDAnnotation

- 기능: PDF 문서에 주석을 추가합니다.

| 카테고리 | 생성자명/메소드명/필드명 | 설명 |
|-----------|---------------------------------------------|----------------------------------------------------|
| 생성자 | PDAnnotationText(), PDAnnotationTextMarkup() |  |
| 메소드 | setContents(String contents) | 주석 내용 설정 |
|   | setRectangle(PDRectangle rect) | 주석 위치 및 크기 설정 |
|   | setColor(PDColor color) | 주석 색상 설정 |


<br><br><br>

### 15-5-2. PDF 생성 및 편집기 구현 

#### 15-5-2-1. 의존성 라이브러리 추가

**pom.xml 수정**

```xml
<dependency>
    <groupId>org.apache.pdfbox</groupId>
    <artifactId>pdfbox</artifactId>
    <version>2.0.24</version>
</dependency>
```

<br><br>

#### 15-5-2-2. Controller 작성

****

```java
package com.spring1.controller;

import com.spring1.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/")
    public String index() {
        return "pdf/pdfCreate";
    }

    @PostMapping("/generatePdf")
    public String generatePdf(@RequestParam("text") String text, Model model) {
        String pdfPath = pdfService.generatePdfFromText(text);
        model.addAttribute("pdfPath", pdfPath);
        return "pdf/pdfView";
    }

    @GetMapping("/editPdf")
    public void editPdf(HttpServletResponse response) throws IOException {
        pdfService.editPdf(response.getOutputStream());
    }
}
```

<br><br>

#### 15-5-2-3. Service 작성

```java
package com.spring1.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public String generatePdfFromText(String text) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText(text);
                contentStream.endText();
            }

            String filePath = "generated.pdf";
            document.save(filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void editPdf(ByteArrayOutputStream outputStream) throws IOException {
        // 여기에 PDF 편집 로직 추가
        // 예: 기존 PDF 파일 로드, 수정, 저장

        // 예시 로직: 기존 PDF에 이미지 추가
        PDDocument document = PDDocument.load(new File("path/to/your/document.pdf"));
        PDPage page = document.getPage(0);

        PDImageXObject pdImage = PDImageXObject.createFromFile("path/to/your/image.jpg", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
        contentStream.drawImage(pdImage, 100, 500);
        contentStream.close();

        document.save(outputStream);
        document.close();
    }
}
```

<br><br>

#### 15-5-2-4. View(JSP) 작성

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
	<h2>${company }</h2>
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/slang/form">비속어 필터링</a></li>
		<li><a href="${path2 }/slang/form2">비속어 필터링2</a></li>
		<li><a href="${path2 }/img/upload">이미지 업로드 스케일링</a></li>
		<li><a href="${path2 }/img/upload2">이미지 편집 업로드</a></li>
		<li><a href="${path2 }/crawl">웹크롤링</a></li>
		<li><a href="${path2 }/chart/charts">구글 차트</a></li>
		<li><a href="${path2 }/pdf/">PDF 생성 실습</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/pdf/pdfCreate.jsp 작성**

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <title>PDF Generator</title>
</head>
<body>
    <h1>Generate PDF</h1>
    <form action="generatePdf" method="post">
        <label for="text">Text to add to PDF:</label>
        <input type="text" id="text" name="text">
        <button type="submit">Generate PDF</button>
    </form>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/pdf/pdfView.jsp 작성**

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>PDF Generated</title>
</head>
<body>
    <h1>PDF Generated Successfully</h1>
    <hr>
    <p>Your PDF has been generated and saved at: <c:out value="${pdfPath}"/></p>
    <a href="${path2/pdf/pdfCreate">Go Back</a>
</body>
</html>
```

<br><br><hr><br><br>

<div id="15-6"></div>

## 15-6. 기상청 날씨 Api 구현

**https://www.data.go.kr/data/15084084/openapi.do 참조**

![기상청 날씨 API 바로가기](https://www.data.go.kr/data/15084084/openapi.do)


<br><br>

### 15-6-1. 자바 컬렉션과 JSON의 변환

- Api의 결과는 대부분 JSON 아니면, xml로 그 결과가 반환됩니다. 그러므로, JSON으로 응답이 오는 경우 자바의 컬렉션이나 객체로 파싱을 해주어야 합니다.
- 아래는 임의로 작성한 JSON 파싱 기능을 탑재한 클래스입니다.

<br><br>

#### 15-6-1-1. 자바 컬렉션와 JSON의 서로 변환할 수 있는 클래스 작성

**com.spring1.util.JsonUtils 클래스**

| 메소드 | 설명 |
|--------------------------------------------------------------|----------------------------------------------|
| String getJsonStringFromMap(Map<String, Object> map) | Map을 JSONString으로 변환 |
| String getJsonStringFromList(List<Map<String, Object>> list) | List<Map>을 JSONString으로 변환 |
| JSONObject getJsonObjectFromString(String jsonStr) | String을 JSONObject를 변환 |
| Map<String, Object> getMapFromJsonObject(JSONObject jsonObject) | JSONObject를 Map<String, String>으로 변환 |
| List<Map<String, Object>> getListMapFromJsonArray(JSONArray jsonArray) | JSONArray를 List<Map<String, String>>으로 변환 |

<br>

```java
package com.spring1.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON 관련된 유틸리티 클래스
 */
public class JsonUtils { 

	/**
	 * Map을 JSONString으로 변환
	 * 
	 * @param map
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String getJsonStringFromMap(Map<String, Object> map) {

		JSONObject json = new JSONObject();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			json.put(entry.getKey(), entry.getValue());
		}
		return json.toJSONString();
	}

	/**
	 * List<Map>을 JSONString으로 변환
	 * 
	 * @param list
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String getJsonStringFromList(List<Map<String, Object>> list) {

		JSONArray jsonArray = new JSONArray();

		for (Map<String, Object> map : list) {
			jsonArray.add(getJsonStringFromMap(map));
		}
		return jsonArray.toJSONString();
	}
	
	/**
	 * String을 JSONObject를 변환
	 * 
	 * @param jsonStr
	 * @return jsonObject
	 */
	public static JSONObject getJsonObjectFromString(String jsonStr) { 
	
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		
		try {
			jsonObject = (JSONObject) jsonParser.parse(jsonStr);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * JSONObject를 Map<String, String>으로 변환
	 * 
	 * @param jsonObject
	 * @return map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapFromJsonObject(JSONObject jsonObject) { 

		Map<String, Object> map = null;
		
		try {
			map = new ObjectMapper().readValue(jsonObject.toJSONString(), Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * JSONArray를 List<Map<String, String>>으로 변환
	 * 
	 * @param jsonArray
	 * @return list
	 */
	public static List<Map<String, Object>> getListMapFromJsonArray(JSONArray jsonArray) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (jsonArray != null) {
			int jsonSize = jsonArray.size();
			for (int i = 0; i < jsonSize; i++) {
				Map<String, Object> map = getMapFromJsonObject((JSONObject)jsonArray.get(i));
				list.add(map);
			}
		}

		return list;
	}
}
```

<br><br><br>

#### 15-6-1-2. 변환 클래스 테스트

**com.spring1.test.JsonUtilsTest 작성**

```java
package com.spring1.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.spring1.util.JsonUtils;

public class JsonUtilsTest {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HashMap<String, Object> map = null;	
		ArrayList<Map<String, Object>> list = null;
		
		String jsonStr = "{\"key01\":\"value01\",\"key02\":\"value02\",\"key03\":\"value03\",\"key04\":\"value04\",\"key05\":\"value05\"}";
		
		JSONObject jsonObject = null;	
		JSONArray jsonArray = null;
		
		//맵
		map = new HashMap<String, Object>();
		map.put("key01", "value01");
		map.put("key02", "value02");
		map.put("key03", "value03");
		map.put("key04", "value04");
		map.put("key05", "value05");
		
		//리스트
		list = new ArrayList<Map<String, Object>>(); 
		list.add(map);		
		list.add(map);		
		list.add(map);

		//JSON
		jsonObject = new JSONObject();
		jsonObject.put("key01", "value01");
		jsonObject.put("key02", "value02");
		jsonObject.put("key03", "value03");
		jsonObject.put("key04", "value04");
		jsonObject.put("key05", "value05");
		
		//JSON
		jsonArray = new JSONArray();
		jsonArray.add(jsonObject);
		jsonArray.add(jsonObject);
		jsonArray.add(jsonObject);
		
		testGetJsonStringFromMap(map);
		testGetJsonStringFromList(list);
		testGetJsonObjectFromString(jsonStr);
		testGetMapFromJsonObject(jsonObject);
		testGetListMapFromJsonArray(jsonArray);
	}
	
	public static void testGetJsonStringFromMap(HashMap<String, Object> map1) {
		String jsonStr = JsonUtils.getJsonStringFromMap(map1);			
		System.out.println("JsonStringFromMap(Map을 Json으로 변환) : " + jsonStr);
	}
	
	public static void testGetJsonStringFromList(ArrayList<Map<String, Object>> list1) {
		String jsonStr = JsonUtils.getJsonStringFromList(list1);		
		System.out.println("JsonStringFromList(List를 Json으로 변환) : " + jsonStr);
	}

	public static void testGetJsonObjectFromString(String jsonStr1) {
		JSONObject jsonObject = JsonUtils.getJsonObjectFromString(jsonStr1);		
		System.out.println("JsonObjectFromString(String을 JsonObject로 변환) : " + jsonObject);
	}

	public static void testGetMapFromJsonObject(JSONObject jsonObject1) {
		Map<String, Object> map = JsonUtils.getMapFromJsonObject(jsonObject1);
		System.out.println("MapFromJsonObject(JsonObject를 Map으로 변환) : " + map);
	}
	
	public static void testGetListMapFromJsonArray(JSONArray jsonArray1) {
		List<Map<String, Object>> list = JsonUtils.getListMapFromJsonArray(jsonArray1);		
		System.out.println("ListMapFromJsonArray(JsonArray를 List로 변환) : " + list);
	}
}
```

<br>

**실행결과**

```javascript
JsonStringFromMap(Map을 Json으로 변환) : {"key04":"value04","key03":"value03","key02":"value02","key01":"value01","key05":"value05"}
JsonStringFromList(List를 Json으로 변환) : ["{\"key04\":\"value04\",\"key03\":\"value03\",\"key02\":\"value02\",\"key01\":\"value01\",\"key05\":\"value05\"}","{\"key04\":\"value04\",\"key03\":\"value03\",\"key02\":\"value02\",\"key01\":\"value01\",\"key05\":\"value05\"}","{\"key04\":\"value04\",\"key03\":\"value03\",\"key02\":\"value02\",\"key01\":\"value01\",\"key05\":\"value05\"}"]
JsonObjectFromString(String을 JsonObject로 변환) : {"key04":"value04","key03":"value03","key02":"value02","key01":"value01","key05":"value05"}
MapFromJsonObject(JsonObject를 Map으로 변환) : {key04=value04, key03=value03, key02=value02, key01=value01, key05=value05}
ListMapFromJsonArray(JsonArray를 List로 변환) : [{key04=value04, key03=value03, key02=value02, key01=value01, key05=value05}, {key04=value04, key03=value03, key02=value02, key01=value01, key05=value05}, {key04=value04, key03=value03, key02=value02, key01=value01, key05=value05}]
```

<br><br><br>

#### 15-6-1-3. 기상청 날씨 Api Parsing하기

**1단계 파싱**

```java
    public JSONArray parsingData(JSONParser jsonParser) throws IOException, ParseException {
        JSONObject jsonObject = (JSONObject) jsonParser.parse(stream);

        if (jsonObject == null || !jsonObject.containsKey("response")) {
            return new JSONArray();
        }

        JSONObject response = (JSONObject) jsonObject.get("response");
        if (response == null || !response.containsKey("header")) {
            return new JSONArray();
        }

        JSONObject header = (JSONObject) response.get("header");
        String resultCode = (String) header.get("resultCode");
        if (!"00".equals(resultCode)) {
            return new JSONArray();
        }

        if (!response.containsKey("body")) {
            return new JSONArray();
        }

        JSONObject body = (JSONObject) response.get("body");
        if (body == null || !body.containsKey("items")) {
            return new JSONArray();
        }

        JSONObject items = (JSONObject) body.get("items");
        if (items == null || !items.containsKey("item")) {
            return new JSONArray();
        }
        
        JSONArray jsonArr = (JSONArray) items.get("item");
        
        log.info("Service : {}", jsonArr);
        return jsonArr;
    }
```

```javascript
{"response":{"header":{"resultCode":"00","resultMsg":"NORMAL_SERVICE"},"body":{"dataType":"JSON","items":{"item":[{"baseDate":"20240604","baseTime":"0600","category":"PTY","nx":60,"ny":127,"obsrValue":"0"},{"baseDate":"20240604","baseTime":"0600","category":"REH","nx":60,"ny":127,"obsrValue":"55"},{"baseDate":"20240604","baseTime":"0600","category":"RN1","nx":60,"ny":127,"obsrValue":"0"},{"baseDate":"20240604","baseTime":"0600","category":"T1H","nx":60,"ny":127,"obsrValue":"17.3"},{"baseDate":"20240604","baseTime":"0600","category":"UUU","nx":60,"ny":127,"obsrValue":"-1.3"},{"baseDate":"20240604","baseTime":"0600","category":"VEC","nx":60,"ny":127,"obsrValue":"47"},{"baseDate":"20240604","baseTime":"0600","category":"VVV","nx":60,"ny":127,"obsrValue":"-1.2"},{"baseDate":"20240604","baseTime":"0600","category":"WSD","nx":60,"ny":127,"obsrValue":"1.9"}]},"pageNo":1,"numOfRows":1000,"totalCount":8}}}
```

- 위 데이터를 아래와 같이 파싱할 수 있도록 한다.

```javascript
[{"obsrValue":"0","baseDate":"20240604","nx":60,"ny":127,"category":"PTY","baseTime":"0600"},{"obsrValue":"55","baseDate":"20240604","nx":60,"ny":127,"category":"REH","baseTime":"0600"},{"obsrValue":"0","baseDate":"20240604","nx":60,"ny":127,"category":"RN1","baseTime":"0600"},{"obsrValue":"17.3","baseDate":"20240604","nx":60,"ny":127,"category":"T1H","baseTime":"0600"},{"obsrValue":"-1.3","baseDate":"20240604","nx":60,"ny":127,"category":"UUU","baseTime":"0600"},{"obsrValue":"47","baseDate":"20240604","nx":60,"ny":127,"category":"VEC","baseTime":"0600"},{"obsrValue":"-1.2","baseDate":"20240604","nx":60,"ny":127,"category":"VVV","baseTime":"0600"},{"obsrValue":"1.9","baseDate":"20240604","nx":60,"ny":127,"category":"WSD","baseTime":"0600"}]
```

<br><br>

**2단계 파싱**

```java
    public Weather resultData(JSONArray jsonArray) throws IOException, ParseException {
        Weather dto = new Weather();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject item = (JSONObject) jsonArray.get(i);

            if (item == null) continue;

            String category = item.get("category").toString().toUpperCase();
            String obsrValue = item.get("obsrValue").toString();

            log.debug("Category: " + category + ", obsrValue: " + obsrValue);

            switch (category) {
                case "PTY":
                    dto.setPty(obsrValue);
                    break;
                case "REH":
                    dto.setReh(obsrValue);
                    break;
                case "RN1":
                    dto.setRn1(obsrValue);
                    break;
                case "T1H":
                    dto.setT1h(obsrValue);
                    break;
                case "UUU":
                    dto.setUuu(obsrValue);
                    break;
                case "VEC":
                    dto.setVec(obsrValue);
                    break;
                case "VVV":
                    dto.setVvv(obsrValue);
                    break;
                case "WSD":
                    dto.setWsd(obsrValue);
                    break;
                case "SKY":
                    dto.setSky(obsrValue);
                    break;
                default:
                    break;
            }
        }

        log.info("service: " + dto.toString());

        return dto;
    }
```

```javascript
[{"obsrValue":"0","baseDate":"20240604","nx":60,"ny":127,"category":"PTY","baseTime":"0600"},{"obsrValue":"55","baseDate":"20240604","nx":60,"ny":127,"category":"REH","baseTime":"0600"},{"obsrValue":"0","baseDate":"20240604","nx":60,"ny":127,"category":"RN1","baseTime":"0600"},{"obsrValue":"17.3","baseDate":"20240604","nx":60,"ny":127,"category":"T1H","baseTime":"0600"},{"obsrValue":"-1.3","baseDate":"20240604","nx":60,"ny":127,"category":"UUU","baseTime":"0600"},{"obsrValue":"47","baseDate":"20240604","nx":60,"ny":127,"category":"VEC","baseTime":"0600"},{"obsrValue":"-1.2","baseDate":"20240604","nx":60,"ny":127,"category":"VVV","baseTime":"0600"},{"obsrValue":"1.9","baseDate":"20240604","nx":60,"ny":127,"category":"WSD","baseTime":"0600"}]
```

- 위 데이터를 아래의 자바 객체 Weather로 파싱할 수 있도록 한다.

```java
package com.spring1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
	private String baseDate;
	private String baseTime;
	private String nx;
	private String ny;
	private String pty;
	private String rn1;
	private String reh;
	private String t1h;
	private String uuu;
	private String vec;
	private String vvv;
	private String sky;
	private String wsd;
}
```


<br><br><br>

#### 15-6-1-4. 기상청 날씨 Api 서비스

http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst/상세기능명(영문)

**상세기능**

| 상세기능명(영문) | 상세기능명(국문) |
|---------------------|-----------------------|
| getUltraSrtNcst | 초단기실황조회 |
| getUltraSrtFcst | 초단기예보조회 |
| getVilageFcst | 단기예보조회 |
| getFcstVersion | 예보버전조회 |


<br><br><br><br>

### 15-6-2. 초단기실황조회 상세기능명세

- 실황정보를 조회하기 위해 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 조회 조건으로 자료구분코드, 실황값, 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 정보를 조회하는 기능
- 서비스 URL : http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst

<br><br>

**JAVA 예시코드**

```java
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20210628", "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
```

<br><br><br>

#### 15-6-2-1. 요청변수(Request Parameter)

| 항목명(국문)      | 항목명(영문)   | 항목크기 | 항목구분 | 샘플데이터 | 항목설명                           |
|-------------------|----------------|----------|----------|------------|------------------------------------|
| 서비스키          | ServiceKey     | 4        | 필수     | -          | 공공데이터포털에서 받은 인증키     |
| 페이지 번호       | pageNo         | 4        | 필수     | 1          | 페이지번호                         |
| 한 페이지 결과 수 | numOfRows      | 4        | 필수     | 1000       | 한 페이지 결과 수                  |
| 응답자료형식      | dataType       | 4        | 옵션     | XML        | 요청자료형식(XML/JSON) Default: XML|
| 발표일자          | base_date      | 8        | 필수     | 20210628   | ‘21년 6월 28일 발표                |
| 발표시각          | base_time      | 4        | 필수     | 0600       | 06시 발표(정시단위)                |
| 예보지점 X 좌표   | nx             | 2        | 필수     | 55         | 예보지점의 X 좌표값                |
| 예보지점 Y 좌표   | ny             | 2        | 필수     | 127        | 예보지점의 Y 좌표값                |

<br><br>


#### 15-6-2-2. 출력결과(Response Element)

| 항목명(국문)      | 항목명(영문)  | 항목크기 | 항목구분 | 샘플데이터 | 항목설명                                   |
|-------------------|---------------|----------|----------|------------|--------------------------------------------|
| 결과코드          | resultCode    | 2        | 필수     | 00         | 결과코드                                   |
| 결과메시지        | resultMsg     | 50       | 필수     | OK         | 결과메시지                                 |
| 한 페이지 결과 수 | numOfRows     | 4        | 필수     | 10         | 한 페이지 결과 수                          |
| 페이지 번호       | pageNo        | 4        | 필수     | 1          | 페이지번호                                 |
| 전체 결과 수     | totalCount    | 4        | 필수     | 3          | 전체 결과 수                              |
| 데이터 타입      | dataType      | 4        | 필수     | XML        | 응답자료형식 (XML/JSON)                   |
| 발표일자         | baseDate      | 8        | 필수     | 20210628   | ‘21년 6월 28일 발표                       |
| 발표시각         | baseTime      | 6        | 필수     | 0600       | 06시 발표(매 정시)                        |
| 예보지점 X 좌표  | nx            | 2        | 필수     | 55         | 입력한 예보지점 X 좌표                     |
| 예보지점 Y 좌표  | ny            | 2        | 필수     | 127        | 입력한 예보지점 Y 좌표                     |
| 자료구분코드      | category      | 3        | 필수     | RN1        | 자료구분코드                              |
| 실황 값          | obsrValue     | 2        | 필수     | 0          | RN1, T1H, UUU, VVV, WSD 실수로 제공된 값 |


<br><br><br>

#### 15-6-2-3. JSON 출력 결과

```javascript
{
  "response": {
    "header": {
      "resultCode": "00",
      "resultMsg": "NORMAL_SERVICE"
    },
    "body": {
      "dataType": "JSON",
      "items": {
        "item": [
          {
            "baseDate": "20240604",
            "baseTime": "0600",
            "category": "PTY",
            "nx": 60,
            "ny": 127,
            "obsrValue": "0"
          },
          {
            "baseDate": "20240604",
            "baseTime": "0600",
            "category": "REH",
            "nx": 60,
            "ny": 127,
            "obsrValue": "55"
          },
          {
            "baseDate": "20240604",
            "baseTime": "0600",
            "category": "RN1",
            "nx": 60,
            "ny": 127,
            "obsrValue": "0"
          },
          {
            "baseDate": "20240604",
            "baseTime": "0600",
            "category": "T1H",
            "nx": 60,
            "ny": 127,
            "obsrValue": "17.3"
          },
          {
            "baseDate": "20240604",
            "baseTime": "0600",
            "category": "UUU",
            "nx": 60,
            "ny": 127,
            "obsrValue": "-1.3"
          },
          {
            "baseDate": "20240604",
            "baseTime": "0600",
            "category": "VEC",
            "nx": 60,
            "ny": 127,
            "obsrValue": "47"
          },
          {
            "baseDate": "20240604",
            "baseTime": "0600",
            "category": "VVV",
            "nx": 60,
            "ny": 127,
            "obsrValue": "-1.2"
          },
          {
            "baseDate": "20240604",
            "baseTime": "0600",
            "category": "WSD",
            "nx": 60,
            "ny": 127,
            "obsrValue": "1.9"
          }
        ]
      },
      "pageNo": 1,
      "numOfRows": 1000,
      "totalCount": 8
    }
  }
}
```

<br><br><br>

#### 15-6-2-4. 출력 결과 코드값

| 항목값 | 항목명     | 단위 | 압축bit수 |
|--------|------------|------|------------|
| T1H    | 기온       | ℃    | 10         |
| RN1    | 1시간 강수량 | 범주 (1 mm) | 8   |
| SKY    | 하늘상태   | 코드값 | 4        |
| UUU    | 동서바람성분 | m/s | 12         |
| VVV    | 남북바람성분 | m/s | 12         |
| REH    | 습도       | %    | 8          |
| PTY    | 강수형태   | 코드값 | 4        |
| LGT    | 낙뢰       | kA(킬로암페어) | 4  |
| VEC    | 풍향       | deg  | 10         |
| WSD    | 풍속       | m/s  | 10         |

<br><br><br><br>

### 15-6-3. 초단기예보조회 상세기능명세

- 초단기예보정보를 조회하기 위해 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 조회 조건으로 자료구분코드, 예보값, 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 정보를 조회하는 기능
- 서비스 요청 URL : http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst

<br>

**자바 예시코드**

```java
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20210628", "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0630", "UTF-8")); /*06시30분 발표(30분 단위)*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
```

<br><br><br>

#### 15-6-3-1. 요청변수(Request Parameter)

| 항목명(국문)       | 항목명(영문)  | 항목크기 | 항목구분 | 샘플데이터 | 항목설명                               |
|---------------------|---------------|----------|----------|------------|----------------------------------------|
| 서비스키             | ServiceKey   | 4        | 필수     | -          | 공공데이터포털에서 받은 인증키         |
| 페이지 번호          | pageNo        | 4        | 필수     | 1          | 페이지번호                             |
| 한 페이지 결과 수   | numOfRows     | 4        | 필수     | 1000       | 한 페이지 결과 수                      |
| 응답자료형식        | dataType     | 4        | 옵션     | XML        | 요청자료형식(XML/JSON) Default: XML  |
| 발표일자             | base_date    | 8        | 필수     | 20210628   | ‘21년 6월 28일 발표                  |
| 발표시각             | base_time    | 4        | 필수     | 0630       | 06시30분 발표(30분 단위)             |
| 예보지점 X 좌표     | nx           | 2        | 필수     | 55         | 예보지점 X 좌표값                     |
| 예보지점 Y 좌표     | ny           | 2        | 필수     | 127        | 예보지점 Y 좌표값                     |

<br><br><br>

#### 15-6-3-2. 출력결과(Response Element)

| 항목명(국문)       | 항목명(영문)  | 항목크기 | 항목구분 | 샘플데이터 | 항목설명                               |
|---------------------|---------------|----------|----------|------------|----------------------------------------|
| 결과코드             | resultCode    | 2        | 필수     | 00         | 결과코드                               |
| 결과메시지           | resultMsg     | 50       | 필수     | OK         | 결과메시지                             |
| 한 페이지 결과 수    | numOfRows     | 4        | 필수     | 10         | 한 페이지 결과 수                      |
| 페이지 번호          | pageNo        | 4        | 필수     | 1          | 페이지번호                             |
| 전체 결과 수        | totalCount    | 4        | 필수     | 3          | 전체 결과 수                          |
| 데이터 타입         | dataType      | 4        | 필수     | XML        | 응답자료형식 (XML/JSON)               |
| 발표일자            | baseDate      | 8        | 필수     | 20210628   | ‘21년 6월 28일 발표                  |
| 발표시각            | baseTime      | 4        | 필수     | 1200       | 12시00분 발표                         |
| 예보지점 X 좌표     | nx            | 2        | 필수     | 55         | 입력한 예보지점 X 좌표                |
| 예보지점 Y 좌표     | ny            | 2        | 필수     | 127        | 입력한 예보지점 Y 좌표                |
| 자료구분코드        | category      | 3        | 필수     | LGT        | 자료구분코드                          |
| 예측일자            | fcstDate      | 8        | 필수     | 20210628   | 예측일자(YYYYMMDD)                   |
| 예측시간            | fcstTime      | 4        | 필수     | 1200       | 예측시간(HH24MI)                      |
| 예보 값             | fcstValue     | 2        | 필수     | 0          | 예보 값                               |

<br><br><br>

#### 15-6-3-3. JSON 출력 결과


```javascript
{
  "response": {
    "header": {
      "resultCode": "0",
      "resultMsg": "NORMAL_SERVICE"
    },
    "body": {
      "dataType": "JSON",
      "items": {
        "item": {
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "T1H",
        "fcstDate": "20240604",
        "fcstTime": "0630",
        "fcstValue": "17.3"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "RN1",
        "fcstDate": "20240604",
        "fcstTime": "0630",
        "fcstValue": "0"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "UUU",
        "fcstDate": "20240604",
        "fcstTime": "0630",
        "fcstValue": "-1.3"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "VVV",
        "fcstDate": "20240604",
        "fcstTime": "0630",
        "fcstValue": "-1.2"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "REH",
        "fcstDate": "20240604",
        "fcstTime": "0630",
        "fcstValue": "55"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "PTY",
        "fcstDate": "20240604",
        "fcstTime": "0630",
        "fcstValue": "55"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "LGT",
        "fcstDate": "20240604",
        "fcstTime": "0630",
        "fcstValue": "0"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "VEC",
        "fcstDate": "20240604",
        "fcstTime": "0630",
        "fcstValue": "47"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "WSD",
        "fcstDate": "20240604",
        "fcstTime": "0630",
        "fcstValue": "1.9"
        "nx": 60,
        "ny": 127
        }
      },
      "numOfRows": 10,
      "pageNo": 1,
      "totalCount": 60
    }
  }
}
```

<br><br><br>

#### 15-6-3-4. 출력 결과 코드값

| 항목값 | 항목명     | 단위 | 압축bit수 |
|--------|------------|------|------------|
| T1H    | 기온       | ℃    | 10         |
| RN1    | 1시간 강수량 | 범주 (1 mm) | 8   |
| SKY    | 하늘상태   | 코드값 | 4        |
| UUU    | 동서바람성분 | m/s | 12         |
| VVV    | 남북바람성분 | m/s | 12         |
| REH    | 습도       | %    | 8          |
| PTY    | 강수형태   | 코드값 | 4        |
| LGT    | 낙뢰       | kA(킬로암페어) | 4  |
| VEC    | 풍향       | deg  | 10         |
| WSD    | 풍속       | m/s  | 10         |

<br><br><br><br>

### 15-6-4. 단기예보조회 상세기능명세

- 단기예보 정보를 조회하기 위해 발표일자, 발표시각, 예보지점 X좌표, 예보지점 Y 좌표의 조회 조건으로 발표일자, 발표시각, 자료구분문자, 예보 값, 예보일자, 예보시각, 예보지점 X 좌표, 예보지점 Y 좌표의 정보를 조회하는 기능
- 서비스 요청URL : http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst

<br><br>

**자바 예시코드**

```java
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20210628", "UTF-8")); /*‘21년 6월 28일발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*05시 발표*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
```

<br><br><br>

#### 15-6-4-1. 요청변수(Request Parameter)

| 항목명(국문)       | 항목명(영문)  | 항목크기 | 항목구분 | 샘플데이터 | 항목설명                               |
|---------------------|---------------|----------|----------|------------|----------------------------------------|
| 서비스키            | ServiceKey    | 4        | 필수     | -          | 공공데이터포털에서 받은 인증키         |
| 페이지 번호         | pageNo        | 4        | 필수     | 1          | 페이지번호                             |
| 한 페이지 결과 수  | numOfRows     | 4        | 필수     | 1000       | 한 페이지 결과 수                      |
| 응답자료형식       | dataType      | 4        | 옵션     | XML        | 요청자료형식(XML/JSON) Default: XML  |
| 발표일자           | base_date     | 8        | 필수     | 20210628   | ‘21년 6월 28일 발표                  |
| 발표시각           | base_time     | 4        | 필수     | 0500       | 05시 발표                            |
| 예보지점 X 좌표    | nx            | 2        | 필수     | 55         | 예보지점의 X 좌표값                   |
| 예보지점 Y 좌표    | ny            | 2        | 필수     | 127        | 예보지점의 Y 좌표값                   |

<br><br><br>

#### 15-6-4-2. 출력결과(Response Element)

| 항목명(국문)        | 항목명(영문)  | 항목크기 | 항목구분 | 샘플데이터 | 항목설명                               |
|--------------------|---------------|----------|----------|------------|----------------------------------------|
| 결과코드           | resultCode    | 2        | 필수     | 00         | 결과코드                               |
| 결과메시지         | resultMsg     | 50       | 필수     | OK         | 결과메시지                             |
| 한 페이지 결과 수  | numOfRows     | 4        | 필수     | 50         | 한 페이지 결과 수                      |
| 페이지 번호        | pageNo        | 4        | 필수     | 1          | 페이지번호                             |
| 전체 결과 수      | totalCount    | 4        | 필수     | 3          | 전체 결과 수                          |
| 데이터 타입       | dataType      | 4        | 필수     | XML        | 응답자료형식 (XML/JSON)               |
| 발표일자           | baseDate      | 8        | 필수     | 20210628   | ‘21년 6월 28일 발표                  |
| 발표시각           | baseTime      | 6        | 필수     | 0500       | 05시 발표                            |
| 예보일자           | fcstDate      | 8        | 필수     | 20210628   | ‘21년 6월 28일 예보                  |
| 예보시각           | fcstTime      | 4        | 필수     | 0600       | 6시 예보                             |
| 자료구분문자       | category      | 3        | 필수     | TMP        | 자료구분코드                         |
| 예보 값            | fcstValue     | 2        | 필수     | 21         | * 하단 코드값 정보 참조              |
| 예보지점 X 좌표    | nx            | 2        | 필수     | 55         | 입력한 예보지점 X 좌표               |
| 예보지점 Y 좌표    | ny            | 2        | 필수     | 127        | 입력한 예보지점 Y 좌표               |

<br><br><br>

#### 15-6-4-3. JSON 출력 결과

```javascript
{
  "response": {
    "header": {
      "resultCode": "0",
      "resultMsg": "NORMAL_SERVICE"
    },
    "body": {
      "dataType": "JSON",
      "items": {
        "item": {
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "T1H",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": "17.3"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "RN1",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": "0"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "SKY",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": ""
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "UUU",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": "-1.3"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "VVV",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": "-1.2"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "REH",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": "55"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "PTY",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": "55"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "LGT",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": "0"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "VEC",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": "47"
        "nx": 60,
        "ny": 127
        },
        {
        "baseDate": "20240604",
        "baseTime": "0600",
        "category": "WSD",
        "fcstDate": "20240604",
        "fcstTime": "0600",
        "fcstValue": "1.9"
        "nx": 60,
        "ny": 127
        }
      },
      "numOfRows": 10,
      "pageNo": 1,
      "totalCount": 742
    }
  }
}
```

<br><br><br>

#### 15-6-4-4. 출력 결과 코드값

| 항목값 | 항목명     | 단위 | 압축bit수 |
|----------|--------|------------|------|------------|
| T1H    | 기온       | ℃    | 10         |
| RN1    | 1시간 강수량 | 범주 (1 mm) | 8   |
| SKY    | 하늘상태   | 코드값 | 4        |
| UUU    | 동서바람성분 | m/s | 12         |
| VVV    | 남북바람성분 | m/s | 12         |
| REH    | 습도       | %    | 8          |
| PTY    | 강수형태   | 코드값 | 4        |
| LGT    | 낙뢰       | kA(킬로암페어) | 4  |
| VEC    | 풍향       | deg  | 10         |
| WSD    | 풍속       | m/s  | 10         |

<br><br><br><br>

### 15-6-5. 예보버전조회 상세기능명세

- 단기예보정보조회서비스 각각의 오퍼레이션(초단기실황, 초단기예보, 단기예보)들의 수정된 예보 버전을 파악하기 위해 예보버전을 조회하는 기능
- 서비스 요청 URL : http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getFcstVersion

<br><br>

**자바 예시 코드**

```java
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getFcstVersion"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("ftype","UTF-8") + "=" + URLEncoder.encode("ODAM", "UTF-8")); /*파일구분 -ODAM: 동네예보실황 -VSRT: 동네예보초단기 -SHRT: 동네예보단기*/
        urlBuilder.append("&" + URLEncoder.encode("basedatetime","UTF-8") + "=" + URLEncoder.encode("202106280800", "UTF-8")); /*각각의 base_time 로 검색*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
```

<br><br><br>

#### 15-6-5-1. 요청변수(Request Parameter)

| 항목명(국문)      | 항목명(영문)      | 항목크기 | 항목구분 | 샘플데이터       | 항목설명                                |
|------------------|------------------|----------|----------|------------------|-----------------------------------------|
| 서비스키         | ServiceKey       | 4        | 필수     | -                | 공공데이터포털에서 받은 인증키        |
| 페이지 번호      | pageNo           | 4        | 필수     | 1                | 페이지번호                            |
| 한 페이지 결과 수| numOfRows        | 4        | 필수     | 1000             | 한 페이지 결과 수                    |
| 응답자료형식     | dataType         | 4        | 옵션     | XML              | 요청자료형식(XML/JSON) Default: XML |
| 파일구분        | ftype            | 5        | 필수     | ODAM             | 파일구분 - ODAM: 동네예보실황 -VSRT: 동네예보초단기 -SHRT: 동네예보단기 |
| 발표일시분      | basedatetime     | 10       | 필수     | 202106280800     | 각각의 base_time으로 검색             |

<br><br><br>

#### 15-6-5-2. 출력결과(Response Element)

| 항목명(국문)       | 항목명(영문)   | 항목크기 | 항목구분 | 샘플데이터           | 항목설명                                          |
|-------------------|---------------|----------|----------|-----------------------|--------------------------------------------------|
| 결과코드          | resultCode    | 2        | 필수     | 00                    | 결과코드                                          |
| 결과메시지        | resultMsg     | 50       | 필수     | OK                    | 결과메시지                                        |
| 한 페이지 결과 수 | numOfRows     | 4        | 필수     | 10                    | 한 페이지 결과 수                                 |
| 페이지 번호       | pageNo        | 4        | 필수     | 1                     | 페이지번호                                       |
| 전체 결과 수     | totalCount    | 4        | 필수     | 3                     | 전체 결과 수                                     |
| 데이터 타입      | dataType      | 4        | 필수     | XML                   | 응답자료형식 (XML/JSON)                          |
| 파일버전         | version       | 4        | 필수     | 20210628092217        | 파일버전 정보 - 파일 생성 시간                    |
| 파일구분         | filetype      | 5        | 필수     | ODAM                  | 파일구분 - ODAM: 초단기실황 -VSRT: 초단기예보 -SHRT: 단기예보 |

<br><br><br>

#### JSON 결과

```javascript
{
  "response": {
    "header": {
      "resultCode": "0",
      "resultMsg": "NORMAL_SERVICE"
    },
    "body": {
      "dataType": "JSON",
      "items": {
        "item": {
          "filetype": "ODAM",
          "version": "20240604063217"
        }
      },
      "numOfRows": 10,
      "pageNo": 1,
      "totalCount": 1
    }
  }
}
```

<br><br><br>

#### 15-6-5-4. 출력 결과 코드값

| 예보구분 | 항목값 | 항목명     | 단위 | 압축bit수 |
|----------|--------|------------|------|------------|
| 단기예보 | POP    | 강수확률   | %    | 8          |
|          | PTY    | 강수형태   | 코드값 | 4        |
|          | PCP    | 1시간 강수량 | 범주 (1 mm) | 8   |
|          | REH    | 습도       | %    | 8          |
|          | SNO    | 1시간 신적설 | 범주(1 cm) | 8   |
|          | SKY    | 하늘상태   | 코드값 | 4        |
|          | TMP    | 1시간 기온 | ℃    | 10         |
|          | TMN    | 일 최저기온 | ℃    | 10        |
|          | TMX    | 일 최고기온 | ℃    | 10        |
|          | UUU    | 풍속(동서성분) | m/s | 12      |
|          | VVV    | 풍속(남북성분) | m/s | 12      |
|          | WAV    | 파고       | M    | 8          |
|          | VEC    | 풍향       | deg  | 10         |
|          | WSD    | 풍속       | m/s  | 10         |
| 초단기실황 | T1H    | 기온       | ℃    | 10         |
|           | RN1    | 1시간 강수량 | mm   | 8          |
|           | UUU    | 동서바람성분 | m/s | 12         |
|           | VVV    | 남북바람성분 | m/s | 12         |
|           | REH    | 습도       | %    | 8          |
|           | PTY    | 강수형태   | 코드값 | 4        |
|           | VEC    | 풍향       | deg  | 10         |
|           | WSD    | 풍속       | m/s  | 10         |
| 초단기예보 | T1H    | 기온       | ℃    | 10         |
|           | RN1    | 1시간 강수량 | 범주 (1 mm) | 8   |
|           | SKY    | 하늘상태   | 코드값 | 4        |
|           | UUU    | 동서바람성분 | m/s | 12         |
|           | VVV    | 남북바람성분 | m/s | 12         |
|           | REH    | 습도       | %    | 8          |
|           | PTY    | 강수형태   | 코드값 | 4        |
|           | LGT    | 낙뢰       | kA(킬로암페어) | 4  |
|           | VEC    | 풍향       | deg  | 10         |
|           | WSD    | 풍속       | m/s  | 10         |

<br><br><br><br>

### 15-6-6. 기상청 날씨 Api 구현 전체 코드 

#### 15-6-6-1. DTO 클래스

**com.spring1.dto.Weather 작성**

```java
package com.spring1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
	public final static String serviceKey = "일반 인증키 (Encoding)";
	private String baseDate;
	private String baseTime;
	private String nx;
	private String ny;
	private String pty;
	private String rn1;
	private String reh;
	private String t1h;
	private String uuu;
	private String vec;
	private String vvv;
	private String sky;
	private String wsd;
}
```

<br><br><br>

#### 15-6-6-2. 서비스 구현

**com.spring1.service.WeatherService 작성**

```java
package com.spring1.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spring1.dto.Weather;

@Service
public class WeatherService {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    public String loadData(String serviceKey, String baseDate, String nx, String ny) throws IOException {
    	log.info("Service serviceKey : {}", serviceKey);
    	log.info("Service baseDate : {}", baseDate);
    	log.info("Service nx : {}", nx);
    	log.info("Service ny : {}", ny);
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*초단기실황조회 URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*‘24년 6월 3일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        log.info("Response code: {}", conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        log.info("Service Before Parsing {}", sb.toString());
        return sb.toString();
    }

    public JSONArray parsingData(String serviceKey, String baseDate, String nx, String ny) throws IOException, ParseException {
        String stream = this.loadData(serviceKey, baseDate, nx, ny);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(stream);

        if (jsonObject == null || !jsonObject.containsKey("response")) {
            return new JSONArray();
        }

        JSONObject response = (JSONObject) jsonObject.get("response");
        if (response == null || !response.containsKey("header")) {
            return new JSONArray();
        }

        JSONObject header = (JSONObject) response.get("header");
        String resultCode = (String) header.get("resultCode");
        if (!"00".equals(resultCode)) {
            return new JSONArray();
        }

        if (!response.containsKey("body")) {
            return new JSONArray();
        }

        JSONObject body = (JSONObject) response.get("body");
        if (body == null || !body.containsKey("items")) {
            return new JSONArray();
        }

        JSONObject items = (JSONObject) body.get("items");
        if (items == null || !items.containsKey("item")) {
            return new JSONArray();
        }
        
        JSONArray jsonArr = (JSONArray) items.get("item");
        
        log.info("Service : {}", jsonArr);
        return jsonArr;
    }

    public Weather resultData(String serviceKey, String baseDate, String nx, String ny) throws IOException, ParseException {
        JSONArray jsonArray = this.parsingData(serviceKey, baseDate, nx, ny);

        Weather dto = new Weather();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject item = (JSONObject) jsonArray.get(i);

            if (item == null) continue;

            String category = item.get("category").toString().toUpperCase();
            String obsrValue = item.get("obsrValue").toString();

            log.debug("Category: " + category + ", obsrValue: " + obsrValue);

            switch (category) {
                case "PTY":
                    dto.setPty(obsrValue);
                    break;
                case "REH":
                    dto.setReh(obsrValue);
                    break;
                case "RN1":
                    dto.setRn1(obsrValue);
                    break;
                case "T1H":
                    dto.setT1h(obsrValue);
                    break;
                case "UUU":
                    dto.setUuu(obsrValue);
                    break;
                case "VEC":
                    dto.setVec(obsrValue);
                    break;
                case "VVV":
                    dto.setVvv(obsrValue);
                    break;
                case "WSD":
                    dto.setWsd(obsrValue);
                    break;
                case "SKY":
                    dto.setSky(obsrValue);
                    break;
                default:
                    break;
            }
        }

        log.info("service: " + dto.toString());

        return dto;
    }
}
```

<br><br><br>

#### 15-6-6-3. 컨트롤러 구현

**com.spring1.controller.WeatherController 작성**

```java
package com.spring1.controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring1.dto.Weather;
import com.spring1.service.WeatherService;

@Controller
public class WeatherController {

	private static final Logger log = LoggerFactory.getLogger(WeatherController.class);
	
    @Autowired
    private WeatherService wService;

    @GetMapping("/weather/home")
    public String weatherHome(Model model) {
        return "weather/home";
    }

    @PostMapping("/weather/result")
    public String resultData(@RequestParam("baseDate") String baseDate, @RequestParam("nx") String nx, @RequestParam("ny") String ny, Model model) {
    	
    	log.info("baseDate : {}", baseDate);
    	log.info("nx : {}", nx);
    	log.info("ny : {}", ny);
    	
        try {
            if (baseDate == null || baseDate.isEmpty() || nx == null || nx.isEmpty() || ny == null || ny.isEmpty()) {
                model.addAttribute("error", "Invalid input data");
                return "error";
            }

            Weather w = wService.resultData(Weather.serviceKey, baseDate, nx, ny);
            
            log.info("controller : "+w.toString());
            
            model.addAttribute("weather", w);
            return "weather/result";
        } catch (IOException | ParseException e) {
            model.addAttribute("error", "Error processing request: " + e.getMessage());
            return "weather/home";
        }
    }
}
```

<br><br><br>

#### 15-6-6-4. View(JSP) 구현

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
	<h2>${company }</h2>
	<p>${msg }</p>	
	<hr>
	<ul>
		<li><a href="${path2 }/slang/form">비속어 필터링</a></li>
		<li><a href="${path2 }/slang/form2">비속어 필터링2</a></li>
		<li><a href="${path2 }/img/upload">이미지 업로드 스케일링</a></li>
		<li><a href="${path2 }/img/upload2">이미지 편집 업로드</a></li>
		<li><a href="${path2 }/crawl">웹크롤링</a></li>
		<li><a href="${path2 }/chart/charts">구글 차트</a></li>
		<li><a href="${path2 }/pdf/">PDF 생성 실습</a></li>
		<li><a href="${path2 }/weather/home">날씨 예보</a></li>
	</ul>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/weather/home.jsp 작성**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>날씨 예보</title>
	<script>
	function sendLocation() {
	    if (navigator.geolocation) {
	        navigator.geolocation.getCurrentPosition(function(position) {
	            var grid = dfs_xy_conv("toXY", position.coords.latitude, position.coords.longitude);
	            document.getElementById('nx').value = grid.x;
	            document.getElementById('ny').value = grid.y;
	            
	            const now = new Date();
	            const year = now.getFullYear();
	            const month = ('0' + (now.getMonth() + 1)).slice(-2);
	            const day = ('0' + now.getDate()).slice(-2);
	            const dateStr = year + month + day;
	            
	            document.getElementById('baseDate').value = dateStr;
	        }, function(error) {
	            console.error("Error Code = " + error.code + " - " + error.message);
	        });
	    } else {
	        alert("Geolocation is not supported by this browser.");
	        return false;
	    }
	}
	
	function dfs_xy_conv(code, v1, v2) {
	    var RE = 6371.00877;
	    var GRID = 5.0;
	    var SLAT1 = 30.0;
	    var SLAT2 = 60.0;
	    var OLON = 126.0;
	    var OLAT = 38.0;
	    var XO = 43;
	    var YO = 136;
	    
	    var DEGRAD = Math.PI / 180.0;
	    var RADDEG = 180.0 / Math.PI;
	
	    var re = RE / GRID;
	    var slat1 = SLAT1 * DEGRAD;
	    var slat2 = SLAT2 * DEGRAD;
	    var olon = OLON * DEGRAD;
	    var olat = OLAT * DEGRAD;
	
	    var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	    sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
	    var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	    sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
	    var ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
	    ro = re * sf / Math.pow(ro, sn);
	    var rs = {};
	    if (code == "toXY") {
	        rs['lat'] = v1;
	        rs['lng'] = v2;
	        var ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5);
	        ra = re * sf / Math.pow(ra, sn);
	        var theta = v2 * DEGRAD - olon;
	        if (theta > Math.PI) theta -= 2.0 * Math.PI;
	        if (theta < -Math.PI) theta += 2.0 * Math.PI;
	        theta *= sn;
	        rs['x'] = Math.floor(ra * Math.sin(theta) + XO + 0.5);
	        rs['y'] = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
	    }
	    return rs;
	}
	</script>
</head>
<body>
    <h1>Send Current Date and Location</h1>
    <hr>
    <form id="locationForm" action="${path2}/weather/result" method="post">
        <input type="hidden" id="nx" name="nx">
        <input type="hidden" id="ny" name="ny">
        <input type="hidden" id="baseDate" name="baseDate">
        <button type="submit">Send Location</button>
    </form>
    <script>
    sendLocation();
    </script>
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/weather/result.jsp 작성**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>날씨 결과</title>
</head>
<body>
    <h1>날씨 예보 결과</h1>
    <ul>
        <li>기온(℃) : ${weather.t1h}</li>
        <li>1시간 강수량 : ${weather.rn1}</li>
        <li>습도(%) : ${weather.reh}</li>
        <li>풍향(deg) : ${weather.vec}</li>
        <li>풍속(m/s) : ${weather.wsd}</li>
    </ul>
    <a href="${path2}">홈으로</a>
</body>
</html>
```

<br><br>


<br><br><hr><br><br>

