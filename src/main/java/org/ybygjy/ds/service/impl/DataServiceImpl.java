package org.ybygjy.ds.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.ybygjy.ds.constant.Constants;
import org.ybygjy.ds.pojo.HmacMessage;
import org.ybygjy.ds.pojo.RequestBodyMessage4ApplyKeys;
import org.ybygjy.ds.pojo.RequestBodyMessage4CheckPersonal;
import org.ybygjy.ds.pojo.RequestHeaderMessage;
import org.ybygjy.ds.service.DataService;
import org.ybygjy.ds.utils.HttpClientUtils;

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
		sbuf.append("{\"head\":").append(rehAppkeys.toJson()).append(",\"body\":").append(reqAppKeys.toJson()).append(",").append(hmacMessage.toJson()).append("}");
System.out.println("组织的报文数据：" + sbuf.toString());
		return this.innerSend(Constants.SERV_URL_APPLYKEYS, sbuf.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, String> checkPersonId(Map<String, String> requestData) {
		RequestHeaderMessage reqMes = new RequestHeaderMessage();
		reqMes.parseMap(requestData);
		RequestBodyMessage4CheckPersonal reqCheckPersonal = new RequestBodyMessage4CheckPersonal();
		reqCheckPersonal.parseMap(requestData);
		HmacMessage hmacMessage = new HmacMessage(reqMes, reqCheckPersonal);
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("{\"head:\"").append(reqMes.toJson()).append(",\"body:\"").append(reqCheckPersonal.toJson()).append(",").append(hmacMessage.toJson()).append("}");
System.out.println("组织的报文数据：" + sbuf.toString());
		return this.innerSend(Constants.SERV_URL_CHECKPERSONAL, sbuf.toString());
	}
	private Map<String, String> innerSend(String url, String text) {
		Map<String, String> reqData = new HashMap<String, String>();
		//通信传输
		String responsePlainText = HttpClientUtils.doRequest(url, text);
System.out.println("响应内容:" + responsePlainText);
		if (null == responsePlainText) {
			reqData.put("error_code", "20001");
			reqData.put("error_message", "申请Key失败!");
		} else {
			reqData.put("error_code", "0000");
			reqData.put("error_message", "");
			reqData.put("result", responsePlainText);
		}
		return reqData;
	}
}
