package com.wjclovejava.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/1 11:16
 */
public class NioTest3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream("NioText3.txt");
        FileChannel fileChannel=fileOutputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(512);

        byte[] messages="hello world,welcome,zzz".getBytes();

        for(int i=0;i<messages.length;i++){
            byteBuffer.put(messages[i]);
        }
        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}
