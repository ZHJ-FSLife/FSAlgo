package com.fsalgo.core.util;

import com.fsalgo.core.math.linear.AnyMatrix;

/**
 * @Author: root
 * @Date: 2023/4/11 12:28
 * @Description:
 */
public class MatrixUtil {

    public static void checkDims(AnyMatrix matrix1, AnyMatrix matrix2) {
        if (matrix1.getRowDimension() != matrix2.getRowDimension() || matrix1.getColDimension() != matrix2.getColDimension()) {
            throw new IllegalArgumentException("matrix dimension mismatch");
        }
    }

}
