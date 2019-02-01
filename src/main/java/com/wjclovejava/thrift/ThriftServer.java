package com.wjclovejava.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.*;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/1/31 15:29
 */
public class ThriftServer {

    public static void main(String[] args) throws Exception {

        TNonblockingServerSocket socket=new TNonblockingServerSocket(8090);
        //半同步半异步Server   最小的WorkerThreads是2 最大4
        THsHaServer.Args arg=new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor=new PersonService.Processor<>(new PersonServiceImpl());
        //一.协议层 强调数据的格式
        // (*)TCompactProtocol 压缩格式
        //TBinaryProtocol 二进制格式
        //TJSONProtocol json格式
        //TSimpleJSONProtocol 提供JSON只写协议,生成的文件很容易通过脚本语言解析 (极少使用)
        arg.protocolFactory(new TCompactProtocol.Factory());
        //二.传输层
        //TSocket -阻塞式socket
        // (*)TFramedTransport 以frame为单位进行传输,非阻塞式服务中使用
        //TFileTransport 以文件形式传输
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));
        //三.Thrift支持的服务模型
        //TSimpleServer 简单的单线程服务模型,常用于测试
        //TThreadPoolServer 多线程服务模型,使用标准的阻塞式I/O
        //TNonblockingServer  多线程服务模型,非阻塞式I/O(需要使用TFramedTransport数据传输方式)
        // (*)THsHaServer -Thshs引入了线程池去处理,其模型把读写任务放到线程池去处理;Half-Sync/Half-Async 半同步半异步的处理模式
        //      Half-Async是在处理IO事件上(accept/read/write io); Half-Sync用于handler对rpc的同步处理(需要使用TFramedTransport数据传输方式)
        TServer server =new THsHaServer(arg);

        System.out.println("Thrift Server Started !");

        server.serve();
    }
}
