package org.ybygjy.ds.pojo;

import java.util.Map;

/**
 * 报文头
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class RequestHeaderMessage implements HeaderMessage {
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
	public void parseMap(Map<String, String> requestData) {
		this.setAction(((String)requestData.get("rhm_action")).charAt(0));
		this.setAuthorizationID(requestData.get("rhm_authid"));
		this.setChannel(((String)requestData.get("rhm_channel")).charAt(0));
		this.setTransactionCode(requestData.get("rhm_transcode"));
		this.setTransactionID(requestData.get("rhm_transid"));
		this.setUserAccountID(requestData.get("rhm_useraccid"));
	}
	public String toJson() {
//		String rtnJson = new GsonBuilder().create().toJson(this);
//		return rtnJson;
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("{")
			.append("\"transactionID\":").append("\"").append(this.getTransactionID()).append("\",")
			.append("\"transactionCode\":").append("\"").append(this.getTransactionCode()).append("\",")
			.append("\"action\":").append("\"").append(this.getAction()).append("\",")
			.append("\"chanel\":").append("\"").append(this.getChannel()).append("\",")
			.append("\"userAccountID\":").append("\"").append(this.getUserAccountID()).append("\",")
			.append("\"authorizationID\":").append("\"").append(this.getAuthorizationID()).append("\"")
		.append("}");
		return sbuf.toString();
	}
	public String toHmacData() {
		return "\"head\":" + this.toJson();
	}
}
