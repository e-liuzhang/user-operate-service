package com.lz.bmp.dataenum;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 16:53
 */

public enum Param {

    DELETE_PARAM("deleteParam"),

    ADD_PARAM("addParam"),

    UPDATE_PARAM("updateParam"),

    CODE_LIST("userTemplateCodeList"),

    TAB_KEY("tabkey")
    ;

    private final String param;

    Param(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
