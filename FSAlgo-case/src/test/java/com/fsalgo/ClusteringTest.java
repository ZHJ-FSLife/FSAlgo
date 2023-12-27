package com.fsalgo;

import com.fsalgo.core.clustering.DBSCAN;
import com.fsalgo.core.clustering.KSpanningTreeClustering;
import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.builder.GraphBuilder;
import com.fsalgo.core.tree.vectorspace.AbstractQuadOcTree;
import com.fsalgo.core.tree.vectorspace.SpacePoint;
import com.fsalgo.core.tree.vectorspace.specific.BallTree;
import com.fsalgo.core.tree.vectorspace.specific.KDTree;
import com.fsalgo.core.tree.vectorspace.specific.OcTree;
import com.fsalgo.core.tree.vectorspace.specific.QuadTree;
import com.fsalgo.utils.FileUtils;
import com.fsalgo.utils.GraphUtil;
import com.fsalgo.utils.TreeUtil;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/3/5 21:10
 * @Description:
 */
public class ClusteringTest {

    List<SpacePoint<String>> data = new ArrayList<>() {{
        add(new SpacePoint.SpacePointImpl<>("P01", new double[]{1, 3}));
        add(new SpacePoint.SpacePointImpl<>("P02", new double[]{1, 8}));
        add(new SpacePoint.SpacePointImpl<>("P03", new double[]{2, 2}));
        add(new SpacePoint.SpacePointImpl<>("P04", new double[]{2, 10}));
        add(new SpacePoint.SpacePointImpl<>("P05", new double[]{3, 6}));
        add(new SpacePoint.SpacePointImpl<>("P06", new double[]{4, 1}));
        add(new SpacePoint.SpacePointImpl<>("P07", new double[]{5, 4}));
        add(new SpacePoint.SpacePointImpl<>("P08", new double[]{6, 8}));
        add(new SpacePoint.SpacePointImpl<>("P09", new double[]{7, 4}));
        add(new SpacePoint.SpacePointImpl<>("P10", new double[]{7, 7}));
        add(new SpacePoint.SpacePointImpl<>("P11", new double[]{8, 2}));
        add(new SpacePoint.SpacePointImpl<>("P12", new double[]{8, 5}));
        add(new SpacePoint.SpacePointImpl<>("P13", new double[]{9, 9}));
    }};

    List<SpacePoint<String>> data2 = new ArrayList<>() {{
        add(new SpacePoint.SpacePointImpl<>("P01", new double[]{1, 3, 5}));
        add(new SpacePoint.SpacePointImpl<>("P02", new double[]{1, 8, 4}));
        add(new SpacePoint.SpacePointImpl<>("P03", new double[]{2, 2, 9}));
        add(new SpacePoint.SpacePointImpl<>("P04", new double[]{2, 10, 6}));
        add(new SpacePoint.SpacePointImpl<>("P05", new double[]{3, 6, 0}));
        add(new SpacePoint.SpacePointImpl<>("P06", new double[]{4, 1, 7}));
        add(new SpacePoint.SpacePointImpl<>("P07", new double[]{5, 4, 1}));
        add(new SpacePoint.SpacePointImpl<>("P08", new double[]{6, 8, 3}));
        add(new SpacePoint.SpacePointImpl<>("P09", new double[]{7, 4, 6}));
        add(new SpacePoint.SpacePointImpl<>("P10", new double[]{7, 7, 8}));
        add(new SpacePoint.SpacePointImpl<>("P11", new double[]{8, 2, 10}));
        add(new SpacePoint.SpacePointImpl<>("P12", new double[]{8, 5, 2}));
        add(new SpacePoint.SpacePointImpl<>("P13", new double[]{9, 9, 6}));
    }};

    @Test
    public void OcTreeDemo() {
        OcTree<String> ocTree = new OcTree<>(data2);
        SpacePoint<String> nearestPoint = ocTree.nearest(new SpacePoint.SpacePointImpl<>("B", new double[]{8, 6, 5}));
        System.out.println(nearestPoint);
        System.out.println(ocTree);

        FileUtils.toMdFile(TreeUtil.toMermaid(ocTree.getRoot(), (AbstractQuadOcTree.Node node) -> {
            List<AbstractQuadOcTree.Node> list = new LinkedList<>();
            if (node.getChild() == null) {
                return list;
            }
            for (AbstractQuadOcTree.Node n : node.getChild()) {
                list.add(n);
            }
            return list;
        }), "OcTreeDemo");
    }

