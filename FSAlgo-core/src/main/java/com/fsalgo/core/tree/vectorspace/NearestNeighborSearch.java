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

import com.fsalgo.core.interfaces.NameEntity;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/5/24 10:16
 * @Description:
 */
public interface NearestNeighborSearch<T> extends NameEntity {

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
