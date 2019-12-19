package com.awakeee.hodgepodge.netty1;

import io.netty.util.CharsetUtil;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioTest6 {

    public static void main(String[] args) throws IOException {

        String inputFile  = "input1.txt";
        String outputFile = "output1.txt";

        RandomAccessFile in = new RandomAccessFile(inputFile,"r");
        RandomAccessFile out = new RandomAccessFile(outputFile,"rw");

        long inputLength = new File(inputFile).length();

        FileChannel inc = in.getChannel();
        FileChannel outc = out.getChannel();

        //内存映射buff,直接在内存中修改文件而不用读取到硬盘，速度更快
        MappedByteBuffer inputData = inc.map(FileChannel.MapMode.READ_ONLY,0,inputLength);

        Charset charset = CharsetUtil.UTF_8;
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inputData);
        ByteBuffer outData = encoder.encode(charBuffer);

        outc.write(outData);

        in.close();
        out.close();



    }
}
