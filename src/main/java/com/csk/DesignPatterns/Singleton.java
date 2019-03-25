package com.csk.DesignPatterns;

/**
 * @program: cheng1
 * @description:
 * @author: Mr.Cheng
 * @create: 2019-03-22 18:24
 **/
//双重锁
public class Singleton {
    private static volatile Singleton singleton;

    public Singleton () {
    }

    public static Singleton getInstance () {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

//静态内部类
class Singleton1 {

    private Singleton1 () {
    }

    public static class getSingleton {
        private static final Singleton1 INSTANCE = new Singleton1();
    }

    public static Singleton1 getInstance () {
        return getSingleton.INSTANCE;
    }

}

//静态常量
class Singleton2 {
    private final static Singleton2 Instance = new Singleton2();

    private Singleton2 () {
    }

    public static Singleton2 getInstance () {
        return Instance;
    }
}

//静态代码块
class Singleton3 {
    private static final Singleton3 instance;

    private Singleton3 () {
    }

    static {
        instance = new Singleton3();
    }

    public static Singleton3 getInstance () {
        return instance;
    }

}


//枚举实现单例
enum Singleton4 {
    INSTANCE;

    public void getSingleton () {
    }
}



