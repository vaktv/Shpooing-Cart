<%@page import="mypack.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    User auth=(User)request.getSession().getAttribute("auth");
    if(auth!=null){
    	 request.setAttribute("auth", auth);
    	 response.sendRedirect("index.jsp");
    }
    
    ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");

    if(cart_list!=null){
    	
    	request.setAttribute("cart_list", cart_list);
    	
    }
    %>
    
<!DOCTYPE html>
<html>
<head>
<title>login page</title>
<%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<%@include file="includes/footer.jsp" %>

<div class="container">
	<div class="card w-50 mx-auto my-5">
		<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">

				<div class="form-group">
				<label>Email Adddress</label>
				<input type="email" class="form-control" name="email" placeholder="enter your email" required">
				</div>

				<div class="form-group">
				<label>Password</label>
				<input type="password" class="form-control" name="password" placeholder="*********	" required">
				</div>
				
				<div class="text-center">
				<button type="submit" class="btn btn-primary">Login</button>
				</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>