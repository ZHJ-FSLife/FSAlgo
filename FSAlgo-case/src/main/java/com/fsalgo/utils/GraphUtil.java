package com.fsalgo.utils;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @Author: root
 * @Date: 2023/11/28 10:11
 * @Description:
 */
public class GraphUtil {

    /**
     * 生成mermaid图（没啥用）
     *
     * @param graph
     * @param <N>
     * @return
     */
    public static <N> String toMermaid(Graph<N> graph) {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("```mermaid");
        sj.add("graph LR");
        for (N node : graph.nodes()) {
            sj.add(node.toString() + "((" + node.toString() + "))");
        }
        int edgeNum = 0;
        for (N node : graph.nodes()) {
            Set<Edge<N>> edges = graph.outgoingEdges(node);
            if (edges.isEmpty()) {
                continue;
            }
            String color = generateRandomHexColor();
            sj.add("style " + node.toString() + " fill: " + color);
            for (Edge<N> edge : edges) {
                if (edge.getWeight() == 0) {
                    sj.add(node.toString() + "-->" + edge.getTarget().toString());
                    sj.add("linkStyle " + (edgeNum++) + " stroke: " + color);
                    continue;
                }
                sj.add(node.toString() + "-->" + "|" + edge.getWeight() + "| " + edge.getTarget().toString());
                sj.add("linkStyle " + (edgeNum++) + " stroke: " + color);
            }
        }
        sj.add("```");
        return sj.toString();
    }

    public static String generateRandomHexColor() {
        SecureRandom random = new SecureRandom();

        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        return String.format("#%02X%02X%02X", red, green, blue);
    }
}
