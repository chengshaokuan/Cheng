package com.csk.utils.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @program: Demo
 * @description: 读取Properties配置文件工具类
 * @author: Mr.Cheng
 * @create: 2018-07-19 14:26
 **/
public class PropertiesReader {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesReader.class.getName());

    private static final String PROPERTIES_SUFFIX = ".properties";


    private PropertiesReader () {
    }

    /**
     * @Description: 根据路径获取配置文件
     * @param: location
     * @return: java.util.Properties
     * @Author: Mr.Cheng
     * @Date: 14:31 2018/7/19
     */
    public static Properties getProperties (String location) {
        Properties props = new Properties();
        InputStream is = null;
        try {
            String pathToUse = location.substring("classpath:".length()) + PROPERTIES_SUFFIX;
            if (pathToUse.startsWith("/")) {
                pathToUse = pathToUse.substring(1);
            }
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathToUse);
            props.load(is);
        } catch (Exception e) {
            logger.info("配置文件{" + location + "}格式有误或不存在！");
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }
}
