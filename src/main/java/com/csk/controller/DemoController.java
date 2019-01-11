package com.csk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2019-01-11 10:01
 **/
@RestController
@RequestMapping("/test")
public class DemoController {
    //两种读取配置文件的方式。
    @Value("${Test.A}")
    private String testA;

    @Autowired
    private Environment environment;

    @RequestMapping("/A")
    public String demoTest(){

        return testA+"/"+environment.getProperty("Test.B");
    }

}
