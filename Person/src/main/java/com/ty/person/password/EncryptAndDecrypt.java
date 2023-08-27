package com.ty.person.password;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

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

public class EncryptAndDecrypt {

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
	public static String decrypt(String algo, SecretKey key, String cipherText, IvParameterSpec iv) {
		try {
			Cipher c = Cipher.getInstance(algo);
			c.init(Cipher.DECRYPT_MODE, key, iv);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String input = "";
	    SecretKey key = generateKey(256);
	    IvParameterSpec ivParameterSpec = genrateIv();
	    String algorithm = "AES/CBC/PKCS5Padding";
	    String cipherText = encrypt(algorithm,  key, input,ivParameterSpec);
	    System.out.println(cipherText);
	    SecretKey key1 = generateKey(256);
		String inputa=decrypt(algorithm, key1, cipherText, ivParameterSpec);
		System.out.println(inputa);
		
	}

}
