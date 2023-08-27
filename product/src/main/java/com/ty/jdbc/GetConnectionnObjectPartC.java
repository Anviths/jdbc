package com.ty.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnectionnObjectPartC {


		private static String URL = "jdbc:postgresql://localhost:5432/shop?user=postgres&password=root";
		public static void main(String[] args) {
			// TODO Auto-generated method stub
		
			try {
				FileInputStream fileInputStream = new FileInputStream("shop_db.properties") ;

				Properties properties = new Properties() ;
				properties.load(fileInputStream);
				
				String driverName =properties.getProperty("DriverPath") ;
				
				Class.forName(driverName) ;
				
				//step2
				//file inputstream used to read file 
				// properties file object created pass my object to get connection method
				
				
				String URL_recive =properties.getProperty("URL") ;
				
				
				Connection con =DriverManager.getConnection(URL_recive,properties);
				
				System.out.println(con);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
