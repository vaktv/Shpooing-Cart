<%@page import="java.util.*"%>
<%@page import="dao.ProductDao"%>
<%@page import="connection.DBCon"%>
<%@page import="mypack.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ProductDao pdao= new ProductDao(DBCon.getConnection()); 
List<Product> products=pdao.getAllProducts();

ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");

if(cart_list!=null){
	
	request.setAttribute("cart_list", cart_list);
	
}

%>

<!DOCTYPE html>
<html>
<head>
<title>welcome to Shopping cart</title>
<%@include file="includes/head.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>


	<div class="container">
		<div class="card-header my-3">My Products</div>
		<div class="row">
		
		<%
			if(!products.isEmpty()){
				for(Product p:products){%>
					<div class="col-md-3 my-3 ">
					<div class="card w-100" style="width: 18rem;">
						<img class="card-img-top" src="product-images/<%=p.getImage() %>" alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title"><%=p.getName() %></h5>
							<h6 class="price ">Price :Rs <%=p.getPrice() %></h6>
							<h6 class="category">Category : <%=p.getCategory() %> </h6>
							<div class="mt-3 d-flex justify-content-between">
							<a href="AddToCart?id=<%=p.getId()%>" class="btn btn-dark">Add to Cart</a>
							<a href="#" class="btn btn-primary">Buy Now</a>
							</div>
							
						</div>
					</div>
				</div>
				<% }
			}
		%>
			
		</div>


	</div>


	<%@include file="includes/footer.jsp"%>
</body>
</html>