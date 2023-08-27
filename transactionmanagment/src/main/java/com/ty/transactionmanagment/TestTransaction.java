package com.ty.transactionmanagment;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con =ConnectionPool.getConnectionObject();
		
		String sql_book="insert into book_info values(1,'air india','bln','cnd')";
		String sql_user1="insert into passenger_info values(1,'raju',45)";
		String sql_user2="insert into passenger_info values(2,'rani',35)";
		String sql_user3="insert into passenger_info values(3,'manja',12)";
		String sql_pay="insert into payment_info values(1,250000,5000,'waiting')";
		
		try {
			con.setAutoCommit(false);
			Statement sm1= con.createStatement();
			sm1.execute(sql_book);
			
			Statement sm2= con.createStatement();
			sm2.execute(sql_user1);
			
			Statement sm3= con.createStatement();
			sm3.execute(sql_user2);
			
			Statement sm4= con.createStatement();
			sm4.execute(sql_user3);
			
			if(PaymentGatway.status()) {
			Statement sm5= con.createStatement();
			sm5.execute(sql_pay);
			con.commit();
			}
			else {
				con.rollback();
			}
			ConnectionPool.returnConnectionPool(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
;	}

}
