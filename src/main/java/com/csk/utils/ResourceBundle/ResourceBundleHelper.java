package com.csk.utils.ResourceBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @program: Cheng
 * @description: 多语言工具类，通过该类，获取本地多语言。
 * @author: Mr.Cheng
 * @create: 2018-07-20 16:03
 **/
public class ResourceBundleHelper {

    private static ResourceBundle resourceBundle = null;

    private String baseName;

    /**
     * 构造方法，通过多语言文件位置创建对象。
     *
     * @param baseName 多语言文件路径。
     */
    public ResourceBundleHelper (String baseName) {
        this.baseName = baseName;
        loadBundle();
    }

    public void setBaseName (String baseName) {
        this.baseName = baseName;
    }

    /**
     * 构造方法，通过多语言文件位置、Locale创建对象。
     */
    public synchronized void loadBundle () {
        resourceBundle = ResourceBundle.getBundle(baseName, Locale.getDefault());
    }

    /**
     * @Description: 根据多语言文件中的key，获取对应的值
     * @param: key  多语言文件中的key
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 15:18 2018/7/20
     */
    public static String getString (String key) {
        return resourceBundle.getString(key);
    }
}
