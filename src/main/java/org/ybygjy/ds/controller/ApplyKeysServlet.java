package org.ybygjy.ds.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("rhm_action", "0");
		requestData.put("rhm_authid", "11111111");
		requestData.put("rhm_channel", "0");
		requestData.put("rhm_transcode", "CF00000001");
		requestData.put("rhm_transid", "BT7D6drYfN0hqUkQWAO");
		requestData.put("rhm_useraccid", "CF00000001");
		requestData.put("rbm_keytype", "0");
		requestData.put("rbm_lastkey", "11111");
		requestData.put("rbm_lastkeygentime", "11111111");
		requestData.put("rbm_validationtime", "0");
		DataService dataService = new DataServiceImpl();
		Map<String, String> responseData = dataService.applyKeys(requestData);
		System.out.println(responseData);
		resp.setContentType("text/json; charset=UTF-8");
		Gson gson = new Gson();
		resp.getOutputStream().write(gson.toJson(responseData).getBytes(Charset.forName("UTF-8")));
	}
}
