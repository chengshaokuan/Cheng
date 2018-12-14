
package com.WebService.client;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2018-12-14T16:34:18.028+08:00
 * Generated source version: 3.2.7
 *
 */
public final class WeatherWebServiceHttpPost_WeatherWebServiceHttpPost_Client {

    private static final QName SERVICE_NAME = new QName("http://WebXml.com.cn/", "WeatherWebService");

    private WeatherWebServiceHttpPost_WeatherWebServiceHttpPost_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = WeatherWebService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        WeatherWebService ss = new WeatherWebService(wsdlURL, SERVICE_NAME);
        WeatherWebServiceHttpPost port = ss.getWeatherWebServiceHttpPost();

        {
        System.out.println("Invoking getSupportProvince...");
        com.WebService.client.ArrayOfString _getSupportProvince__return = port.getSupportProvince();
        System.out.println("getSupportProvince.result=" + _getSupportProvince__return);


        }
        {
        System.out.println("Invoking getWeatherbyCityNamePro...");
        java.lang.String _getWeatherbyCityNamePro_theCityName = "";
        java.lang.String _getWeatherbyCityNamePro_theUserID = "";
        com.WebService.client.ArrayOfString _getWeatherbyCityNamePro__return = port.getWeatherbyCityNamePro(_getWeatherbyCityNamePro_theCityName, _getWeatherbyCityNamePro_theUserID);
        System.out.println("getWeatherbyCityNamePro.result=" + _getWeatherbyCityNamePro__return);


        }
        {
        System.out.println("Invoking getSupportCity...");
        java.lang.String _getSupportCity_byProvinceName = "";
        com.WebService.client.ArrayOfString _getSupportCity__return = port.getSupportCity(_getSupportCity_byProvinceName);
        System.out.println("getSupportCity.result=" + _getSupportCity__return);


        }
        {
        System.out.println("Invoking getSupportDataSet...");
        com.WebService.client.DataSet _getSupportDataSet__return = port.getSupportDataSet();
        System.out.println("getSupportDataSet.result=" + _getSupportDataSet__return);


        }
        {
        System.out.println("Invoking getWeatherbyCityName...");
        java.lang.String _getWeatherbyCityName_theCityName = "";
        com.WebService.client.ArrayOfString _getWeatherbyCityName__return = port.getWeatherbyCityName(_getWeatherbyCityName_theCityName);
        System.out.println("getWeatherbyCityName.result=" + _getWeatherbyCityName__return);


        }

        System.exit(0);
    }

}
