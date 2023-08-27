package com.jdbc.ty;


	import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

	public class PersonInsertPre1 {
	    static final String KEY_FILE_PATH = "encryption_key.bin";
	    static final String KEY_ALGORITHM = "AES";
	    static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

	    public static void main(String[] args) {
//	    	insert();
			getData();
		}
	    public static void insert() {
	        try {
	            SecretKey key = retrieveKeyFromFile(KEY_FILE_PATH);
	            if (key == null) {
	                key = generateKey(256);
	                storeKeyToFile(key, KEY_FILE_PATH);
	                System.out.println("New encryption key generated and stored at: " + KEY_FILE_PATH);
	            }
                 
	            FileInputStream input = new FileInputStream("Person.properties");
	            Properties properties = new Properties();
	            properties.load(input);

	            String driverpath = properties.getProperty("path");
	            Class.forName(driverpath);

	            String url = properties.getProperty("url");
	            Connection con = DriverManager.getConnection(url, properties);

	            String sql = "insert into person values(?,?,?,?,?,?)";
	            PreparedStatement pstm = con.prepareStatement(sql);

	            Scanner s = new Scanner(System.in);

	            System.out.println("Enter id");
	            pstm.setInt(1, s.nextInt());
	            System.out.println("Enter name");
	            pstm.setString(2, s.next());
	            System.out.println("Enter email");
	            pstm.setString(3, s.next());
	            System.out.println("Enter phone");
	            pstm.setLong(4, s.nextLong());
	            System.out.println("Enter password");
	            String input1 = s.next();
	            pstm.setString(5, encrypt(CIPHER_ALGORITHM, key, input1));
	            System.out.println("Enter age");
	            pstm.setInt(6, s.nextInt());

	            pstm.execute();

	            con.close();
	            System.out.println("Thank you");
	            s.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public static SecretKey generateKey(int keySize) {
	        try {
	            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
	            keyGenerator.init(keySize);
	            return keyGenerator.generateKey();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static void storeKeyToFile(SecretKey key, String filePath) {
	        try (FileOutputStream output = new FileOutputStream(filePath)) {
	            byte[] encodedKey = key.getEncoded();
	            output.write(encodedKey);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static SecretKey retrieveKeyFromFile(String filePath) {
	        try (FileInputStream input = new FileInputStream(filePath)) {
	            byte[] encodedKey = input.readAllBytes();
	            return new SecretKeySpec(encodedKey, KEY_ALGORITHM);
	        } catch (IOException e) {
	            // Key file doesn't exist or couldn't be read
	            return null;
	        }
	    }

	    public static String encrypt(String algorithm, SecretKey key, String input) {
	        try {
	            byte[] iv = generateIv();
	            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

	            Cipher cipher = Cipher.getInstance(algorithm);
	            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
	            byte[] cipherText = cipher.doFinal(input.getBytes());

	            // Combine the IV and cipher text and encode as Base64
	            byte[] combined = new byte[iv.length + cipherText.length];
	            System.arraycopy(iv, 0, combined, 0, iv.length);
	            System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);
	            return Base64.getEncoder().encodeToString(combined);
	        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
	                InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static String decrypt(String algorithm, SecretKey key, String cipherText) {
	        try {
	            byte[] combined = Base64.getDecoder().decode(cipherText);
	            byte[] iv = new byte[16];
	            byte[] encryptedData = new byte[combined.length - 16];
	            System.arraycopy(combined, 0, iv, 0, iv.length);
	            System.arraycopy(combined, iv.length, encryptedData, 0, encryptedData.length);

	            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

	            Cipher cipher = Cipher.getInstance(algorithm);
	            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
	            byte[] decryptedBytes = cipher.doFinal(encryptedData);
	            return new String(decryptedBytes);
	        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
	                InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static byte[] generateIv() {
	        byte[] iv = new byte[16];
	        new SecureRandom().nextBytes(iv);
	        return iv;
	    }
	    public static void getData() {

			String url ="jdbc:postgresql://localhost:5432/Person?user=postgres&password=root";


			try {
				//step1 load  Driver
				Class.forName("org.postgresql.Driver");

				//step 2 establish a connection by single String argument
				Connection con =DriverManager.getConnection(url);
				Scanner s = new Scanner(System.in);
				//step 3 create a statement
				String sql="select * from person";

				Statement stm =con.createStatement();
				//step 4 execute
				ResultSet rs =stm.executeQuery(sql);

				while(rs.next()) {
					int id =rs.getInt(1);
					String name = rs.getString(2);
					String email = rs.getString(3);
					long phone =rs.getLong(4);
					int age = rs.getInt(6);
					String pass = rs.getString(5);
					
					SecretKey key = retrieveKeyFromFile(KEY_FILE_PATH);
		            if (key == null) {
		                key = generateKey(256);
		                storeKeyToFile(key, KEY_FILE_PATH);
		                System.out.println("New encryption key generated and stored at: " + KEY_FILE_PATH);
		            }
				    String inputa=decrypt(CIPHER_ALGORITHM, key, pass);
					

					System.out.println("id :"+id);
					System.out.println("name : "+name);
					System.out.println("email : "+email);
					System.out.println("phone number : "+phone);
					System.out.println("password : "+inputa);
					System.out.println("age : "+age);
					System.out.println("============================");

				}


				//step 5 close connection
				con.close();
				s.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	}


