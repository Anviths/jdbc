package edu.ty.personapp.view;

import java.util.Scanner;

import edu.ty.personapp.bean.Person;
import edu.ty.personapp.controller.PersonController;

public class PersonView {

	static Scanner read=new Scanner(System.in);
	public static void main(String[] args) {

		System.out.println("1.save data");
		System.out.println("2.fetch data by Id");
		System.out.println("3.fetch all data");
		System.out.println("4.update data");
		System.out.println("5.remove data");
		int choice=read.nextInt();
		switch (choice) {
		case 1:
			 Person person=createPerson();
			 boolean result=PersonController.savePerson(person);
			 showResult(result);
			break;
		case 2:
			
			PersonController.getRecordByID(read.nextInt());
			
			break;
		case 3:
			
			PersonController.getAllRecords();
			
			break;

		default:
			break;
		}
	}
	private static void showResult(boolean result) {

		if(result==true) {
			System.out.println("Sucessfull...!");
		}
		else {
			System.err.println("Unsucessfull...!");
		}
	}
	/***
	 * method to create a person object return 
	 * to caller
	 */
	private static Person createPerson() {

		Person person=new Person();
		System.out.println("enter person id");
		person.setPersonId(read.nextInt());
		System.out.println("enter the person name");
		read.nextLine();
		person.setPersonName(read.nextLine());
		System.out.println("enter the person email");
		person.setPersonEmail(read.next().toLowerCase());
		System.out.println("enter the phone number");
		person.setPersonPhoneNumber(read.nextLong());
		System.out.println("enter the password");
		person.setPersonPassword(PersonSecurity.encrypt(read.next()));
		System.out.println("enter the age");
		person.setPersonAge(read.nextInt());
		return person;
	}
	/**
	 * 
	 */
 
}
