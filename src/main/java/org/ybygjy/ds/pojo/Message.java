package org.ybygjy.ds.pojo;

/**
 * 定义基础报文服务
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public interface Message {
	/**
	 * json格式表述
	 * @return rtnJson
	 */
	public String toJson();
	/**
	 * 参与签名的内容
	 * @return rtnPartStr
	 */
	public String toHmacData();
}
