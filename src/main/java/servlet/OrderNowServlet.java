package servlet;
import java.util.ArrayList;
import java. util. Date;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import connection.DBCon;
import dao.OrderDao;
import mypack.Cart;
import mypack.Order;
import mypack.User;


@WebServlet("/OrderNowServlet")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		try {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");		
		Date date=new Date();
		
		User auth=(User)request.getSession().getAttribute("auth");
		if(auth!=null) {
			
			String productId=request.getParameter("id");
			
			int productQuantity=Integer.parseInt( request.getParameter("quantity"));
			if(productQuantity <=0 ) {
				productQuantity=1;
			}
			
			Order orderModel = new Order();
			orderModel.setId(Integer.parseInt(productId));
			orderModel.setUid(auth.getId());
			orderModel.setQuantity(productQuantity);
			orderModel.setDate(formatter.format(date));
			
			OrderDao orderdao=new OrderDao(DBCon.getConnection());
			boolean result=orderdao.insertOrder(orderModel);
			
			if(result) {
				
				ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if(cart_list!=null) {
					for(Cart c:cart_list) {
						if(c.getId()==Integer.parseInt(productId)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
				}				
				response.sendRedirect("orders.jsp");
			}else {
				out.println("order failed");
			}			
		}else {
			response.sendRedirect("login.jsp");			
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
