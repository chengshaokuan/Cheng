package com.csk.DesignPatterns;

/**
 * @program: Cheng
 * @description: 装饰模式
 * @author: Mr.Cheng
 * @create: 2018-11-26 17:07
 **/
public class DecoratorTest {
    public static void main (String[] args) {
        ConcreteComponent concreteComponent = new ConcreteComponent();
        Decorator decorator = new Decorator(concreteComponent);
        decorator.method();
    }
}

interface Component{
    void method();
}
class ConcreteComponent implements  Component{
    @Override
    public void method () {
        System.out.println("实现类");
    }
}
class Decorator implements Component{
    private Component component;
    public Decorator(Component component){
        this.component = component;
    }
    @Override
    public void method () {
        System.out.println("装饰类");
        component.method();
    }
}













