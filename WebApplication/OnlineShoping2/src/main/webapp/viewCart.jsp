<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- To do a server side programming we have scriplets -->
<!--  some implicit objects we are getting in jsp  -->
<h5>
Following table lists out the nine Implicit Objects that JSP supports − <br>

No. Object & Description <br>
1	request  This is the HttpServletRequest object associated with the request.<br>
2	response This is the HttpServletResponse object associated with the response to the client.<br>
3	out      This is the PrintWriter object used to send output to the client.<br>
4   session  This is the HttpSession object associated with the request.<br>
5	application This is the ServletContext object associated with the application context.<br>
6	config     This is the ServletConfig object associated with the page.<br>
7	pageContext This encapsulates use of server-specific features like higher performance JspWriters.<br>
8	page      This is simply a synonym for this, and is used to call the methods defined by the translated servlet class.<br>
9	Exception The Exception object allows the exception data to be accessed by designated JSP.<br>


</h6>
<h3>Use of scriptlet in JSP to do java programming</h3>
        <% int a=3;
           int b=4;
           int c=5;
           
           out.println("a is: " +a+ "<br>" + "b is:" +b+ "<br>" + "c is:" +c+ "<br>");
           out.println("Multiplication gives: " + a*b*c + "<br>");
           out.println("Addition gives:" + (a+b+c));
        %>
     
    <br>
    <br>
    <br>
    <hr> </hr>   
        <%
        
       java.util.ArrayList<String> cart =  (java.util.ArrayList<String>)request.getAttribute("cartItems");
        
        if(cart == null || cart.isEmpty())
        {
        	out.println("Your cart is empty");
        }
        else
        {
        	
        	for(String item : cart)
        	{
        		out.println("<p>" + item + "</p>");
        	}
        }
        
        %>
<br>
<br>
<br>
<a href = "index.jsp">Continue Shopping!</a> &nbsp; &nbsp; &nbsp;
<a href = "LogoutServlet">Logout</a>

</body>
</html>
