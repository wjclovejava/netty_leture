package com.wjclovejava.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/1/29 15:47
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     *
     * @param ctx 上下文
     * @param msg 客户端发出的请求对象,String类型
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+","+msg);

        ctx.channel().writeAndFlush("from server:"+UUID.randomUUID());

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
