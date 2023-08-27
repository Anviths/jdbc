package edu.ty.foodapp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ty.foodapp.bean.Order;
import edu.ty.foodapp.bean.Product;

public class OrderController {

	private String driverPath="org.postgresql.Driver";
	private String url="jdbc:postgresql://localhost:5432/foodapp_jdbc";
	private String user="postgres";
	private String password="root";
	
	public boolean saveOrder(Order order) {
		boolean flag=false;
		try {
			Class.forName(driverPath);
			Connection connection=DriverManager.getConnection(url, user, password);
			String sql="insert into orders values(?,?)";
			
			PreparedStatement preapeStatement=connection.prepareCall(sql);
			preapeStatement.setInt(1, order.getOrderId());
			preapeStatement.setString(2, order.getOrderNumber());
			
			preapeStatement.execute();
			
			connection.close();
			flag=true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public Order findOrderById(int orderId) {
		Order order = null;
		try {
			Class.forName(driverPath);
			Connection connection = DriverManager.getConnection(url, user, password);
			String sql = "select * from orders where order_id=? ";

			PreparedStatement preapeStatement = connection.prepareCall(sql);
			preapeStatement.setInt(1,orderId );
			ResultSet result = preapeStatement.executeQuery();
			result.next();
			order = new Order();
			order.setOrderId(result.getInt(1));
			order.setOrderNumber(result.getString(2));
			
			
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
		
	}
	public boolean updateOrderNameByOrderId(int orderId, String orderName) {
		boolean flag=false;
		
		Order order = findOrderById(orderId);
		if (order != null) {
			try {
				Class.forName(driverPath);
				Connection connection = DriverManager.getConnection(url, user, password);
				String sql = "update orders set order_number=? where order_id=?";

				PreparedStatement preapeStatement = connection.prepareCall(sql);
				preapeStatement.setString(1, orderName);
				preapeStatement.setInt(2, orderId);

				preapeStatement.execute();

				connection.close();
                 flag=true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean removeOrderById(int orderId) {
		boolean flag=false;
		try {
			Class.forName(driverPath);
			Connection connection = DriverManager.getConnection(url, user, password);
			String sql = "delete from orders  where order_id=?";

			PreparedStatement preapeStatement = connection.prepareCall(sql);
			preapeStatement.setInt(1, orderId);
			preapeStatement.executeUpdate();
			connection.close();
			flag=true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
