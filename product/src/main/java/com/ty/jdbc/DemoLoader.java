package com.ty.jdbc;

public class DemoLoader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver class is loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
