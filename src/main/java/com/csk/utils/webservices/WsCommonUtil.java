package com.csk.utils.webservices;

import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-12-17 16:38
 **/
public class WsCommonUtil {

    public static String MessageBody_begin = "<MessageBody><row>";//报文体-开始节点
    public static String MessageBody_end = "</row></MessageBody></creditease>";//报文体-结束
    public static String Body_begin = "<MessageBody>";//报文体-开始节点
    public static String Body_end = "</MessageBody></creditease>";//报文体-结束

    /**
     * @Description: 报文头
     * @param:
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:39 2018/12/17
     */
    public static String MessageHead () {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return "<?xml version='1.0' encoding='UTF-8'?><creditease><MessageHead><tranTime>" + (dateFormat.format(now)) + "</tranTime><channel>Ecif</channel><exCode>0000</exCode></MessageHead>";
    }

    public static String MessageHeadForCoreQuer () {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return "<?xml version='1.0' encoding='GBK'?><creditease><MessageHead><tranTime>" + (dateFormat.format(now)) + "</tranTime><channel>Ecif</channel><exCode>0000</exCode></MessageHead>";
    }

    /**
     * @Description: 报文头
     * @param:
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:40 2018/12/17
     */
    public static String MessageHeadForCore () {
        int xmlLength = (Integer) null;
        return "<![CDATA[" + xmlLength + "51003001RQ0" + "003001";
    }

    /**
     * @Description:
     * @param:
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:41 2018/12/17
     */
    public static String MessageEndForCore () {
        return "]]>";
    }

    /**
     * @Description: 错误报文
     * @param:
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:41 2018/12/17
     */
    public static String ErrorMessage () {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return "<?xml version='1.0' encoding='UTF-8'?><creditease><MessageHead><tranTime>" + (dateFormat.format(now)) + "</tranTime><channel>Ecif</channel><exCode>102</exCode></MessageHead><MessageBody></MessageBody></creditease>";
    }

    /**
     * @Description: 根据Map返回 拼接的XML 数据根据Map返回 拼接的XML 数据
     * eg:<student> ----> head
     * <name/>
     * <age/>
     * </student>
     * @param: head
     * @param: map
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:41 2018/12/17
     */
    public static String getXMLByMap (String head, Map<String, Object> map) {
        String sb = "";
        Set set = map.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = "";
            Map.Entry entry = (Map.Entry) it.next();
            String title = ((String) entry.getKey()).toLowerCase();
            str = "<" + title + ">" + entry.getValue() + "</" + title + ">";
            sb += str;
        }
        //如果表头不为空，添加表头
        if (StringUtils.isNotEmpty(head)) {
            sb = "<" + head + ">" + sb + "</" + head + ">";
        }
        return sb;
    }

    /**
     * @Description: {"firstName":"Brett","lastName":"McLaughlin","email":"aaaa"}
     * 根据Map返回 拼接的json 数据  map为null 返回{}
     * @param: map
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:44 2018/12/17
     */
    public static String getJSONByMap (Map<String, Object> map) {
        String sb = "{";
        Set set = map.entrySet();
        Iterator it = set.iterator();
        //如果map是null 首次判断
        if (!it.hasNext()) {
            sb = "{}";
        }
        while (it.hasNext()) {//如果有第一个元素
            String str = "";
            //取出当前元素
            Map.Entry entry = (Map.Entry) it.next();
            String title = ((String) entry.getKey()).toLowerCase();
            if (it.hasNext()) {//不是最后一个
                str = "\"" + title + "\" : \"" + entry.getValue() + "\",";
            } else {//是最后一个
                str = "\"" + title + "\" : \"" + entry.getValue() + "\"}";
            }
            sb += str;
        }
        return sb;
    }

    /**
     * @Description:  {"firstName":"Brett","lastName":"McLaughlin","email":"aaaa"}
     * 根据Map返回 拼接的json 数据  map为null 返回{}
     * @param: map
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:45 2018/12/17
     */
    public static String getJSON (Map<String, Object> map) {
        String sb = "{";
        Set set = map.entrySet();
        Iterator it = set.iterator();
        //如果map是null 首次判断
        if (!it.hasNext()) {
            sb = "{}";
        }
        while (it.hasNext()) {//如果有第一个元素
            String str = "";
            //取出当前元素
            Map.Entry entry = (Map.Entry) it.next();
            String title = ((String) entry.getKey());
            if (it.hasNext()) {//不是最后一个
                str = "\"" + title + "\" : \"" + entry.getValue() + "\",";
            } else {//是最后一个
                str = "\"" + title + "\" : \"" + entry.getValue() + "\"}";
            }
            sb += str;
        }
        return sb;
    }

    /**
     * @Description:  根据Map返回 拼接的XML 数据
     * <student> ----> head
     *   <name></name>
     *   <age/>
     *   <emails>
     *      <email/>
     *      <email/>
     *   </emails>
     * </student>
     * @param: head
     * @param: map
     * @param: children
     * @param: children1
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:46 2018/12/17
     */
    public static String getXMLByMap (String head, Map<String, Object> map, String children, String children1) {
        String sb = "";
        Set set = map.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = "";
            Map.Entry entry = (Map.Entry) it.next();
            String title = ((String) entry.getKey()).toLowerCase();
            str = "<" + title + ">" + entry.getValue() + "</" + title + ">";
            sb += str;
        }
        //添加孩子内容
        if (StringUtils.isNotEmpty(children)) {
            sb += children;
        }
        if (StringUtils.isNotEmpty(children1)) {
            sb += children1;
        }
        //如果表头不为空，添加表头
        if (StringUtils.isNotEmpty(head)) {
            sb = "<" + head + ">" + sb + "</" + head + ">";
        }
        return sb;
    }

    /**
     * @Description: 根据Map返回 拼接的XML 数据
     * <student> ----> head
     *   <name/>
     *   <age/>
     *   <emails>
     *      <email/>
     *      <email/>
     *   </emails>
     * </student>
     * @param: head
     * @param: map
     * @param: strlist
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:47 2018/12/17
     */
    public static String getXMLByMap (String head, Map<String, Object> map, List strlist) {
        String sb = "";
        Set set = map.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = "";
            Map.Entry entry = (Map.Entry) it.next();
            String title = ((String) entry.getKey()).toLowerCase();
            str = "<" + title + ">" + entry.getValue() + "</" + title + ">";
            sb += str;
        }
        //添加孩子节点
        if (strlist != null && strlist.size() > 0) {
            for (int i = 0; i < strlist.size(); i++) {
                String s = (String) strlist.get(i);
                sb += s;
            }
        }
        //如果表头不为空，添加表头
        if (StringUtils.isNotEmpty(head)) {
            sb = "<" + head + ">" + sb + "</" + head + ">";
        }
        return sb;
    }

    /**
     * @Description:  验证传递的日期  是否符合格式
     * @param: strdate
     * @return: boolean
     * @Author: Mr.Cheng
     * @Date: 16:48 2018/12/17
     */
    public static boolean checkDate (String strdate) {
        boolean check = true;
        java.text.DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = f.parse(strdate);
        } catch (java.text.ParseException e) {
            check = false;
            System.out.println("The input is error ");
        }
        return check;
    }
}
