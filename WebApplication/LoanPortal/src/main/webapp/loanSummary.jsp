<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="com.bank.LoanBean" %>
<%@ include file="header.jsp" %>

<jsp:useBean id="loan" class="com.bank.LoanBean" scope="session" />

<h3>Loan Application Summary</h3>
<p>Name: <jsp:getProperty name="loan" property="customerName" /></p>
<p>Loan Amount: <jsp:getProperty name="loan" property="loanAmount" /></p>
<p>Tenure: <jsp:getProperty name="loan" property="tenure" /> months</p>
<p>EMI: ₹<jsp:getProperty name="loan" property="emi" /></p>

<%@ include file="footer.jsp" %>
</body>
</html>