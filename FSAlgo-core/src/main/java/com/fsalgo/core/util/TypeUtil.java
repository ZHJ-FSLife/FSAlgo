package com.fsalgo.core.util;

/**
 * @Author: root
 * @Date: 2023/6/25 15:49
 * @Description: TypeUtil isolates type-unsafety so that code which uses it for legitimate reasons can stay warning-free.
 */
public class TypeUtil {

    private TypeUtil() {}

    /**
     * Casts an object to a type.
     *
     * @param o   object to be cast
     * @param <T> the type of the result
     * @return the result of the cast
     */
    @SuppressWarnings("unchecked")
    public static <T> T uncheckedCase(Object o) {
        return (T) o;
    }
}
