package com.manageTeam.global.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;

public class AESUtil {
	public static String alg = "AES/CBC/PKCS5Padding";
    private static final String key = "12345678910111213";
    private static final String iv = key.substring(0, 16); // 16byte

    /**
     * @descreption: AES256 암호화메소드
     * @Request: String text (평문 데이터)
     * @author skhan
     * */
    public static String encrypt(String text){
    	try {
    		Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.ENCRYPT_ERROR);
		}
    }

    /**
     * @descreption: AES256 복호화 메소드
     * @Request: String cipherText (암호화 데이터)
     * @author skhan
     * */
    public static String decrypt(String cipherText){
    	try {
    		Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.DECRYPT_ERROR);
		}
    }
   
}
