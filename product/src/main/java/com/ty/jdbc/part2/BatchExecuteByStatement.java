package com.ty.jdbc.part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchExecuteByStatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "jdbc:postgresql://localhost:5432/shop?user=postgres&password=root";

		// step1
		try {
			Class.forName("org.postgresql.Driver");

			// step 2 create a connection
			Connection con = DriverManager.getConnection(url);

			// step3 create statemnent
			Statement stm = con.createStatement();
			String sql = "insert into product values(13,'oppof23',25000,8),(14,'redmi11',35000,8)";
			stm.addBatch(sql);
			sql = "insert into product values(15,'realme',25000,6),(16,'lava',35000,8)";
			stm.addBatch(sql);

			int[] a = stm.executeBatch();
			for (int i : a) {
				System.out.println(i);
			}
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
