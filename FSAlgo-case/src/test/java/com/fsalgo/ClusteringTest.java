package com.fsalgo;

import com.fsalgo.core.tree.KDimensionalTree;
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

    @Test
    public void KDTreeDemo() {
        List<double[]> points = new ArrayList<>();
        points.add(new double[] {2, 3});
        points.add(new double[] {5, 4});
        points.add(new double[] {9, 6});
        points.add(new double[] {4, 7});
        points.add(new double[] {8, 1});
        KDimensionalTree kdTree = new KDimensionalTree(points);

        double[] queryPoint = new double[] {5, 5};
        double[] nearestPoint = kdTree.nearest(queryPoint);

        System.out.println("Query point: " + Arrays.toString(queryPoint));
        System.out.println("Nearest point: " + Arrays.toString(nearestPoint));
    }
}
