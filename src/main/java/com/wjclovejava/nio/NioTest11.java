package com.wjclovejava.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author: wjc
 * @Description:     关于buffer的 Scattering(分散) 与 Gathering(聚集)
 * @Date: created in 2019/2/11 14:24
 */
public class NioTest11 {
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        InetSocketAddress address=new InetSocketAddress(8090);
        serverSocketChannel.bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffers=new ByteBuffer[3];

        buffers[0] =ByteBuffer.allocate(2);
        buffers[1] =ByteBuffer.allocate(3);
        buffers[2] =ByteBuffer.allocate(4);
        SocketChannel socketChannel=serverSocketChannel.accept();
        while (true){
            int byteRead = 0;
            while(byteRead <messageLength){
                long r=socketChannel.read(buffers);
                byteRead++;

                System.out.println("byteRead: "+byteRead);
                Arrays.asList(buffers).stream().map(buffer->"position" +buffer.position()+",limit:"+buffer.limit())
                        .forEach(System.out::println);
            }
            Arrays.asList(buffers).forEach(buffer->{
                buffer.flip();
            });

            long byteWritten =0;
            while(byteWritten <messageLength){
                long r=socketChannel.write(buffers);
                byteWritten += r;
            }
            Arrays.asList(buffers).forEach(buffer->{
                buffer.clear();
            });
            System.out.println("byteRead: "+byteRead+",byteWritten: "+byteWritten+",messageLength:"+messageLength);

        }
        //客户端通过 cmd命令窗口 telnet localhost 8090 连接
    }
}
