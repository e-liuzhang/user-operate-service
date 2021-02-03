package com.lz.bmp.entity.widget;

import java.util.List;

/**
 * 单选框属性类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class RadioProps {

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
     * 是否需要搜索功能
     */
    private Boolean isFilterable;

    /**
     * 自定义选项值类
     */
    private List<RadioOption> options;

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

    public List<RadioOption> getOptions() {
        return options;
    }

    public void setOptions(List<RadioOption> options) {
        this.options = options;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Boolean getFilterable() {
        return isFilterable;
    }

    public void setFilterable(Boolean filterable) {
        isFilterable = filterable;
    }

    @Override
    public String toString() {
        return "RadioProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", placeholder='" + placeholder + '\'' +
                ", isFilterable=" + isFilterable +
                ", options=" + options +
                '}';
    }
}
