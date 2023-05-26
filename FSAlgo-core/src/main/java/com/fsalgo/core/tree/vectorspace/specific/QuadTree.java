package com.fsalgo.core.tree.vectorspace.specific;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;
import com.fsalgo.core.tree.vectorspace.AbstractQuadOcTree;
import com.fsalgo.core.tree.vectorspace.SpacePoint;
import com.fsalgo.core.util.VectorUtil;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/19 13:17
 * @Description: 四叉树
 */
public class QuadTree<T extends Comparable<T>> extends AbstractQuadOcTree<T> {

    public QuadTree(List<SpacePoint<T>> points) {
        this(points, Distance.EUCLIDEAN);
    }

    public QuadTree(List<SpacePoint<T>> points, DistanceMetric distanceMetric) {
        super(points, distanceMetric);
    }

    /**
     * 公式计算坐标属于第几象限
     * quadrant = ((x / |x|) * 0.5 + 0.5) * (-y / |y|) - ((y / |y|) * 0.5 + 0.5) + 3
     *
     * @param center 原点坐标
     * @param target 目标点坐标
     * @return 象限（数组索引）
     */
    @Override
    protected int calcCoordinateIndex(double[] center, double[] target) {
        VectorUtil.checkDims(center, dimension);
        VectorUtil.checkDims(center, target);

        // +0.01是为了避免x或y为0时，将其归到最近的象限上
        double x = target[0] - center[0];
        double y = target[1] - center[1];
        x += x == 0 ? 0.01 : 0;
        y += y == 0 ? 0.01 : 0;
        int quadrant = (int) (((x / Math.abs(x)) * 0.5 + 0.5) * (-y / Math.abs(y)) - ((y / Math.abs(y)) * 0.5 + 0.5) + 3);
        return quadrant - 1;
    }

    @Override
    protected int getDimension() {
        return 2;
    }

}
