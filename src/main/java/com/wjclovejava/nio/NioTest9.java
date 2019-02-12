package com.wjclovejava.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/11 14:12
 */
public class NioTest9 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile=new RandomAccessFile("NioText9.txt", "rw");
        FileChannel fileChannel=randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE,0 , 5);
        //hello world welcome ->  aelbo world welcome
        mappedByteBuffer.put(0,(byte)'a');
        mappedByteBuffer.put(3,(byte)'b');

        randomAccessFile.close();

    }
}
