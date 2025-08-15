<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Use of scriptlet in JSP</h3>
        <% int a=3;
           int b=4;
           int c=5;
           out.println("a is: " +a+ "<br>" + "b is:" +b+ "<br>" + "c is:" +c+ "<br>");
           out.println("Multiplication gives: " + a*b*c + "<br>");
           out.println("Addition gives:" + (a+b+c));
        %>
 <hr><hr> 
 <h3>Use of Expressions Example-1 in JSP</h3>
<% String s1="expressions: done by kajal"; %>
<% out.print(s1); %> </br>
<%=s1.toUpperCase() %>

 <hr><hr> 
<h3>Use of Expressions Example-2 in JSP</h3>
<% int n1=10, n2=20; %>
<% out.print("Addition of numbers is ="); %>
<%=n1+n2 %>


 <hr><hr> 
 
 <h3>Use of Declaration in JSP</h3>
        <%! int num1=2, num2=3, n=0; %>
        <% n=num1+num2+1;
      out.println("The number after adding declared variables is " + n);
        %>

</body>
</html>