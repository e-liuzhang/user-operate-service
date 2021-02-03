package com.lz.bmp.dataenum;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 11:40
 */

public enum UserField {

    USER_COMMON_INFO("userCommonInfo"),

    USER_TEMPLATE_CODE("userTemplateCode"),

    USER_UUID("userUuid"),

    IS_DEL("isDel"),

    USER_BASIC_INFO_MAP("userBasicInfoMap"),

    /**
     * 用户基本属性栏
     */
    USER_BASIC_TAB("userBasicAttrInfo"),

    USER_NAME("username"),
    ;

    private final String field;

    UserField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
