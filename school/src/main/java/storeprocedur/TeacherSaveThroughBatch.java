package storeprocedur;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TeacherSaveThroughBatch {

	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://localhost:5432/school";
		String user = "postgres";
		String password = "root";
		try {
			// step1
			Class.forName("org.postgresql.Driver");
			// step3
			Connection con = DriverManager.getConnection(url, user, password);

			// step3
			CallableStatement csm = con.prepareCall("call teacher_records(?,?,?,?,?,?,?)");
			csm.setInt(1, 7);
			csm.setString(2, "akshay");
			csm.setString(3, "male");
			csm.setInt(4, 22);
			csm.setString(5, "akshya@gmail.com");
			csm.setLong(6, 94482525210l);
			csm.setString(7, "A-");
			
			csm.addBatch();

			csm.setInt(1, 8);
			csm.setString(2, "sury");
			csm.setString(3, "male");
			csm.setInt(4, 27);
			csm.setString(5, "surya@gmail.com");
			csm.setLong(6, 94482527810l);
			csm.setString(7, "AB-");
			
			
			
			csm.addBatch();
			
			csm.setInt(1, 9);
			csm.setString(2, "preethi");
			csm.setString(3, "female");
			csm.setInt(4, 25);
			csm.setString(5, "preethi@gmail.com");
			csm.setLong(6, 98882525210l);
			csm.setString(7, "AB+");
			
			csm.addBatch();
			
			int[] a =csm.executeBatch();
			
			for(int i:a) {
				System.out.print
				(i);
			}
			con.close();
			System.out.println("data inserted");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
