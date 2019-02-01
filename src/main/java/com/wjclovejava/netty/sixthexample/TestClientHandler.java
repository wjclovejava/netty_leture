package com.wjclovejava.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/1/31 11:06
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt=new Random().nextInt(3);
        MyDataInfo.Mymessqge mymessqge=null;
        if(0==randomInt){
            mymessqge=MyDataInfo.Mymessqge.newBuilder()
                    .setDataType(MyDataInfo.Mymessqge.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder().setName("李四").setAge(19).setAddress("杭州").build())
                    .build();
        }else if(1 ==randomInt){
            mymessqge=MyDataInfo.Mymessqge.newBuilder()
                    .setDataType(MyDataInfo.Mymessqge.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setName("来福").setAge(5).build())
                    .build();
        }else if(2 ==randomInt){
           mymessqge=MyDataInfo.Mymessqge.newBuilder()
                    .setDataType(MyDataInfo.Mymessqge.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("猫猫").setCity("上海").build())
                    .build();
        }

        //发送给服务端
        ctx.channel().writeAndFlush(mymessqge);
    }
}
