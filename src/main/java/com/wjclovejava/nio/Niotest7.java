package com.wjclovejava.nio;

import java.nio.ByteBuffer;

/**
 * @Author: wjc
 * @Description:
 *
 * 只读buffer 可以随时将一个读写buffer 通过调用 asReadOnlyBuffer方法返回一个只读buffer
 * 但是不能讲一个只读buffer 转成 读写buffer
 *
 * @Date: created in 2019/2/11 11:19
 */
public class Niotest7 {
    public static void main(String[] args) {
        //allocate 创建一个 HeapByteBuffer 堆
        ByteBuffer buffer=ByteBuffer.allocate(10);

        for (int i=0;i<buffer.capacity();i++){
            buffer.put((byte)i);
        }
        //只读buffer
        ByteBuffer readonlyBuffer=buffer.asReadOnlyBuffer();

        System.out.println(readonlyBuffer.getClass());

        readonlyBuffer.position(0);
        //会抛出异常 ReadOnlyBufferException
        readonlyBuffer.put((byte)2);
    }
}
