package com.fsalgo.core.tree.vectorspace;

import java.util.Arrays;

/**
 * @Author: root
 * @Date: 2023/4/20 14:17
 * @Description:
 */
public interface SpacePoint<T> {

    /**
     * 获取空间中节点信息
     *
     * @return 空间节点
     */
    T getPoint();

    /**
     * 获取空间中节点坐标
     *
     * @return 节点坐标
     */
    double[] getCoord();

    /**
     * 获取最近距离
     *
     * @return 最近距离
     */
    double getDistance();

    /**
     * 设置最近距离
     *
     * @param distance 最近距离
     */
    void setDistance(double distance);

    class SpacePointImpl<T> implements SpacePoint<T> {
        T point;
        double[] coord;
        double distance;

        public SpacePointImpl(T point, double[] coord) {
            this.point = point;
            this.coord = coord;
        }

        @Override
        public String toString() {
            return point.toString() + " : " + Arrays.toString(coord) + " : " + distance;
        }

        @Override
        public T getPoint() {
            return point;
        }

        @Override
        public double[] getCoord() {
            return coord;
        }

        @Override
        public double getDistance() {
            return distance;
        }

        @Override
        public void setDistance(double distance) {
            this.distance = distance;
        }
    }

}
