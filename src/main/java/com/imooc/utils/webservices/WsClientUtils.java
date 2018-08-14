package com.imooc.utils.webservices;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * Description:
 *
 * @author zcq
 * @version 1.0
 * <pre>
 * Modification History:
 * Date                  Author      Version     Description
 * ------------------------------------------------------------------------
 * 2016-3-22下午03:14:29   zcq       1.0        1.0 Version
 * </pre>
 */
public class WsClientUtils {

    /**
     * @Description: 包装客户端
     * @param: cleint 客户端;<T> 泛型
     * @return: T
     * @Author: Mr.Cheng
     * @Date: 14:41 2018/8/14
     */
    private static <T> T getWrappClient (T cleint) {
        Client client = ClientProxy.getClient(cleint);
        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(360000);
        httpClientPolicy.setAllowChunking(false);
        httpClientPolicy.setReceiveTimeout(360000);
        http.setClient(httpClientPolicy);
        return cleint;
    }


    /**
     * @Description: 获取客户端
     * @param: wsAddress
     * @param: t
     * @return: T
     * @Author: Mr.Cheng
     * @Date: 14:41 2018/8/14
     */
    public static <T> T getWsClient (String wsAddress, Class<T> t) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(t);
        factory.setAddress(wsAddress);
        T client = (T) factory.create();
        return getWrappClient(client);
    }

}


