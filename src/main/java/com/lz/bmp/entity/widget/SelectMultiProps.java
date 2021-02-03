package com.lz.bmp.entity.widget;

import java.util.List;

/**
 * 下拉多选框属性类
 *
 * @author shangang_luo
 * @date 20201031
 */
public class SelectMultiProps {

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
    private Integer sourceType;

    /**
     * 数据源名称
     */
    private String sourceName;

    /**
     * 是否需要搜索功能
     */
    private Boolean isFilterable;

    /**
     * 自定义选项值类
     */
    private List<SelectMultiOption> options;

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

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public List<SelectMultiOption> getOptions() {
        return options;
    }

    public void setOptions(List<SelectMultiOption> options) {
        this.options = options;
    }

    public Boolean getFilterable() {
        return isFilterable;
    }

    public void setFilterable(Boolean filterable) {
        isFilterable = filterable;
    }

    @Override
    public String toString() {
        return "SelectMultiProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", placeholder='" + placeholder + '\'' +
                ", sourceType=" + sourceType +
                ", sourceName='" + sourceName + '\'' +
                ", isFilterable=" + isFilterable +
                ", options=" + options +
                '}';
    }
}
