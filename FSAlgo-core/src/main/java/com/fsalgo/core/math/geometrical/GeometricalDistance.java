package com.fsalgo.core.math.geometrical;

import com.fsalgo.core.interfaces.NameEntity;

/**
 * @Author: root
 * @Date: 2023/3/6 20:42
 * @Description:
 */
public interface GeometricalDistance extends NameEntity {

    /**
     * 几何中点与点之间的距离
     *
     * @param source 源
     * @param target 目标
     * @return 返回几何上的距离结果
     */
    double getDistance(final double[] source, final double[] target);

}
