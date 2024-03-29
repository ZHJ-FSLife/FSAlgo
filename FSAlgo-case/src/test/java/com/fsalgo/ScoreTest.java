package com.fsalgo;

import com.fsalgo.core.graph.scoring.PageRank;
import com.fsalgo.core.interfaces.score.NodeScoringAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.builder.GraphBuilder;
import com.fsalgo.core.struct.specific.DirectedGraph;
import com.fsalgo.utils.FileUtils;
import com.fsalgo.utils.GraphUtil;
import org.junit.Test;

/**
 * @Author: root
 * @Date: 2023/4/8 14:36
 * @Description:
 */
public class ScoreTest {

    @Test
    public void PageRankDemo() {
        Graph<String> graph = GraphBuilder.<String>directed().weighted(true).build();

        String A = "A";
        String B = "B";
        String C = "C";
        String D = "D";
        String S = "S";
        String T = "T";
        graph.addEdge(new Edge<>(A, B, 6));
        graph.addEdge(new Edge<>(A, C, 3));
        graph.addEdge(new Edge<>(A, S, 7));
        graph.addEdge(new Edge<>(B, C, 4));
        graph.addEdge(new Edge<>(B, D, 2));
        graph.addEdge(new Edge<>(B, T, 5));
        graph.addEdge(new Edge<>(C, D, 3));
        graph.addEdge(new Edge<>(C, S, 8));
        graph.addEdge(new Edge<>(D, T, 2));

        NodeScoringAlgorithm<String, Double> pageRank = new PageRank<>(graph);
        System.out.println(pageRank.getScores());
        System.out.println(pageRank.getNodeScore(D));

        FileUtils.toMdFile(GraphUtil.toMermaid(graph), "PageRankDemo");
    }
}
