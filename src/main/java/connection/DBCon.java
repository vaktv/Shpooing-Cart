package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	private static Connection connection=null;
	
	public static Connection getConnection() throws ClassNotFoundException,SQLException{
		if(connection==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","Bharat@123");
			System.out.println("connected");
		}
		return connection;
	}
}
