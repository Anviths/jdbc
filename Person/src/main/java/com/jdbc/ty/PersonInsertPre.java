package com.jdbc.ty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PersonInsertPre {
	static final SecretKey key = generateKey(256);
    static final IvParameterSpec ivParameterSpec = genrateIv();
    static final String algorithm = "AES/CBC/PKCS5Padding";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileInputStream input = new FileInputStream("Person.properties") ;

			Properties properties = new Properties() ;
			properties.load(input);

			//step1
			String driverpath =properties.getProperty("path");
			Class.forName("org.postgresql.Driver") ;

			//step 2 connection
			String url=properties.getProperty("url");
			Connection con =DriverManager.getConnection(url,properties);

			//step 3 create statement
			String sql ="insert into person values(?,?,?,?,?,?)";
			PreparedStatement pstm =con.prepareStatement(sql);

			Scanner s=new Scanner(System.in);
			
				System.out.println("enter id");
				pstm.setInt(1, s.nextInt());
				System.out.println("enter name");
				pstm.setString(2, s.next());
				System.out.println("enter email");
				pstm.setString(3, s.next());
				System.out.println("enter phone");
				pstm.setLong(4, s.nextLong());
				System.out.println("enter password");
				String input1 = s.next();
			    
				pstm.setString(5, encrypt(algorithm,  key, input1,ivParameterSpec) );
				System.out.println("enter age");
				pstm.setInt(6, s.nextInt());



				//step 4 execute query
				pstm.execute();
				
			
			//step5 close
			con.close();
			System.out.println("thank you");
			s.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	// generate key 2 ways random key or passwordkey
		public static SecretKey generateKey(int n) {
			try {
				KeyGenerator genkey = KeyGenerator.getInstance("AES");// algo name
				genkey.init(n);// size of algorithm
				SecretKey key = genkey.generateKey();
				return key;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		// genrate key using password
		public static SecretKey genKeyByPass(String password, String salt) {
			try {
				SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
				KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
				SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
				System.out.println(key);
				return key;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		// generate IV
		public static IvParameterSpec genrateIv() {
			byte[] b = new byte[16];
			new SecureRandom().nextBytes(b);
			return new IvParameterSpec(b);
		}

		public static String encrypt(String algo, SecretKey key, String input, IvParameterSpec iv) {
			try {
				Cipher c = Cipher.getInstance(algo);
				c.init(Cipher.ENCRYPT_MODE, key, iv);
				byte[] cipherText = c.doFinal(input.getBytes());
				return Base64.getEncoder().encodeToString(cipherText);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    return null;
		}
		//decrypt
		public static String decrypt(String algo, SecretKey key, String cipherText) {
			try {
				Cipher c = Cipher.getInstance(algo);
				c.init(Cipher.DECRYPT_MODE, key);
				byte[] input=c.doFinal(Base64.getDecoder().decode(cipherText));
				return new String(input);

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
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
	    return null;
		}

		
}
