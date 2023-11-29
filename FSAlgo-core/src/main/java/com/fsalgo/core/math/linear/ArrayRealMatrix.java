/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
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

import com.fsalgo.core.constant.BaseConstant;
import com.fsalgo.core.util.MatrixUtil;

import java.io.Serializable;

/**
 * @Author: root
 * @Date: 2023/4/14 17:35
 * @Description:
 */
public class ArrayRealMatrix extends AbstractRealMatrix implements Serializable {

    private static final long serialVersionUID = BaseConstant.SERIAL_VERSION_UID;

    private double[][] data;

    public ArrayRealMatrix(int rows, int cols) {
        data = new double[rows][cols];
    }

    @Override
    public int getRowDimension() {
        return data != null ? data.length : 0;
    }

    @Override
    public int getColDimension() {
        return data != null && data[0] != null ? data[0].length : 0;
    }

    @Override
    public RealMatrix copy() {
        return null;
    }

    @Override
    public RealMatrix createMatrix(int rows, int cols) {
        return new ArrayRealMatrix(rows, cols);
    }

    @Override
    public void setEntry(int row, int col, double value) {
        MatrixUtil.checkMatrixIndex(row, col, this);
        data[row][col] = value;
    }

    @Override
    public double getEntry(int row, int col) {
        MatrixUtil.checkMatrixIndex(row, col, this);
        return data[row][col];
    }
}
