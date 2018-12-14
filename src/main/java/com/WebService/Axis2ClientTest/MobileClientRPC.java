package com.WebService.Axis2ClientTest;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;


/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-12-11 19:07
 **/
public class MobileClientRPC {
    public static void main (String[] args) throws AxisFault {

        ServiceClient serviceClient = new ServiceClient();
        //创建服务地址WebService的URL,注意不是WSDL的URL
//        String url = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        String url = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx";
        EndpointReference targetEPR = new EndpointReference(url);
        Options options = serviceClient.getOptions();
        options.setTo(targetEPR);
        //确定调用方法（wsdl 命名空间地址 (wsdl文档中的targetNamespace) 和 方法名称 的组合）
        options.setAction("http://WebXml.com.cn/getWeather");

        //创建一个OMFactory，下面的namespace、方法与参数均需由它创建
        OMFactory fac = OMAbstractFactory.getOMFactory();
        /*
         * 指定命名空间，参数：
         * uri--即为wsdl文档的targetNamespace，命名空间
         * perfix--可不填
         */
        OMNamespace omNs = fac.createOMNamespace("http://WebXml.com.cn/", "");
        // 指定方法
        OMElement method = fac.createOMElement("getWeather", omNs);
        // 指定方法的参数
        OMElement theRegionCode = fac.createOMElement("theCityCode", omNs);
        theRegionCode.setText("北京");
        OMElement theUserID = fac.createOMElement("theUserID", omNs);
        theUserID.setText("");
        method.addChild(theRegionCode);
        method.addChild(theUserID);
        method.build();
        //远程调用web服务
        OMElement result = serviceClient.sendReceive(method);
        System.out.println(result);
    }

    /**
     * 为SOAP Header构造验证信息，
     * 如果你的服务端是没有验证的，那么你不用在Header中增加验证信息
     *
     * @param serviceClient
     * @param tns           命名空间
     * @param user
     * @param passwrod
     */
    public void addValidation (ServiceClient serviceClient, String tns, String user, String passwrod) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(tns, "nsl");
        OMElement header = fac.createOMElement("AuthenticationToken", omNs);
        OMElement ome_user = fac.createOMElement("Username", omNs);
        OMElement ome_pass = fac.createOMElement("Password", omNs);

        ome_user.setText(user);
        ome_pass.setText(passwrod);

        header.addChild(ome_user);
        header.addChild(ome_pass);

        serviceClient.addHeader(header);
    }
}
