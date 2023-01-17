package com.fsalgo;

import com.fsalgo.core.graph.*;
import com.fsalgo.core.tree.*;
import com.fsalgo.core.interfaces.*;
import com.fsalgo.core.struct.*;
import com.fsalgo.core.struct.specific.*;

import org.junit.Test;

/**
 * @Author: root
 * @Date: 2023/1/16 17:30
 * @Description:
 */
public class GraphTest {

    String n1 = "n1";
    String n2 = "n2";
    String n3 = "n3";
    String n4 = "n4";
    String n5 = "n5";
    String n6 = "n6";
    String n7 = "n7";
    String n8 = "n8";
    String n9 = "n9";

    @Test
    public void tarjanDemo() {

        Graph<String> graph = new DirectedGraph<>();
        addNodeToGraph(graph);

        TarjanStrongConnectivityInspector<String> tarjan = new TarjanStrongConnectivityInspector<>(graph);
        tarjan.searchGraph();

        System.out.println(tarjan.getCutNodeRst());
        System.out.println(tarjan.getConnEdgeRst());
        System.out.println(tarjan.getConnGraphRst());
    }

    @Test
    public void undirectedGraphDemo() {
        Graph<String> graph = new UndirectedGraph<>();
        addNodeToGraph(graph);

        System.out.println(graph);
    }

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
    public void graphTypeDemo() {
        new DefaultGraphType.Builder().directed().weighted(true).build();
        GraphTypeBuilder.<String>mixed().weighted(true).buildGraph();
    }
}
