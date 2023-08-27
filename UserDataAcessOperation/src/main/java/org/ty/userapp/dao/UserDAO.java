package org.ty.userapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserDAO{

	final private static String url="jdbc:postgresql://localhost:5432/Person";
	private static String user="postgres";
	private static String pass="root";
	
	private  Connection con;

	/****non -static initializer for loading driver and get connection
	 *  this block will load the driver class and establish connection
	 * **/
	Scanner s=new Scanner(System.in);
	{
		//            
		//            System.out.println("enter user name");
		//            user=s.next();
		//            System.out.println("enter password");
		//            pass=s.next();

		//loading the driver class
		try {
			Class.forName("org.postgresql.Driver") ;
			//establish a connection using getconnection()
			con=DriverManager.getConnection(url,user,pass);
			System.out.println("login sucessful");

		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {

			e.printStackTrace();
		}
	} //static block end 




	//start of save user method used to store in database
	public void saveUser(int id,String name,String email,long phone,String password) {

		//create sql query for prepared statement
		String sql ="insert into \"user\" values(?,?,?,?,?)";

		try {
			PreparedStatement ps =con.prepareStatement(sql);
			//set the values for delimiter using setx() present in PreparedStatement
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setLong(4, phone);
			ps.setString(5, password);

			//execute 
			ps.execute();
			System.out.println("data saved");


		} catch (SQLException e) {
			e.printStackTrace();
		}

	}//end of save user


	public void findUserById(int id) {//start of find user by id

		//Statement to fetch data by using id
		String sql="select * from \"user\" where id=?" ;
		try {
			PreparedStatement psm= con.prepareStatement(sql);
			psm.setInt(1, id);
			//execute 
			ResultSet rs = psm.executeQuery();
			if(rs.next()) {
			while(rs.next()) {
				int id1=rs.getInt(1);
				String name=rs.getString(2);
				String email=rs.getString(3);
				long phone=rs.getLong(4);
				System.out.println("================");
				System.out.println("id:"+id1);
				System.out.println("name : "+name);
				System.out.println("email : "+email);
				System.out.println("phone : "+phone);
				System.out.println("====================");
			}
			}
			else {
				System.out.println("no data present");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//end of finduserbyid mehod

	private String getPassword(int id) throws SQLException {

		Statement stm =con.createStatement();
		String sql="select password from \"user\" where id="+id;
		ResultSet rs =stm.executeQuery(sql);
		rs.next();
		return rs.getString("password");


	}//end of getPassword

	public void updateUserById(int id,String password) throws SQLException {//start of user

		//start of find user by id
		String verifyPassword=getPassword(id);
		if(password.equals(verifyPassword)) {
			//Statement to fetch data by using id

			System.out.println("select the the column name to update");
			System.out.println("1.name\n2.email\n3.phone\n4.password");
			System.out.println("enter choice");
			int choice=s.nextInt();
			String sql;
			PreparedStatement psm;

			switch(choice) {
			case 1:{
				sql="update  \"user\" set name =? where id=?" ;
				psm= con.prepareStatement(sql);
				System.out.println("enter name");
				psm.setString(1, s.next());
				psm.setInt(2, id);
				//execute 
				psm.execute();
				break;}
			case 2:
			{
				sql="update  \"user\" set email =? where id=?" ;
				psm= con.prepareStatement(sql);
				System.out.println("enter email");
				psm.setString(1, s.next());
				psm.setInt(2, id);
				//execute 
				psm.execute();
				break;
			}
			case 3:
			{
				sql="update  \"user\" set phone =? where id=?" ;
				psm= con.prepareStatement(sql);
				System.out.println("enter phone number");
				psm.setLong(1, s.nextLong());
				psm.setInt(2, id);
				//execute 
				psm.execute();
				break;
			}
			case 4:
			{
				sql="update  \"user\" set password =? where id=?" ;
				psm= con.prepareStatement(sql);
				System.out.println("enter new password ");
				psm.setString(1, s.next());
				psm.setInt(2, id);
				//execute 
				psm.execute();
				break;
			}

			default:{System.out.println("ivalid choice");
			System.out.println("data not updated enter correct choice");
			}

			}
			System.out.println("data updated");
		}
		else {
			System.out.println("wrong password");
		}


	}//end of updateaUser

	public void deleteUserById(int id) {
		//start of user

		//start of find user by id

		//Statement to fetch data by using id
		String sql="delete from \"user\"  where id=?" ;
		try {
			PreparedStatement psm= con.prepareStatement(sql);
			psm.setInt(1, id);
			//execute 
			psm.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}//end of delete method

	void close() {
		try {
			con.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
