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

/**
 * @Author: root
 * @Date: 2023/4/6 17:23
 * @Description: Principal Component Analysis, PCA 降维算法，高纬度数据降维至低维
 *
 * 在纠结有没有必要必要手锉一个，手锉EigenDecomposition挺麻烦的，org.apache.commons.math3明明就有现成的可用
 *
 */
public class PrincipalComponentAnalysis {

    private PrincipalComponentAnalysis() {}

    /**
     * 高维度数据降维处理。
     *
     * @param data      高维数据
     * @param dimension 维度值
     * @return 降维后的数据
     */
    /*public static double[][] dimensionReduction(double[][] data, int dimension) {
        RealMatrix matrix = new Array2DRowRealMatrix(data);

        // 计算协方差矩阵
        RealMatrix covarianceMatrix = matrix.transpose().multiply(matrix).scalarMultiply(1.0 / data.length);

        // 计算特征向量和特征值
        EigenDecomposition eig = new EigenDecomposition(covarianceMatrix);

        // 选择主要成分
        RealMatrix eigenvectors = eig.getV().getSubMatrix(0, eig.getV().getRowDimension() - 1, 0, dimension - 1);

        // 构建新的特征空间
        RealMatrix transformedData = matrix.multiply(eigenvectors);

        // 返回降维后的数据
        return transformedData.getData();
    }*/

}
