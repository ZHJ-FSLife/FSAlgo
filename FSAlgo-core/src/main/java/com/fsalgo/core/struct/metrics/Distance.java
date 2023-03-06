package com.fsalgo.core.struct.metrics;

/**
 * @Author: root
 * @Date: 2023/3/6 20:34
 * @Description:
 */
public enum Distance implements DistanceMetric {

    EUCLIDEAN {
        @Override
        public double getDistance(double[] source, double[] target) {
            if (source.length != target.length) {
                throw new IllegalArgumentException("inconsistent node length!");
            }
            double distance = 0.00;
            for (int i = 0; i < source.length; i++) {
                distance += Math.pow(source[i] - target[i], 2);
            }
            return Math.sqrt(distance);
        }

        @Override
        public String getName() {
            return "getDistance";
        }
    },
    HAMMING {

        @Override
        public double getDistance(double[] source, double[] target) {
            return 0;
        }

        @Override
        public String getName() {
            return "Hamming";
        }
    };

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
