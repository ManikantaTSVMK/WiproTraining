<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor=cyan>

<center>
 <h1> Online Shopping !</h1><br>
 <p><b>${storename}</b></p>
 <% 
 		Cookie[] cookies = request.getCookies();
			String uname = null;
			if(cookies!=null)
			{
				for(Cookie co : cookies)
				{
					if("username".equals(co.getName()))
					uname = co.getValue();
					
					
				}
			}
%>
<%

%>

 Hi! <%=uname %> <p><b>${greet}</b></p>
  <form action="AddToCartServlet" method="post">
   <select name="product">
  
  			<option value="Laptop">Laptop</option>
  			<option value="Mobile">Mobile</option>
  			<option value="Speaker">Speaker</option>
  			<option value="Headphone">Headphone</option>
  			
  </select>
  
  
  	<input type ="submit" value="Add to Cart"> 
  </form>
  <br>
  <a href= "ViewCartServlet">View Cart</a>
  </center>
</body>
</html>
