package org.ybygjy.ds.utils;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class AESUtils {
	private static String ALG_DEF = "AES";
	/**
	 * AES加密
	 * @param text 明文
	 * @param key 密钥,AES固定格式为128/192/256，16/24/32bytes
	 * @param iv 初始向量,初始化向量，AES为16bytes;DES为8bytes
	 * @return rtnBytes
	 */
	public static byte[] doEncrypt(String text, String key, String iv) {
		//明文
		//私钥字节组，加密方式
		Key keySpec = new SecretKeySpec(key.getBytes(), AESUtils.ALG_DEF);
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
		try {
			Cipher chipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//初始化，三种方式
			//1.无第三参数
			//2.第三参数为SecureRandom(AES不可采用)
			//3.采用IvParameterSpec
			chipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			//加密操作,返回加密后的字节数组，需要编码{Base64,HEX,UUE,7bit等}
			byte[] bytes = chipher.doFinal(text.getBytes());
			return bytes;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解密
	 * @param text 密文
	 * @param key 密钥
	 * @param iv 初始向量
	 * @return
	 */
	public static String doDecrypt(String text, String key, String iv) {
		byte[] bytes = Base64Utils.decode(text);
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
		Key keyInst = new SecretKeySpec(key.getBytes(), ALG_DEF);
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, keyInst, ivSpec);
			byte[] retBytes = cipher.doFinal(bytes);
			return new String(retBytes, Charset.forName("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
