package com.ty.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TeacherInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url="jdbc:postgresql://localhost:5432/Employee";
		String user = "postgres" ;
		String password = "root" ;
		
		//step 1
		try {
			Class.forName("org.postgresql.Driver");
			
			//step 2
			Connection con =DriverManager.getConnection(url,user,password);
			System.out.println(con);
			
String sql1 = "INSERT INTO teacher VALUES(1,'sheela' ,'sheela@gmail.com', 8523697411,'engilsh' ,'17000' ,'assiatant teacher','government high school')";
String sql2 = "INSERT INTO teacher VALUES(2,'radha' ,'radha@gmail.com', 8523456413,'kannada' ,'19000' ,'Principal','government high school')";
String sql3 = "INSERT INTO teacher VALUES(3,'mahesh' ,'mahesh@gmail.com', 69875697411,'PT' ,'28000' ,'Physical trainer','government high school')";
String sql4 = "INSERT INTO teacher VALUES(4,'farad' ,'farad@gmail.com', 8523678511,'maths' ,'25000' ,'vice Principal','government high school')";
String sql5 = "INSERT INTO teacher VALUES(5,'priya' ,'priya@gmail.com', 7483697411,'science' ,'20000' ,'assosiate teacher','government high school')";

		
			//step 3
			Statement stm =con.createStatement();
			
			//step 4
			stm.execute(sql5);
			
			//step 5
			con.close();
			System.out.println("data inserted");
			
		} 
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
