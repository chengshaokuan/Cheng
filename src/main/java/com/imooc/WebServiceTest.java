package com.imooc;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * webservice客户端
 *
 *
 * @author：WangYuanJun
 * @date：2017年12月19日 下午9:39:49
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WebServiceTest {

//    @Test
    public static void main  (String[]args) {

        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8082/services/queryCustomerCardIdEcifIdByTelNumService?wsdl");

        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        Object[] objects = new Object[0];
        try {

            // invoke("方法名",参数1,参数2,参数3....);
            System.out.println("返回结果："+client.invoke("queryCustomerCardIdEcifIdByTelNum", "15736252883","510227197312049369"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
