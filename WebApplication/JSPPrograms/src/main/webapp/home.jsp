<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<h1> Redirected to Home Page</h1>

Welcome!   <%=session.getAttribute("username") %>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>