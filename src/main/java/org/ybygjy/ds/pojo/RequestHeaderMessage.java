package org.ybygjy.ds.pojo;

/**
 * 报文头
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class RequestHeaderMessage {
	/** 交易流水号 */
	private String transactionID;
	/** 产品编码*/
	private String transactionCode;
	/** 报文动作*/
	private char action;
	/** 接入渠道*/
	private char channel;
	/** 用户编号*/
	private String userAccountID;
	/** 授权号*/
	private String authorizationID;
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public char getAction() {
		return action;
	}
	public void setAction(char action) {
		this.action = action;
	}
	public char getChannel() {
		return channel;
	}
	public void setChannel(char channel) {
		this.channel = channel;
	}
	public String getUserAccountID() {
		return userAccountID;
	}
	public void setUserAccountID(String userAccountID) {
		this.userAccountID = userAccountID;
	}
	public String getAuthorizationID() {
		return authorizationID;
	}
	public void setAuthorizationID(String authorizationID) {
		this.authorizationID = authorizationID;
	}
}
