package com.csk;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-08-10 11:37
 **/
public class HelloProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();


    //通过Class对象获取代理对象
    public Object getProxy(Class c){
        //设置创建子类的类
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method m, Object[] args, MethodProxy proxy) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("日志开始...");
        //代理类调用父类的方法
        proxy.invokeSuper(obj, args);
        System.out.println("日志结束...");
        return null;
    }

}
