package org.ybygjy.ds.pojo;

/**
 * 报文体
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public abstract class RequestBodyMessage implements BodyMessage {

	/**
	 * {@inheritDoc}
	 */
	public abstract String toHmacData();

	/**
	 * {@inheritDoc}
	 */
	public abstract String toJson();
}
