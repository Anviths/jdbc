package com.ty.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class DemoRegisterDriver {

	public static void main(String[] args) {

		Driver driver = new Driver();
		
		try {
			 
			DriverManager.registerDriver(driver);
			System.out.println("Driver register");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
