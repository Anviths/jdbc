package edu.ty.personapp.view;

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

public class PersonSecurity {
//enter the secretkey and saltvlaue
	private static final String secretkey = "123456789";
	private static final String saltValue = "abcdefg";

public static String encrypt(String strToEnp) {
		
		try {
			//create iv of 16 size array
			byte[] iv= new byte[16];
			//create a instance of iv by using iv parameterspec class
			IvParameterSpec ivspec=new IvParameterSpec(iv);
			//create a instance of secretekeyfactory using algo
			SecretKeyFactory factory=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			//generate pbkey
			KeySpec spec=new PBEKeySpec(secretkey.toCharArray(),saltValue.getBytes(),65536,256);
			//genrate key
			SecretKey tmp = factory.generateSecret(spec);  
			
		      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
		      //create cipher
		      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
		      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);  
		      /* Retruns encrypted value. */  
		      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEnp.getBytes(StandardCharsets.UTF_8)));  
			
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
public static String decrypt(String strToDecrypt)   
{  
try   
{  
  /* Declare a byte array. */  
  byte[] iv = new byte[16];  
  IvParameterSpec ivspec = new IvParameterSpec(iv);  
  /* Create factory for secret keys. */  
  SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
  /* PBEKeySpec class implements KeySpec interface. */  
  KeySpec spec = new PBEKeySpec(secretkey.toCharArray(), saltValue.getBytes(), 65536, 256);  
  SecretKey tmp = factory.generateSecret(spec);  
  SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
  Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");  
  cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);  
  /* Retruns decrypted value. */  
  return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));  
}   
catch (InvalidAlgorithmParameterException  e)   
{  
  System.out.println("Error occured during decryption: " + e.toString());  
} catch (NoSuchAlgorithmException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IllegalBlockSizeException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (BadPaddingException e) {
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
}  
return null;  
}  
 

}
