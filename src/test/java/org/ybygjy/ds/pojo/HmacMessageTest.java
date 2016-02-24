package org.ybygjy.ds.pojo;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HmacMessageTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetHmac() {
		RequestHeaderMessage headerMessage = new RequestHeaderMessage();
		headerMessage.setAction('0');
		headerMessage.setChannel('0');
		headerMessage.setTransactionCode("CF00000001");
		headerMessage.setUserAccountID("CF00000001");
		headerMessage.setAuthorizationID("11111111");
		headerMessage.setTransactionID("BT7D6drYfN0hqUkQWAO");
		RequestBodyMessage4ApplyKeys bodyMessage = new RequestBodyMessage4ApplyKeys();
		bodyMessage.setKeyType("0");
		bodyMessage.setValidationTime('0');
		bodyMessage.setLastKeyGenTime("11111111");
		bodyMessage.setLastKey("11111");
		
	}

	@Test
	public void testToJson() {
		fail("Not yet implemented");
	}

}
