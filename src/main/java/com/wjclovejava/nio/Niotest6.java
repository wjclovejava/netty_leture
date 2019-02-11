package com.wjclovejava.nio;

import java.nio.ByteBuffer;

/**
 * @Author: wjc
 * @Description:
 *
 * @Date: created in 2019/2/11 11:10
 */
public class Niotest6 {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(10);

        for(int i=0;i<buffer.capacity();i++){
            buffer.put((byte)i);
        }

        buffer.position(2);
        buffer.limit(6);
        //总结:sliceBuffer 与 buffer 共用一个buffer
        ByteBuffer sliceBuffer = buffer.slice();

        for(int i=0;i<sliceBuffer.capacity();i++){
            byte b=sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i,b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
