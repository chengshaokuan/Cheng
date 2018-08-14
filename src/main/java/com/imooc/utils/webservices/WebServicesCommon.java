package com.imooc.utils.webservices;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class WebServicesCommon {
	
	public static String MessageBody_begin="<MessageBody><row>";//报文体-开始节点
	public static String MessageBody_end="</row></MessageBody></creditease>";//报文体-结束
	
	public static String Body_begin="<MessageBody>";//报文体-开始节点
	public static String Body_end="</MessageBody></creditease>";//报文体-结束
	//报文头
	public static String MessageHead(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		return "<?xml version='1.0' encoding='UTF-8'?><creditease><MessageHead><tranTime>"+(dateFormat.format(now))+"</tranTime><channel>Ecif</channel><exCode>0000</exCode></MessageHead>";
	}
	
	
	public static String MessageHeadForCoreQuer(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		return "<?xml version='1.0' encoding='GBK'?><creditease><MessageHead><tranTime>"+(dateFormat.format(now))+"</tranTime><channel>Ecif</channel><exCode>0000</exCode></MessageHead>";
	}
	
	
	//报文头
	public static String MessageHeadForCore(){
		
		int xmlLength = (Integer) null;
		
		return "<![CDATA["+xmlLength+"51003001RQ0"+                       "003001";
	}
	
	
	
	//报文头
		public static String MessageEndForCore(){
			return "]]>";
		}
	
	
	
	//错误报文
	public static String ErrorMessage(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		return "<?xml version='1.0' encoding='UTF-8'?><creditease><MessageHead><tranTime>"+(dateFormat.format(now))+"</tranTime><channel>Ecif</channel><exCode>102</exCode></MessageHead><MessageBody></MessageBody></creditease>";
	}
 
	/**
	 * 根据Map返回 拼接的XML 数据
	 * @param map
	 * @return
	 */
	 public static  String getXMLByMap(String head,Map<String, Object> map) {
		 /**
		  * <student> ----> head 
		  *   <name/>
		  *   <age/>
		  * </student> 
		  */
		 
		 
		 String sb ="";
		 Set set = map.entrySet();
		 Iterator it = set.iterator();
		 while (it.hasNext()) {
			 String str ="";
		    Map.Entry entry = (Map.Entry) it.next();
		    String title =((String) entry.getKey()).toLowerCase();
		    str="<"+title+">"+entry.getValue()+"</"+title+">";
		    sb+=str;
		  }
		  //如果表头不为空，添加表头
		  if(StringUtils.isNotEmpty(head)){
			  sb="<"+head+">"+sb+"</"+head+">";
		  }
		  //System.out.println(sb);
		  return sb;
		 }
		/**
		 *  {"firstName":"Brett","lastName":"McLaughlin","email":"aaaa"}
		 * 根据Map返回 拼接的json 数据  map为null 返回{}
		 * @author liqinliang
		 * @param map
		 * @return
		 */
		 @SuppressWarnings("unchecked")
	public static  String getJSONByMap(Map<String, Object> map) {
		 String sb ="{";
		 Set set = map.entrySet();
		 Iterator it = set.iterator();
		 //如果map是null 首次判断
		 if(!it.hasNext()){
			 sb="{}";
		 }
		 while (it.hasNext()) {//如果有第一个元素
			String str ="";
			//取出当前元素
		    Map.Entry entry = (Map.Entry) it.next();
	
		    String title =((String) entry.getKey()).toLowerCase();

		    if(it.hasNext()){//不是最后一个
		    	 
				    str="\""+title+"\" : \""+entry.getValue()+"\",";
		    	
		    }else{//是最后一个
		    	 str="\""+title+"\" : \""+entry.getValue()+"\"}";
		    }
		    
		    sb+=str;
		  }
		 
		  return sb;
		 }
		 
		 /**
			 *  {"firstName":"Brett","lastName":"McLaughlin","email":"aaaa"}
			 * 根据Map返回 拼接的json 数据  map为null 返回{}
			 * @author liqinliang
			 * @param map
			 * @return
			 */
		public static  String getJSON(Map<String, Object> map) {
			 String sb ="{";
			 Set set = map.entrySet();
			 Iterator it = set.iterator();
			 //如果map是null 首次判断
			 if(!it.hasNext()){
				 sb="{}";
			 }
			 while (it.hasNext()) {//如果有第一个元素
				String str ="";
				//取出当前元素
			    Map.Entry entry = (Map.Entry) it.next();
		
			    String title =((String) entry.getKey());

			    if(it.hasNext()){//不是最后一个
			    	 
					    str="\""+title+"\" : \""+entry.getValue()+"\",";
			    	
			    }else{//是最后一个
			    	 str="\""+title+"\" : \""+entry.getValue()+"\"}";
			    }
			    
			    sb+=str;
			  }
			 
			  return sb;
			 }
	 /**
		 * 根据Map返回 拼接的XML 数据
		 * @param map
		 * @return
		 */
		 public static  String getXMLByMap(String head,Map<String, Object> map,String children,String children1) {
			 /**
			  * <student> ----> head 
			  *   <name></name>
			  *   <age/>
			  *   <emails>
			  *      <email/>
			  *      <email/>
			  *   </emails>
			  * </student> 
			  */
			 
			 
			 String sb ="";
			 Set set = map.entrySet();
			 Iterator it = set.iterator();
			 while (it.hasNext()) {
				 String str ="";
			    Map.Entry entry = (Map.Entry) it.next();
			    String title =((String) entry.getKey()).toLowerCase();
			    str="<"+title+">"+entry.getValue()+"</"+title+">";
			    sb+=str;
			  }
			 //添加孩子内容
			  if(StringUtils.isNotEmpty(children)){
				  sb+=children;
			  }
			  if(StringUtils.isNotEmpty(children1)){
				  sb+=children1;
			  }
			 
			  //如果表头不为空，添加表头
			  if(StringUtils.isNotEmpty(head)){
				  sb="<"+head+">"+sb+"</"+head+">";
			  }
			 
			  
			  
			  //System.out.println(sb);
			  return sb;
			 }
	 
		 
		 /**
			 * 根据Map返回 拼接的XML 数据
			 * @param map
			 * @return
			 */
			 public static  String getXMLByMap(String head,Map<String, Object> map,List strlist) {
				 /**
				  * <student> ----> head 
				  *   <name/>
				  *   <age/>
				  *   <emails>
				  *      <email/>
				  *      <email/>
				  *   </emails>
				  * </student> 
				  */
				 
				 
				 String sb ="";
				 Set set = map.entrySet();
				 Iterator it = set.iterator();
				 while (it.hasNext()) {
					 String str ="";
				    Map.Entry entry = (Map.Entry) it.next();
				    String title =((String) entry.getKey()).toLowerCase();
				    str="<"+title+">"+entry.getValue()+"</"+title+">";
				    sb+=str;
				  }
				 
				 //添加孩子节点
				 if(strlist!=null&&strlist.size()>0){
					 for (int i = 0; i < strlist.size(); i++) {
						String s = (String) strlist.get(i);
						sb+=s;
					}		 			 
				 }
				  //如果表头不为空，添加表头
				  if(StringUtils.isNotEmpty(head)){
					  sb="<"+head+">"+sb+"</"+head+">";
				  }
				 
				  
				  
				  //System.out.println(sb);
				  return sb;
				 }
			 
			 
			 
			 
	 

				//验证传递的日期  是否符合格式
				public static boolean checkDate(String strdate){
					boolean check=true;
					java.text.DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
					//String s= "2006-01-01 ";
					try{
					 Date d=f.parse(strdate);
					}catch(java.text.ParseException e)
					{
						check=false;
					  System.out.println( "The input is error ");
					}
					return check; 
				}	 
			 
}
