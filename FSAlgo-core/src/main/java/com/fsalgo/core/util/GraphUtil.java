package com.fsalgo.core.util;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;

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
        for (Edge<N> edge : graph.edges()) {
            N source = edge.getSource();
            graph.outgoingNodes(source);
            StringJoiner tempSJ = new StringJoiner(" & ");
            for (N node : graph.outgoingNodes(source)) {
                tempSJ.add(node.toString());
            }
            sj.add(source.toString() + "-->" + tempSJ);
        }
        sj.add("```");
        return sj.toString();
    }
}
