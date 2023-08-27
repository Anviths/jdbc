package com.jdbc.ty;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class GetPersonData {

	public static void main(String[] args) {




		String url ="jdbc:postgresql://localhost:5432/Person?user=postgres&password=root";


		try {
			//step1 load  Driver
			Class.forName("org.postgresql.Driver");

			//step 2 establish a connection by single String argument
			Connection con =DriverManager.getConnection(url);
			Scanner s = new Scanner(System.in);
			//step 3 create a statement
			String sql="select * from person";

			Statement stm =con.createStatement();
			//step 4 execute
			ResultSet rs =stm.executeQuery(sql);

			while(rs.next()) {
				int id =rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				long phone =rs.getLong(4);
				int age = rs.getInt(6);
				String pass = rs.getString(5);
				
			    SecretKey key = PersonInsertPre.key;
			    IvParameterSpec ivParameterSpec = PersonInsertPre.ivParameterSpec;
			    String algorithm = "AES/CBC/PKCS5Padding";
			    String inputa=PersonInsertPre.decrypt(algorithm, key, pass, ivParameterSpec);
				

				System.out.println("id :"+id);
				System.out.println("name : "+name);
				System.out.println("email : "+email);
				System.out.println("phone number : "+phone);
				System.out.println("password : "+inputa);
				System.out.println("age : "+age);
				System.out.println("============================");

			}


			//step 5 close connection
			con.close();
			s.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


