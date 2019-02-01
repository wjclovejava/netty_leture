package com.wjclovejava.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/1/31 10:36
 */
public class ProtoBufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三")
                .setAge(20)
                .setAddress("哈尔滨")
                .build();
        byte[] student2ByteArray=student.toByteArray();

        DataInfo.Student student2= DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}
