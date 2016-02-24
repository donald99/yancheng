package org.ybygjy.ds.service.impl;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ybygjy.ds.service.DataService;

public class DataServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApplyKeys() {
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("rhm_action", "0");
		requestData.put("rhm_authid", "11111111");
		requestData.put("rhm_channel", "0");
		requestData.put("rhm_transcode", "CF00000001");
		requestData.put("rhm_transid", "BT7D6drYfN0hqUkQWAO");
		requestData.put("rhm_useraccid", "CF00000001");
		requestData.put("rbm_keytype", "0");
		requestData.put("rbm_lastkey", "11111");
		requestData.put("rbm_lastkeygentime", "11111111");
		requestData.put("rbm_validationtime", "0");
		DataService dataService = new DataServiceImpl();
		Map<String, String> responseData = dataService.applyKeys(requestData);
		System.out.println(responseData);
	}

	@Test
	public void testCheckPersonId() {
		fail("Not yet implemented");
	}

}
