package com.imooc.utils.request;

import com.imooc.utils.property.PropertyFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;

import java.io.IOException;

public class HttpRequest {

	protected static Log log = LogFactory.getLog(HttpRequest.class);
	
	public static final String newEcifUrl= PropertyFactory.getProperty("ecif.url");//"http://10.10.41.21:8080/ecif/percustomer";
	
	private static HttpRequest httpRequst = null;

	private HttpRequest() {
	}

	public static HttpRequest getInstance() {
		if (httpRequst == null) {
			synchronized (HttpRequest.class) {
				if (httpRequst == null) {
					httpRequst = new HttpRequest();
				}
			}
		}
		return httpRequst;
	}

	public String doGet(String url, String charset) {
		String resStr = null;
		HttpClient htpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = htpClient.executeMethod(getMethod);
			// log.info(statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("Method failed: " + getMethod.getStatusLine());
				return resStr;
			}
			byte[] responseBody = getMethod.getResponseBody();
			resStr = new String(responseBody, charset);
		} catch (HttpException e) {
			log.error("Please check your provided http address!"); // 发生致命的异常，可能是协议不对或者返回的内容有问题
		} catch (IOException e) {
			log.error("Network anomaly"); // 发生网络异常
		} finally {
			getMethod.releaseConnection(); // 释放连接
		}
		return resStr;
	}

	public String doPost(String url, String charset, String jsonObj) {
		String resStr = null;
		HttpClient htpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
		try {
			postMethod.setRequestEntity(new StringRequestEntity(jsonObj,
					"application/json", charset));
			int statusCode = htpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				// post和put不能自动处理转发 301：永久重定向，告诉客户端以后应从新地址访问 302：Moved
				if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
						|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
					Header locationHeader = postMethod
							.getResponseHeader("location");
					String location = null;
					if (locationHeader != null) {
						location = locationHeader.getValue();
						log.info("The page was redirected to :" + location);
					} else {
						log.info("Location field value is null");
					}
				} else {
					log.error("Method failed: " + postMethod.getStatusLine());
				}
				return resStr;
			}
			byte[] responseBody = postMethod.getResponseBody();
			resStr = new String(responseBody, charset);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return resStr;
	}

	public String doPut(String url, String charset, String jsonObj) {
		String resStr = null;
		HttpClient htpClient = new HttpClient();
		PutMethod putMethod = new PutMethod(url);
		putMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
		try {
			putMethod.setRequestEntity(new StringRequestEntity(jsonObj,
					"application/json", charset));
			int statusCode = htpClient.executeMethod(putMethod);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("Method failed: " + putMethod.getStatusLine());
				return null;
			}
			byte[] responseBody = putMethod.getResponseBody();
			resStr = new String(responseBody, charset);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			putMethod.releaseConnection();
		}
		return resStr;
	}
}