package com.ty.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class Demo1 {

	static {
		 try {
			DriverManager.registerDriver(new Driver());
			System.out.println("loaded");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
	}
	
}
