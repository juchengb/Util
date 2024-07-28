package com.example.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	
	private static final String ALGORITHM = "AES";
	private static final String SECRET_KEY = "myCrytoSecretKey"; // 16 words
	private static SecretKeySpec keySpec;

	static {
		try {
			keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize AES secret key", e);
		}
	}

	public String encrypt(String plainPassword) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] encrypted = cipher.doFinal(plainPassword.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public String decrypt(String encryptedPassword) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword);
		byte[] decryptedPassword = cipher.doFinal(decodedPassword);
		return new String(decryptedPassword);
	}
}
