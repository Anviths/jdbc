package edu.ty.person.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Enc {
	public static void main(String[] args) {
        try {
            // Provide your own secret key
            byte[] customKeyBytes = "ThisIsySecretKey".getBytes();
            SecretKey customKey = new SecretKeySpec(customKeyBytes, "AES");

            // Create a Cipher instance for encryption and decryption
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Encrypt
            String plainText = "1234";
            cipher.init(Cipher.ENCRYPT_MODE, customKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            System.out.println("Encrypted: " + new String(encryptedBytes));

            // Decrypt
            cipher.init(Cipher.DECRYPT_MODE, customKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            System.out.println("Decrypted: " + new String(decryptedBytes));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidKeyException | IllegalBlockSizeException |
                BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
