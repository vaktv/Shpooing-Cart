package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.DBCon;
import dao.OrderDao;
import mypack.Cart;
import mypack.Order;
import mypack.User;


@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out= response.getWriter();
		
		try{
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			
			ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");//cart che product retrive kele
			User auth=(User)request.getSession().getAttribute("auth");//user login ahe ki nhi check kara
			
			if(cart_list !=null && auth != null) {
				
				for(Cart c:cart_list) {
					Order order=new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					OrderDao oDoa=new OrderDao(DBCon.getConnection());
					boolean result= oDoa.insertOrder(order);
					
					if(!result) break;
					
				}
				
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			}else {
				if(auth == null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
