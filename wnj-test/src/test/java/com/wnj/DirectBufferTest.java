package com.wnj;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DirectBufferTest {
    static int _1Gb = 1024 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        System.out.println("分配前...");
        System.in.read();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1Gb);
        System.out.println("分配完毕...");
        System.in.read();
        System.out.println("开始释放...");
        byteBuffer = null;
        System.gc(); // 显式的垃圾回收，Full GC
        System.out.println("显示gc...");
        System.in.read();
    }
}
