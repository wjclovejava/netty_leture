package com.wjclovejava.decorator;


/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/1 10:13
 */
//相当于FileInputStream
public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
