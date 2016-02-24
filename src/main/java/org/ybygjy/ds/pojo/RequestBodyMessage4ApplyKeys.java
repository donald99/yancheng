package org.ybygjy.ds.pojo;

import java.util.Map;

/**
 * 报文体
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class RequestBodyMessage4ApplyKeys extends RequestBodyMessage {
	/** 最次一次有效密钥*/
	private String lastKey;
	/** 最近一次生成时间*/
	private String lastKeyGenTime;
	/** 有效期{0:永久;3:三个月}*/
	private char validationTime;
	/** 密钥类型*/
	private String keyType;
	/**
	 * Constructor
	 */
	public RequestBodyMessage4ApplyKeys() {
		super();
	}
	/**
	 * construction
	 * @param lastKey
	 * @param lastKeyGenTime
	 * @param validationTime
	 * @param keyType
	 */
	public RequestBodyMessage4ApplyKeys(String lastKey, String lastKeyGenTime,
			char validationTime, String keyType) {
		super();
		this.lastKey = lastKey;
		this.lastKeyGenTime = lastKeyGenTime;
		this.validationTime = validationTime;
		this.keyType = keyType;
	}
	
	public String getLastKey() {
		return lastKey;
	}

	public void setLastKey(String lastKey) {
		this.lastKey = lastKey;
	}

	public String getLastKeyGenTime() {
		return lastKeyGenTime;
	}

	public void setLastKeyGenTime(String lastKeyGenTime) {
		this.lastKeyGenTime = lastKeyGenTime;
	}

	public char getValidationTime() {
		return validationTime;
	}

	public void setValidationTime(char validationTime) {
		this.validationTime = validationTime;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	@Override
	public String toHmacData() {
		return "";
	}
	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 数据转换
	 * @param requestData
	 */
	public void parseMap(Map<String, String> requestData) {
		this.setKeyType(requestData.get("key_type"));
		this.setLastKey(requestData.get("last_key"));
		this.setLastKeyGenTime(requestData.get("last_keygen_time"));
		this.setValidationTime(((String)requestData.get("validation_time")).toCharArray()[0]);
	}
}
