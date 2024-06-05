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
    <title>ChatBot</title>
</head>
<body>
    <h1>ChatBot</h1>
    <form method="post" action="${path2}/chatbot/chat">
        <label for="prompt">Enter your prompt:</label>
        <input type="text" id="prompt" name="prompt" required/>
        <button type="submit">Send</button>
    </form>
    <h2>Response:</h2>
    <p>${response}</p>
</body>
</html>