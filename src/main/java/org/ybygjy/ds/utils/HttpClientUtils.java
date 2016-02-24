package org.ybygjy.ds.utils;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

/**
 * 远程服务通信
 * @author WangYanCheng
 * @version 2016年2月24日
 */
public class HttpClientUtils {
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
			SSLConnectionSocketFactory sslConnFactory = new SSLConnectionSocketFactory(sslCtx);
			return HttpClients.custom().setSSLSocketFactory(sslConnFactory).build();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String doRequest(URL url) {
		CloseableHttpClient httpClient = HttpClientUtils.createSSLClientDefault();
		HttpGet getMethod = new HttpGet(url.getUserInfo());
	}
}
