package com.ty.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Updateemp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url="jdbc:postgresql://localhost:5432/Employee";
		String user = "postgres" ;
		String password = "root" ;
		
		//step 1
		try {
			Class.forName("org.postgresql.Driver");
			
			//step 2
			Connection con =DriverManager.getConnection(url,user,password);
			System.out.println(con);
			
			String sql = " update employee set name='pavan' where 'phone number'= '9878543210' ";
			//step 3
			Statement stm =con.createStatement();
			
			//step 4
			stm.execute(sql);
			
			//step 5
			con.close();
			System.out.println("data updated");
			
		} 
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
