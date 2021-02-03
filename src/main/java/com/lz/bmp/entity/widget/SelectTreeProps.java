package com.lz.bmp.entity.widget;

/**
 * 下拉单选框(树结构)属性类
 *
 * @author shangang_luo
 * @date 20201031
 */
public class SelectTreeProps {

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

    @Override
    public String toString() {
        return "SelectTreeProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", placeholder='" + placeholder + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", sourceName='" + sourceName + '\'' +
                '}';
    }
}
