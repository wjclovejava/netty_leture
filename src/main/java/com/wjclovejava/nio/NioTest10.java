package com.wjclovejava.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/11 14:20
 */
public class NioTest10 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile=new RandomAccessFile("NioText10.txt", "rw");
        FileChannel fileChannel=randomAccessFile.getChannel();
        //从第三个位置开始锁,锁6个长度,共享锁,false是排他锁
        FileLock lock = fileChannel.lock(3,6,true);

        System.out.println("valid: "+lock.isValid());
        System.out.println("lock type:" +lock.isShared());
    }
}
