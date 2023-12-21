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
