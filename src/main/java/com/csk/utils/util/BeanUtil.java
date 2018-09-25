package com.csk.utils.util;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * @Project : panda
 * @Program Name : com.creditease.panda.engine.utils.BeanUtil
 * @Class Name : BeanUtil
 * @Copyright : Copyright (c)2017-2015
 * @Company : CreditEase
 * @Description :
 * @version : 1.0
 * @Author : ivan
 * @Creation Date : 2017/7/12 9:58
 * @ModificationHistory
 * Date Author Version Description
 * ------------------------------------------------------------------
 * 2017/7/12 1.0 Version
 */
public class BeanUtil {

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static <T,S> void copy2List(List<T> target, List<S> src, Class<T> t) throws IllegalAccessException, InstantiationException {
        if(CollectionUtils.isNotEmpty(src)){
            for(int i= 0 ;i<src.size();i++){
                target.add(t.newInstance());
                BeanUtil.copyPropertiesIgnoreNull(src.get(i), target.get(i));
            }
        }
    }

    /**
     * 忽略空字段进行数据copy
     * Description:
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static void copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target);
    }


    public static Map<String, String> beanToMap(Object obj) {
        Map<String, String> params = new HashMap<String, String>();
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                Object value = propertyUtilsBean.getNestedProperty(obj, name);
                if (!"class".equals(name) && value!=null) {
                    params.put(name, String.valueOf(value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return params;
    }
}
