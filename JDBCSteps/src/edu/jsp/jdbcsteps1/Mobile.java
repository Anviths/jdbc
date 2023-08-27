package edu.jsp.jdbcsteps1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class Mobile {

	
	public static void main(String[] args) {
 
		
		String url="jdbc:postgresql://localhost:5432/mobile";
		String user="postgres";
		String password="root";
		try {
			long start=System.nanoTime();
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,password);
			
			String sql="call total_price";
			CallableStatement ps=con.prepareCall(sql);
//			ps.setInt(1, 10);
//			ps.setString(2, "oneplus");
//			ps.setDouble(3, 28880);
			System.out.println(ps.executeQuery());
			con.close();
			long end=System.nanoTime();
			System.out.println("time "+(end-start));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
