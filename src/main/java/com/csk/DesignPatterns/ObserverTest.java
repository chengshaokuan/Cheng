package com.csk.DesignPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Cheng
 * @description: 观察者模式
 * @author: Mr.Cheng
 * @create: 2018-11-26 10:06
 **/
public class ObserverTest {

    public static void main (String[] args) {

        ConcreteObserver concreteObserver = new ConcreteObserver();
        ConcreteObserver2 concreteObserver2 = new ConcreteObserver2();
        ConcreteObserverable concreteObserverable = new ConcreteObserverable();
        concreteObserverable.addObserver(concreteObserver);
        concreteObserverable.addObserver(concreteObserver2);
        concreteObserverable.run();
        concreteObserverable.deleteObserver(concreteObserver);
        concreteObserverable.run();
    }
}
interface Observer{
    void upadate();
}

interface Observerable{
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
    void notifyObserver();
}
class ConcreteObserver implements Observer{

    @Override
    public void upadate () {
        System.out.println("实际观察者一号");
    }
}
class ConcreteObserver2 implements Observer{

    @Override
    public void upadate () {
        System.out.println("实际观察者2号");
    }
}

class ConcreteObserverable implements Observerable{
    private List<Observer> list = new ArrayList<>();
    @Override
    public void addObserver (Observer observer) {
        list.add(observer);
    }

    @Override
    public void deleteObserver (Observer observer) {
        list.remove(observer);
    }

    @Override
    public void notifyObserver () {

        for(Observer o:list){
            o.upadate();
        }
    }
    public void run(){
        System.out.println("具体要修改的事务");
        notifyObserver();


    }

}