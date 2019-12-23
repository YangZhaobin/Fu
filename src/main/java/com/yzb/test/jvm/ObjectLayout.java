package com.yzb.test.jvm;

import org.openjdk.jol.info.ClassLayout;

public class ObjectLayout {

    int i;

    String str;

    public static void main(String[] args) {
//        com.yzb.test.jvm.ObjectLayout object internals:
//        OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
//        0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4                    (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
//        12     4                int ObjectLayout.i                            0
//        16     4   java.lang.String ObjectLayout.str                          null
//        20     4                    (loss due to the next object alignment)
//        Instance size: 24 bytes
//        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

        // 对象头： 12byte 96bit
        //    - Mark Work (8byte)                存储对象的hashCode、锁信息、分代年龄（占4bit 所以年龄最高为15）、GC标志等信息
        //    - Klass Pointer (4byte)   指向对象的类元数据，JVM通过这个指针确定该对象是哪个类的实例 （若没有开启指针压缩，则大小为8byte）
        //
        // 对象体（实例变量）：
        // 对齐填充：4byte
        ObjectLayout objectLayout = new ObjectLayout();
//        synchronized (objectLayout) {
            System.out.println(
                    ClassLayout.parseInstance(objectLayout).toPrintable()
            );
//        }

//        对象的状态：
//            - 无状态
//            - 偏向锁
//            - 轻量锁
//            - 重量锁
//            - GC标记

//        [Ljava.lang.Integer; object internals:
//        OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
//        0     4                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4                     (object header)                           4f 62 00 f8 (01001111 01100010 00000000 11111000) (-134192561)
//        12     4                     (object header)                           03 00 00 00 (00000011 00000000 00000000 00000000) (3)
//        16    12   java.lang.Integer Integer;.<elements>                       N/A
//        28     4                     (loss due to the next object alignment)
//        Instance size: 32 bytes
//        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

        // 对象头中多了数组的长度
        Integer[] nums = new Integer[3];
        System.out.println(
            ClassLayout.parseInstance(nums).toPrintable()
        );
    }

}
