package org.ybygjy.ds.pojo;

import java.util.Map;

import org.ybygjy.ds.utils.Sha256Util;

/**
 * 报文签名
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class HmacMessage implements Message {
	private HeaderMessage headerMessage;
	private BodyMessage bodyMessage;
	/**
	 * construction
	 * @param headerMessage
	 * @param bodyMessage
	 */
	public HmacMessage(HeaderMessage headerMessage, BodyMessage bodyMessage) {
		super();
		this.headerMessage = headerMessage;
		this.bodyMessage = bodyMessage;
	}

	/**
	 * 获取签名
	 * @return rtnHmac
	 */
	public String getHmac() {
System.out.println("body签名原始数据：" + bodyMessage.toJson());
		String bodyHmac = bodyMessage.toHmacData();
		String headerHmac = headerMessage.toHmacData();
		String hmacSrcData = headerHmac + ",\"body\":\"" + bodyHmac + "\""; 
System.out.println("计算签名数据：" + hmacSrcData);
		String rtnHmac = Sha256Util.encrypt(hmacSrcData);
System.out.println("计算签名数据=>SHA256：" + rtnHmac);
		return rtnHmac;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toJson() {
		return "\"hashValue\":\"" + this.getHmac() + "\"";
	}

	public String toHmacData() {
		return null;
	}
	public void parseMap(Map<String, String> requestData) {
	}
}
