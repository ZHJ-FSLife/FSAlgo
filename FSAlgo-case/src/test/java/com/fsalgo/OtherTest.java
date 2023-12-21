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
        int num = 233;

        System.out.println(BitUtil.lowBit(num));
        System.out.println(BitUtil.lowBitAll(num));

        System.out.println(BitUtil.brianKernighan(num));

        System.out.println(BitUtil.binaryMultiplication(num, -num));
    }

}
