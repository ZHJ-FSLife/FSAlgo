package com.fsalgo;

import com.fsalgo.core.clustering.DBSCAN;
import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.tree.vectorspace.KDTreeBack;
import com.fsalgo.core.tree.vectorspace.KDTree;
import com.fsalgo.core.tree.vectorspace.SpacePoint;
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
        add(new double[]{1, 3});
        add(new double[]{1, 8});
        add(new double[]{2, 2});
        add(new double[]{2, 10});
        add(new double[]{3, 6});
        add(new double[]{4, 1});
        add(new double[]{5, 4});
        add(new double[]{6, 8});
        add(new double[]{7, 4});
        add(new double[]{7, 7});
        add(new double[]{8, 2});
        add(new double[]{8, 5});
        add(new double[]{9, 9});
    }};

    @Test
    public void KDTreeDemo() {
        KDTreeBack kdTree = new KDTreeBack(points);

        double[] queryPoint = new double[]{8, 4};
        double[] nearestPoint = kdTree.nearest(queryPoint);
        List<double[]> result = kdTree.range(queryPoint, 4);

        System.out.println("Query point: " + Arrays.toString(queryPoint));
        System.out.println("Nearest point: " + Arrays.toString(nearestPoint));
        for (double[] coord : result) {
            System.out.print(Arrays.toString(coord));
        }
    }

    @Test
    public void KDTreeDemo2() {
        List<SpacePoint<String>> data = new ArrayList<>();
        int i = 0;
        for (double[] list : points) {
            data.add(new SpacePoint.SpacePointImpl<>("P" + (i++), list));
        }
        data.add(new SpacePoint.SpacePointImpl<>("B", new double[]{8, 4}));
        KDTree<String> kDimensionalTree = new KDTree<>(data);

        SpacePoint<String> queryPoint = new SpacePoint.SpacePointImpl<>("B", new double[]{8, 4});
        SpacePoint<String> nearestPoint = kDimensionalTree.nearest(queryPoint);
        List<SpacePoint<String>> rangPoint = kDimensionalTree.range(queryPoint, 4);
        System.out.println(nearestPoint);
        for (SpacePoint<String> temp : rangPoint) {
            System.out.print(Arrays.toString(temp.getCoord()) + ":" + temp.getDistance() + ", ");
        }
    }

    @Test
    public void dbscanDemo() {
        List<SpacePoint<String>> data = new ArrayList<>();
        int i = 0;
        for (double[] list : points) {
            data.add(new SpacePoint.SpacePointImpl<>("P" + (i++), list));
        }

        ClusteringAlgorithm<String> clustering = new DBSCAN<>(3, 3);
        List<List<SpacePoint<String>>> result = clustering.cluster(data);
        for (List<SpacePoint<String>> list : result) {
            for (SpacePoint<String> temp : list) {
                System.out.print(temp.getPoint() + ", ");
            }
            System.out.println();
        }
    }
}
