package com.csk.utils.property;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @program: Demo
 * @description: 加载配置文件
 * @author: Mr.Cheng
 * @create: 2018-07-19 14:55
 **/
public class PropertyFactory {

    private static Logger logger = LoggerFactory.getLogger(PropertyFactory.class);
    private static Properties pros = new Properties();


    public PropertyFactory (List<String> filePaths) {
        if (filePaths != null) {
            for (int i = 0; i < filePaths.size(); i++) {
                String filePath = filePaths.get(i);
                InputStream in = null;
                URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
                if (null == url) {
                    url = this.getClass().getClassLoader().getResource(filePath);
                }
                // 解决文件路径可能出现空格的问题
                String path = url.getFile();
                if (!"".equals(path)) {
                    path = path.replace("%20", " ");
                }
                try {
                    in = new BufferedInputStream(new FileInputStream(path));
                    pros.load(in);
                } catch (Exception e) {
                    logger.error("加载配置文件出错 ：\n", e);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            logger.error("关闭输入流出错 ：\n", e);
                        }
                    }
                }
            }
        }
    }

    /**
     * @Description: 通过key获取参数值。
     * @param: key  参数文件key
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 14:58 2018/7/19
     */
    public static String getProperty (String key) {
        return pros.getProperty(key);
    }

    /**
     * @Description:
     * @param: key
     * @param: value
     * @return: void
     * @Author: Mr.Cheng
     * @Date: 14:58 2018/7/19
     */
    public void setProperty (String key, String value) {
        pros.setProperty(key, value);
    }

    /**
     * @Description: 读取参数文件
     * @param: path   参数文件路径
     * @param: fileName  参数文件名
     * @return: java.util.HashMap<java.lang.String   ,   java.lang.String>
     * @Author: Mr.Cheng
     * @Date: 14:59 2018/7/19
     */
    public HashMap<String, String> readProperty (String path, String fileName) {
        HashMap<String, String> map = new HashMap<String, String>();
        InputStream in = null;
        Properties p = new Properties();
        URL url = Thread.currentThread().getContextClassLoader().getResource(path + fileName);
        if (null == url) {
            url = this.getClass().getClassLoader().getResource(path + fileName);
        }
        // 解决文件路径可能出现空格的问题
        String filepath = url.getFile();
        if (!"".equals(filepath)) {
            path = path.replace("%20", " ");
        }
        try {
            in = new BufferedInputStream(new FileInputStream(filepath));
            p.load(in);
            Enumeration<?> e = p.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String ruleStr = p.getProperty(key);
                map.put(key, ruleStr);
            }
        } catch (Exception e) {
            logger.error("加载验证文件出错 ：\n", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("关闭输入流出错 ：\n", e);
                }
            }
        }
        return map;
    }

    /**
     * Description:加载XML文件
     * @param path     参数文件路径
     * @param fileName 参数文件名
     * @return List<HashMap                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               String>>
     * @throws
     * @Author: Mr.Cheng
     * @Date: 14:59 2018/7/19
     */
    @SuppressWarnings("unchecked")
    public List<HashMap<String, String>> readValidateXml (String path, String fileName) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(path + fileName);
        if (null == url) {
            url = this.getClass().getClassLoader().getResource(path + fileName);
        }
        InputStream in = null;
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        String name = "";
        String condition = "";
        String notNull = "";
        String express = "";
        String message = "";
        String nameStr = "";
        try {
            in = new BufferedInputStream(new FileInputStream(url.getFile()));
            SAXReader sReader = new SAXReader();
            Document document = sReader.read(in);
            Element root = document.getRootElement();
            List<Element> fields = root.elements("field");
            for (Element field : fields) {
                HashMap<String, String> ruleMap = new HashMap<String, String>();
                name = field.attributeValue("name");
                condition = field.elementText("condition");
                express = field.elementText("express");
                notNull = field.elementText("notNull");
                message = field.elementText("message");
                nameStr = field.elementText("nameStr");
                ruleMap.put("name", name);
                ruleMap.put("condition", condition);
                ruleMap.put("express", express);
                ruleMap.put("notNull", notNull);
                ruleMap.put("message", message);
                ruleMap.put("nameStr", nameStr);
                list.add(ruleMap);
            }
        } catch (Exception e) {
            logger.error("加载验证XML文件出错 ：\n", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("关闭输入流出错 ：\n", e);
                }
            }
        }
        return list;
    }

    /**
     * 重新加载/config.properties配置信息。
     */
    public synchronized void reloadProperties () {
        HashMap<String, String> properties = this.readProperty("", "/config.properties");
        pros.putAll(properties);
    }

    public static void main (String[] args) throws Exception {
//        System.out.println(getValue("multi.year"));
        while(true){
            System.out.print(1);
        }
    }

}
