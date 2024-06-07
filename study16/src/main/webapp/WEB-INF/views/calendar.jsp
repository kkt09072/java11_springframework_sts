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