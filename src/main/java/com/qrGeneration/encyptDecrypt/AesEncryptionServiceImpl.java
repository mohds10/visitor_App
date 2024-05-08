package com.qrGeneration.encyptDecrypt;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Component
public class AesEncryptionServiceImpl {

	private static final String SECRET_KEY = "123"; // Change this with your secret key

	public String encrypt(String plainText) {
		try {
			Key key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String decrypt(String cipherText) {
		try {
			Key key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
			return new String(decryptedBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String args[]) {

		String str = new AesEncryptionServiceImpl().encrypt("https://hanzapps.oc.holcim.net/vima/20240507-121242");

		System.out.println(str);
	}

}
