package cn.client;

import cn.com.weather.WeatherWebServiceStub;
import cn.com.webxml.ArrayOfString;
import cn.com.webxml.GetWeatherbyCityName;
import cn.com.webxml.GetWeatherbyCityNameResponse;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-12-07 14:47
 **/
public class Axis2WeatherTest {
    public static void main (String[] args) throws RemoteException {
        //wsdl2java.bat -p cn.com.weather  -u -o G://web/weather -g -uri http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl
       // http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl
        String url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
        WeatherWebServiceStub weatherWebServiceStub = new WeatherWebServiceStub(url);
        GetWeatherbyCityName weatherbyCityName = new GetWeatherbyCityName();
        weatherbyCityName.setTheCityName("北京");
        GetWeatherbyCityNameResponse cityName = weatherWebServiceStub.getWeatherbyCityName(weatherbyCityName);

        ArrayOfString result = cityName.getGetWeatherbyCityNameResult();
        String[] string = result.getString();
        for (String s : string) {
            System.out.println(s);
        }

    }
}
