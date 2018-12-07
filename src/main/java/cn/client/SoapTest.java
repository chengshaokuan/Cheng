
package cn.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-12-03 11:15
 **/
public class SoapTest {
    public static void main (String[] args) throws Exception {
        /**
         * SOAP协议 = HTTP协议 + XML数据格式
         * 一个严格定义的信息交换协议
         *
         * REST不是协议或标准。
         */


        //第一步：创建服务地址，不是WSDL地址http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl
        //wsimport -keep -s D:\Workspaces -p com.cn.weather -verbose C:\Users\sun\Desktop\WeatherWS.xml
        URL url = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx");
        //第二步：打开一个通向服务地址的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //第三步：设置参数
        //3.1发送方式设置：POST必须大写
        connection.setRequestMethod("POST");
        //3.2设置数据格式：content-type
        connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
        //3.3设置输入输出，因为默认新创建的connection没有读写权限，
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //第四步：组织SOAP数据，发送请求
        String soapXML = getXML("18317570940");
        OutputStream os = connection.getOutputStream();
        os.write(soapXML.getBytes());
        //第五步：接收服务端响应，打印
        int responseCode = connection.getResponseCode();
        if (200 == responseCode) {//表示服务端响应成功
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String temp = null;
            while (null != (temp = br.readLine())) {
                sb.append(temp);
            }
            System.out.println(sb.toString());

            is.close();
            isr.close();
            br.close();
        }

        os.close();
    }

    /**
     * <?xml version="1.0" encoding="utf-8"?>
     * <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
     * <soap:Body>
     * <getMobileCodeInfo xmlns="http://WebXml.com.cn/">
     * <mobileCode>string</mobileCode>
     * <userID>string</userID>
     * </getMobileCodeInfo>
     * </soap:Body>
     * </soap:Envelope>
     *
     * @param phoneNum
     * @return
     */
    public static String getXML (String phoneNum) {
        String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>"
                + "<getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">"
                + "<mobileCode>" + phoneNum + "</mobileCode>"
                + "<userID></userID>"
                + "</getMobileCodeInfo>"
                + "</soap:Body>"
                + "</soap:Envelope>";
        return soapXML;
    }
}
