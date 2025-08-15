<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! int count =0;
public String message()
{
return "Welcome to jsp implicit object demo";	
}

%>

<jsp:include page="header.jsp"></jsp:include>

<%= request.getParameter("txt1") %>
<%=  message() %>

<% String uname =  request.getParameter("txt1");
	if(uname.equals("Niti"))
		{
		    session.setAttribute("username", uname);
			response.sendRedirect("home.jsp");
		}
	
	
	
	else
	{
		application.setAttribute("name", uname);
		
		response.sendRedirect("error.jsp");
	}
%>


</body>
</html>