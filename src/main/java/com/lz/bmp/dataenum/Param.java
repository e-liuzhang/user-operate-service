package com.lz.bmp.dataenum;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 16:53
 */

public enum Param {

    DELETEPARAM("deleteParam"),

    CODE_LIST("userTemplateCodeList"),
    ;

    private final String param;

    Param(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
