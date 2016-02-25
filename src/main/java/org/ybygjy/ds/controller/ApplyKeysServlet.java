package org.ybygjy.ds.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ybygjy.ds.service.DataService;
import org.ybygjy.ds.service.impl.DataServiceImpl;

import com.google.gson.Gson;

/**
 * 实名认证入口
 * @author WangYanCheng
 * @version 2016年2月25日
 */
public class ApplyKeysServlet extends HttpServlet {
	/**
	 * serialNumber
	 */
	private static final long serialVersionUID = 6765742536274831233L;
	private static Logger logger = LoggerFactory.getLogger(ApplyKeysServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.service(req, resp);
		logger.debug("接收请求{}#{}", req.getPathInfo(), req.getParameterMap());
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("rhm_transid", "BT7D6drYfN0hq" + ((int)(Math.random() * 1000000)));
		requestData.put("rhm_transcode", "CF00000001");
		requestData.put("rhm_action", "0");
		requestData.put("rhm_channel", "0");
		requestData.put("rhm_useraccid", req.getParameter("rhm_useraccid"));
		requestData.put("rhm_authid", req.getParameter("rhm_authid"));
		requestData.put("rbm_keytype", "0");
		requestData.put("rbm_lastkey", req.getParameter("rbm_lastkey"));
		requestData.put("rbm_lastkeygentime", req.getParameter("rbm_lastkeygentime"));
		requestData.put("rbm_validationtime", "0");
		logger.debug("解析传递参数{}#{}", req.getPathInfo(), requestData);
		DataService dataService = new DataServiceImpl();
		Map<String, String> responseData = dataService.applyKeys(requestData);
		System.out.println(responseData);
		resp.setContentType("text/json; charset=UTF-8");
		logger.debug("响应请求{}#{}", req.getPathInfo(), responseData);
		Gson gson = new Gson();
		resp.getOutputStream().write(gson.toJson(responseData).getBytes(Charset.forName("UTF-8")));
	}
}
