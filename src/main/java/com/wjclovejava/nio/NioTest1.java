package com.wjclovejava.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @Author: wjc
 * @Description:
 *  java.io
 *  java.nio
 *
 *  java.io  核心概念是流(stream),面向流编程,一个流要么是输入流,要么输出流,不可能既是输入流又是输出流
 *  java.nio 有3个核心概念:channel, selector, buffer ,它是面向块(block) 或者缓冲区(buffer)编程的
 *           Buffer本身就是一块内存,底层实现是数组,数据的读写都是通过buffer实现的,除了数组之外,
 *           Buffer还提供了对于数据的结构化访问方式,并且可以追踪到系统的读写过程
 *           Java中的8种原生数据类型都有各自对应的Buffer类型,如IntBuffer.LongBuffer,ByteBuffer,CharBuffer
 *           Channel指的是可以向其写入数据或是从中读取数据的对象,它类似java.io的Stream
 *           所有数据的读写都是通过Buffer来执行的,永远不会出现直接向Channel写入数据的情况,或是直接从CHannel读取数据的情况
 *           与Stream不同的是,Channel是双向的,一个流只能是InputSream或者是OutputStream,Channel打开后则可以进行读取,写入或者是读写
 *           由于Channel是双向的,因此它能更好地反映出底层操作系统的真是情况,在Linux系统中,底层操作系统的通道就是双向的
 *
 * @Date: created in 2019/2/1 10:45
 */
public class NioTest1 {

    public static void main(String[] args) {
        IntBuffer buffer=IntBuffer.allocate(10);

        for (int i =0 ;i<buffer.capacity();i++ ){
            int randomNumber =new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        buffer.flip();

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
