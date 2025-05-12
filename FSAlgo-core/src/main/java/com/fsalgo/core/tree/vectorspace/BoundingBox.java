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

import com.fsalgo.core.other.util.VectorUtil;

/**
 * @Author: root
 * @Date: 2023/12/29 13:26
 * @Description: Minimum Bounding Rectangle - MBR - 最小边界矩阵
 */
public class BoundingBox {

    private double[] min;
    private double[] max;

    public BoundingBox(double[] val) {
        new BoundingBox(val, val);
    }

    public BoundingBox(double[] min, double[] max) {
        VectorUtil.checkDims(min, max);
        this.min = min;
        this.max = max;
    }

    public double[] getMin() {
        return min;
    }

    public double[] getMax() {
        return max;
    }

    public void setMin(double[] min) {
        this.min = min;
    }

    public void setMax(double[] max) {
        this.max = max;
    }

    public void updateMin(int dimension, double val) {
        updateVal(dimension, val, this.min);
    }

    public void updateMax(int dimension, double val) {
        updateVal(dimension, val, this.max);
    }

    public void updateVal(int dimension, double val, double[] target) {
        target[dimension] = val;
    }

    /**
     * 最大差值的维度
     *
     * @return 维度
     */
    public int largestGapDimension() {
        double largestGap = 0;
        int dimension = 0;
        for (int i = 0; i < this.min.length; i++) {
            double gap = Math.abs(this.max[i] - this.min[i]);
            if (gap > largestGap) {
                largestGap = gap;
                dimension = i;
            }
        }
        return dimension;
    }
}
