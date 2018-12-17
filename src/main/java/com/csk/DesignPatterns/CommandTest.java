package com.csk.DesignPatterns;

/**
 * @program: Cheng
 * @description: 命令模式
 * @author: Mr.Cheng
 * @create: 2018-11-26 15:01
 **/
public class CommandTest {
    public static void main (String[] args) {
        Receiver receiver = new Receiver();
        ConcreteCommand concreteCommand = new ConcreteCommand(receiver);

        Invoker invoker = new Invoker(concreteCommand);
        invoker.action();
    }
}

interface Command{
    void method();
}
class ConcreteCommand implements Command{
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void method () {
        receiver.action();
    }
}

class Invoker{
    private Command command;
    public Invoker(Command command){
        this.command = command;
    }
    public void action(){
        command.method();
    }
}
class Receiver{
    public void action(){
        System.out.println("接收者执行");
    }
}


