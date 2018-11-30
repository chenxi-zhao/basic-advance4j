package com.chenxi.javacore.bitop;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 流操作工具类
 *
 * @author 崔素强
 */
public class StreamTool {

    /**
     * InputStream 转为 byte
     *
     * @param inStream
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] inputStream2Byte(InputStream inStream)
            throws Exception {
        // ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        // byte[] buffer = new byte[1024];
        // int len = -1;
        // while ((len = inStream.read(buffer)) != -1) {
        // outSteam.write(buffer, 0, len);
        // }
        // outSteam.close();
        // inStream.close();
        // return outSteam.toByteArray();
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        return b;
    }

    /**
     * byte 转为 InputStream
     *
     * @param b 字节数组
     * @return InputStream
     * @throws Exception
     */
    public static InputStream byte2InputStream(byte[] b) throws Exception {
        return new ByteArrayInputStream(b);
    }

    /**
     * 短整型与字节的转换
     *
     * @param number 短整型
     * @return 两位的字节数组
     */
    public static byte[] shortToByte(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
            temp = temp >> 8; // 向右移8位
        }
        return b;
    }

    /**
     * 字节的转换与短整型
     *
     * @param b 两位的字节数组
     * @return 短整型
     */
    public static short byteToShort(byte[] b) {
        short s = 0;
        short s0 = (short) (b[0] & 0xff);// 最低位
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    /**
     * 整型与字节数组的转换
     *
     * @param i
     * @return 四位的字节数组
     */
    public static byte[] intToByte(int i) {
        byte[] bt = new byte[4];
        bt[0] = (byte) (0xff & i);
        bt[1] = (byte) ((0xff00 & i) >> 8);
        bt[2] = (byte) ((0xff0000 & i) >> 16);
        bt[3] = (byte) ((0xff000000 & i) >> 24);
        return bt;
    }

    /**
     * 字节数组和整型的转换
     *
     * @param bytes 字节数组
     * @return 整型
     */
    public static int bytesToInt(byte[] bytes) {
        int num = bytes[0] & 0xFF;
        num |= ((bytes[1] << 8) & 0xFF00);
        num |= ((bytes[2] << 16) & 0xFF0000);
        num |= ((bytes[3] << 24) & 0xFF000000);
        return num;
    }

    /**
     * 字节数组和长整型的转换
     *
     * @param number
     * @return 长整型
     */
    public static byte[] longToByte(long number) {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Long(temp & 0xff).byteValue();
            // 将最低位保存在最低位
            temp = temp >> 8;
            // 向右移8位
        }
        return b;
    }

    /**
     * 字节数组和长整型的转换
     *
     * @param b 字节数组
     * @return 长整型
     */
    public static long byteToLong(byte[] b) {
        long s = 0;
        long s0 = b[0] & 0xff;// 最低位
        long s1 = b[1] & 0xff;
        long s2 = b[2] & 0xff;
        long s3 = b[3] & 0xff;
        long s4 = b[4] & 0xff;// 最低位
        long s5 = b[5] & 0xff;
        long s6 = b[6] & 0xff;
        long s7 = b[7] & 0xff; // s0不变
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }

    /**
     * Byte转Bit
     */
    public static String byteToBit(byte b) {
        return "" + (byte) ((b >> 7) & 0x1) +
                (byte) ((b >> 6) & 0x1) +
                (byte) ((b >> 5) & 0x1) +
                (byte) ((b >> 4) & 0x1) +
                (byte) ((b >> 3) & 0x1) +
                (byte) ((b >> 2) & 0x1) +
                (byte) ((b >> 1) & 0x1) +
                (byte) ((b) & 0x1);
    }

    /**
     * Bit转Byte
     */
    public static byte BitToByte(String byteStr) {
        int re, len;
        if (null == byteStr) {
            return 0;
        }
        len = byteStr.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {
            // 8 bit处理
            if (byteStr.charAt(0) == '0') {
                // 正数
                re = Integer.parseInt(byteStr, 2);
            } else {
                //负数
                re = Integer.parseInt(byteStr, 2) - 256;
            }
        } else {
            //4 bit处理
            re = Integer.parseInt(byteStr, 2);
        }
        return (byte) re;
    }
}