package edu.ty.personapp.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



import edu.ty.personapp.bean.Person;
import edu.ty.personapp.view.PersonSecurity;

public class PersonController {
	private static Connection connection;
	static {
		try {
			FileInputStream input = new FileInputStream("Person.properties");
			Properties properties = new Properties();
			properties.load(input);

			// step1
			String driverpath = properties.getProperty("path");
			Class.forName(driverpath);
			String url = properties.getProperty("url");
			
			// step 2
			connection = DriverManager.getConnection(url, properties);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean savePerson(Person person) {
		String sql="insert into person values(?,?,?,?,?,?)";
		//step3
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, person.getPersonId());
			statement.setString(2, person.getPersonName());
			statement.setString(3, person.getPersonEmail());
            statement.setLong(4, person.getPersonPhoneNumber());
            statement.setString(5, person.getPersonPassword());
            statement.setInt(6, person.getPersonAge());

			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void getAllRecords() {
		//step 3
		try {
			PreparedStatement prepareStatement=connection.prepareStatement("select * from person");
			ResultSet result= prepareStatement.executeQuery();
			while(result.next()) {
				int id =result.getInt(1);
				String name = result.getString(2);
				String email = result.getString(3);
				long phone =result.getLong(4);
				int age = result.getInt(6);
				
				String pass = result.getString(5);
				String passWord=PersonSecurity.decrypt(pass);
			    
				System.out.println("id :"+id);
				System.out.println("name : "+name);
				System.out.println("email : "+email);
				System.out.println("phone number : "+phone);
				System.out.println("password : "+passWord);
				System.out.println("age : "+age);
				System.out.println("============================");

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;
	}
	public static Person getRecordByID(int nextInt) {
		//step 3
		Person person=new Person();
				try {
					PreparedStatement prepareStatement=connection.prepareStatement("select from person where id=?");
					prepareStatement.setInt(1, nextInt);
					ResultSet result=prepareStatement.executeQuery();
					while(result.next()) {
						person.setPersonId(result.getInt(1));
						person.setPersonName(result.getString(2));
						String email = result.getString(3);
						person.setPersonEmail(email);
						long phone =result.getLong(4);
						person.setPersonPhoneNumber(phone);
						int age = result.getInt(6);
						person.setPersonAge(age);
						
						String pass = result.getString(5);
						String passWord=PersonSecurity.decrypt(pass);
						person.setPersonPassword(passWord);
					    
						

					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return person;
	}
}
