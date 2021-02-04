package com.lz.bmp.dataenum;

/**
 * @author rocky
 * @ClassName DataSourceField
 * @Description
 * @Create by rocky
 * @Date 2020/10/19 下午12:32
 */
public enum DataSourceField {


    /**
     * id
     */
    ID("_id"),


    /**
     *
     */
    IDS("ids"),

    /**
     * 名称
     */
    CODE("code"),

    /**
     * 返回信息
     */
    MESSAGE("message"),

    /**
     * 返回信息
     */
    SUCCESS("success"),

    /**
     * 数据源名称
     */
    DISPLAY_NAME("displayName"),


    /**
     * accessMode 访问方式
     */
    ACCESS_MODE("accessMode"),

    /**
     * url
     */
    URL("url"),

    /**
     * parameter
     */
    PARAMETER("parameter"),


    /**
     * 返回类型
     */
    RETURN_TYPE("returnType"),

    /**
     * 是否被删除
     */
    IS_DELETED("isDeleted"),

    /**
     * 修改时间
     */
    MODIFY_TIME("modifyTime"),

    DATASOURCECODES("dataSourceCodes"),

    DATASOURCECODE("dataSourceCode"),

    ;

    private final String fieldName;

    DataSourceField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return this.fieldName;
    }


}
