package com.jdbc.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonFetchByPhonestm {

	public static void main(String[] args) {

		String url="jdbc:postgresql://localhost:5432/Person?user=postgres&password=root";

		//step1

		//step2
		try {
			Connection con =DriverManager.getConnection(url);
			//Step 3
			Statement stm =con.createStatement();
			//step 4
			String sql="select * from person where phone_numberorcontact_number='9448785856'";


			ResultSet rs=stm.executeQuery(sql);
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

			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
