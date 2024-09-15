<%@page import="connection.DBCon"%>
<%@page import="mypack.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
<div class="container">
  <a class="navbar-brand" href="index.jsp">Shoping-cart</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="cart.jsp">Cart <span class="badge badge-danger px-1">${cart_list.size() }</span></a>
      </li>
      
      <%
      if(auth!=null){%>   	  
    	 <li class="nav-item"> <a class="nav-link " href="orders.jsp">Orders</a> </li>
        <li class="nav-item"> <a class="nav-link " href="LogoutServlet">Logout</a></li>
    	<% 
      }else{
      %>     
      <li class="nav-item"><a class="nav-link " href="login.jsp">Login</a> </li>  
      <%} %>
    </ul>
   
  </div>
  </div>
</nav>