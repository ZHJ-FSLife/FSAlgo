package com.fsalgo.core.math.linear;

/**
 * @Author: root
 * @Date: 2023/4/11 9:25
 * @Description:
 */
public interface AnyMatrix {

    /**
     * 是否为正方形
     *
     * @return true or false
     */
    boolean isSquare();

    /**
     * 行维度
     *
     * @return 维度值
     */
    int getRowDimension();

    /**
     * 列维度
     *
     * @return 维度值
     */
    int getColDimension();
}
