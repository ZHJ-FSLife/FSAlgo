package com.fsalgo;

import com.fsalgo.core.clustering.DBSCAN;
import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.tree.vectorspace.KDTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/5 21:10
 * @Description:
 */
public class ClusteringTest {

    List<double[]> points = new ArrayList<>() {{
        add(new double[] {1, 3});
        add(new double[] {1, 8});
        add(new double[] {2, 2});
        add(new double[] {2, 10});
        add(new double[] {3, 6});
        add(new double[] {4, 1});
        add(new double[] {5, 4});
        add(new double[] {6, 8});
        add(new double[] {7, 4});
        add(new double[] {7, 7});
        add(new double[] {8, 2});
        add(new double[] {8, 5});
        add(new double[] {9, 9});
    }};

    @Test
    public void KDTreeDemo() {
        KDTree kdTree = new KDTree(points);

        double[] queryPoint = new double[] {8, 4};
        double[] nearestPoint = kdTree.nearest(queryPoint);
        List<double[]> result = kdTree.range(queryPoint, 4);

        System.out.println("Query point: " + Arrays.toString(queryPoint));
        System.out.println("Nearest point: " + Arrays.toString(nearestPoint));
        for (double[] coord : result) {
            System.out.print(Arrays.toString(coord) );
        }
    }

    @Test
    public void dbscanDemo() {
        ClusteringAlgorithm clustering = new DBSCAN(3, 3);
        List<List<double[]>> result = clustering.cluster(points);
        for (List<double[]> list : result) {
            for (double[] coord : list) {
                System.out.print(Arrays.toString(coord) + ", ");
            }
            System.out.println();
        }
    }
}
