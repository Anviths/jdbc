package com.ty.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoRetriveData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url="jdbc:postgresql://localhost:5432/shop";
		String user = "postgres" ;
		String password = "root" ;
		
	
			// step 1
			try {
				Class.forName("org.postgresql.Driver");
				
				//step2
				Connection con = DriverManager.getConnection(url,user,password) ;
				String sql ="select * from product";
				//step3
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while(rs.next()) {
					int id=rs.getInt(1);
					String name = rs.getString(2);
					double cost = rs.getDouble(3) ;
					int quantity = rs.getInt(4); 
					
					System.out.println("id is "+id);
					System.out.println( "name is"+name);
					System.out.println( "cost is "+cost);
					System.out.println( "Quantity is " +quantity);
					System.out.println("--------------------");
					
					//step 5																																																																																																																															
					con.close();
				}
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
