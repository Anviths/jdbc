package storeprocedur;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "jdbc:postgresql://localhost:5432/school";
		String user = "postgres";
		String password = "root";

		// step1
		try {
			Class.forName("org.postgresql.Driver");

			//	step2	
			Connection con = DriverManager.getConnection(url, user, password);
  
			String sql="select count_teacher(?)";
			CallableStatement cs =con.prepareCall(sql);
			cs.setString(1, "female");
			
			ResultSet rs =cs.executeQuery();
			
			rs.next();
			System.out.println(rs.getInt(1));
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// step3
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
