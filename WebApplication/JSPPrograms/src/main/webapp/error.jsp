<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Oops ! user name you have entered as  <%= application.getAttribute("name") %>  is not correct Try Again! </h1>

<a href="index.html"> Back to login</a>

</body>
</html>