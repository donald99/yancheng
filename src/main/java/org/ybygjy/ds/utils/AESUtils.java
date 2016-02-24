package org.ybygjy.ds.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class AESUtils {
	private static String ALG_DEF = "AES";
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(AESUtils.ALG_DEF);
			keyGenerator.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encodeFmt = secretKey.getEncoded();
			SecretKeySpec secretKeySpec = new SecretKeySpec(encodeFmt, AESUtils.ALG_DEF);
			Cipher cipher = Cipher.getInstance(AESUtils.ALG_DEF);
			byte[] encryptContent = content.getBytes(Charset.forName("UTF-8"));
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] result = cipher.doFinal(encryptContent);
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
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
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(AESUtils.ALG_DEF);
			keyGenerator.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encodeFmt = secretKey.getEncoded();
			SecretKeySpec sksInst = new SecretKeySpec(encodeFmt, AESUtils.ALG_DEF);
			Cipher cipher = Cipher.getInstance(AESUtils.ALG_DEF);
			cipher.init(Cipher.DECRYPT_MODE, sksInst);
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
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
	public static String parseByte2HexStr(byte[] buff) {
		StringBuffer sbuf = new StringBuffer();
		for(int i = 0; i < buff.length; i++) {
			String hexStr = Integer.toHexString(buff[i] & 0xFF);
			if (hexStr.length() == 1) {
				hexStr = '0' + hexStr;
			}
			sbuf.append(hexStr.toUpperCase());
		}
		return sbuf.toString();
	}
	public static byte[] parseHexStr2Bytes(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length()/2];
		for(int i = 0; i < hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte)(high * 16 + low);
		}
		return result;
	}
	public static byte[] anotherEncrypt(String content, String password) {
		SecretKeySpec sksInst = new SecretKeySpec(password.getBytes(), AESUtils.ALG_DEF);
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, sksInst);
			byte[] result = cipher.doFinal(byteContent);
			return result;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
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
	public static byte[] anotherDecrypt(byte[] bytes, String password) {
		SecretKeySpec sksInst = new SecretKeySpec(password.getBytes(), AESUtils.ALG_DEF);
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, sksInst);
			byte[] result = cipher.doFinal(bytes);
			return result;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
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
	/**
	 * 测试入口
	 * @param args 参数列表
	 */
	public static void main(String[] args) {
		String content = "1111111111111111";
		String password = "1234567812345678";
//		byte[] encryptBytes = AESUtils.encrypt(content, password);
//		String encryptResultStr = parseByte2HexStr(encryptBytes);
//		System.out.println("加密后：" + encryptResultStr);
		byte[] encryptBytes;
		String encryptResultStr = parseByte2HexStr(AESUtils.anotherEncrypt(content, password));
		System.out.println("加密后：" + encryptResultStr);
		encryptBytes = parseHexStr2Bytes(encryptResultStr);
		encryptBytes = AESUtils.anotherDecrypt(encryptBytes, password);
		System.out.println("解密后：" + new String(encryptBytes));
	}
}
