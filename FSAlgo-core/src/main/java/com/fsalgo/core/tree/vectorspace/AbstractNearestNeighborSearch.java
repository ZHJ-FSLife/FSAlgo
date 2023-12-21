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
