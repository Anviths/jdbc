package storeprocedur;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SaveTeacherRecord {

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
			csm.setInt(1, 6);
			csm.setString(2, "govind");
			csm.setString(3, "male");
			csm.setInt(4, 39);
			csm.setString(5, "govinda@gmail.com");
			csm.setLong(6, 9448645210l);
			csm.setString(7, "O+");

			csm.execute();
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
