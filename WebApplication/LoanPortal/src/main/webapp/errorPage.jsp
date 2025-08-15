<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page isErrorPage="true" %>
<%@ include file="header.jsp" %>
<h3 style="color:red;">Oops! An error occurred.</h3>
<p>Error Details: <%= exception.getMessage() %></p> 
<%@ include file="footer.jsp" %>
</body>
</html>