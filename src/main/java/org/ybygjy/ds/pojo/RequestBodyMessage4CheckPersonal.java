package org.ybygjy.ds.pojo;

import com.google.gson.GsonBuilder;

/**
 * 报文体
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class RequestBodyMessage4CheckPersonal extends RequestBodyMessage {
	/**姓名*/
	private String personName;
	/**证件类型*/
	private String identityType;
	/**证件号码*/
	private String identityNumber;
	/**
	 * construction
	 * @param personName
	 * @param identityType
	 * @param identityNumber
	 */
	public RequestBodyMessage4CheckPersonal(String personName, String identityType, String identityNumber) {
		super();
		this.personName = personName;
		this.identityType = identityType;
		this.identityNumber = identityNumber;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	@Override
	public String toHmacData() {
		return this.toJson();
	}
	@Override
	public String toJson() {
		return new GsonBuilder().create().toJson(this);
	}
}
