package com.wjclovejava.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/12 17:01
 */
public class NewClient {
    public static void main(String[] args) throws  Exception{
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8090));
        socketChannel.configureBlocking(true);

        String fileName = "C:\\Program Files\\Java\\jdk1.8.0_191\\javafx-src.zip";

        FileChannel fileChannel=new FileInputStream(fileName).getChannel();
        long startTime=System.currentTimeMillis();

        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数：" + transferCount + "，耗时： " + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }
}
