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
package com.fsalgo.core.math.geometrical;

import com.fsalgo.core.other.util.VectorUtil;

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
        public double getDistance(final double[] source, final double[] target) {
            VectorUtil.checkDims(source, target);

            double distance = 0.0D;
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
        public boolean isBinaryDistance() {
            return false;
        }
    },
    /**
     * 汉明距离
     */
    HAMMING {
        @Override
        public double getDistance(final double[] source, final double[] target) {
            VectorUtil.checkDims(source, target);

            double count = 0.0D;
            for (int i = 0; i < source.length; i++) {
                if (source[i] != target[i]) {
                    count++;
                }
            }
            return 1 - (count / source.length);
        }

        @Override
        public String getName() {
            return "Hamming";
        }

        @Override
        public boolean isBinaryDistance() {
            return true;
        }
    },
    /**
     * 曼哈顿距离
     */
    MANHATTAN {
        @Override
        public double getDistance(final double[] source, final double[] target) {
            VectorUtil.checkDims(source, target);

            double distance = 0.0D;
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
        public boolean isBinaryDistance() {
            return false;
        }
    },
    /**
     * 切比雪夫距离
     */
    CHEBYSHEV {
        @Override
        public double getDistance(final double[] source, final double[] target) {
            VectorUtil.checkDims(source, target);

            double distance = 0.0D;
            for (int i = 0; i < source.length; i++) {
                distance = Math.max(Math.abs(source[i] - target[i]), distance);
            }
            return distance;
        }

        @Override
        public String getName() {
            return "Chebyshev";
        }

        @Override
        public boolean isBinaryDistance() {
            return false;
        }
    },
    /**
     * 余弦距离
     */
    COSINE {
        @Override
        public double getDistance(final double[] source, final double[] target) {
            VectorUtil.checkDims(source, target);

            double numerator = 0.0D;
            double normSource = 0.0D;
            double normTarget = 0.0D;

            for (int i = 0; i < source.length; i++) {
                numerator += source[i] * target[i];
                normSource += Math.pow(source[i], 2);
                normTarget += Math.pow(target[i], 2);
            }

            return 1 - (numerator / (Math.sqrt(normSource) * Math.sqrt(normTarget)));
        }

        @Override
        public String getName() {
            return "Cosine Similarity";
        }

        @Override
        public boolean isBinaryDistance() {
            return false;
        }
    },
    /**
     * 布雷柯蒂斯相异度
     */
    BRAY_CURTIS {
        @Override
        public double getDistance(final double[] source, final double[] target) {
            VectorUtil.checkDims(source, target);

            double sum1 = 0.0D;
            double sum2 = 0.0d;
            for (int i = 0; i < source.length; i++) {
                sum1 += Math.abs(source[i] - target[i]);
                sum2 += Math.abs(source[i] + target[i]);
            }
            return sum1 == 0 ? 0 : nanInf(sum1 / sum2);
        }

        @Override
        public String getName() {
            return "BrayCurtis";
        }

        @Override
        public boolean isBinaryDistance() {
            return false;
        }
    },
    /**
     * 坎贝拉距离
     */
    CANBERRA {
        @Override
        public double getDistance(final double[] source, final double[] target) {
            VectorUtil.checkDims(source, target);

            double sum = 0.0D;
            double numer;
            for (int i = 0; i < source.length; i++) {
                numer = Math.abs(source[i] - target[i]);
                sum += numer == 0 ? 0 : nanInf(numer / (Math.abs(source[i]) + Math.abs(target[i])));
            }
            return sum;
        }

        @Override
        public String getName() {
            return "Canberra";
        }

        @Override
        public boolean isBinaryDistance() {
            return false;
        }
    };

    private static double nanInf(double nums) {
        return Double.isNaN(nums) ? Double.POSITIVE_INFINITY : nums;
    }

    @Override
    public String toString() {
        return getName();
    }

    public abstract boolean isBinaryDistance();
}
