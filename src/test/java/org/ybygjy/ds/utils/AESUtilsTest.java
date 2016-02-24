package org.ybygjy.ds.utils;

import static org.junit.Assert.fail;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AESUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEncrypt() {
//		String headerStr = "\"head\":{\"chanel\":\"0\",\"transactionCode\":\"CF00000001\",\"userAccountID\":\"CF00000001\",\"action\":\"0\",\"authorizationID\":\"11111111\",\"transactionID\":\"BT7D6drYfN0hqUkQWAO\"}";
//		String bodyStr = "{\"keyType\":\"0\",\"validationTime\":\"0\",\"lastKeyGenTime\":\"11111111\",\"lastKey\":\"11111\"}";
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
		
	}

	@Test
	public void testParseByte2HexStr() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseHexStr2Bytes() {
		fail("Not yet implemented");
	}
}
