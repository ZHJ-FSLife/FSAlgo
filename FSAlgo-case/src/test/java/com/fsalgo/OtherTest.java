package com.fsalgo;

import com.fsalgo.core.other.LeastFrequentlyUsedCache;
import com.fsalgo.core.other.LeastRecentlyUsedCache;
import com.fsalgo.core.other.util.BitUtil;
import org.junit.Test;

/**
 * @Author: root
 * @Date: 2023/12/21 10:15
 * @Description:
 */
public class OtherTest {

    @Test
    public void lfuDemo() {
        LeastFrequentlyUsedCache<String, Integer> lfu = new LeastFrequentlyUsedCache<>(3);

        lfu.put("zhangsan", 18);
        lfu.put("lisi", 19);
        lfu.put("wangwu", 20);
        lfu.get("zhangsan");
        lfu.get("wangwu");
        lfu.put("laoliu", 11);
        System.out.println(lfu);
    }

    @Test
    public void lruDemo() {
        LeastRecentlyUsedCache<String, Integer> lru = new LeastRecentlyUsedCache<>(3);

        lru.put("zhangsan", 18);
        lru.put("lisi", 19);
        lru.put("wangwu", 20);
        lru.get("zhangsan");
        lru.get("wangwu");
        lru.put("laoliu", 11);

    }

    @Test
    public void lowBitDemo() {
        int num = 233;

        System.out.println(BitUtil.lowBit(num));
        System.out.println(BitUtil.lowBitAll(num));

        System.out.println(BitUtil.brianKernighan(num));

        System.out.println(BitUtil.binaryMultiplication(num, -num));
    }

}
