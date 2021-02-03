package com.lz.bmp.dataenum;

import com.fpi.simple.result.interfaces.IErrorCode;

/**
 * @Author shangang_luo
 * @Date 2021/1/21 19:56
 */

public enum CommonErrorCode implements IErrorCode {


    /**
     * param参数为空
     * param参数中的data为空
     */
    PARAM_ERROR("参数中的%s为空"),


    LENGTH_ERROR("%s的长度错误，应为:%s位"),

    HAS_EXIT("%s 的数据已存在%s条"),

    NOT_EXIT("%s为%s的数据不存在"),


    QUERY_ERROR("查询失败:%s"),

    QUERY_PAGE_DETAIL_ERROR("分页参数有误 Page:%s, Size:%s"),

    ADD_ERROR("添加失败:%s"),

    UPDATE_ERROR("更新失败:%s"),

    DELETE_ERROR("删除失败:%s"),

    CHECK_ERROR("校验失败:%s"),

    /**
     * json str 解析错误
     */
    JSON_STR_PARSE_ERROR("模板结构解析错误:%s"),

    /**
     * 不存在
     */
    TAB_KEY_NOT_EXIT("tabkey不存在 %s"),

    /**
     * 数据不合法
     */
    JSON_DATA_ERROR("业务结构数据错误，%s"),

    /**
     * 数据不合法
     */
    JSON_DATA_IS_ILLEGAL("业务结构数据不应当为空"),


    /* check */

    /**
     * 控件名称不合法
     */
    WIDGET_NAME_IS_ILLEGAL("%s控件名称不合法"),

    /**
     * 模板和数据校验不过
     */
    DATA_NOT_MATCH_JSON("%s是必填项，请填写"),

    /**
     * 模板和数据校验不过
     */
    WIDGET_EXCEED_MAX_LENGTH("%s超过了最大填写长度"),

    /**
     * 模板和数据校验不过
     */
    WIDGET_VALUE_INVALID("%s值输入非法"),

    /**
     * 控件值不存在
     */
    DATASOURCE_IS_ILLEGAL("数据源值非法"),

    /**
     * 数据不合法
     */
    JSON_WIDGET_NOT_FOUND("业务结构对应控件不存在， %s"),


    ;


    private final String message;

    CommonErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.name().toLowerCase();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
