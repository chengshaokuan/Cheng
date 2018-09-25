package com.csk.utils.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * @program: Demo
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-07-19 14:37
 **/
public class PropertyDictionaryFactory {


    private static final Logger logger = LoggerFactory.getLogger(PropertyDictionaryFactory.class);

    public static Properties getDicProperty() {
        return HoldDicSingletonClass.dicProps;
    }

    public static Properties getReverseProperty(){
        return HoldDicSingletonClass.reverseProps;
    }

    private static class HoldDicSingletonClass{
        private static final String fileName = "timeUtil_zh_CN.properties";
        private static final Properties dicProps = readProperty(fileName);
        private static final String reversePropertyPath = "reverse-dictionary-converted.properties";
        private static final Properties reverseProps = readProperty(reversePropertyPath);
    }

    /** 
     * @Description: 读取配置文件的名称， 
     * @param: fileName
     * @return: java.util.Properties 
     * @Author: Mr.Cheng
     * @Date: 14:38 2018/7/19 
     */ 
    private static Properties readProperty(String fileName) {
        Reader reader = null;
        Properties pros = new Properties();
        try {
            reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName), "UTF-8");
            pros.load(reader);
        } catch (Exception e) {
            logger.error("加载文件出错 ：", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("关闭输入流出错 ：", e);
                }
            }
        }
        return pros;
    }
    public static void main (String[] args) throws Exception {
//        System.out.println(getValue("multi.year"));
    }
}
