package com.fsalgo.core.tree.vectorspace.impl;


import com.fsalgo.core.tree.vectorspace.AbstractQuadOcTree;
import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/19 13:17
 * @Description: 八叉树
 */
public class OcTree<T extends Comparable<T>> extends AbstractQuadOcTree<T> {

    public OcTree() {
        super();
        Node<T> node = new Node<T>(null);
        System.out.println(node);
    }

    @Override
    protected int getDimension() {
        return 3;
    }

    @Override
    protected int getChildNums() {
        return 8;
    }


    @Override
    public SpacePoint<T> nearest(SpacePoint<T> point) {
        return null;
    }

    @Override
    public List<SpacePoint<T>> range(SpacePoint<T> point, double radius) {
        return null;
    }

    /**
     * 公式计算指定坐标在三维坐标系中的第几八分体
     *
     * @param center 原点坐标
     * @param target 目标点坐标
     * @return 八分体索引
     */
    @Override
    protected int calcCoordinateIndex(double[] center, double[] target) {
        if (center.length != dimension || target.length != dimension) {
            throw new IllegalArgumentException("the point not be three dimension!");
        }
        double x = target[0] - center[0];
        double y = target[1] - center[1];
        double z = target[2] - center[2];
        x = x == 0 ? x + 0.1 : x;
        y = y == 0 ? y + 0.1 : y;
        z = z == 0 ? z + 0.1 : z;
        return (x >= 0 ? 1 : 0) | (y >= 0 ? 2 : 0) | (z >= 0 ? 4 : 0);
    }
}
