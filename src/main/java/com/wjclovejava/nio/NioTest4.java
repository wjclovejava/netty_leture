package com.wjclovejava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: wjc
 * @Description:
 *
 *  通过NIO读取文件涉及到3个步骤:
 *   1.从FileInputStream获取到FileChannel对象
 *   2.创建Buffer
 *   3.将数据从Channel中读取到文件中
 *  绝对方法与相对方法的含义:
 *  1.相对方法: limit值与position值会在操作时被考虑到
 *  2.绝对方法: 完全忽略掉limit值与position值
 * @Date: created in 2019/2/11 10:37
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("input.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("output.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        ByteBuffer buffer=ByteBuffer.allocate(1024);

        while(true){
            //如果注释该行代码? limit与position读取完一遍后
            //limit没有还原到capacity  position=limit 读取不到数据会返回0 永远不可能 -1 会不断重复追加相同内容
            //clear方法 : position=0 ,limit=capacity
            buffer.clear();

            int read =inputChannel.read(buffer);
            System.out.println("read="+read);
            if(-1 == read){
                break;
            }
            //flip方法:  position=0 ,limit不变
            buffer.flip();
            outChannel.write(buffer);
        }

        inputChannel.close();
        outChannel.close();
    }
}
