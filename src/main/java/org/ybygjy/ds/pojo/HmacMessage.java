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
System.out.println("body加密输入数据：" + bodyMessage.toJson());
		String bodyHmac = bodyMessage.toHmacData();
System.out.println("body加密输出数据：" + bodyHmac);
		String headerHmac = headerMessage.toHmacData();
		String hmacSrcData = headerHmac + ",\"body\":\"" + bodyHmac + "\""; 
System.out.println("签名输入数据：" + hmacSrcData);
		String rtnHmac = Sha256Util.encrypt(hmacSrcData);
System.out.println("签名输出数据：" + rtnHmac);
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
