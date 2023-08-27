package com.ty.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeDyn {

	
	public static void main(String[] args) {
		
	Scanner s =new Scanner(System.in);
	String url="jdbc:postgresql://localhost:5432/Employee";
	String user = "postgres" ;
	String password = "root" ;
	
	//step 1
	try {
		Class.forName("org.postgresql.Driver");
		
		//step 2
		Connection con =DriverManager.getConnection(url,user,password);
		
		
		
		
		Statement stm =con.createStatement();
		System.out.println("enter number of data inserted");
		int n= s.nextInt();
		String sql;
					for(int i=1;i<=n;i++) {
				System.out.println("enter id");
						int id=s.nextInt();
						System.out.println("enter name");
						
						String name=s.next();
						System.out.println("enter email");
						String email=s.next();
						System.out.println("enter designation");
					String designation=s.next();
					System.out.println("enter phone number");
					long phone=s.nextLong();
					System.out.println("enter the department");
					
					String dept=s.next();
					System.out.println("enter the salary");
					int salary=s.nextInt();
					System.out.println("enter the password");
					String pasword=s.next();

		 sql = "INSERT INTO employee VALUES("+id+",'"+name+ "','"+email+"','"+designation+"','"+phone+"','"+dept+"','"+salary+"','"+pasword+"')";
		 stm.execute(sql);
		}
		
		
		
		//step 5
		con.close();
		System.out.println("data inserted");
		
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
