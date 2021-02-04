package com.lz.bmp.dataenum;

/**
 * @author rocky
 * @ClassName DataSource
 * @Description
 * @Create by rocky
 * @Date 2020/10/29 上午10:29
 */
public enum DataSourceSymbol {


    /**
     * id
     */
    ID("_id"),


    /**
     * 名称
     */
    NAME("name"),

    /**
     * 展示名称
     */
    DISPLAY_NAME("displayName"),

    /**
     * 访问方式
     */
    ACCESS_MODE("accessMode"),

    /**
     * url
     */
    URL("url"),

    /**
     * 参数
     */
    PARAMETER("parameter"),

    /**
     * 返回类型
     */
    RETURN_TYPE("returnType"),
    
    /**
     * &
     */
    SYMBOL_AND("&"),

    /**
     * =
     */
    EQUAL("="),

    /**
     * ？
     */
    ASK_SYMBOL("?"),

    /**
     * 占位符
     */
    PARAM_TRANSFORM("&%s={%s}"),
    ;

    private String name;

    DataSourceSymbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
