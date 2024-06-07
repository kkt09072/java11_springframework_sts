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
    <title>Compose Message</title>
</head>
<body>
    <h1>Compose Message</h1>
    <form action="${path2}/messages/compose" method="post">
        <label for="receiver">To:</label>
        <input type="text" id="receiver" name="receiver"><br>
        <label for="subject">Subject:</label>
        <input type="text" id="subject" name="subject"><br>
        <label for="body">Message:</label>
        <textarea id="body" name="body"></textarea><br>
        <input type="submit" value="Send">
    </form>
</body>
</html>