package com.ty.transactionmanagment;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.Driver;

public class Demo {

	public static void main(String[] args) {
		try {
			DataSource
			Class.forName(null);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			qvk-qgqz-yyo
		}
		Driver d=new Driver();
		try {
			DriverManager.registerDriver(d);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
