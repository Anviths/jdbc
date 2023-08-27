package com.jdbc.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.Driver;

public class PesonUpdateStm {

	public static void main(String[] args) {
		
		String url ="jdbc:postgresql://localhost:5432/Person?user=postgres&password=root";
		
		
		try {
			//step1 load or register Driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			//step 2 establish a connection by single String argument
			Connection con =DriverManager.getConnection(url);
			
			//step 3 create a statement
			Statement  stm=con.createStatement();
			
			//step 4 execute
			String sql="update person set name='suman' where 'email'='surya@gmail.com'";
			stm.execute(sql);
		System.out.println("data updated");
		
			
			//step 5 close connection
			con.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
