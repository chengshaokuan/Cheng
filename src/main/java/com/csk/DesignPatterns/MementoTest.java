package com.csk.DesignPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Cheng
 * @description: 备忘录模式
 * @author: Mr.Cheng
 * @create: 2018-11-27 14:38
 **/
public class MementoTest {
    public static void main (String[] args) {

        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}

class Memento {
    private String state;

    public Memento (String state) {
        this.state = state;
    }

    public String getState () {
        return state;
    }
}

class Originator {
    private String state;
    public String getState () {
        return state;
    }
    public void setState (String state) {
        this.state = state;
    }
    public Memento saveMemento () {
        return new Memento(state);
    }
    public void getMemento (Memento memento) {
        state = memento.getState();
    }
}

class CareTaker {
    private List<Memento> list = new ArrayList<>();

    public void add (Memento memento) {
        list.add(memento);
    }

    public Memento get (int i) {
        return list.get(i);
    }
}


////备忘录
//class Memento {
//    private String state;
//
//    public Memento (String state) {
//        this.state = state;
//    }
//    public String getState () {
//        return state;
//    }
//}
//
////发起人创建一个备忘录
//class Originator {
//    private String state;
//
//    public String getState () {
//        return state;
//    }
//
//    public void setState (String state) {
//        this.state = state;
//    }
//
//    public Memento saveMemento () {
//        return new Memento(state);
//    }
//
//    public void getMemento (Memento memento) {
//        state = memento.getState();
//    }
//}
//
////管理者
//class CareTaker {
//    private List<Memento> list = new ArrayList<>();
//
//    public void add (Memento memento) {
//        list.add(memento);
//    }
//
//    public Memento get (int index) {
//        return list.get(index);
//    }
//}