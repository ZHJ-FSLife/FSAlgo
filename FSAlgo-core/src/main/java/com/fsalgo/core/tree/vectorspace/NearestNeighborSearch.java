package com.fsalgo.core.tree.vectorspace;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/5/24 10:16
 * @Description:
 */
public interface NearestNeighborSearch<T> {

    /**
     * 距离目标节点最近的邻近节点
     *
     * @param point 目标节点信息
     * @return 邻近节点
     */
    SpacePoint<T> nearest(SpacePoint<T> point);

    /**
     * 目标节点为圆心，指定半径内的所有节点
     *
     * @param point  目标节点
     * @param radius 半径
     * @return 邻近节点集
     */
    List<SpacePoint<T>> range(SpacePoint<T> point, double radius);
}
