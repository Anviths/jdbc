package com.jdbc.ty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PersonInsertStm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileInputStream input = new FileInputStream("Person.properties") ;

			Properties properties = new Properties() ;
			properties.load(input);

			//step1
			String driverpath =properties.getProperty("path");
			Class.forName(driverpath) ;

			//step 2 connection
			String url=properties.getProperty("url");
			Connection con =DriverManager.getConnection(url,properties);

			//step 3 create statement
			Statement stm =con.createStatement();
			String sql ="insert into person1 values(7,'rohith','rohit@gmail.com','9747785556','124roo',23)";

			//step 4 execute query
			stm.execute(sql);

			//step5 close
			con.close();







		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
