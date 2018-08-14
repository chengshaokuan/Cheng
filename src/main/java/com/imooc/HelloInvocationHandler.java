package com.imooc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-08-09 10:32
 **/
public class HelloInvocationHandler  implements InvocationHandler{

    private Object target;

    public HelloInvocationHandler(Object target){
        this.target = target;
    }


    @Override
    public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(target,args);
        return null;
    }
}
