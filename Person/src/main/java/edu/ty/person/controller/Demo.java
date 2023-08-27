package edu.ty.person.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Demo {
public static void main(String[] args) {
	try {
		String password="1235466";
		MessageDigest md=MessageDigest.getInstance("SHA-224");
		byte[] messageDigest=md.digest(password.getBytes());
		BigInteger bigINT=new BigInteger(1,messageDigest);
		System.out.println(bigINT.toString(16));
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
