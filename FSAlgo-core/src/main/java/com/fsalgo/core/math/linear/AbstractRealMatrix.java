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
package com.fsalgo.core.math.linear;

import com.fsalgo.core.util.MatrixUtil;

/**
 * @Author: root
 * @Date: 2023/4/14 15:36
 * @Description:
 */
public abstract class AbstractRealMatrix implements RealMatrix {

    @Override
    public boolean isSquare() {
        return getRowDimension() == getColDimension();
    }

    @Override
    public RealMatrix transpose() {
        int rows = getRowDimension();
        int cols = getColDimension();
        RealMatrix result = createMatrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setEntry(j, i, getEntry(i, j));
            }
        }
        return result;
    }

    @Override
    public RealMatrix add(RealMatrix matrix) {
        MatrixUtil.checkDims(this, matrix);
        int rows = getRowDimension();
        int cols = getColDimension();
        RealMatrix result = createMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setEntry(i, j, getEntry(i, j) + matrix.getEntry(i, j));
            }
        }
        return result;
    }

    @Override
    public RealMatrix subtract(RealMatrix matrix) {
        MatrixUtil.checkDims(this, matrix);
        int rows = getRowDimension();
        int cols = getColDimension();
        RealMatrix result = createMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setEntry(i, j, getEntry(i, j) - matrix.getEntry(i, j));
            }
        }
        return result;
    }

    @Override
    public RealMatrix multiply(RealMatrix matrix) {
        MatrixUtil.checkMatrixMultiply(this, matrix);
        int rows = getRowDimension();
        int cols = matrix.getColDimension();
        RealMatrix result = createMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double nums = 0.0D;
                for (int k = 0; k < getColDimension(); k++) {
                    nums += getEntry(i, k) * matrix.getEntry(k, j);
                }
                result.setEntry(i, j, nums);
            }
        }
        return result;
    }

    @Override
    public double[][] getData() {
        int rows = getRowDimension();
        int cols = getColDimension();
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = getEntry(i, j);
            }
        }
        return result;
    }
}
