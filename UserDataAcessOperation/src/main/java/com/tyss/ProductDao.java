package com.tyss;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class ProductDao {

	private String url = "jdbc:postgresql://localhost:5432/Product";
	private String user = "postgres";
	private String password = "root";

	public void saveProduct(Product product) {
		// save the product
		// step1
		try {
			Class.forName("org.postgresql.Driver");

			// step2
			Connection con = DriverManager.getConnection(url, user, password);

			// step 3
			String sql = "call product_save(?,?,?,?,?,?)";
			CallableStatement cs = con.prepareCall(sql);
			Scanner s = new Scanner(System.in);
			System.out.println("enter id");
			product.setId(s.nextInt());
			System.out.println("enter name");
			product.setName(s.next());
			System.out.println("enter type");
			product.setType(s.next());
			System.out.println("enter descripetion");

			s.nextLine();
			product.setDescripetion(s.nextLine());

			System.out.println("enter availability");
			product.setAvailability(s.next());
			System.out.println("enter price");
			product.setPrice(s.nextDouble());

			// setting delimiter
			cs.setInt(1, product.getId());
			cs.setString(2, product.getName());
			cs.setString(3, product.getType());
			cs.setString(4, product.getDescripetion());
			cs.setString(5, product.getAvailability());
			cs.setDouble(6, product.getPrice());
			cs.addBatch();

			// step4
			cs.execute();
			// step 5
			cs.close();
			s.close();
			con.close();
           System.out.println("data saved");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Product findProductById(int id) {
		// Find Product by id and return the product
		Product product = null;
		// step1
		try {
			// step1 load driver
			Class.forName("org.postgresql.Driver");

			// step2 create a connection
			Connection con = DriverManager.getConnection(url, user, password);

			// step3 create statement
			// St
			CallableStatement cs = con.prepareCall("call fetch_product(?,?,?,?,?,?)");
			cs.setInt(1, id);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);

			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.DOUBLE);

			// step4 execute

			cs.execute();
			product = new Product();
			// storing attributes values by using setter
			product.setId(cs.getInt(1));
			product.setName(cs.getString(2));
			product.setType(cs.getString(3));
			product.setDescripetion(cs.getString(4));
			product.setAvailability(cs.getString(5));
			product.setPrice(cs.getDouble(6));
			// if data not found it will return null
			if (cs.getInt(1) == 0) {
				System.out.println("no record found");
			}
			// step 5close
			con.close();

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return product;
	}

	public Product updateProductById(Product product) {
		// update a product by id and return product

		Scanner s = new Scanner(System.in);
		try {
			Class.forName("org.postgresql.Driver");

			// step2
			Connection con = DriverManager.getConnection(url, user, password);

			// step 3
			System.out.println("enter id");
			int id = s.nextInt();
			Product p = findProductById(id);
			if (id == p.getId()) {
				CallableStatement cs1;
				System.out.println();
				System.out.println("enter option to update");
				System.out.println("1.id");
				System.out.println("2.name");
				System.out.println("3.type");
				System.out.println("4.description");
				System.out.println("5.availabilty");
				System.out.println("6.price");
				int choice = s.nextInt();
				switch (choice) {
				case 1: {
					cs1 = con.prepareCall("call update_product(?,?)");
					System.out.println("enter new id");
					cs1.setInt(2, id);
					cs1.setInt(1, s.nextInt());
					cs1.execute();
					System.out.println("updated");
					cs1.close();
					break;
				}
				case 2: {
					cs1 = con.prepareCall("call update_name(?,?)");
					System.out.println("enter new name");
					cs1.setInt(1, id);
					cs1.setString(2, s.next());
					cs1.execute();
					System.out.println("updated");
					cs1.close();
					break;
				}
				case 3: {
					cs1 = con.prepareCall("call update_type(?,?)");
					System.out.println("enter new type");
					cs1.setInt(1, id);
					cs1.setString(2, s.next());
					cs1.execute();
					System.out.println("updated");
					cs1.close();
					break;
				}
				case 4: {
					cs1 = con.prepareCall("call update_description(?,?)");
					System.out.println("enter new desc");
					cs1.setInt(1, id);
					cs1.setString(2, s.next());
					cs1.execute();
					System.out.println("updated");
					cs1.close();
					break;
				}
				case 5: {
					cs1 = con.prepareCall("call update_availabilty(?,?)");
					System.out.println("update availability");
					cs1.setInt(2, id);
					cs1.setString(1, s.next());
					cs1.execute();
					System.out.println("updated");
					cs1.close();
					break;
				}
				case 6: {
					cs1 = con.prepareCall("call update_price(?,?)");
					System.out.println("enter new price");
					cs1.setInt(1, id);
					cs1.setDouble(2, s.nextDouble());
					cs1.execute();
					System.out.println("updated");
					cs1.close();
					break;
				}
				default:
					System.out.println("enter correct choice");

				}// end of switch

			}

			s.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;

	}

	public void removeProductById(int id) {
		// find the product by id and remove if exist
		try {
			// step1 load driver
			Class.forName("org.postgresql.Driver");

			// step2 create a connection
			Connection con = DriverManager.getConnection(url, user, password);

			// step3 create statement
			Scanner s = new Scanner(System.in);
			Product p = findProductById(id);

			if (id == p.getId()) {
				CallableStatement cs = con.prepareCall("call delete_product(?)");
				cs.setInt(1, id);
				// step4 execute
				cs.execute();
				System.out.println("data deleted");
			}
			// step 5close
			con.close();
			s.close();

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
