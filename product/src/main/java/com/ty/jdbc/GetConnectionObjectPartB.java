package com.ty.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnectionObjectPartB {

	private static String URL = "jdbc:postgresql://localhost:5432/shop?user=postgres&password=root";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		try {
			Class.forName("org.postgresql.Driver") ;
			
			//step2
			
			Connection con =DriverManager.getConnection(URL);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
