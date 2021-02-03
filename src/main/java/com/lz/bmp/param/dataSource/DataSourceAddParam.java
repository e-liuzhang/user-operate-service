package com.lz.bmp.param.dataSource;


import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author rocky
 * @ClassName AddDataSourceParam
 * @Description
 * @Create by rocky
 * @Date 2020/10/19 上午11:28
 */
public class DataSourceAddParam {

    @Id
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
    private String returnType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 是否被使用
     */
    private Boolean isDeleted;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "DataSourceAddParam{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", displayName='" + displayName + '\'' +
                ", accessMode='" + accessMode + '\'' +
                ", url='" + url + '\'' +
                ", parameter='" + parameter + '\'' +
                ", returnType='" + returnType + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
