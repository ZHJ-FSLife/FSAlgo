package com.fsalgo.core.struct.metrics;

/**
 * @Author: root
 * @Date: 2023/3/6 20:42
 * @Description:
 */
public interface GeometricalDistance extends NameEntity {

    double getDistance(final double[] source, final double[] target);

}
