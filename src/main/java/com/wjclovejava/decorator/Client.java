package com.wjclovejava.decorator;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/1 10:13
 */
public class Client {

    public static void main(String[] args) throws Exception {

        //InputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("D://abc.txt")));

        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));

        component.doSomething();
    }
}