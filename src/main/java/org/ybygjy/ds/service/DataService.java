package org.ybygjy.ds.service;

import java.util.Map;

/**
 * 基础服务接口，抽象定义基础服务接口规则
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public interface DataService {
	/**
	 * 密钥申请
	 * @param requestData
	 * @return rtnResult
	 */
	public Map<String, String> applyKeys(Map<String, String> requestData);
	/**
	 * 身份较验
	 * @param requestData
	 * @return rtnResultMap
	 */
	public Map<String, String> checkPersonId(Map<String, String> requestData);
}
