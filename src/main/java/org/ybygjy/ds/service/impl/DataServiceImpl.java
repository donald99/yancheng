package org.ybygjy.ds.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.ybygjy.ds.service.DataService;

/**
 * 服务实现
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class DataServiceImpl implements DataService {
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, String> applyKeys(Map<String, String> requestData) {
		Map<String, String> respData = new HashMap<String, String>();
		//参数较验
		///header
		///body
		///签名
		//组织报文
		///header
		///body
		///签名
		//加密签名
		//通信传输
		return respData;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, String> checkPersonId(Map<String, String> requestData) {
		Map<String, String> respData = new HashMap<String, String>();
		return respData;
	}

}
