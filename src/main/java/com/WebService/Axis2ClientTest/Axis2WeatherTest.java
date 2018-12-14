package com.WebService.Axis2ClientTest;


import com.WebService.Axis2Client.MobileCodeWSStub;
import com.WebService.Axis2Client1.WeatherWebServiceStub;

import java.rmi.RemoteException;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-12-07 14:47
 **/
public class Axis2WeatherTest {
    public static void main (String[] args) throws RemoteException {
//%AXIS2_HOME%\bin\wsdl2java.bat -p com.WebService.Axis2Client1  -o G://Cheng/src/main/java -g -uri http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl
        String url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
        WeatherWebServiceStub weatherWebServiceStub = new WeatherWebServiceStub(url);
        WeatherWebServiceStub.GetWeatherbyCityName weatherbyCityName = new WeatherWebServiceStub.GetWeatherbyCityName();
        weatherbyCityName.setTheCityName("北京");
        WeatherWebServiceStub.GetWeatherbyCityNameResponse cityName = weatherWebServiceStub.getWeatherbyCityName(weatherbyCityName);

        WeatherWebServiceStub.ArrayOfString result = cityName.getGetWeatherbyCityNameResult();
        String[] string = result.getString();
        for (String s : string) {
            System.out.println(s);
        }

    }
}
