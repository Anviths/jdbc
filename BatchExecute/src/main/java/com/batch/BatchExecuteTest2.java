package com.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchExecuteTest2 {

	public static void main(String[] args) {

		String url="jdbc:postgresql://localhost:5432/Person";
		String user="postgres";
		String password="root";
		//step1
		try {
			Class.forName("org.postgresql.Driver");
			
			// step 2
			Connection connection=DriverManager.getConnection(url, user, password);
			
			//Step 3
			Statement statement=connection.createStatement();
			
			//create first query
			String sql1="insert into person values(1272,'abc','manoj@gmail.com')";
			String sql2="insert into person values(8282,'manoj','manoj@gmail.com')";
			String sql3="insert into person values(7921,'manoj','manoj@gmail.com')";
			
			//add query to batch
			long start=System.currentTimeMillis();
			statement.addBatch(sql3);
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			
			statement.executeBatch();
			
			connection.close();
			long end=System.currentTimeMillis();
			System.out.println("time taken" +(start-end));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
