package com.fsalgo.core.util;

import java.util.*;

/**
 * @Author: root
 * @Date: 2023/12/21 10:11
 * @Description:
 */
public class BitUtil {

    private BitUtil() {}

    /**
     * 二进制的最低位
     */
    public static List<Integer> lowBitAll(int num) {
        List<Integer> result = new LinkedList<>();
        while (num > 0) {
            int temp = lowBit(num);
            num -= temp;
            result.add(temp);
        }
        return result;
    }

    /**
     * 二进制的最低位
     */
    public static int lowBit(int num) {
        return num & -num;
    }

    /**
     * Brian Kernighan 算法
     * 对于任意整数 x，令 x = x & (x − 1)，该运算将 xx 的二进制表示的最后一个 11 变成 00。
     * 因此，对 xx 重复该操作，直到 xx 变成 00，则操作次数即为 xx 的「一比特数」。
     */
    public static int brianKernighan(int num) {
        int count = 0;
        while (num > 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }

    /**
     * 二进制位运算计算乘法
     * 例：11 * 10
     * 10 = 1010
     * 11 << bitMap.get(1000) = 11 << 3 = 88
     * 11 << bitMap.get(0010) = 11 << 1 = 22
     * 88 + 22 = 110
     */
    public static int binaryMultiplication(int x, int y) {
        int sum = 0;
        // 如果y为负数，先取正，计算后结果再取负
        boolean neg = y < 0;
        if (neg) {
            y = -y;
        }
        // 定义数值在二进制中的位置，用于后面计算决定应该左移多少位
        Map<Integer, Integer> bitMap = new HashMap<>();
        for (int i = 0; i < 31; i++) {
            bitMap.put(1 << i, i);
        }
        // 重复取出y在二进制中的最后一个1，x左移
        while (y > 0) {
            int temp = bitMap.get(lowBit(y));
            sum += x << temp;
            y -= lowBit(y);
        }
        return neg ? -sum : sum;
    }
}
