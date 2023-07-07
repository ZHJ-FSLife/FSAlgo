package com.fsalgo;

import com.fsalgo.core.graph.shortestpath.DijkstraShortestPath;
import com.fsalgo.core.interfaces.ShortestPathAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.builder.GraphBuilder;
import org.junit.Test;

/**
 * @Author: root
 * @Date: 2023/1/17 9:02
 * @Description:
 */
public class ShortestPathTest {

    @Test
    public void DijkstraDemo() {
        String n0 = "n0";
        String n1 = "n1";
        String n2 = "n2";
        String n3 = "n3";
        String n4 = "n4";
        String n5 = "n5";
        String n6 = "n6";
        String n7 = "n7";
        String n8 = "n8";
        Graph<String> graph = GraphBuilder.<String>undirected().build();
        assert graph != null;
        graph.addEdge(new Edge<>(n0, n1, 4));
        graph.addEdge(new Edge<>(n0, n7, 8));
        graph.addEdge(new Edge<>(n1, n7, 11));
        graph.addEdge(new Edge<>(n1, n2, 8));
        graph.addEdge(new Edge<>(n7, n8, 7));
        graph.addEdge(new Edge<>(n7, n6, 1));
        graph.addEdge(new Edge<>(n2, n3, 7));
        graph.addEdge(new Edge<>(n2, n8, 2));
        graph.addEdge(new Edge<>(n2, n5, 4));
        graph.addEdge(new Edge<>(n6, n8, 6));
        graph.addEdge(new Edge<>(n6, n5, 2));
        graph.addEdge(new Edge<>(n3, n5, 14));
        graph.addEdge(new Edge<>(n3, n4, 9));
        graph.addEdge(new Edge<>(n5, n4, 10));

        System.out.println(graph);

        ShortestPathAlgorithm<String> dijkstra = new DijkstraShortestPath<String>(graph) {};
        ShortestPathAlgorithm.GraphPath<String> paths = dijkstra.getPath(n0, n4);
        System.out.println(paths.getNodes());
        System.out.println(paths.getEdges());
    }
}
