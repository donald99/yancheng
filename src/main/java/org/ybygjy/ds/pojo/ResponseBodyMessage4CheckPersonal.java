package org.ybygjy.ds.pojo;

/**
 * 报文体
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class ResponseBodyMessage4CheckPersonal extends ResponseBodyMessage {
	/**校验结果{0-验证不通过;1-验证通过;2-用户信息不匹配;3-查验无结果}*/
	private String verificationResult;
}
