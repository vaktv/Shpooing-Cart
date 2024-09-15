package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.DBCon;
import dao.OrderDao;

@WebServlet("/cancelOrder")
public class cancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		try {
			String id=request.getParameter("id");
			
			if(id!=null) {
				OrderDao orderDao=new OrderDao(DBCon.getConnection());
				orderDao.cancelOrder(Integer.parseInt(id));
				
			}
			response.sendRedirect("orders.jsp");
			
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
