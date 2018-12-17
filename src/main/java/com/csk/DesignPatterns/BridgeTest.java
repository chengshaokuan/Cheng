package com.csk.DesignPatterns;

/**
 * @program: Cheng
 * @description: 桥接模式
 * @author: Mr.Cheng
 * @create: 2018-11-26 16:54
 **/
public class BridgeTest {
    /**
     * 桥接模式就是将抽象部分与它的实现部分分离，使它们都可以独立地变化
     */
    public static void main (String[] args) {

        Bridge bridge = new ConcreteBridge();
        Bridge bridge1 = new ConcreteBridge1();
        Bridge bridge2 = new ConcreteBridge2();

        bridge.setImplementor(new ConcreteImplementor());
        bridge.run();
        bridge.setImplementor(new ConcreteImplementor1());
        bridge.run();
        bridge1.setImplementor(new ConcreteImplementor());
        bridge1.run();
        bridge1.setImplementor(new ConcreteImplementor1());
        bridge1.run();
        bridge2.setImplementor(new ConcreteImplementor());
        bridge2.run();
        bridge2.setImplementor(new ConcreteImplementor1());
        bridge2.run();
    }
}

interface Implementor {
    void method ();
}

class ConcreteImplementor implements Implementor {
    @Override
    public void method () {
        System.out.println("桥接的实现类1");
    }
}
class ConcreteImplementor1 implements Implementor {
    @Override
    public void method () {
        System.out.println("桥接的实现类2");
    }
}

abstract class Bridge {
    private Implementor implementor;

    public Implementor getImplementor () {
        return implementor;
    }

    public void setImplementor (Implementor implementor) {
        this.implementor = implementor;
    }

    public void run () {
        implementor.method();
    }
}

class ConcreteBridge extends Bridge {

    @Override
    public void run () {
        System.out.println("具体桥接1");
        super.run();
        System.out.println("-----------");
    }
}
class ConcreteBridge1 extends Bridge {

    @Override
    public void run () {
        System.out.println("具体桥接2");
        super.run();
        System.out.println("-----------");
    }
}class ConcreteBridge2 extends Bridge {

    @Override
    public void run () {
        System.out.println("具体桥接3");
        super.run();
        System.out.println("-----------");
    }
}