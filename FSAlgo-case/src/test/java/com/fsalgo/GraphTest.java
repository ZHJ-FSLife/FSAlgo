package com.fsalgo;

import com.fsalgo.core.graph.strongconn.TarjanCutPointAndBridge;
import com.fsalgo.core.graph.strongconn.TarjanStrongConnectivityInspector;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.Graphs;
import com.fsalgo.core.struct.builder.GraphBuilder;
import com.fsalgo.core.struct.specific.DirectedGraph;
import com.fsalgo.core.struct.specific.UndirectedGraph;
import com.fsalgo.core.util.GraphUtil;
import org.junit.Test;

import java.util.Set;

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
    public void cloneGraph() {
        Graph<String> directedGraphgraph = new DirectedGraph<>();
        addNodeToGraph(directedGraphgraph);

        // Graph<String> newGraph = (Graph<String>) graph.clone();
        // System.out.println(newGraph);

        Graph<String> newDirectedGraph = new DirectedGraph<>();
        Graphs.addGraph(newDirectedGraph, directedGraphgraph);
        System.out.println(newDirectedGraph);


        /////////////////////////

        Graph<String> undirectedGraph = new UndirectedGraph<>();
        addNodeToGraph(undirectedGraph);

        Graph<String> newUndirectedGraph = new UndirectedGraph<>();
        Graphs.addGraph(newUndirectedGraph, undirectedGraph);
        System.out.println(newUndirectedGraph);
    }

    @Test
    public void TarjanDemo() {

        Graph<String> graph = new DirectedGraph<>();
        addNodeToGraph(graph);

        TarjanStrongConnectivityInspector<String> tarjan = new TarjanStrongConnectivityInspector<>(graph);
        tarjan.searchGraph();

        System.out.println(tarjan.getCutNodeRst());
        System.out.println(tarjan.getConnEdgeRst());
        System.out.println(tarjan.getConnGraphRst());

        System.out.println(GraphUtil.toMermaid(graph));
    }

    @Test
    public void TarjanCutPointAndBridgeDemo() {
        Graph<String> graph = new UndirectedGraph<>();
        addNodeToGraph(graph);

        TarjanCutPointAndBridge<String> tarjan = new TarjanCutPointAndBridge<>(graph);
        tarjan.search(n1);
        System.out.println(tarjan.getCutPoints());
        System.out.println(tarjan.getBridges());

        System.out.println(GraphUtil.toMermaid(graph));

    }

    @Test
    public void DirectedGraphDemo() {

        Graph<String> graph = GraphBuilder.<String>directed().build();
        addNodeToGraph(graph);

        System.out.println(graph);
        Set<Edge<String>> outgoingEdges = graph.outgoingEdges(n1);
        System.out.println(outgoingEdges);

        graph.removeNode(n1);

        graph.removeEdge(n2, n3);

        System.out.println(graph);
        System.out.println(graph.getGraphType());

        System.out.println(GraphUtil.toMermaid(graph));

    }

    @Test
    public void UndirectedGraphDemo() {
        Graph<String> graph = GraphBuilder.<String>undirected().build();
        graph.addEdge(new Edge<>(n1, n5));
        addNodeToGraph(graph);

        System.out.println(graph);
        Set<Edge<String>> outgoingEdges = graph.outgoingEdges(n5);
        System.out.println(outgoingEdges);
        System.out.println(graph.getGraphType());

    }

    @Test
    public void DirectedAcyclicGraphDemo() {
        Graph<String> graph = GraphBuilder.<String>directed().allowsCycles(true).build();
        graph.addEdge(new Edge<>(n1, n2));
        graph.addEdge(new Edge<>(n1, n6));
        graph.addEdge(new Edge<>(n2, n3));
        graph.addEdge(new Edge<>(n3, n4));
        graph.addEdge(new Edge<>(n4, n5));
        // graph.addEdge(new Edge<>(n5, n2));
        // graph.addEdge(new Edge<>(n5, n1));
        graph.addEdge(new Edge<>(n6, n7));
        graph.addEdge(new Edge<>(n6, n8));
        graph.addEdge(new Edge<>(n8, n9));
        // graph.addEdge(new Edge<>(n9, n6));

        System.out.println(GraphUtil.toMermaid(graph));
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
        GraphBuilder.<String>mixed().weighted(true).build();
    }
}
