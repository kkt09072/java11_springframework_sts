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
	<hr>
	<ul>
		<li><a href="${path2 }/chatbot/home">Chat GPT</a></li>
		<li><a href="${path2 }/schedule/reservationForm">예약하기</a></li>
        <li><a href="${path2 }/sheets">스프레드시트 작업하기</a></li>
        <li><a href="${path2 }/chat">멀티채팅방</a></li>
        <li><a href="${path2 }/messages/compose">쪽지 보내기</a></li>
        <li><a href="${path2 }/messenger/compose">RabbitMQ 메신저</a></li>
        <li><a href="${path2 }/fileboard/list">파일 자료실</a></li>
	</ul>
</body>
</html>