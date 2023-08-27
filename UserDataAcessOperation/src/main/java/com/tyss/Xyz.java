package com.tyss;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class Xyz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "jdbc:postgresql://localhost:5432/Product";
		String user = "postgres";
		String pass = "root";

		try {
			Class.forName("org.postgresql.Driver");
			
			Connection con =DriverManager.getConnection(url, user, pass);
			
			CallableStatement cs =con.prepareCall("call fetch_product(?,?)");
			cs.setInt(1, 6);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.registerOutParameter(2, Types.VARCHAR);
			
			cs.execute();
			System.out.println("id "+cs.getInt(1));
			System.out.println("id "+cs.getString(2));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
