package com.ty.metada;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetaDatas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url="jdbc:postgresql://localhost:5432/Product";
		String user="postgres";
		String password="root";
//		Step1
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,password);
			
         DatabaseMetaData dmd=con.getMetaData();
         
         ResultSet name=dmd.getSchemas();
         System.out.println(name);
        name. next();
         System.out.println(name.getString(1));
         name. next();
         System.out.println(name.getString(1));
         name. next();
         System.out.println(name.getString(1));
         System.out.println(name.getInt(2));
         
         
         String ver=dmd.getDriverVersion();
         System.out.println(ver);
         con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
