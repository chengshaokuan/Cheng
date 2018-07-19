package com.imooc.utils.util2.utils;

import com.creditease.skytrain.binding.PropertyHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by xding on 2017/5/31.
 */
@Component
public class SIAConfUtil {

    @Value("${sia.conf.path}")
    private String siaConfPath;

    @PostConstruct
    private void init() {
        System.out.println("加载 SIA 配置路径:" + siaConfPath);
        PropertyHelper.setProfilePath(siaConfPath);
    }

}
