package edu.ty.foodapp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ty.foodapp.bean.Product;

public class ProductController {

	private String driverPath = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost:5432/foodapp_jdbc";
	private String user = "postgres";
	private String password = "root";

	public boolean saveProduct(Product product) {
		boolean flag=false;
		try {
			Class.forName(driverPath);
			Connection connection = DriverManager.getConnection(url, user, password);
			String sql = "insert into product values(?,?,?,?,?)";
			PreparedStatement preapeStatement = connection.prepareCall(sql);
			preapeStatement.setInt(1, product.getProductId());
			preapeStatement.setString(2, product.getProductName());
			preapeStatement.setString(3, product.getProductDescription());
			preapeStatement.setDouble(4, product.getProductPrice());
			preapeStatement.setInt(5, product.getOrderId());
        
			preapeStatement.execute();
             flag=true;
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public Product findProductById(int productId) {
		Product product = null;
		try {
			Class.forName(driverPath);
			Connection connection = DriverManager.getConnection(url, user, password);
			String sql = "select * from product where product_id=? ";

			PreparedStatement preapeStatement = connection.prepareCall(sql);
			preapeStatement.setInt(1, productId);
			ResultSet result = preapeStatement.executeQuery();
			result.next();
			product = new Product();
			product.setProductId(result.getInt("product_id"));
			product.setProductName(result.getString(2));
			product.setProductDescription(result.getString(3));
			product.setProductPrice(result.getDouble(4));
			product.setOrderId(result.getInt(5));
			
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	public boolean updateProductPriceByProductId(int productId, double productNewPrice) {
	boolean flag=false;
		Product product = findProductById(productId);
		if (product != null) {
			try {
				Class.forName(driverPath);
				Connection connection = DriverManager.getConnection(url, user, password);
				String sql = "update product set product_price=? where product_id=?";

				PreparedStatement preapeStatement = connection.prepareCall(sql);
				preapeStatement.setDouble(1, productNewPrice);
				preapeStatement.setInt(2, productId);

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

	public boolean removeProductById(int productId) {
		boolean flag=false;
		try {
			Class.forName(driverPath);
			Connection connection = DriverManager.getConnection(url, user, password);
			String sql = "delete from product  where product_id=?";

			PreparedStatement preapeStatement = connection.prepareCall(sql);
			preapeStatement.setInt(1, productId);
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
