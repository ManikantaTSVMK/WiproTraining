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
<form action="loanForm.jsp" method=post>
<center>
<h1> Login</h1>
User Name : <input type=text name="username"> <br><br>
Password  : <input type=password name="password"><br><br>
<button type=submit> Login </button>
</center>
</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>