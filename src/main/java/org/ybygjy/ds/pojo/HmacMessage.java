package org.ybygjy.ds.pojo;

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
		String headerStr = this.headerMessage.toHmacData();
		String bodyStr = this.bodyMessage.toHmacData();
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	public String toJson() {
		return "{\"hashValue\":\"" + this.getHmac() + "\"}";
	}
}
