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
package com.fsalgo.core.enums;

/**
 * @Author: root
 * @Date: 2023/8/17 20:31
 * @Description: 图结构类型
 */
public enum GraphTypeEnum implements BaseEnum {

      DIRECTED_GRAPH("有向图")
    , UNDIRECTED_GRAPH("无向图")
    , DIRECTED_ACYCLIC_GRAPH("有向无环图")
    , DIRECTED_MULTIGRAPH("有向重图")
    , DIRECTED_PSEUDOGRAPH("有向伪图")
    ;

    private final String desc;

    GraphTypeEnum(String desc) {
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return getCode() + "::" + getDesc();
    }
}
