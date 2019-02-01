package com.wjclovejava.netty.fourthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/1/30 10:35
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline=ch.pipeline();
        //空闲状态检测的处理器,5秒内没有读数据,7秒内没有写数据,读写操作加一起超过10秒
        pipeline.addLast(new IdleStateHandler(5,7,10,TimeUnit.SECONDS));
        pipeline.addLast(new MyServerHandler());
    }
}
