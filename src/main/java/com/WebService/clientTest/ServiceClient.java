package com.WebService.clientTest;


import com.WebService.client.ArrayOfString;
import com.WebService.client.WeatherWebServiceSoap;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;




/**
 * <p>Title: ServiceClient.java</p>
 * <p>Description:Service编程实现服务端调用</p>
 */
public class ServiceClient {

    public static void main (String[] args) throws Exception {
        /**
         * 注意s:scheme标签
         */
        //创建WSDL的URL，注意不是服务地址
        URL url = new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl");
        //创建服务名称
        //1.namespaceURI - 命名空间地址
        //2.localPart - 服务视图名
        QName qname = new QName("http://WebXml.com.cn/", "WeatherWebService");
        //创建服务视图
        //参数解释：
        //1.wsdlDocumentLocation - wsdl地址
        //2.serviceName - 服务名称
        Service service = Service.create(url, qname);
        //获取服务实现类
        WeatherWebServiceSoap mobileCodeWSSoap = service.getPort(WeatherWebServiceSoap.class);
        //调用查询方法
        ArrayOfString weatherInfo = mobileCodeWSSoap.getWeatherbyCityName("北京");
        List<String> lstWeatherInfo = weatherInfo.getString();
        //遍历天气预报信息
        for (String string : lstWeatherInfo) {
            System.out.println(string);
            System.out.println("------------------------");
        }





//        //创建一个WeatherWebService工厂
//        WeatherWebService factory = new WeatherWebService();
//        //根据工厂创建一个WeatherWebServiceSoap对象
//        WeatherWebServiceSoap weatherWSSoap = factory.getWeatherWebServiceSoap();
//        //调用WebService提供的getWeather方法获取南宁市的天气预报情况
//        ArrayOfString weatherInfo = weatherWSSoap.getWeatherbyCityName("北京");
//        List<String> lstWeatherInfo = weatherInfo.getString();
//        //遍历天气预报信息
//        for (String string : lstWeatherInfo) {
//            System.out.println(string);
//            System.out.println("------------------------");
//        }
//        //获得中国省份、直辖市、地区和与之对应的ID
//        ArrayOfString s = weatherWSSoap.getSupportProvince();
//        List<String> list = s.getString();
//        for (String string : list) {
//            System.out.println(string);
//            System.out.println("------------------------");
//        }


    }
}


