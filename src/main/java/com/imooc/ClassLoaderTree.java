package com.imooc;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-08-09 10:35
 **/
public class ClassLoaderTree {

    public static void main (String[] args) {
        ClassLoader loader = ClassLoaderTree.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }
}


//    public static void main(String[] args){
//生成$Proxy0的class文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        HelloInvocationHandler helloInvocationHandler = new HelloInvocationHandler(new IHelloImpl());
//获取动态代理类
//        IHello o = (IHello)Proxy.newProxyInstance(IHelloImpl.class.getClassLoader(), IHelloImpl.class.getInterfaces(), helloInvocationHandler);
//        Class proxyClazz = Proxy.getProxyClass(IHello.class.getClassLoader(),IHello.class);
//获得代理类的构造函数，并传入参数类型InvocationHandler.class
//        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
//通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
//        IHello iHello = (IHello) constructor.newInstance(new HelloInvocationHandler(new IHelloImpl()));
//通过代理对象调用目标方法
//        o.sayHello();
//    }
