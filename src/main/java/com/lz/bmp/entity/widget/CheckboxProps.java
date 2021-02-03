package com.lz.bmp.entity.widget;

import java.util.List;

/**
 * 多选框属性类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class CheckboxProps {

    /**
     * 中文显示名称
     */
    private String label;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 自定义选项值类
     */
    private List<CheckboxOption> options;

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

    public List<CheckboxOption> getOptions() {
        return options;
    }

    public void setOptions(List<CheckboxOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "CheckboxProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", options=" + options +
                '}';
    }
}
