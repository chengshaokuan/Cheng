package com.imooc.utils.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class RequestOsbUtil {
	public static boolean sendClientToOsbXML(String xml,String url) {
		JaxWsDynamicClientFactory dynamicClient = JaxWsDynamicClientFactory.newInstance();
        Client client = dynamicClient.createClient(url);
		Object[] results = null;
		//http://vosb.creditease.corp:7077/HeXin/tppubmag/ps/TppubmagK_ps?wsdl
		try {
			results = client.invoke("SendHX", new Object[] {xml});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(xml);
		System.out.println((String)results[0]);
		return true;
	}
}