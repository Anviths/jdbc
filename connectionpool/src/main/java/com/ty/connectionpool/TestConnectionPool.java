package com.ty.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnectionPool {

	public static void main(String[] args) {
		
		Connection con=ConnectionPool.getConnectionObject();
		
		try {
			String sql ="select * from product";
			Statement sm= con.createStatement();
			sm.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionPool.reciveConnectionPool(con);
		
       
	}

}
