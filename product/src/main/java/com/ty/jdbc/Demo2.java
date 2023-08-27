package com.ty.jdbc;

public class Demo2 {

	public static void main(String[] args) {
		
		try {
			Class.forName("com.ty.jdbc.Demo1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
