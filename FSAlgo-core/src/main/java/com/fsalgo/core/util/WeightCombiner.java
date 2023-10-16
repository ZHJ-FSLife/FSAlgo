package com.fsalgo.core.util;

import java.io.Serializable;

/**
 * @Author: root
 * @Date: 2023/10/16 9:36
 * @Description:
 */
public interface WeightCombiner {

    double combine(double a, double b);

    /**
     * Maximum weight.
     */
    WeightCombiner MAX = (WeightCombiner & Serializable) Math::max;

    /**
     * Minimum weight.
     */
    WeightCombiner MIN = (WeightCombiner & Serializable) Math::min;

    /**
     * Sum of weights.
     */
    WeightCombiner SUM = (WeightCombiner & Serializable) Double::sum;

    /**
     * Multiplication of weights.
     */
    WeightCombiner MULT = (WeightCombiner & Serializable) (a, b) -> a * b;

    /**
     * First weight.
     */
    WeightCombiner FIRST = (WeightCombiner & Serializable) (a, b) -> a;

    /**
     * Second weight.
     */
    WeightCombiner SECOND = (WeightCombiner & Serializable) (a, b) -> b;
}
