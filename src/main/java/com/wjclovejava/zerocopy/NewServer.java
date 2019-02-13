package com.wjclovejava.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/12 16:53
 */
public class NewServer {
    public static void main(String[] args)throws Exception {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(8090));
        socket.setReuseAddress(true);

        ByteBuffer byteBuffer=ByteBuffer.allocate(4096);

        while(true){
            SocketChannel socketChannel=serverSocketChannel.accept();
            socketChannel.configureBlocking(true);

            int readCount=0;
            while(-1 !=readCount){
                try {
                    readCount =socketChannel.read(byteBuffer);
                }catch (Exception e){
                    e.printStackTrace();
                }
               byteBuffer.rewind();
            }
        }

    }
}
