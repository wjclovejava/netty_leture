package com.wjclovejava.decorator;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/1 10:14
 */
//相当于BuffedInputStream
public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }

    private void doAnotherThing() {
        System.out.println("功能B");
    }
}
