package com.shopify.inventorymanagement.utils;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

import java.util.Base64;    
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;   
import javax.crypto.SecretKey; 

import com.shopify.inventorymanagement.models.Users;

public class BaseMethods {
	
	public static String generateBatchNumber() {
		byte[] array = new byte[7]; // length is bounded by 7
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}
	
	public static Date generateDate() {
		return new Date();
	}
	
	public static Users generateStaticUser(Long userId) {
		Users user = new Users(userId);
		user.setFirstName("Anvi");
		user.setLastName("Parikh");
		user.setAddressLine1("401 Sunset Ave");
		user.setAddressLine2("");
		user.setCity("Windsor");
		user.setState("Ontario");
		user.setCountry("Canada");
		user.setZipCode("N9B 3P4");
		user.setEmail("anvi@uwindsor.ca");
		user.setPhone("519-253-3000");
		user.setEmailVerified(true);
		user.setPhoneVerified(true);
		user.setCreatedDate(generateDate());
		user.setUpdatedDate(generateDate());
		user.setPassword(encrypt("Test@123"));
		return user;
	}
	
	public static String encrypt(String plainText) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128); // block size is 128bits
			SecretKey secretKey = keyGenerator.generateKey();
			Cipher cipher = Cipher.getInstance("AES");
			byte[] plainTextByte = plainText.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedByte = cipher.doFinal(plainTextByte);
			Base64.Encoder encoder = Base64.getEncoder();
			String encryptedText = encoder.encodeToString(encryptedByte);
			return encryptedText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plainText;
	}

	public static String decrypt(String encryptedText) throws Exception {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128); // block size is 128bits
			SecretKey secretKey = keyGenerator.generateKey();
			Cipher cipher = Cipher.getInstance("AES");
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] encryptedTextByte = decoder.decode(encryptedText);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
			String decryptedText = new String(decryptedByte);
			return decryptedText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedText;
	}
}
