package com.fsalgo.core.tree.vectorspace;

import com.fsalgo.core.math.geometrical.DistanceMetric;

/**
 * @Author: root
 * @Date: 2023/5/25 9:00
 * @Description:
 */
public abstract class AbstractNearestNeighborSearch<T> implements NearestNeighborSearch<T>  {

    protected final DistanceMetric distanceMetric;

    protected AbstractNearestNeighborSearch(DistanceMetric distanceMetric) {
        this.distanceMetric = distanceMetric;
    }
}
