package com.lz.bmp.dataenum;

/**
 * @Author shangang_luo
 * @Date 2021/1/21 20:01
 */

public enum UserTemplateField {

    USER_TEMPLATE_COMMONATTR("userTemplateCommonAttr"),

    USER_TEMPLATE_NAME("userTemplateName"),

    USER_TEMPLATE_CODE("userTemplateCode"),

    TYPE("type"),

    STATUS("status"),

    ;

    private final String field;

    UserTemplateField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
