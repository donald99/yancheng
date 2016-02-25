package org.ybygjy.ds.pojo;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ybygjy.ds.constant.Constants;
import org.ybygjy.ds.utils.AESUtils;
import org.ybygjy.ds.utils.Base64Utils;

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
	private String cellPhoneNumber;
	private transient String keys;
	/** Logger*/
	private static Logger logger = LoggerFactory.getLogger(RequestBodyMessage.class);
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
	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(String cellPhoneNumb) {
		this.cellPhoneNumber = cellPhoneNumb;
	}
	
	public String getKeys() {
		return keys == null ? Constants.SERV_KEY_NEW : keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	@Override
	public String toHmacData() {
		logger.debug("加密用到的信息:{},{},{}", this.toJson(), this.getKeys(), Constants.SERV_IV);
		byte[] bytes = AESUtils.doEncrypt(this.toJson(), this.getKeys(), Constants.SERV_IV);
		return null == bytes ? null : Base64Utils.encode(bytes);
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
		this.setCellPhoneNumber(requestData.get("rbm_cellphonenumber"));
		this.setEmail(requestData.get("rbm_email"));
	}
}
