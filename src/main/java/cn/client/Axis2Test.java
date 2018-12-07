package cn.client;


import cn.com.csk.MobileCodeWS;
import cn.com.csk.MobileCodeWSStub;
import cn.com.webxml.GetMobileCodeInfo;
import cn.com.webxml.GetMobileCodeInfoResponse;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-12-06 14:20
 **/
public class Axis2Test {

    public static void main (String[] args) throws Exception {

//http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl

//wsdl2java -uri http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl  - all -p com.csk.test -o G:\web
//wsdl2java -uri http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl -s -p com.csk.mobile -o G:\web\client

//wsdl2java -uri http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl -s -p com.csk.mobile -o G:\web\client
        //wsdl2java -uri http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl  -d G:\web

//wsdl2java.bat -p cn.com.csk  -u -o G://web/cc -g -uri http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl
/////wsdl2java.bat -p com.xview.cms.webservice.database -t -u -o G://web -uri http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl



        //wsdl2java -uri http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl -s -p com.ming.mobile -o G://web/bb

        String url = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
        MobileCodeWSStub mobileCodeWSStub = new MobileCodeWSStub(url);
        GetMobileCodeInfo getMobileCodeInfo = new GetMobileCodeInfo();
        getMobileCodeInfo.setMobileCode("18317570940");
        getMobileCodeInfo.setUserID("");
        GetMobileCodeInfoResponse mobileCodeInfo = mobileCodeWSStub.getMobileCodeInfo(getMobileCodeInfo);
        System.out.println(mobileCodeInfo.getGetMobileCodeInfoResult());



    }


}
