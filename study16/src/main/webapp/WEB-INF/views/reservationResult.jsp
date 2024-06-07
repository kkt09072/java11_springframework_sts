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