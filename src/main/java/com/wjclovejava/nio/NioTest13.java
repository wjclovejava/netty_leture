package com.wjclovejava.nio;


import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2019/2/11 17:24
 */
public class NioTest13 {
    public static void main(String[] args) throws Exception {
        String inputFile ="NioText13_in.txt";
        String outputFile ="NioText13_out.txt";

        RandomAccessFile inputRandomAccessFile=new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile=new RandomAccessFile(outputFile, "rw");

        long inputLength=new File(inputFile).length();

        FileChannel inputFileChannel=inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        MappedByteBuffer inputData =inputFileChannel.map(FileChannel.MapMode.READ_ONLY,0 ,inputLength );
        //系统所有的可用编码
        Charset.availableCharsets().forEach((k,v)->{
            System.out.println(k+","+v);
        });
        Charset charset=Charset.forName("iso-8859-1");
        CharsetDecoder decoder=charset.newDecoder();
        CharsetEncoder encoder=charset.newEncoder();

        CharBuffer charBuffer=decoder.decode(inputData);

        ByteBuffer outputData =encoder.encode(charBuffer);

        outputFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();


    }
}
