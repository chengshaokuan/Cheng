package com.WebService.Axis2ClientTest;


import com.WebService.Axis2Client.MobileCodeWSStub;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-12-06 14:20
 **/
public class Axis2Test {

    public static void main (String[] args) throws Exception {


//%AXIS2_HOME%\bin\wsdl2java.bat -uri http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl -p com.csk.mobile -o G:\web\client1

//%AXIS2_HOME%\bin\wsdl2java.bat -p cn.com.csk  -u -o G://web/cc -g -uri http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl

        String url = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
        MobileCodeWSStub mobileCodeWSStub = new MobileCodeWSStub(url);
        MobileCodeWSStub.GetMobileCodeInfo getMobileCodeInfo = new MobileCodeWSStub.GetMobileCodeInfo();
        getMobileCodeInfo.setMobileCode("18317570940");
        getMobileCodeInfo.setUserID("");
        MobileCodeWSStub.GetMobileCodeInfoResponse mobileCodeInfo = mobileCodeWSStub.getMobileCodeInfo(getMobileCodeInfo);
        System.out.println(mobileCodeInfo.getGetMobileCodeInfoResult());



    }


}
