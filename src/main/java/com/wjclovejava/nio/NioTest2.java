package com.wjclovejava.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/1 11:11
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("NioText2.txt");

        FileChannel fileChannel=fileInputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(512);
        //将文件内容读取到byteBuffer中
        fileChannel.read(byteBuffer);
        //操作反转
        byteBuffer.flip();

        while(byteBuffer.remaining()>0){
            byte b=byteBuffer.get();
            System.out.println("Character:"+(char)b);
        }
        fileInputStream.close();
    }
}
