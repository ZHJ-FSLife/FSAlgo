package com.fsalgo.core.enums;

/**
 * @Author: root
 * @Date: 2023/6/30 12:37
 * @Description:
 */
public interface BaseEnum<T> {

    T getCode();

    String getDesc();
}
