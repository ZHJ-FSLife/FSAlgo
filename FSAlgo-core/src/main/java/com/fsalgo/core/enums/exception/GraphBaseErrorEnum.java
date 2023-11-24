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
