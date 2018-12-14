package com.WebService.CxfClientTest;




//import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.WebService.CxfClient.ArrayOfString;
import com.WebService.CxfClient.MobileCodeWS;
import com.WebService.CxfClient.MobileCodeWSSoap;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-12-12 15:07
 **/
public class CxfTest {

    public static void main (String[] args) throws Exception {

        URL wsdlURL = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
       // namespace是命名空间，methodName是方法名
        QName SERVICE_NAME = new QName("http://WebXml.com.cn/", "MobileCodeWS");
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        MobileCodeWSSoap client = service.getPort(MobileCodeWSSoap.class);
        String result = client.getMobileCodeInfo("18317570940","");
        System.out.println(result);

        // 代理工厂
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        // 设置代理地址
        jaxWsProxyFactoryBean.setAddress("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
        // 设置接口类型
        jaxWsProxyFactoryBean.setServiceClass(MobileCodeWSSoap.class);
        // 创建一个代理接口实现
        MobileCodeWSSoap cs = (MobileCodeWSSoap) jaxWsProxyFactoryBean.create();
        String result1 = client.getMobileCodeInfo("18317570940","");
        System.out.println(result1);

        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client1 = clientFactory.createClient("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
        Object[] result2 = client1.invoke("getMobileCodeInfo", "18317570940","");
        System.out.println(result2[0]);


        MobileCodeWS mobileCodeWS = new MobileCodeWS();
        ArrayOfString databaseInfo = mobileCodeWS.getMobileCodeWSSoap().getDatabaseInfo();
        databaseInfo.getString().forEach(s -> System.out.println(s));


    }
}