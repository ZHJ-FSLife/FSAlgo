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
package com.fsalgo.core.other.util;

import com.fsalgo.core.math.linear.AnyMatrix;
import com.fsalgo.core.math.linear.RealMatrix;

/**
 * @Author: root
 * @Date: 2023/4/11 12:28
 * @Description:
 */
public class MatrixUtil {

    private MatrixUtil() {}

    public static void checkDims(RealMatrix matrix1, RealMatrix matrix2) {
        if (matrix1.getRowDimension() != matrix2.getRowDimension() || matrix1.getColDimension() != matrix2.getColDimension()) {
            throw new IllegalArgumentException("matrix dimension mismatch");
        }
    }

    public static void checkRowIndex(int row, AnyMatrix matrix) {
        if (row < 0 || row >= matrix.getRowDimension()) {
            throw new IllegalArgumentException("the row number is out of bounds");
        }
    }

    public static void checkColIndex(int col, AnyMatrix matrix) {
        if (col < 0 || col >= matrix.getColDimension()) {
            throw new IllegalArgumentException("the col number is out of bounds");
        }
    }

    public static void checkMatrixIndex(int row, int col, AnyMatrix matrix) {
        checkRowIndex(row, matrix);
        checkColIndex(col, matrix);
    }

    public static void checkMatrixMultiply(AnyMatrix matrix1, AnyMatrix matrix2) {
        if (matrix1.getColDimension() != matrix2.getRowDimension()) {
            throw new IllegalArgumentException("the matrix does not match");
        }
    }

}
