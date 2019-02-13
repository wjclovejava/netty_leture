package com.wjclovejava.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/12 16:27
 */
public class OldServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket(8090);

        while(true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
            try{
                byte[] byteArrays=new byte[4096];
                while (true){
                    int readCount=dataInputStream.read(byteArrays,0 ,byteArrays.length );
                    if(-1 ==readCount){
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
