package com.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ItemConnect2 {

	public static void main(String[] args) {
		
		String URL="jdbc:postgresql://localhost:5432/Item?user=postgres&password=root";
		
		
		
		try {
			//step1 load/register driver
			Class.forName( "org.postgresql.Driver");
			
			//step2 establish a connection
			
			Connection con=DriverManager.getConnection(URL) ;
			
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
		}

	}
}
