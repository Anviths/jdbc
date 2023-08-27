package com.ty;

import java.util.Scanner;

public class SqlCRUDOperation {

	// insert the query
	static String sql;
	public static String insert() {
		Scanner s =new Scanner(System.in);
		// take number of data to inserted
		
		
		System.out.println("enter id");
		int id =s.nextInt() ;
		System.out.println("enter item name ");
		String name = s.next();
		System.out.println("the the quantity");
		double quantity = s.nextDouble() ;
		System.out.println("enter price");
		double price =s.nextDouble() ;
		System.out.println("enter the manufacturer");
		
		String manufacturer =s.next() ;
		
		sql="insert into item values("+id+",'"+name+"','"+quantity+"','"+price+"','"+manufacturer+"') ";
		
		return sql;
	}
	public static String update(){
		Scanner s= new Scanner(System.in) ;
		System.out.println("enter the colname to be updated");
		String colname=s.next();
		System.out.println("enter the value for column to update");
		String value=s.next();
		
		if(colname=="id") {
			int values=Integer.parseInt(value);
			System.out.println("enter the  column name to where update");
			String colname11=s.next();
			System.out.println("enter the value for where column ");
			String value1=s.next();
			if(colname11=="id") {
				int valueint=Integer.parseInt(value1);
				sql="update item set "+colname+"='"+values+"' where "+colname11+"='"+valueint+"'";
		           return sql;
		           
			}
			else if(colname11=="price" || colname11=="quantity") {
				int valueint=Integer.parseInt(value1);
				sql="update item set "+colname+"='"+values+"' where "+colname11+"='"+valueint+"'";
		           return sql;
			}
			
			sql="update item set "+colname+"='"+values+"' where "+colname11+"='"+value1+"'";
           return sql;
		}
		
		//set column name with double parameter
		else if(colname=="quantity" || colname=="price") {
			double values=Double.parseDouble(value);
			System.out.println("enter the  column name to where update");
			String colname11=s.next();
			System.out.println("enter the value for where column ");
			String value1=s.next();
			sql="update item set "+colname+"='"+values+"' where "+colname11+"='"+value1+"'";
           return sql;
		}
		else {
			System.out.println("enter the  column name to where update");
			String colname11=s.next();
			System.out.println("enter the value for where column ");
			String value1=s.next();
		sql="update item set "+colname+"='"+value+"' where "+colname11+"='"+value1+"'";
		return sql;
		}
	}

}
