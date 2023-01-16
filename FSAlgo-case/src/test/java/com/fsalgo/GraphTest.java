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
    public void KruskalDemo() {
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
    public void FibonacciHeapDemo() {
        int[] nums = {3, 1, 4, 5, 0, 2, 9, 7, 8, 6};
        FibonacciHeap<Integer> heap = new FibonacciHeap<Integer>() {
            // 重写该方法，小顶堆换成大顶堆
            public boolean compareTo(Integer o1, Integer o2) {
                return o1.compareTo(o2) > 0;
            }
        };

        for (int num : nums) {
            heap.add(num);
        }

        int i = 0;
        while (!heap.isEmpty()) {
            if (i >= 5 && i < 10) {
                heap.add(nums[i]);
            }
            i++;
            System.out.print(heap.remove() + ", ");
        }
    }

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
        Graph<String> graph = new UndirectedGraph<>();
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

        ShortestPathAlgorithm<String> dijkstra = new DijkstraShortestPath<String>(graph) {
        };
        dijkstra.getPath(n0, n4);
    }

    @Test
    public void graphTypeDemo() {
        new DefaultGraphType.Builder().directed().weighted(true).build();
        GraphTypeBuilder.<String>mixed().weighted(true).buildGraph();
    }
}
