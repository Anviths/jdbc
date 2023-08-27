package com.ty.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "jdbc:postgresql://localhost/shop";
		String user = "postgres";
		String password = "root";

		// step 1
		try {
			Class.forName("org.postgresql.Driver");

			// step 2
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println(con);

			/*
			 * String sql = "INSERT INTO product VALUES(3,'xiamoi' ,35700,2)"; //step 3
			 * Statement stm =con.createStatement(); PreparedStatement pstm =
			 * con.prepareStatement(sql);
			 */

			String sql = "SELECT * FROM product";
			// step 4
			ResultSet result = con.prepareStatement(sql).executeQuery();

				System.out.println(result.getInt(1));
			

			// step 5
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
