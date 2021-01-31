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


    QUERY_ERROR("查询失败:%s"),

    QUERY_PAGE_DETAIL_ERROR("分页参数有误 Page:%s, Size:%s"),

    ADD_ERROR("添加失败:%s"),

    DELETE_ERROR("删除失败:%s"),

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
