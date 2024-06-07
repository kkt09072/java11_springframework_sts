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