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
    <title>Google Sheets Integration</title>
</head>
<body>
<h2>Google Sheets Integration</h2>

<form action="${path2 }/sheets/save" method="post">
    <label for="spreadsheetId">Spreadsheet ID:</label>
    <input type="text" id="spreadsheetId" name="spreadsheetId"><br>
    <label for="range">Range:</label>
    <input type="text" id="range" name="range"><br>
    <label for="values">Values (comma separated):</label>
    <input type="text" id="values" name="values"><br>
    <button type="submit">Save</button>
</form>

<form action="${path2 }/sheets/load" method="post">
    <label for="spreadsheetId">Spreadsheet ID:</label>
    <input type="text" id="spreadsheetId" name="spreadsheetId"><br>
    <label for="range">Range:</label>
    <input type="text" id="range" name="range"><br>
    <button type="submit">Load</button>
</form>

<h3>Loaded Data</h3>
<table border="1">
    <c:forEach var="row" items="${values}">
        <tr>
            <c:forEach var="cell" items="${row}">
                <td>${cell}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>