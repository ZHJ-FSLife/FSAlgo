package com.fsalgo;

import com.fsalgo.core.clustering.DBSCAN;
import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.tree.vectorspace.BallTree;
import com.fsalgo.core.tree.vectorspace.KDTree;
import com.fsalgo.core.tree.vectorspace.QuadTree;
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

    @Test
    public void QuadTreeDemo() {
        QuadTree<String> quadTree = new QuadTree<>(data);
        SpacePoint<String> nearestPoint = quadTree.nearest(new SpacePoint.SpacePointImpl<>("B", new double[]{8, 5.2}));
        System.out.println(nearestPoint);
    }

    @Test
    public void BallTreeDemo() {
        BallTree<String> ballTree = new BallTree<>(data);
        System.out.println(ballTree);
    }

    @Test
    public void KDTreeDemo() {
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
