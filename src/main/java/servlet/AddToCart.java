package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.ExpiresFilter.XPrintWriter;

import mypack.Cart;


@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		try {
			ArrayList<Cart>cartlist=new ArrayList<>();
			
			int id=Integer.parseInt(request.getParameter("id"));
			Cart cm=new Cart();
			cm.setId(id);
			cm.setQuantity(1);
			
			HttpSession session=request.getSession();
			ArrayList<Cart>cart_list= (ArrayList<Cart>) session.getAttribute("cart-list");
			
			
			if(cart_list==null) {
				cartlist.add(cm); 
				session.setAttribute("cart-list", cartlist);
				
			}else {
				//out.println("cart lsit exist");
				cartlist=cart_list;
				boolean exist=false;
				for(Cart c:cart_list) {
					if(c.getId()==id) {
						exist=true;
						out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
					}
				}
				if(!exist) {
					cartlist.add(cm);
					response.sendRedirect("index.jsp");
				}
			}
			
				
		
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	

	
	}
}
