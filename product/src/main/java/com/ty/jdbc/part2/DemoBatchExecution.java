package com.ty.jdbc.part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DemoBatchExecution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "jdbc:postgresql://localhost:5432/shop?user=postgres&password=root";

		// step1
		try {
			Class.forName("org.postgresql.Driver");

			// step 2 create a connection
			Connection con = DriverManager.getConnection(url);

			// step3 create statemnent
			String sql = "insert into product values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			// insert data for delimiter
			ps.setInt(1, 10);
			ps.setString(2, "mobile");

			ps.setDouble(3, 15000);
			ps.setDouble(4, 1);

			ps.addBatch();
			ps.setInt(1, 11);
			ps.setString(2, "BLUETHOOTH");

			ps.setDouble(3, 1800);
			ps.setDouble(4, 1);

			ps.addBatch();
			ps.executeBatch();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
