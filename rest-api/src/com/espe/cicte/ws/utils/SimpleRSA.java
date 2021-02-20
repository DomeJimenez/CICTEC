package com.espe.cicte.ws.utils;

import java.security.PrivateKey;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;

public class SimpleRSA {
	public static final String ALGORITHM = "RSA";
	private static final String UTF_8 = "UTF-8";
	private BASE64Decoder decoder = new BASE64Decoder();
	
	public String decrypt(String cipherText,PrivateKey privateKey) throws Exception{	
		byte[] encryptedData = decoder.decodeBuffer(cipherText);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] plainText = cipher.doFinal(encryptedData);
		return new String(plainText, UTF_8);
	}	
}
