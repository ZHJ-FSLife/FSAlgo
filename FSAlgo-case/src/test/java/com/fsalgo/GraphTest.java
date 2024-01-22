package com.fsalgo;

import com.fsalgo.core.graph.strongconn.TarjanCutPointAndBridge;
import com.fsalgo.core.graph.strongconn.TarjanStrongConnectivityInspector;
import com.fsalgo.core.interfaces.functional.GraphNodeMapper;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.Graphs;
import com.fsalgo.core.struct.builder.GraphBuilder;
import com.fsalgo.core.iterator.graph.BreadthFirstIterator;
import com.fsalgo.core.iterator.graph.DepthFirstIterator;
import com.fsalgo.core.struct.specific.DirectedGraph;
import com.fsalgo.core.struct.specific.UndirectedGraph;
import com.fsalgo.utils.FileUtils;
import com.fsalgo.utils.GraphUtil;
import org.junit.Test;

import java.util.*;

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

        FileUtils.toMdFile(GraphUtil.toMermaid(graph), "TarjanDemo");
    }

    @Test
    public void TarjanCutPointAndBridgeDemo() {
        Graph<String> graph = new UndirectedGraph<>();
        addNodeToGraph(graph);

        TarjanCutPointAndBridge<String> tarjan = new TarjanCutPointAndBridge<>(graph);
        tarjan.search(n1);
        System.out.println(tarjan.getCutPoints());
        System.out.println(tarjan.getBridges());

        FileUtils.toMdFile(GraphUtil.toMermaid(graph), "TarjanCutPointAndBridgeDemo");

    }

    @Test
    public void DirectedGraphDemo() {

        Graph<String> graph = GraphBuilder.<String>directed().build();
        addNodeToGraph(graph);
        graph.addNode("n100");

        FileUtils.toMdFile(GraphUtil.toMermaid(graph), "DirectedGraphDemo");
        System.out.println(graph.edgeSize());

        // System.out.println(graph);
        Set<Edge<String>> outgoingEdges = graph.outgoingEdges(n1);
        System.out.println(outgoingEdges);

        // 广搜迭代器测试
        Iterator<String> bfsIterator = new BreadthFirstIterator<>(graph, n1);
        System.out.print("广搜迭代器测试: ");
        while (bfsIterator.hasNext()) {
            String next = bfsIterator.next();
            System.out.print(next + ", ");
        }
        System.out.println();

        // 深搜迭代器测试
        Iterator<String> dfsIterator = new DepthFirstIterator<>(graph, n1);
        System.out.print("深搜迭代器测试: ");
        while (dfsIterator.hasNext()) {
            String next = dfsIterator.next();
            System.out.print(next + ", ");
        }
        System.out.println();


        graph.removeNode(n1);

        graph.removeEdge(n2, n3);

        System.out.println(graph);
        System.out.println(graph.getGraphType());

        FileUtils.toMdFile(GraphUtil.toMermaid(graph), "DirectedGraphDemo", true);
        System.out.println(graph.edgeSize());

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

        // 广搜迭代器测试
        Iterator<String> bfsIterator = new BreadthFirstIterator<>(graph, n1);
        System.out.print("广搜迭代器测试: ");
        while (bfsIterator.hasNext()) {
            String next = bfsIterator.next();
            System.out.print(next + ", ");
        }
        System.out.println();

        FileUtils.toMdFile(GraphUtil.toMermaid(graph), "UndirectedGraphDemo", true);

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

        FileUtils.toMdFile(GraphUtil.toMermaid(graph), "DirectedAcyclicGraphDemo");
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

    @Test
    public void iteratorDemo() {
        Graph<String> graph = GraphBuilder.<String>directed().allowsCycles(true).build();
        addNodeToGraph(graph);
        Iterator<String> graphIterator = new BreadthFirstIterator<>(graph, n1);
        while (graphIterator.hasNext()) {
            System.out.printf(graphIterator.next() + ", ");;
        }

        GraphNodeMapper<String> graphNodeMapper = Graph::outgoingNodes;
        // Set<String> temp = (Set<String>) graphNodeMapper.outgoingNodes(graph, n1);

        Set<String> flag = new HashSet<>();
        Deque<String> queue = new LinkedList<>();
        queue.add(n1);
        System.out.println();
        while (!queue.isEmpty()) {
            String node = queue.removeFirst();
            System.out.printf(node + ", ");
            flag.add(node);
            for (String next : graphNodeMapper.outgoingNodes(graph, node)) {
                if (flag.contains(next)) {
                    continue;
                }
                queue.addLast(next);
            }
        }

    }
}
