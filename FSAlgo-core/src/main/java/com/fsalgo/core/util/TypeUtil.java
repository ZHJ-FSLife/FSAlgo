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
