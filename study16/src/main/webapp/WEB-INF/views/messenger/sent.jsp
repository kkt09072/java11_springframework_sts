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