package com.imooc.utils.util;


import com.creditease.sia.api.SIA;
import com.creditease.sia.pojo.SIAMessage;
import org.apache.log4j.Logger;


public class RequestSiaUtil {
	private static final Logger logger = Logger.getLogger(RequestSiaUtil.class);
	/**
	 * 
	 * <p>
	 * 同步发送
	 * </p>
	 * @author dzy
	 * @date 2015年1月05日
	 * code  消息业务code
	 * json  json字符串
	 */
	public static String syncSendClientRecoToEcif(String code,String json)throws Exception{
		logger.info("---------同步发送开始!--------sia业务code："+code);
		
		String answer="";
		
		try {
			SIAMessage mes = new SIAMessage();
			mes.setTimeout(900);
			//业务code
			mes.setBusinessCode(code);
			//发送端发送消息
			mes.setMessageInfoClob(json);
			//发送端 接收   接收端返回信息
			answer = SIA.answer(mes);
			
		}catch(Exception e){
			logger.error("---------同步发送失败!--------sia业务code："+code, e);
		}
		logger.info("---------同步发送结束!--------sia业务code："+code);
		return answer;
	}
	
	/**
	 * 
	 * <p>
	 * 异步发送
	 * </p>
	 * @author dzy
	 * @date 2015年1月05日
	 * code  消息业务code
	 * json  json字符串
	 */
	public static boolean asyncSendClientRecoToEcif(String code,String json)throws Exception{
		logger.info("---------异步发送开始!--------sia业务code："+code);
		boolean bl=false;
		try {
			SIAMessage mes = new SIAMessage();
			mes.setBusinessCode(code);//业务code
			mes.setMessageInfoClob(json);//发送端发送消息
			//mes.setMessageType("object");
			 bl= SIA.send(mes);
			
		}catch(Exception e){
			logger.error("---------异步发送失败!--------sia业务code："+code, e);
		}
		logger.info("---------异步发送结束!--------sia业务code："+code);
		return bl;
	}
}

