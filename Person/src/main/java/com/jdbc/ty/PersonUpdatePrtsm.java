package com.jdbc.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.postgresql.Driver;

public class PersonUpdatePrtsm {
	public static void main(String[] args) {



		String url ="jdbc:postgresql://localhost:5432/Person?user=postgres&password=root";


		try {
			//step1 load or register Driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			//step 2 establish a connection by single String argument
			Connection con =DriverManager.getConnection(url);
			Scanner s = new Scanner(System.in);
			//step 3 create a statement
			String sql="update person set name=? where phone_numberORcontact_number =?";
			PreparedStatement pstm =con.prepareStatement(sql);
			System.out.println("enter value");
			pstm.setString(1, s.next());
			System.out.println("enter value for update");
			pstm.setLong(2, s.nextLong());

			//step 4 execute
			pstm.execute();
			System.out.println("data updated");


			//step 5 close connection
			con.close();
			s.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

