package com.jdbc.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonfetchdataByPhonePstm {

	public static void main(String[] args) {

		String url="jdbc:postgresql://localhost:5432/Person?user=postgres&password=root";

		try {
			//step1
			Class.forName("org.postgresql.Driver");
			//step2
			Scanner s =new Scanner(System.in);
			Connection con =DriverManager.getConnection(url);
			//Step 3
			String sql="select * from person where phone_numberorcontact_number=?";
			PreparedStatement pstm =con.prepareStatement(sql);
			System.out.println("enter your mobile number");
			pstm.setLong(1, s.nextLong());

			//step 4

			ResultSet rs=pstm.executeQuery();
			while(rs.next()) {
				int id =rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				long phone =rs.getLong(4);
				int age = rs.getInt(6);
				String pass = rs.getString(5);

				System.out.println("id :"+id);
				System.out.println("name : "+name);
				System.out.println("email : "+email);
				System.out.println("phone number : "+phone);
				System.out.println("password : "+pass);
				System.out.println("age : "+age);
				System.out.println("============================");
				s.close();
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
