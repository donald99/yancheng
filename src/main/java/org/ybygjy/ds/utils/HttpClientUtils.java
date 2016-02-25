package org.ybygjy.ds.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 远程服务通信
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class HttpClientUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	/**
	 * 创建自定义通信对象
	 * @return rtnHttpClient {@link CloseableHttpClient}
	 */
	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslCtx = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy(){
				public boolean isTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslConnFactory = new SSLConnectionSocketFactory(sslCtx,new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			return HttpClients.custom().setSSLSocketFactory(sslConnFactory).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String doRequest(String url, String requestData) {
		CloseableHttpClient httpClient = HttpClientUtils.createSSLClientDefault();
		HttpPost postMethod = new HttpPost(url);
		logger.debug("传输数据:{}:{}", url, requestData);
		postMethod.setEntity(new StringEntity(requestData, Charset.forName("UTF-8")));
		try {
			CloseableHttpResponse response = httpClient.execute(postMethod);
			StringBuffer sbuf = new StringBuffer();
			logger.debug("接收数据statusLine:{}", response.getStatusLine());
			if (response.getStatusLine().getStatusCode() == 200) {
				sbuf.append(EntityUtils.toString(response.getEntity(), "UTF-8"));
			}
			logger.debug("接收数据Content:{}", sbuf);
			return sbuf.toString();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
