package org.ybygjy.ds.utils;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ybygjy.ds.constant.Constants;

public class AESUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEncrypt() {
		String headerStr = "\"head\":{\"transactionID\":\"2J5uvSN0v5A0mPAPcB5\",\"transactionCode\":\"CF209b0010\",\"action\":\"0\",\"chanel\":\"0\",\"userAccountID\":\"CF00000001\",\"authorizationID\":\"11111111\"}";
		String bodyStr = "{\"identityType\":\"1\",\"address\":\"上海市东方路\",\"email\":\"abc@163.com\",\"cellPhoneNumb\":\"13888886666\",\"identityNumber\":\"348888666688886666\",\"personName\":\"CFCA\",\"cardNumber\":\"629999753596976789\"}";
//		String key = "SDGr4234gG465356";
//		String key = "iovK4ZEZez0oPKcs";
		String key = "9HhcDC1OOrPf7uwc";
		String iv = "Xadiapdfaxi0s91D";
		byte[] encryptedBody = AESUtils.doEncrypt(bodyStr, key, iv);
		String base64Str = new String(Base64.encodeBase64(encryptedBody));
		System.out.println(base64Str);
		String hmacStr = headerStr + ",\"body\":\"" + base64Str + "\"";
System.out.println(hmacStr);
		hmacStr = Sha256Util.encrypt(hmacStr);
		System.out.println(hmacStr);
	}
	@Test
	public void testDecrypt() {
		String srcBase64 = "LLi/UTtmmsVCRe+vRQpSVDap4HgtnSUDGxaL+u8xpko=";
		String keys = "SDGr4234gG465356";
		String decryptText = AESUtils.doDecrypt(srcBase64, keys, Constants.SERV_IV);
		System.out.println("密文：" + srcBase64);
		System.out.println("解密：" + decryptText);
	}
}
