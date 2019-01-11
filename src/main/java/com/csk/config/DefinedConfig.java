package com.csk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: cheng
 * @description: 自定义读取配置文件
 * @author: Mr.Cheng
 * @create: 2019-01-10 18:14
 **/
@Data
@Component
@ConfigurationProperties(prefix = "defined")
//application.yml指定的dev文档也可以使用@Value("${键名}")或者Environment，env.getProperty("键名")
//只能是.properties(任意名),或者application.yml(或者指定的application-dev.yml)才可以读取到值。
@PropertySource(value = "classpath:/defined.properties")
public class DefinedConfig {

    private String name;
    private String age;

    /**
     * 可以在类中使用下面方法调用
     * @Autowired
     * private  DefinedConfig definedConfig;
     *
     * definedConfig.getNmae()
     *
     */
}
