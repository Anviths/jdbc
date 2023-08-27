package edu.ty.person.controller;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class JavaAes {
    private static final String AES_ALGORITHM = "AES";
    private static final int AES_KEY_SIZE = 256;

    public static void main(String[] args) {
        try {
            String plaintext = "Hello, World!";
            String keyString = "mySecretKey123456789"; // The key should be 16, 24, or 32 bytes long for AES-128, AES-192, or AES-256, respectively
             
            byte[] encryptedBytes = encrypt(plaintext, keyString);
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decrypt(encryptedBytes, keyString);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] encrypt(String plaintext, String keyString) throws Exception {
        SecretKey secretKey = new SecretKeySpec(keyString.getBytes(), "AES") ;
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
    }

    private static String decrypt(byte[] ciphertext, String keyString) throws Exception {
        SecretKey secretKey = new SecretKeySpec(keyString.getBytes(), "AES") ;
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static SecretKey generateKey(String keyString) throws NoSuchAlgorithmException {
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGenerator.init(AES_KEY_SIZE, new SecureRandom(keyBytes));

        return new SecretKeySpec(keyGenerator.generateKey().getEncoded(), AES_ALGORITHM);
    }
}
