package com.fsalgo.core.math.linear;

import com.fsalgo.core.util.MatrixUtil;

import java.io.Serializable;

/**
 * @Author: root
 * @Date: 2023/4/14 17:35
 * @Description:
 */
public class ArrayRealMatrix extends AbstractRealMatrix implements Serializable {

    private static final long serialVersionUID = 1L;

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
