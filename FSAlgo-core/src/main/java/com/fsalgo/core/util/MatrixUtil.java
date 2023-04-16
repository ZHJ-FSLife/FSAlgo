package com.fsalgo.core.util;

import com.fsalgo.core.math.linear.AnyMatrix;
import com.fsalgo.core.math.linear.RealMatrix;

/**
 * @Author: root
 * @Date: 2023/4/11 12:28
 * @Description:
 */
public class MatrixUtil {

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
