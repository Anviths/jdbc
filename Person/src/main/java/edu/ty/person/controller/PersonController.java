package edu.ty.person.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class PersonController {
	

	public static void insertPerson() {
		try {
			
			FileInputStream input = new FileInputStream("Person.properties");

			Properties properties = new Properties();
			properties.load(input);

			// step1
			String driverpath = properties.getProperty("path");
			Class.forName(driverpath);

			// step 2 connection
			String url = properties.getProperty("url");
			Connection con = DriverManager.getConnection(url, properties);

			// step 3 create statement
			String sql = "insert into person values(?,?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql);

			Scanner s = new Scanner(System.in);
				System.out.println("enter id");
				pstm.setInt(1, s.nextInt());
				System.out.println("enter name");
				pstm.setString(2, s.next());
				System.out.println("enter email");
				pstm.setString(3, s.next());
				System.out.println("enter phone");
				pstm.setLong(4, s.nextLong());
				System.out.println("enter password");
				pstm.setString(5, passwordGenerate(s.next()));
				System.out.println("enter age");
				pstm.setInt(6, s.nextInt());

				// step 4 execute query
				pstm.execute();
			}
			// step5 close
			con.close();
			System.out.println("thank you");
			s.close();

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

	
	}
	public static void main(String[] args) {
		// Generate a symmetric encryption key
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256); // Specify the key size (e.g., 128, 192, or 256 bits)
		SecretKey encryptionKey = keyGenerator.generateKey();
	}
}
