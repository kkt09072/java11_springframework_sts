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
    <title>Spring Chat Application</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/sockjs/1.1.4/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body>
    <h1>Chat Room</h1>
    <div id="messages"></div>
    <input type="text" id="messageInput" placeholder="Enter message"/>
    <button onclick="sendMessage()">Send</button>

    <script>
        var socket = new SockJS('/chat');
        var stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/roomId', function (message) {
                showMessage(JSON.parse(message.body).content);
            });
        });

        function sendMessage() {
            var messageInput = document.getElementById('messageInput');
            stompClient.send("${path2}/chat/roomId/sendMessage", {}, JSON.stringify({'content': messageInput.value}));
            messageInput.value = '';
        }

        function showMessage(message) {
            var messages = document.getElementById('messages');
            var messageElement = document.createElement('div');
            messageElement.textContent = message;
            messages.appendChild(messageElement);
        }
    </script>
</body>
</html>