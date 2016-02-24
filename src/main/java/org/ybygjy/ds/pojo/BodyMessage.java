package org.ybygjy.ds.pojo;

/**
 * 报文体抽象
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public interface BodyMessage extends Message {
	/**
	 * 参与签名的内容
	 * @return rtnPartStr
	 */
	public String toHmacData();
}
