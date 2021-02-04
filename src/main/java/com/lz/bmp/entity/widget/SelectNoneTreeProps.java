package com.lz.bmp.entity.widget;

import java.util.List;

/**
 * 下拉单选框(非树结构)属性类
 *
 * @author shangang_luo
 * @date 20201031
 */
public class SelectNoneTreeProps {

    /**
     * 中文显示名称
     */
    private String label;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 预填字段
     */
    private String placeholder;

    /**
     * 数据源类型
     */
    private Integer userType;

    /**
     * 数据源名称
     */
    private String userName;

    /**
     * 是否需要搜索功能
     */
    private Boolean isFilterable;

    /**
     * 自定义选项值类
     */
    private List<SelectNoneTreeOption> options;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getFilterable() {
        return isFilterable;
    }

    public void setFilterable(Boolean filterable) {
        isFilterable = filterable;
    }

    public List<SelectNoneTreeOption> getOptions() {
        return options;
    }

    public void setOptions(List<SelectNoneTreeOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "SelectNoneTreeProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", placeholder='" + placeholder + '\'' +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                ", isFilterable=" + isFilterable +
                ", options=" + options +
                '}';
    }
}
