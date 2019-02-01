package com.wjclovejava.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/1/29 19:58
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     *  保存每一个与服务器创建连接的channel对象
     */
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 服务端收到任意一个客户端的信息时调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //向服务器发送消息的连接
        Channel channel=ctx.channel();
        channelGroup.forEach(ch -> {
            if(channel!= ch){
                ch.writeAndFlush(channel.remoteAddress()+"发送的消息:"+msg+"\n");
            }else {
                ch.writeAndFlush("[自己]:"+msg+"\n");
            }
        });
    }

    /**
     * 连接建立
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        //广播通知每一个channel
        channelGroup.writeAndFlush("[服务器] - "+channel.remoteAddress() +"加入\n");
        channelGroup.add(channel);
        System.out.println("聊天人数:在线"+channelGroup.size()+"人");
    }

    /**
     * 失去连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        channelGroup.writeAndFlush("[服务器] - "+channel.remoteAddress() +"离开\n");
        //不需要写,会自动移除 channelGroup.remove(channel);
        System.out.println("聊天人数:在线"+channelGroup.size()+"人");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        System.out.println(channel.remoteAddress()+"上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        System.out.println(channel.remoteAddress()+"下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
