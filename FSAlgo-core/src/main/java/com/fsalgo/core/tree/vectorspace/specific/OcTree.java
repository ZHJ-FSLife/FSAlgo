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
 * @Description: 八叉树
 */
public class OcTree<T extends Comparable<T>> extends AbstractQuadOcTree<T> {

    public OcTree(List<SpacePoint<T>> points) {
        this(points, Distance.EUCLIDEAN);
    }

    public OcTree(List<SpacePoint<T>> points, DistanceMetric distanceMetric) {
        super(points, distanceMetric);
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
        VectorUtil.checkDims(center, dimension);
        VectorUtil.checkDims(center, target);

        // +0.01是为了避免x或y或z为0时，将其归到最近的八分体上
        double x = target[0] - center[0];
        double y = target[1] - center[1];
        double z = target[2] - center[2];
        x += x == 0 ? 0.01 : 0;
        y += y == 0 ? 0.01 : 0;
        z += z == 0 ? 0.01 : 0;
        return (x >= 0 ? 1 : 0) | (y >= 0 ? 2 : 0) | (z >= 0 ? 4 : 0);
    }

    @Override
    protected int getDimension() {
        return 3;
    }

    @Override
    public String getName() {
        return "octo-tree";
    }
}
