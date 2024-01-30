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
package com.fsalgo.core.enums.exception;

/**
 * @Author: root
 * @Date: 2023/6/30 12:40
 * @Description:
 */
public enum GraphBaseErrorEnum implements BaseErrorEnum {

      NODE_NOT_EXIST("The node not exist！")
    , EDGE_NOT_EXIST("The edge not exist！")
    , NODES_ARE_NOT_DIRECTLY_ADJACENT("The source node is not directly adjacent to the target node！")
    , SOURCE_AND_TARGET_MUST_EXIST("source node and target node must exist!")
    , NOT_DIRECTED_ACYCLIC_GRAPH("The graph is not a directed acyclic chart");
    ;

    private final String desc;

    GraphBaseErrorEnum(String desc) {
        this.desc = desc;
    }

    @Override
    public Object getCode() {
        return this.name();
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
