package com.csk.DesignPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Cheng
 * @description: 访问者模式
 * @author: Mr.Cheng
 * @create: 2018-11-26 14:08
 **/
public class VisitorTest {
    public static void main (String[] args) {

    }
}
interface Visitor{
    void visitor(ConcreteElementA concreteElementA);
    void visitor(ConcreteElementB concreteElementB);
}
class ConcreteVisitor implements Visitor{
    @Override
    public void visitor (ConcreteElementA concreteElementA) {
        concreteElementA.accept();
    }

    @Override
    public void visitor (ConcreteElementB concreteElementB) {

        concreteElementB.accept();
    }
}


interface Element{
    void accept();
    void doSomething();
}
class ConcreteElementA implements Element{
    private Visitor visitor;
    public void setVisitor (Visitor visitor) {
        this.visitor = visitor;
    }
    @Override
    public void accept () {
        visitor.visitor(this);
    }
    @Override
    public void doSomething () {

    }
}
class ConcreteElementB implements Element{
    private Visitor visitor;
    @Override
    public void accept () {
        visitor.visitor(this);
    }

    @Override
    public void doSomething () {

    }
}
class ObjectStructure{
    public static List<Element> getList(){
        List<Element> elements = new ArrayList<>();
        elements.add(new ConcreteElementA());
        return elements;
    }
}