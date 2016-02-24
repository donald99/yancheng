package org.ybygjy.ds.pojo;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 单元测试
 * @author WangYanCheng
 * @version 2016年2月25日
 */
public class RequestBodyMessage4CheckPersonalTest {
	private Map<String, String> requestData;
	@Before
	public void setUp() throws Exception {
		requestData = new HashMap<String, String>();
		requestData.put("rbm_identitynumber", "348888666688886666");
		requestData.put("rbm_identitytype", "1");
		requestData.put("rbm_personname", "CFCA");
		requestData.put("rbm_address", "上海市东方路");
		requestData.put("rbm_email", "abc@163.com");
		requestData.put("rbm_cardnumber", "629999753596976789");
		requestData.put("rbm_cellphonenumber", "13888886666");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToHmacData() {
		RequestBodyMessage4CheckPersonal reqPersonal = new RequestBodyMessage4CheckPersonal();
		reqPersonal.parseMap(requestData);
		System.out.println(reqPersonal.toHmacData());
	}

	@Test
	public void testToJson() {
		RequestBodyMessage4CheckPersonal reqPersonal = new RequestBodyMessage4CheckPersonal();
		reqPersonal.parseMap(requestData);
		System.out.println(reqPersonal.toJson());
	}

}
