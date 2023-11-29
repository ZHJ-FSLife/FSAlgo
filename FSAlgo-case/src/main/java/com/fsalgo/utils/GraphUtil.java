package com.fsalgo.utils;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;

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
     * @param graph
     * @return
     * @param <N>
     */
    public static <N> String toMermaid(Graph<N> graph) {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("```mermaid");
        sj.add("graph LR");
        for (N node : graph.nodes()) {
            sj.add(node.toString() + "((" + node.toString() + "))");
        }
        for (N node : graph.nodes()) {
            Set<Edge<N>> edges = graph.outgoingEdges(node);
            if (edges.isEmpty()) {
                continue;
            }
            for (Edge<N> edge : edges) {
                sj.add(node.toString() + "-->" + "|" + edge.getWeight() + "| " + edge.getTarget().toString());
            }
        }
        sj.add("```");
        return sj.toString();
    }
}
