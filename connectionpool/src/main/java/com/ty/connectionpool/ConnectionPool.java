package com.ty.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

	// creatng array list object and store the reference using list interface
//	specify gereics to connection
	private static List<Connection> connectionPool = new ArrayList<Connection>();
	private static String driverPath = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://localhost:5432/Product";
	private static String user = "postgres";
	private static String password = "root";

	private static final int POOL_SIZE = 5;
	// using static block load the driver
	static {
		try {
			Class.forName(driverPath);
			// creating 5 pool for connection
			for (int i = 0; i < POOL_SIZE; i++) {
				// create a connection by calling create connection method and store in
				// ArrayList 
				Connection connection = createConnection();
				connectionPool.add(connection);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// create static method that create connection object and return connection
	// object
//this method help to create connection
	private static Connection createConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnectionObject() {
//		if my connection pool is not empty return first connection object else create create connection
		if (!connectionPool.isEmpty()) {
			return connectionPool.remove(0);
		} else {
			return createConnection();
		}
	}

	// it recive connection from a customer and store back to pool
	public static void reciveConnectionPool(Connection connection) {
		if (connectionPool.size() < POOL_SIZE) {
			connectionPool.add(connection);
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
