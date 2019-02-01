package com.wjclovejava.decorator;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/1 10:15
 */
//相当于DataInputStream
public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }

    private void doAnotherThing() {
        System.out.println("功能C");
    }

}