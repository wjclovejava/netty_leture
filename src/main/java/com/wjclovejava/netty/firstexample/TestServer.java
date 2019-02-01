package com.wjclovejava.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/1/29 14:02
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        //boss线程组 不断的接收连接 但是不做处理,都交给worker线程组处理
        EventLoopGroup bossGroup =new NioEventLoopGroup();
        EventLoopGroup  workerGroup=new NioEventLoopGroup();
        try {
            //用于启动服务端的类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            //关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
