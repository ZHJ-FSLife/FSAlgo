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

        @Override
        public boolean isBinaryDistance() { return false; }
    },
    /**
     * 汉明距离
     */
    HAMMING {
        @Override
        public double getDistance(double[] source, double[] target) {
            VectorUtil.checkDims(source, target);

            double count = 0.00;
            for (int i = 0; i < source.length; i++) {
                if (source[i] != target[i]) {
                    count++;
                }
            }
            return count / source.length;
        }

        @Override
        public String getName() {
            return "Hamming";
        }

        @Override
        public boolean isBinaryDistance() { return true; }
    },
    /**
     * 曼哈顿距离
     */
    MANHATTAN {
        @Override
        public double getDistance(double[] source, double[] target) {
            VectorUtil.checkDims(source, target);

            double distance = 0.00;
            for (int i = 0; i < source.length; i++) {
                distance += Math.abs(source[i] - target[i]);
            }
            return distance;
        }

        @Override
        public String getName() {
            return "Manhattan";
        }

        @Override
        public boolean isBinaryDistance() { return false; }
    },
    /**
     * 余弦距离
     */
    ;

    @Override
    public double getDistance(final double[] source, final double[] target) {
        return 0.00;
    }

    @Override
    public String toString() {
        return getName();
    }

    abstract public boolean isBinaryDistance();
}
