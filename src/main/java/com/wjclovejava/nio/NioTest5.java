package com.wjclovejava.nio;

import java.nio.ByteBuffer;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/11 11:03
 */
public class NioTest5 {

    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putChar('你');
        buffer.putDouble(11.223);
        buffer.putShort((short) 2);
        buffer.putLong(10000000L);

        buffer.flip();

        System.out.println();
        //必须按顺序取出,否则会报错 BufferUnderflowException
        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
        // System.out.println(buffer.getDouble());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getLong());


    }
}
