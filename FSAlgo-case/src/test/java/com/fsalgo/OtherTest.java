package com.fsalgo;

import com.fsalgo.core.util.BitUtil;
import org.junit.Test;

/**
 * @Author: root
 * @Date: 2023/12/21 10:15
 * @Description:
 */
public class OtherTest {

    @Test
    public void lowBitDemo() {
        System.out.println(BitUtil.lowBit(234));
        System.out.println(BitUtil.lowBitAll(234));
    }

    @Test
    public void brianKernighanDemo() {
        System.out.println(BitUtil.brianKernighan(234));
    }

    @Test
    public void binaryMultiplicationDemo() {
        System.out.println(BitUtil.binaryMultiplication(1234, -1234));
    }
}
