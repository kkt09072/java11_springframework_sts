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
    <title>Message</title>
</head>
<body>
    <h1>Message</h1>
    <p><strong>From:</strong> ${message.sender}</p>
    <p><strong>To:</strong> ${message.receiver}</p>
    <p><strong>Subject:</strong> ${message.subject}</p>
    <p>${message.body}</p>
    <form action="${path2}/messages/delete/${message.id}" method="post">
        <input type="submit" value="Delete">
    </form>
</body>
</html>