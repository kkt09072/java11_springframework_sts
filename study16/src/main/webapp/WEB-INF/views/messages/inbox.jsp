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