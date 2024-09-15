package dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import mypack.Cart;
import mypack.Product;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public ProductDao(Connection con) {
		 
		this.con = con;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products=new ArrayList<Product>();
		
		try {
			query=" select * from products ";
			pst=this.con.prepareStatement(query);
			rs =pst.executeQuery();
			
			while (rs.next()) {
				Product row=new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
				
				
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartlist){
		List<Cart> products=new ArrayList<Cart>();
		
		try {
			if(cartlist.size()>0) {
				for(Cart item:cartlist) {
					query="select * from products where id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs=pst.executeQuery();
					while(rs.next()) {
						Cart row=new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
						
						
						

						}
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		
		
		
		return products;
		
	}
	
	public Product getSingleProduct(int id) {
		Product row=null;
		
		try {
			query="select * from products where id=?";
			pst=this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				row=new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return row;
	}
	
	public double getTotalCartPrice(ArrayList<Cart> cartlist) {
		double sum=0;
		
		try {
			if(cartlist.size()>0) {
				for(Cart item:cartlist) {
					query="select price from products where id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs=pst.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
						
					}
				}
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sum;
	}
}
