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
