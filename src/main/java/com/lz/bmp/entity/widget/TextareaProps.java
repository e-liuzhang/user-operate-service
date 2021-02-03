package com.lz.bmp.entity.widget;

/**
 * 多行文本框控件类
 *
 * @author shangang_luo
 * @date 20201031
 *
 */
public class TextareaProps {

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
     * 最大长度
     */
    private Integer maxlength;

    /**
     * 最小行数
     */
    private Integer minRows;

    /**
     * 最大行数
     */
    private Integer maxRows;

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

    public Integer getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(Integer maxlength) {
        this.maxlength = maxlength;
    }

    public Integer getMinRows() {
        return minRows;
    }

    public void setMinRows(Integer minRows) {
        this.minRows = minRows;
    }

    public Integer getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }

    @Override
    public String toString() {
        return "TextareaProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", placeholder='" + placeholder + '\'' +
                ", maxlength=" + maxlength +
                ", minRows=" + minRows +
                ", maxRows=" + maxRows +
                '}';
    }
}
