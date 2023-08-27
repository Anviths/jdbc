package com.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchExecuteTest {

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
			String sql1="insert into person values(112,'manoj','manoj@gmail.com')";
			String sql2="insert into person values(212,'manoj','manoj@gmail.com')";
			String sql3="insert into person values(312,'manoj','manoj@gmail.com')";
			String sql4="update person set id=4 where id=102";
			//add query to batch
			
			long start=System.currentTimeMillis();
			statement.execute(sql3);
			statement.execute(sql1);
			statement.execute(sql2);
			statement.execute(sql4);
			System.out.println("execte");
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
