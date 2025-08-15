<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor=cyan>
<center>
 <h1> Online Shopping !</h1>
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