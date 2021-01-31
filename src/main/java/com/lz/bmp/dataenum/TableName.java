package com.lz.bmp.dataenum;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 14:39
 */

public enum TableName {

    /**
     * 模板表名
     */
    USER_TEMPLATE("user-template"),


    /**
     * 數據源
     */
    DATA_SOURCE("data-source"),

    /*
     * 测站表名
     */
    USER("user"),

    ;


    private final String tableName;

    TableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
