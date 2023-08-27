package org.ty.userapp.dao;

import java.sql.SQLException;
import java.util.Scanner;

public class User {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserDAO user =new UserDAO();
		Scanner s =new Scanner(System.in);
		System.out.println("welcome to user database");

		while(true) {
			System.out.println();
			System.out.println("1.Insert the data");
			System.out.println("2.get data");
			System.out.println("3.update data");
			System.out.println("4.delete data");
			System.out.println("5.exit ");
			System.out.println("enter the choice");

			int choice = s.nextInt();
			switch(choice) {
			case 1:
			{
				System.out.println("enter id");
				int id=s.nextInt();
				System.out.println("enter name");
				String name=s.next();
				System.out.println("enter email");
				String email=s.next();
				System.out.println("enter phone number");
				long phone=s.nextLong();
				System.out.println("enter password");
				String pass=s.next() ;
				user.saveUser(id, name, email, phone, pass);
				break;
			}//end of case 1

			case 2:{
				System.out.println("enter the id to get data");
				user.findUserById(s.nextInt());
				break;
			}//end of case 2
			case 3:
			{
				System.out.println("enter id and password to be updated");
				try {
					user.updateUserById(s.nextInt(),s.next());
				} catch (SQLException e) {

					e.printStackTrace();
				}
				break;
			}//end of case 3
			case 4:{
				System.out.println("enter the id to delete");
				user.deleteUserById(s.nextInt());
				break;
			}
			case 5:{
				System.out.println("thank you");
				user.close();
				s.close();
				System.exit(0);

			}
			default :System.out.println("invalid choice");


			}//end of switch
		}//end of while
		
	}

}
