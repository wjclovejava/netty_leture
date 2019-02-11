package com.wjclovejava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/11 11:29
 */
public class Niotest8 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("input2.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("output2.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        //与NioTest4不同的就是 创建一个直接缓冲   实现0拷贝
        ByteBuffer buffer=ByteBuffer.allocateDirect(1024);

        while(true){
            buffer.clear();
            int read =inputChannel.read(buffer);
            System.out.println("read= "+read);
            if(-1 == read){
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
        }

        inputChannel.close();
        outChannel.close();
    }
}
