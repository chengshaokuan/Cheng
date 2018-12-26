package com.csk.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * @program: cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-12-26 11:15
 **/
//@Configuration
public class MyBatisConfig {

//    @Bean
//    @ConditionalOnMissingBean //当容器里没有指定的Bean的情况下创建该对象
    public SqlSessionFactoryBean sqlSessionFactory (DataSource dcDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        // 设置数据源
//        sqlSessionFactoryBean.setDataSource(dcDataSource);
//        // 设置mybatis的主配置文件
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource mybatisConfigXml = resolver.getResource("classpath:mybatis/mybatis-config.xml");
//        sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);
//        // 设置别名包
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.taotao.cart.pojo");
        return sqlSessionFactoryBean;
    }
}
