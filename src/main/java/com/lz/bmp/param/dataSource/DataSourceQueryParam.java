package com.lz.bmp.param.dataSource;


/**
 * @author rocky
 * @ClassName QueryDataSource
 * @Description
 * @Create by rocky
 * @Date 2020/10/19 上午10:34
 */
public class DataSourceQueryParam {

    /**
     * 数据模糊查询
     */
    private String displayNameOrCode;

    /**
     * api key name；
     */
    private String code;

    /**
     * 页面号
     */
    private Integer page;

    /**
     * 页面尺寸
     */
    private Integer size;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDisplayNameOrCode() {
        return displayNameOrCode;
    }

    public void setDisplayNameOrCode(String displayNameOrCode) {
        this.displayNameOrCode = displayNameOrCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DataSourceQueryParam{" +
                "displayNameOrCode='" + displayNameOrCode + '\'' +
                ", code='" + code + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
