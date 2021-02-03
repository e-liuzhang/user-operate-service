package com.lz.bmp.param.dataSource;


import com.lz.bmp.entity.BaseEntity;

/**
 * @author rocky
 * @ClassName DataSourceUpdateParam
 * @Description 数据源更新
 * @Create by rocky
 * @Date 2020/10/29 上午11:43
 */
public class DataSourceUpdateParam extends BaseEntity {

    /**
     * id
     */
    private String id;

    /**
     * 数据源名称
     */
    private String code;

    /**
     * 数据源名称
     */
    private String displayName;

    /**
     * 访问方式，类似于get、post
     */
    private String accessMode;

    /**
     * 数据源地址
     */
    private String url;

    /**
     * 链接参数，类似于，size=10&page=5
     */
    private String parameter;

    /**
     * 返回值，类似于，List<Integer>，String
     */
    private  String returnType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(String accessMode) {
        this.accessMode = accessMode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "DataSourceUpdateParam{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", displayName='" + displayName + '\'' +
                ", accessMode='" + accessMode + '\'' +
                ", url='" + url + '\'' +
                ", parameter='" + parameter + '\'' +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}
