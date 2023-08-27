package com.ty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ItemConnection3 {

	public static void main(String[] args) {
		
	
		
		try {
			//create a instant of file stream which helps file to read this class throws 
			//file not found exception
			FileInputStream input =new FileInputStream("item.properties");
			//properties class present in java util package
			//create instance the that loads method 
			Properties properties =new Properties();
			properties.load(input);
			
			//load the driverpath using properties file
			String driverpath =properties.getProperty("driverpath");
			
			//load url for properties file
			String URL =properties.getProperty("url") ;
			//step1 load/register driver
			Class.forName( driverpath);
			
			
			//step2 establish a connection
			
			Connection con=DriverManager.getConnection(URL,properties) ;
			
			//step 3 create statement
			Statement stm= con.createStatement() ;
			
			//step 4 crud operation
			Scanner s=new Scanner(System.in) ;
			System.out.println("1.insert\n2.update\n3.delete");
			System.out.println("enter option");
			int choice =s.nextInt();
			switch(choice) {
			case 1:{
				System.out.println("enter number of data to be inserted");
				int n=s.nextInt() ;
				for(int i=1;i<=n;i++) {
				stm.execute(SqlCRUDOperation.insert());
				}
				break;
			}
			case 2:{
				String sql="update item set manufacturer='oneplus' where id='102'";
				stm.execute(sql);
				System.out.println("data updated");
				break;
			}
			case 3:{
				String sql="delete from item   where id='105'";
				stm.execute(sql);
				System.out.println("data deleted");
				
				break;
			}
			
			default:System.out.println("invalid choice");
			}
			
			
			
			//step 5
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
