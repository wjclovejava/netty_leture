package com.wjclovejava.decorator;


/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/1 10:14
 */
//相当于FilterInputStream
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void doSomething() {
        component.doSomething();
    }

}