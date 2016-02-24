package org.ybygjy.ds.pojo;

import java.util.Map;

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
	/**地址*/
	private String address;
	/**邮件*/
	private String email;
	/**cardNumber*/
	private String cardNumber;
	/**phoneNumber*/
	private String cellPhoneNumb;
	/**
	 * Constructor
	 */
	public RequestBodyMessage4CheckPersonal() {
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCellPhoneNumb() {
		return cellPhoneNumb;
	}
	public void setCellPhoneNumb(String cellPhoneNumb) {
		this.cellPhoneNumb = cellPhoneNumb;
	}
	@Override
	public String toHmacData() {
		return this.toJson();
	}
	@Override
	public String toJson() {
		return new GsonBuilder().create().toJson(this);
	}
	public void parseMap(Map<String, String> requestData) {
		this.setIdentityNumber(requestData.get("rbm_identitynumber"));
		this.setIdentityType(requestData.get("rbm_identitytype"));
		this.setPersonName(requestData.get("rbm_personname"));
		this.setAddress(requestData.get("rbm_address"));
		this.setCardNumber(requestData.get("rbm_cardnumber"));
		this.setCellPhoneNumb(requestData.get("rbm_cellphonenumber"));
		this.setEmail(requestData.get("rbm_email"));
	}
}
