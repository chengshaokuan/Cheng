package com.csk.mybatisConfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
//添加配置文件,使用@Value("${key的值}")
//@PropertySource(value = {"classpath:jdbc.properties",}, ignoreResourceNotFound = true)
//@ImportResource(value = {"classpath:/spring-mybatis.xml"})   // 直接使用这个引入配置文件
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionTemplateRef  = "dcSqlSessionTemplate")
//配置mybatis mapper扫描路径
public class DataSourceConfig {

    static final String PACKAGE = "com.csk.repository.mapper";
    static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Bean(name = "dcDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dcDataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "dcTransactionManager")
//    public DataSourceTransactionManager dcTransactionManager() {
//        return new DataSourceTransactionManager(dcDataSource());
//    }

    @Bean(name = "dcSqlSessionFactory")
    public SqlSessionFactory dcSqlSessionFactory(@Qualifier("dcDataSource") DataSource dcDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        //设置mybatis的主配置文件
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource mybatisConfigXml = resolver.getResource("classpath:mybatis-config.xml");
//        sessionFactory.setConfigLocation(mybatisConfigXml);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCallSettersOnNulls(true);
        sessionFactory.setConfiguration(configuration);
        sessionFactory.setDataSource(dcDataSource);
        //配置mapper中的*.xml 文件
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "dcSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("dcSqlSessionFactory") SqlSessionFactory sqlSessionFactory)  {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