    @Test
    public void QuadTreeDemo() {
        QuadTree<String> quadTree = new QuadTree<>(data);
        SpacePoint<String> nearestPoint = quadTree.nearest(new SpacePoint.SpacePointImpl<>("B", new double[]{6, 7.3}));
        System.out.println(nearestPoint);
        System.out.println("{6, 7.3} -> {6, 8} = " + Distance.EUCLIDEAN.getDistance(new double[]{6, 7.3}, new double[]{6, 8}));
        System.out.println("{6, 7.3} -> {7, 7} = " + Distance.EUCLIDEAN.getDistance(new double[]{6, 7.3}, new double[]{7, 7}));

        FileUtils.toMdFile(TreeUtil.toMermaid(quadTree.getRoot(), (AbstractQuadOcTree.Node node) -> {
            List<AbstractQuadOcTree.Node> list = new LinkedList<>();
            if (node.getChild() == null) {
                return list;
            }
            for (AbstractQuadOcTree.Node n : node.getChild()) {
                list.add(n);
            }
            return list;
        }), "QuadTreeDemo");
    }

    @Test
    public void BallTreeDemo() {
        data.add(new SpacePoint.SpacePointImpl<>("B", new double[]{8, 4}));
        BallTree<String> ballTree = new BallTree<>(data);

        SpacePoint<String> queryPoint = new SpacePoint.SpacePointImpl<>("B", new double[]{8, 4});

        // SpacePoint<String> nearestPoint = ballTree.nearest(queryPoint);
        // System.out.println(nearestPoint);

        List<SpacePoint<String>> rangPoint = ballTree.range(queryPoint, 4);
        for (SpacePoint<String> temp : rangPoint) {
            System.out.println(temp.getPoint() + ": " + Arrays.toString(temp.getCoord()));
        }

        FileUtils.toMdFile(TreeUtil.toMermaid(ballTree.getRoot(), BallTree.Node::getChild), "BallTreeDemo");
    }

    @Test
    public void KDTreeDemo() {
        String node = "B";
        data.add(new SpacePoint.SpacePointImpl<>(node, new double[]{8, 4}));
        KDTree<String> kDimensionalTree = new KDTree<>(data);

        SpacePoint<String> queryPoint = new SpacePoint.SpacePointImpl<>(node, new double[]{8, 4});
        SpacePoint<String> nearestPoint = kDimensionalTree.nearest(queryPoint);
        System.out.println(nearestPoint);

        List<SpacePoint<String>> rangPoint = kDimensionalTree.range(queryPoint, 4);
        for (SpacePoint<String> temp : rangPoint) {
            System.out.println(temp.getPoint() + ": " + Arrays.toString(temp.getCoord()));
        }

        FileUtils.toMdFile(TreeUtil.toMermaid(kDimensionalTree.getRoot(), KDTree.Node::getChild), "KDTreeDemo");
    }

    @Test
    public void dbscanDemo() {
        ClusteringAlgorithm<String> clustering = new DBSCAN<>(3, 2.5, data);
        ClusteringAlgorithm.Clustering<SpacePoint<String>> clusters = clustering.getClustering();
        System.out.println("the number of clusters: " + clusters.getNumberClusters());
        for (Set<SpacePoint<String>> list : clusters.getClusters()) {
            for (SpacePoint<String> temp : list) {
                System.out.print(temp.getPoint() + ", ");
            }
            System.out.println();
        }
    }

    @Test
    public void kSpanningTreeClusteringDemo() {
        Graph<String> graph = GraphBuilder.<String>undirected().build();

        String n1 = "n1";
        String n2 = "n2";
        String n3 = "n3";
        String n4 = "n4";
        String n5 = "n5";
        String n6 = "n6";
        String n7 = "n7";
        String n8 = "n8";
        String n9 = "n9";
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

        ClusteringAlgorithm<String> clustering = new KSpanningTreeClustering(graph, 8);

        ClusteringAlgorithm.Clustering<String> clusters = clustering.getClustering();
        System.out.println("the number of clusters: " + clusters.getNumberClusters());
        for (Set<String> list : clusters.getClusters()) {
            System.out.println(list);
        }

        FileUtils.toMdFile(GraphUtil.toMermaid(graph), "kSpanningTreeClusteringDemo");
    }
}
