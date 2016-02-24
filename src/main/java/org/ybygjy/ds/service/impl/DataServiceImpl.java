package org.ybygjy.ds.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.ybygjy.ds.pojo.HmacMessage;
import org.ybygjy.ds.pojo.RequestBodyMessage4ApplyKeys;
import org.ybygjy.ds.pojo.RequestHeaderMessage;
import org.ybygjy.ds.service.DataService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

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
		RequestHeaderMessage rehAppkeys = new RequestHeaderMessage();
		rehAppkeys.parseMap(requestData);
		///body
		RequestBodyMessage4ApplyKeys reqAppKeys = new RequestBodyMessage4ApplyKeys();
		reqAppKeys.parseMap(requestData);
		///签名
		HmacMessage hmacMessage = new HmacMessage(rehAppkeys, reqAppKeys);
		//组织报文
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("{").append(rehAppkeys.toJson()).append(",").append(reqAppKeys.toJson()).append(",").append(hmacMessage.toJson()).append("}");
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
