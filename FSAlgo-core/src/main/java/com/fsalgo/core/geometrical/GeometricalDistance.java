package com.fsalgo.core.geometrical;

/**
 * @Author: root
 * @Date: 2023/3/6 20:42
 * @Description:
 */
public interface GeometricalDistance extends NameEntity {

    double getDistance(final double[] source, final double[] target);

}
