package com.fsalgo.core.clustering;

import com.fsalgo.core.constant.BaseConstant;
import com.fsalgo.core.graph.spanningtree.PrimMinimumSpanningTree;
import com.fsalgo.core.interfaces.SpanningTreeAlgorithm;
import com.fsalgo.core.interfaces.clusters.Clustering;
import com.fsalgo.core.interfaces.clusters.GraphNodeClusteringAlgo;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.tree.UnionFind;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/6/30 15:39
 * @Description:
 */
public class KSpanningTreeClustering<N extends Comparable<N>> implements GraphNodeClusteringAlgo<N>, Serializable {

    private static final long serialVersionUID = BaseConstant.SERIAL_VERSION_UID;

    private Graph<N> graph;

    private int k;

    public KSpanningTreeClustering(Graph<N> graph, int k) {
        this.graph = graph;
        if (k < 1 || k > graph.nodeSize()) {
            throw new IllegalArgumentException("Illegal number of clusters");
        }
        this.k = k;
    }


    @Override
    public Clustering<N> getClustering() {

        // 获取最小生成树
        SpanningTreeAlgorithm<N> primMst = new PrimMinimumSpanningTree<>(graph);

        UnionFind<N> unionFind = new UnionFind<>(graph.nodes());
        List<Edge<N>> edges = new ArrayList<>(primMst.getSpanningTree().getEdges());
        edges.sort(Comparator.comparingDouble(Edge::getWeight));

        for (Edge<N> edge : edges) {
            if (unionFind.numberOfSets() == k) {
                break;
            }
            N source = edge.getSource();
            N target = edge.getTarget();
            if (unionFind.check(source, target)) {
                continue;
            }
            unionFind.union(source, target);
        }

        Map<N, Set<N>> clusterMap = new LinkedHashMap<>();
        for (N node : graph.nodes()) {
            N curr = unionFind.findRoot(node);
            Set<N> cluster = clusterMap.get(curr);
            if (cluster == null) {
                cluster = new LinkedHashSet<>();
                clusterMap.put(curr, cluster);
            }
            cluster.add(node);
        }

        return null;
    }

    @Override
    public String getName() {
        return "K-SpanningTree Clustering";
    }
}
