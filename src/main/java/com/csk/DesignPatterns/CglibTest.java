package com.csk.DesignPatterns;

import net.sf.cglib.core.DebuggingClassWriter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @program: Cheng
 * @description: CGLIB动态代理模式
 * @author: Mr.Cheng
 * @create: 2018-11-27 10:47
 **/
public class CglibTest {
    public static void main (String[] args) throws IOException {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BuyBus.class);
        enhancer.setCallback(new CglibProxy());
        BuyBus buyBus = (BuyBus) enhancer.create();
        buyBus.buy();
        //orm


    }
}
class BuyBus{
    public void buy(){
        System.out.println("卖汽车");
    }
}
class CglibProxy implements MethodInterceptor{
    @Override
    public Object intercept (Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理前");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("代理后");
        return invoke;
    }
}