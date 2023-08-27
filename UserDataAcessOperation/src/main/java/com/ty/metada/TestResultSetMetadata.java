package com.ty.metada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestResultSetMetadata {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url="jdbc:postgresql://localhost:5432/Product";
		String user="postgres";
		String password="root";
//		Step1
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,password);
			
        String sql="select * from product";
        Statement sm=con.createStatement();
        
        ResultSet rs=sm.executeQuery(sql);
        ResultSetMetaData dmd=rs.getMetaData();
         
         String name=dmd.getColumnName(1);
         System.out.println(name);
         
         String ver=dmd.getColumnLabel(1);
         System.out.println(ver);
         
         int count =dmd.getColumnCount();
         System.out.println(count);
         
         String tname=dmd.getTableName(2
        		 );
         System.out.println(tname);
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
