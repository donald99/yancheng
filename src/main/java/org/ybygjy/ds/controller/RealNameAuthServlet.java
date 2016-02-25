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
public class RealNameAuthServlet extends HttpServlet {
	/**
	 * serialNumber
	 */
	private static final long serialVersionUID = 6765742534274831233L;

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
		requestData.put("rhm_channel", "0");
		requestData.put("rhm_transcode", "CF209b0010");
		requestData.put("rhm_transid", "2J5uvSN0v5A0mP" + ((int)(Math.random() * 100000)));
		requestData.put("rhm_useraccid", req.getParameter("rhm_useraccid"));
		requestData.put("rhm_authid", req.getParameter("rhm_authid"));
		requestData.put("rbm_identitynumber", req.getParameter("rbm_identitynumber"));
		requestData.put("rbm_identitytype", req.getParameter("rbm_identitytype"));
		requestData.put("rbm_personname", req.getParameter("rbm_personname"));
		requestData.put("rbm_address", req.getParameter("rbm_address"));
		requestData.put("rbm_email", req.getParameter("rbm_email"));
		requestData.put("rbm_cardnumber", req.getParameter("rbm_cardnumber"));
		requestData.put("rbm_cellphonenumber", req.getParameter("rbm_cellphonenumber"));
		requestData.put("sys_ctx_key", req.getParameter("sys_ctx_key"));
		
		DataService dataService = new DataServiceImpl();
		Map<String, String> responseData = dataService.checkPersonId(requestData);
		resp.setContentType("text/json; charset=UTF-8");
		Gson gson = new Gson();
		resp.getOutputStream().write(gson.toJson(responseData).getBytes(Charset.forName("UTF-8")));
	}
}
