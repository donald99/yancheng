package org.ybygjy.ds.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 摘要算法为SHA-256
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class Sha256Util {
    //定义摘要算法为SHA-256
    private static final String SHA256 = "SHA-256";

    /**
     * 对字符串进行摘要,摘要算法使用SHA-256
     * @param bts 要加密的字符串的byte数组
     * @return 16进制表示的大写字符串 长度一定是8的整数倍
     */
    public static byte[] encrypt(byte[] bts) {
        MessageDigest md = null;
        byte[] result = null;
        try {
            md = MessageDigest.getInstance(SHA256);
            md.update(bts);
            result = md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return result;
    }

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     * @param strSrc 要加密的字符串
     * @return rtnValue
     */
    public static String encrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = new byte[0];
        try {
            bt = strSrc.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        try {
            md = MessageDigest.getInstance(Sha256Util.SHA256);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }
    private static String bytes2Hex(byte[] bytes) {
    	StringBuffer sbuf = new StringBuffer();
    	for(byte tbyte : bytes) {
    		String tmpStr = Integer.toHexString((tbyte & 0xFF));
    		if (tmpStr.length() == 1) {
    			tmpStr = "0" + tmpStr;
    		}
    		sbuf.append(tmpStr);
    	}
    	return sbuf.toString().toUpperCase();
    }
}
