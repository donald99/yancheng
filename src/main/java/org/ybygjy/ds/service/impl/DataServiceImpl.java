package org.ybygjy.ds.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ybygjy.ds.constant.Constants;
import org.ybygjy.ds.pojo.HmacMessage;
import org.ybygjy.ds.pojo.RequestBodyMessage4ApplyKeys;
import org.ybygjy.ds.pojo.RequestBodyMessage4CheckPersonal;
import org.ybygjy.ds.pojo.RequestHeaderMessage;
import org.ybygjy.ds.service.DataService;
import org.ybygjy.ds.utils.HttpClientUtils;
import org.ybygjy.ds.utils.Sha256Util;

/**
 * 服务实现
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class DataServiceImpl implements DataService {
	/**logger*/
	private static Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);
	/**
	 * {@inheritDoc}
	 */
	public Map<String, String> applyKeys(Map<String, String> requestData) {
		logger.debug("接收Key申请请求#{}", requestData);
		//参数较验
		///header
		RequestHeaderMessage rehAppkeys = new RequestHeaderMessage();
		rehAppkeys.parseMap(requestData);
		///body
		RequestBodyMessage4ApplyKeys reqAppKeys = new RequestBodyMessage4ApplyKeys();
		reqAppKeys.parseMap(requestData);
		///签名
		String srcHMAC = "\"head\":" + rehAppkeys.toJson() + ",\"body\":" + reqAppKeys.toJson();
		logger.debug("处理Key申请请求#原始SHA256#{}", srcHMAC);
		srcHMAC = Sha256Util.encrypt(srcHMAC);
		logger.debug("处理Key申请请求#最终SHA256#{}", srcHMAC);
		//组织报文
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("{\"head\":").append(rehAppkeys.toJson()).append(",\"body\":").append(reqAppKeys.toJson()).append(",\"hashValue\":\"").append(srcHMAC).append("\"}");
		logger.debug("处理Key申请请求#组织完成的报文数据{}", sbuf.toString());
		Map<String, String> rtnData = this.innerSend(Constants.SERV_URL_APPLYKEYS, sbuf.toString());
		logger.debug("完成Key申请请求#结果数据{}", rtnData);
		return rtnData;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, String> checkPersonId(Map<String, String> requestData) {
		logger.debug("接收身份认证请求#{}", requestData);
		RequestHeaderMessage reqHeader = new RequestHeaderMessage();
		reqHeader.parseMap(requestData);
		RequestBodyMessage4CheckPersonal reqCPBody = new RequestBodyMessage4CheckPersonal();
		reqCPBody.parseMap(requestData);
		reqCPBody.setKeys(requestData.get("sys_ctx_key"));
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("{\"head\":").append(reqHeader.toJson()).append(",\"body\":\"").append(reqCPBody.toHmacData()).append("\",").append(new HmacMessage(reqHeader, reqCPBody).toJson()).append("}");
		logger.debug("处理身份认证请求,组织的报文数据#{}", sbuf);
		Map<String, String> rtnData = this.innerSend(Constants.SERV_URL_CHECKPERSONAL, sbuf.toString());
		logger.debug("处理身份认证请求,响应信息#{}", rtnData);
		return rtnData;
	}
	private Map<String, String> innerSend(String url, String text) {
		Map<String, String> reqData = new HashMap<String, String>();
		//通信传输
		String responsePlainText = HttpClientUtils.doRequest(url, text);
		logger.debug("通信传输,响应内容#{}", responsePlainText);
		if (null == responsePlainText) {
			reqData.put("error_code", "20001");
			reqData.put("error_message", "验证失败!");
		} else {
			reqData.put("error_code", "0000");
			reqData.put("error_message", "");
			reqData.put("result", responsePlainText);
		}
		return reqData;
	}
}
