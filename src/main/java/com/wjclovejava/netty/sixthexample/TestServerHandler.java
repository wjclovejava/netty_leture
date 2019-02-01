package com.wjclovejava.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/1/31 10:58
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Mymessqge> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Mymessqge msg) throws Exception {

        MyDataInfo.Mymessqge.DataType dataType=msg.getDataType();

        if(dataType ==MyDataInfo.Mymessqge.DataType.PersonType){
            MyDataInfo.Person person=msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        }else  if(dataType ==MyDataInfo.Mymessqge.DataType.DogType){
            MyDataInfo.Dog dog=msg.getDog();
            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        }else  if(dataType ==MyDataInfo.Mymessqge.DataType.CatType){
            MyDataInfo.Cat cat=msg.getCat();
            System.out.println(cat.getName());
            System.out.println(cat.getCity());
        }
    }


}
