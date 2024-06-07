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