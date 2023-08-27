package edu.ty.person.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Emp {

	final private static String url="jdbc:postgresql://localhost:5432/Employee";
	private static String user="postgres";
	private static String pass="root";
	public static void main(String[] args) {
		
	
	try {
		// step1 load driver
		Class.forName("org.postgresql.Driver");

		// step2 create a connection
		Connection con = DriverManager.getConnection(url, user, pass);

		// step3 create statement
		
		PreparedStatement cs = con.prepareStatement("insert into emp values(1,'anvith',?)");
		
		cs.setString(1, enpPass());
		

		// step4 execte
		cs.execute();
		// step 5close
		con.close();

		System.out.println("data saved");
	} 
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	private static String enpPass() {
		
		return null;
	}
	
}
