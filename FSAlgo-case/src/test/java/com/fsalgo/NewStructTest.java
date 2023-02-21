package com.fsalgo;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.builder.GraphBuilder;
import com.fsalgo.core.struct.Graph;
import org.junit.Test;

import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 3:47
 * @Description:
 */
public class NewStructTest {

    String n1 = "n1";
    String n2 = "n2";
    String n3 = "n3";
    String n4 = "n4";
    String n5 = "n5";
    String n6 = "n6";
    String n7 = "n7";
    String n8 = "n8";
    String n9 = "n9";

    private void addNodeToGraph(Graph<String> graph) {
        graph.addEdge(new Edge<>(n1, n2));
        graph.addEdge(new Edge<>(n1, n6));
        graph.addEdge(new Edge<>(n2, n3));
        graph.addEdge(new Edge<>(n3, n4));
        graph.addEdge(new Edge<>(n4, n5));
        graph.addEdge(new Edge<>(n5, n2));
        graph.addEdge(new Edge<>(n5, n1));
        graph.addEdge(new Edge<>(n6, n7));
        graph.addEdge(new Edge<>(n6, n8));
        graph.addEdge(new Edge<>(n8, n9));
        graph.addEdge(new Edge<>(n9, n6));
    }

    @Test
    public void DirectedGraphTest() {

        Graph<String> graph = GraphBuilder.<String>directed().build();
        addNodeToGraph(graph);

        System.out.println(graph);
        Set<Edge<String>> outgoingEdges = graph.outgoingEdges(n1);
        System.out.println(outgoingEdges);

    }

    @Test
    public void UndirectedGraphTest() {
        Graph<String> graph = GraphBuilder.<String>undirected().build();
        graph.addEdge(new Edge<>(n1, n5));
        addNodeToGraph(graph);

        System.out.println(graph);
        Set<Edge<String>> outgoingEdges = graph.outgoingEdges(n5);
        System.out.println(outgoingEdges);
        System.out.println((new Edge<>(n1, n2)).hashCode());
        System.out.println((new Edge<>(n1, n2)).hashCode());
    }


}
