package com.csk;

import com.csk.config.DefinedConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import sun.util.logging.resources.logging;

/**
 * Created by 程少宽
 * 2017-06-02 17:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Value("${logging.config}")
    private String log1;

    @Autowired
    private  DefinedConfig definedConfig;

    @Autowired
    Environment environment;
    @Test
    public void test1() {
        String name = "csk";
        String password = "123456";

        System.err.println(definedConfig.getName()+definedConfig.getAge());
//        log.debug("debug....。。。。。。。。。。");
//        log.info(log1);
//        log.info(environment.getProperty("logging.config"));
//        log.info("name: " + name + " ,password: " + password);
//        log.info("name: {}, password: {}", name, password);
//        log.error("error...");
//        log.warn("warn...");

    }
}
