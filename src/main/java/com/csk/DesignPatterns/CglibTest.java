package com.csk.DesignPatterns;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: Cheng
 * @description: CGLIB动态代理模式
 * @author: Mr.Cheng
 * @create: 2018-11-27 10:47
 **/
public class CglibTest {
    public static void main (String[] args) {

        BuyBus buyBus = new BuyBus();
        CglibProxy cglibProxy = new CglibProxy();
        BuyBus bus = (BuyBus) cglibProxy.getCglibProxy(buyBus);
        bus.buy();
    }
}
class BuyBus{
    public void buy(){
        System.out.println("买汽车");
    }
}
class CglibProxy implements MethodInterceptor{
    private Object target;
    public Object getCglibProxy(final Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept (Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object invoke = methodProxy.invoke(target, objects);
        return invoke;
    }
}