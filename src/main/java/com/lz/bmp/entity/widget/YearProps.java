package com.lz.bmp.entity.widget;

/**
 * 年属性类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class YearProps {
    /**
     * 中文显示名称
     */
    private String label;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 预填字体
     */
    private String placeholder;

    /**
     * 形式
     */
    private Integer format;

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

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "YearProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", placeholder='" + placeholder + '\'' +
                ", format=" + format +
                '}';
    }
}
