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
 *
 *  ASCII(American Standard Code for Information Interchange,美国信息交换标准代码): 7bit 来表示一个字符,共计可以表示128种字符
 *  ISO-8859-1: 8bit 来表示一个字符,即用一个字节(byte)表示一个字符,共计可以表示256种字符
 *  gb2312 : 两个字节表示一个汉字
 *  gbk : 扩充gb2312
 *  gb18030 : 扩充gbk
 *  unicode : 采用2个字节表示一个字符
 *  UTF, (Unicode Translation Format)
 *      unicode 是一个编码方式,UTF是一种存储方式,UTF-8 是unicode的实现方式之一
 *      utf-16LE(little endian),utf-16BE(big endian)  0xFEFF(BE) 0xFFFE(LE)
 *  UTF-8 : 变长字节表示形式 英文用一个字节,用三个字节表示一个中文,最多6
 *  BOM (Byte Order Mark):
 *
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
            //System.out.println(k+","+v);
        });
        Charset charset=Charset.forName("iso-8859-1");
        CharsetDecoder decoder=charset.newDecoder();
        CharsetEncoder encoder=charset.newEncoder();

        CharBuffer charBuffer=decoder.decode(inputData);

        System.out.println(charBuffer.get(12));
        ByteBuffer outputData =encoder.encode(charBuffer);
        //ByteBuffer outputData =Charset.forName("utf-8").encode(charBuffer);
        outputFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();


    }
}
