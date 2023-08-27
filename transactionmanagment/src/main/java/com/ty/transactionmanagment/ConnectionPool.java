package com.ty.transactionmanagment;

import java.lang.module.ModuleDescriptor.Version;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	
	
	/**
	 * 
	      Class creates object for connection pool which help
	      to get the connection object for multiple class
	 * @author Anvith gowda
	 *
	 * @version 1.0 05 JUL 2023
	   
	 */

	private static String driverPath = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://localhost:5432/ticket";
	private static String user = "postgres";
	private static String password = "root";

	private static final int POOL_SIZE = 5;
	private static List<Connection> connectionPool = new ArrayList<Connection>();

	static {
		try {
			Class.forName(driverPath);

			for (int i = 0; i < POOL_SIZE; i++) {
				Connection connection = createConnection();
				connectionPool.add(connection);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Connection createConnection() {
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	public static Connection getConnectionObject() {
		if (!connectionPool.isEmpty()) {
			return connectionPool.remove(0);
		} else {
			return createConnection();
		}
	}

	public static void returnConnectionPool(Connection con) {
		if (connectionPool.size() < POOL_SIZE) {
			connectionPool.add(con);
		} else {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
