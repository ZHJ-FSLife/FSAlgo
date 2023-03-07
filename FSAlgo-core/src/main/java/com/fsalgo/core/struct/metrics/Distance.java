package com.fsalgo.core.struct.metrics;

import com.fsalgo.core.util.VectorUtil;

/**
 * @Author: root
 * @Date: 2023/3/6 20:34
 * @Description:
 */
public enum Distance implements DistanceMetric {

    /**
     * 欧式距离 / 欧几里德距离
     */
    EUCLIDEAN {
        @Override
        public double getDistance(double[] source, double[] target) {
            VectorUtil.checkDims(source, target);

            double distance = 0.00;
            for (int i = 0; i < source.length; i++) {
                distance += Math.pow(source[i] - target[i], 2);
            }
            return Math.sqrt(distance);
        }

        @Override
        public String getName() {
            return "Euclidean";
        }
    },
    /**
     * 汉明距离
     */
    HAMMING {
        @Override
        public double getDistance(double[] source, double[] target) {
            VectorUtil.checkDims(source, target);
            return 0;
        }

        @Override
        public String getName() {
            return "Hamming";
        }
    },
    /**
     * 曼哈顿距离
     */
    /**
     * 余弦距离
     */
    ;

    @Override
    public double getDistance(final double[] source, final double[] target) {
        return 0.00;
    }

    @Override
    public double getP() {
        return DEFAULT_P;
    }

    @Override
    public String toString() {
        return getName();
    }
}
