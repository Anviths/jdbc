package com.jdbc.ty;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

class AesExample{
	private static final String secretkey="123456789";
	private static final String saltValue="abcdefg";
	
	public static String encrypt(String strToEnp) {
		
		try {
			byte[] iv= new byte[16];
			IvParameterSpec ivspec=new IvParameterSpec(iv);
			SecretKeyFactory factory=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec=new PBEKeySpec(secretkey.toCharArray(),saltValue.getBytes(),65536,256);
			SecretKey tmp = factory.generateSecret(spec);  
		      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
		      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
		      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);  
		      /* Retruns encrypted value. */  
		      return Base64.getEncoder()  
		.encodeToString(cipher.doFinal(strToEnp.getBytes(StandardCharsets.UTF_8)));  
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
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
	
}
public class Encrypt {
	public static void main(String[] args) {
		/* Message to be encrypted. */  
        String originalval = "AES Encryption";  
        /* Call the encrypt() method and store result of encryption. */  
        String encryptedval = AesExample.encrypt(originalval);  
        /* Call the decrypt() method and store result of decryption. */  
//        String decryptedval = decrypt(encryptedval);  
        /* Display the original message, encrypted message and decrypted message on the console. */  
        System.out.println("Original value: " + originalval);  
        System.out.println("Encrypted value: " + encryptedval);  
//        System.out.println("Decrypted value: " + decryptedval); 
	}

}
