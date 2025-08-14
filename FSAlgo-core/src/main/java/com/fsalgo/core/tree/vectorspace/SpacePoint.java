/*
 * FSAlgo
 * https://github.com/ZHJ-FSLife/FSAlgo
 *
 * Copyright (C) [2023] [ZhongHongJie]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.fsalgo.core.tree.vectorspace;

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
            return point.toString();
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
