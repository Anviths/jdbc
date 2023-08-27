package com.jdbc.ty;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PersonImageInsert {
	public static void main(String[] args) {

		String url="jdbc:postgresql://localhost:5432/Person?user=postgres&password=root";
        
		try {
			File f=new File("C:\\Users\\anwit\\Desktop\\kohilimage.jpeg");
			//step1
			Class.forName("org.postgresql.Driver");
			//step2
			Scanner s =new Scanner(System.in);
			Connection con =DriverManager.getConnection(url);
			//Step 3
			String sql="insert into imagetable values(?,?,?) ";
			PreparedStatement pstm =con.prepareStatement(sql);
			pstm.setInt(1, 2);
			pstm.setString(2, "virat");
			FileInputStream fs=new FileInputStream(f);
			pstm.setBinaryStream(3, fs);


        
			//step 4
			int n=pstm.executeUpdate();
			if(n==0) {
				System.out.println("data not inserted");
			}
			else System.out.println("data inserted");
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
}}
