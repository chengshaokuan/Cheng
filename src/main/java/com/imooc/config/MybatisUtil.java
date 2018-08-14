package com.imooc.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-07-23 14:32
 **/
public class MybatisUtil {

    public static SqlSession getInsertance(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //这是读取文件后 形成一个输入流 涨知识了
        //Test 其实就是一个类型 写自己的什么类型都行 主要是为了获取到getClassLoader().getResourceAsStream
        InputStream reader = MybatisUtil.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        try {
            InputStream reader1 = MybatisUtil.class.getClassLoader().getResource("").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List list = sqlSession.selectList("com.foo.bean.BlogMapper.queryAllBlogInfo");
        //获取sqlsessionFactory
        SqlSessionFactory factory =  builder.build(reader);
        //获取session
        SqlSession session = factory.openSession();
        return session;
    }
}
