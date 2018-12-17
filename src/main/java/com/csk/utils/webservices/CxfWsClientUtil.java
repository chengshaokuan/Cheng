package com.csk.utils.webservices;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * @program: Cheng
 * @description: cxf框架调用客户端
 * 这种方法客户端必须依赖服务器端的接口，这种调用方式要求服务器端的webservice必须是java实现
 * 这样也就失去了使用webservice的意义
 * @author: Mr.Cheng
 * @create: 2018-12-17 16:31
 **/
public class CxfWsClientUtil {
    /**
     * @Description: 包装客户端
     * @param: cleint 客户端;<T> 泛型
     * @return: T
     * @Author: Mr.Cheng
     * @Date: 14:41 2018/8/14
     */
    private static <T> T getWrappClient (T cleint) {
        Client clientTest = ClientProxy.getClient(cleint);
        HTTPConduit http = (HTTPConduit) clientTest.getConduit();
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
        //客户端直接服务器端提供的服务接口(interface)
        //CXF通过运行时代理生成远程服务的代理对象，在客户端完成对webservice的访问;几个必填的字段：
        JaxWsProxyFactoryBean DesignPatterns = new JaxWsProxyFactoryBean();
        DesignPatterns.setServiceClass(t);
        //setAddress-这个就是我们发布webservice时候的地址，保持一致
        DesignPatterns.setAddress(wsAddress);
        T clientTest = (T) DesignPatterns.create();
        return getWrappClient(clientTest);
    }
}
