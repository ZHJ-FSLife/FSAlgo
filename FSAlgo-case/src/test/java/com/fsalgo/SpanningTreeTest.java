package com.fsalgo;

import com.fsalgo.core.graph.spanningTree.KruskalMinimumSpanningTree;
import com.fsalgo.core.graph.spanningTree.PrimMinimumSpanningTree;
import com.fsalgo.core.interfaces.SpanningTreeAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.builder.GraphBuilder;
import com.fsalgo.core.struct.specific.DirectedGraph;
import org.junit.Test;

/**
 * @Author: root
 * @Date: 2023/1/17 9:03
 * @Description:
 */
public class SpanningTreeTest {

    @Test
    public void KruskalDemo() {
        // 有向图和无向图都适用于克鲁斯卡尔算法
        Graph<String> graph = new DirectedGraph<>();

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


        SpanningTreeAlgorithm<String> kruskal = new KruskalMinimumSpanningTree<>(graph);
        SpanningTreeAlgorithm.SpanningTree<String> result = kruskal.getSpanningTree();
        System.out.println(result.getEdges());
        System.out.println(result.getWeight());
    }

    @Test
    public void PrimDemo() {
        // 普里姆算法只适用于无向图
        Graph<String> graph = GraphBuilder.<String>undirected().build();

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

        SpanningTreeAlgorithm<String> prim = new PrimMinimumSpanningTree<>(graph);
        SpanningTreeAlgorithm.SpanningTree<String> result = prim.getSpanningTree();
        System.out.println(result.getEdges());
        System.out.println(result.getWeight());

    }
}
