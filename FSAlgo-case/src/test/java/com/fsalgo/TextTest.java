package com.fsalgo;

import com.fsalgo.core.math.geometrical.Distance;
import com.hankcs.hanlp.HanLP;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: root
 * @Date: 2023/3/30 9:10
 * @Description:
 */
public class TextTest {

    private final static String FILTER_RULE = "`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'\"。，、？ ";

    private Map<String, Long> segment(String words) {
        // Map<String, Long> map = HanLP.segment(words1)
        //         .stream()
        //         .map(n -> n.word)
        //         .filter(s -> !SEGMENT_RULE.contains(s))
        //         .collect(Collectors.groupingBy(key -> key, Collectors.counting()));
        // System.out.println(map);

        return HanLP.segment(words)
                .stream()
                .map(n -> n.word)
                .filter(s -> !FILTER_RULE.contains(s))
                // .filter(s -> !"243".contains(s))
                // .collect(Collectors.toList());
                .collect(Collectors.groupingBy(key -> key, Collectors.counting()));
    }



    @Test
    public void segment() {
        String word = "云南南天信息设备有限公司\"（客户编号：0101010101）位于中国（云南）自由贸易试验区昆明片区经开区洛羊街道办事处拓翔路243号";
        Map<String, Long> map1 = segment(word);
        System.out.println(map1);
    }

    @Test
    public void textCompare() {
        String words1 = "无线路由器怎么无线上网";
        String words2 = "无线上网卡和无线路由器怎么用";

        Map<String, Long> map1 = segment(words1);
        Map<String, Long> map2 = segment(words2);
        System.out.println(map1);
        System.out.println(map2);
        // 合并单词集合
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(map1.keySet());
        wordSet.addAll(map2.keySet());

        // 计算两份文本的单词向量
        double[] vector1 = new double[wordSet.size()];
        double[] vector2 = new double[wordSet.size()];
        int index = 0;
        for (String word : wordSet) {
            vector1[index] = map1.containsKey(word) ? map1.get(word) : 0;
            vector2[index] = map2.containsKey(word) ? map2.get(word) : 0;
            index++;
        }

        double similarity = Distance.COSINE.getDistance(vector1, vector2);

        // 输出相似度
        System.out.println("相似度: " + String.format("%.2f", similarity) + "%");
    }

    @Test
    public void hamming() {
        String paper1 = "看图猜一电影名";
        String paper2 = "看图猜电影";

        SimHash simHash1 = new SimHash(paper1, 64);
        SimHash simHash2 = new SimHash(paper2, 64);
        System.out.println(simHash1.strSimHash);
        System.out.println(simHash2.strSimHash);
        double[] vector1 = new double[simHash1.strSimHash.length()];
        double[] vector2 = new double[simHash1.strSimHash.length()];
        for (int i = 0; i < simHash1.strSimHash.length(); i++) {
            vector1[i] = simHash1.strSimHash.charAt(i);
            vector2[i] = simHash2.strSimHash.charAt(i);
        }

        System.out.println(Distance.HAMMING.getDistance(vector1, vector2));
    }

}
