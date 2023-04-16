package com.fsalgo.core.math.linear;

/**
 * @Author: root
 * @Date: 2023/4/11 9:52
 * @Description:
 */
public interface RealMatrix extends AnyMatrix {

    /**
     * 复制矩阵
     *
     * @return 复制后的新矩阵
     */
    RealMatrix copy();

    /**
     * 矩阵转置
     *
     * @return 转置后的新矩阵
     */
    RealMatrix transpose();

    /**
     * 创建矩阵
     *
     * @param row 行数
     * @param col 列数
     * @return 创建的新矩阵
     */
    RealMatrix createMatrix(int row, int col);

    /**
     * 矩阵相加
     *
     * @param matrix 矩阵
     * @return 相加后的矩阵
     */
    RealMatrix add(RealMatrix matrix);

    /**
     * 矩阵相减
     *
     * @param matrix 矩阵
     * @return 相减后的矩阵
     */
    RealMatrix subtract(RealMatrix matrix);

    /**
     * 矩阵相乘
     *
     * @param matrix 矩阵
     * @return 相乘后的矩阵
     */
    RealMatrix multiply(RealMatrix matrix);

    /**
     * 设置矩阵中指定位置的值
     *
     * @param row   行号
     * @param col   列号
     * @param value 值
     */
    void setEntry(int row, int col, double value);

    /**
     * 获取矩阵中指定位置的值
     *
     * @param row 行号
     * @param col 列号
     * @return 值
     */
    double getEntry(int row, int col);

    /**
     * 以二维数组的形式返回矩阵数据
     *
     * @return 二维数组数据
     */
    double[][] getData();
}
