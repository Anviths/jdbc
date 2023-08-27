package edu.ty.person.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrpy {

	public static void main(String[] args) {
		try {
		//16 digit key
		String key="qwe?tyuiopas1fgh";
		String password="12345";
		SecretKey seckey=new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
			
			//encrypt
			cipher.init(Cipher.ENCRYPT_MODE, seckey);
			byte[] encpt=cipher.doFinal(password.getBytes());
			System.out.println("encp "+new String(encpt));
			
			//dycpt
			cipher.init(Cipher.DECRYPT_MODE, seckey);
			byte[] decpr=cipher.doFinal(encpt);
			System.out.println("dycpt "+new String(decpr));
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//encrypt

	}

}
