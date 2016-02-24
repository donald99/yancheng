package org.ybygjy.ds.pojo;

import org.ybygjy.ds.constant.Constants;
import org.ybygjy.ds.utils.AESUtils;
import org.ybygjy.ds.utils.Base64Utils;
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
		String bodyHmac = bodyMessage.toHmacData();
		byte[] bytes = AESUtils.doEncrypt(bodyHmac, Constants.SERV_KEY, Constants.SERV_IV);
		String base64Str = Base64Utils.encode(bytes);
		String headerHmac = headerMessage.toHmacData();
		String hmacSrcData = headerHmac + ",\"body\":\"" + base64Str + "\""; 
		String rtnHmac = Sha256Util.encrypt(hmacSrcData);
		return rtnHmac;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toJson() {
		return "{\"hashValue\":\"" + this.getHmac() + "\"}";
	}

	public String toHmacData() {
		return null;
	}
}
