package org.ty.userapp.daoII;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class EmployeeDAO {

	private static String url = "jdbc:postgresql://localhost:5432/Employee";
	private static String user = "postgres";
	private static String pass = "root";

	// employee save method
	public void saveEmployeeData(Employee e) {// start of save method

		try {
			// step1 load driver
			Class.forName("org.postgresql.Driver");

			// step2 create a connection
			Connection con = DriverManager.getConnection(url, user, pass);

			// step3 create statement
			String sql = "call create_emp(?,?,?,?,?,?,?,?)";
			CallableStatement cs = con.prepareCall("call create_emp(?,?,?,?,?,?,?,?)");
			cs.setInt(1, e.getId());
			cs.setString(2, e.getName());
			cs.setString(3, e.getEmail());
			cs.setString(4, e.getDesignation());
			cs.setLong(5, e.getPhone());
			cs.setString(6, e.getDepartment());
			cs.setDouble(7, e.getSalary());
			cs.setString(8, e.getPassword());

			// step4 execte
			cs.execute();
			// step 5close
			con.close();

			System.out.println("data saved");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}// end of save method

	public void updateEmployee(Employee e) {
		// start of emp update
		try {
			// step1 load driver
			Class.forName("org.postgresql.Driver");

			// step2 create a connection
			Connection con = DriverManager.getConnection(url, user, pass);
			Scanner s = new Scanner(System.in);
			// step3 create statement
//			String sql = "call update_emp_name(?,?)";
			CallableStatement cs = con.prepareCall("call update_emp_name(?,?)");
			System.out.println("enter id");
			e.setId(s.nextInt());
			cs.setInt(2, e.getId());
			System.out.println("enter name");
			e.setName(s.next());
			cs.setString(1, e.getName());

			// step4 execte
			cs.execute();
			// step 5close
			con.close();

			System.out.println("data saved");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}// end of empupdate

	public void deleteEmployee(Employee e) {
		// start of delete employee
		try {
			// step1 load driver
			Class.forName("org.postgresql.Driver");

			// step2 create a connection
			Connection con = DriverManager.getConnection(url, user, pass);

			// step3 create statement
//			String sql = "call update_emp_name(?,?)";
			CallableStatement cs = con.prepareCall("call delete_emp_id(?)");
			Scanner s = new Scanner(System.in);
			System.out.println("enter id");
			e.setId(s.nextInt());
			cs.setInt(1, e.getId());
			// step4 execte
			cs.execute();
			// step 5close
			con.close();
			s.close();

			System.out.println("data deleted");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}// end of delete method

	public void getData(int id) {// start of get data
		try {
			// step1 load driver
			Class.forName("org.postgresql.Driver");

			// step2 create a connection
			Connection con = DriverManager.getConnection(url, user, pass);

			// step3 create statement
//			String sql = "call update_emp_name(?,?)";
			CallableStatement cs = con.prepareCall("call get_database(?,?,?,?,?,?,?)");
			cs.setInt(1, id);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.BIGINT);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.DOUBLE);

			// step4 execute

			cs.execute();

			System.out.println("id: " + cs.getInt(1));
			System.out.println("name : " + cs.getString(2));
			System.out.println("email : " + cs.getString(3));
			System.out.println("designation: " + cs.getString(4));
			System.out.println("phone: " + cs.getLong(5));
			System.out.println("department: " + cs.getString(6));
			System.out.println("salary: " + cs.getDouble(7));

			// step 5close
			con.close();

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}// end of get data

}// end of class
