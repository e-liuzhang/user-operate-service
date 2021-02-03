package com.lz.bmp.entity.widget;

/**
 * 输入框属性类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class InputProps {

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
     * 最大长度
     */
    private Integer maxlength;

    /**
     * 数据类型
     */
    private Integer dataType;

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

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "InputProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", placeholder='" + placeholder + '\'' +
                ", maxlength=" + maxlength +
                ", dataType=" + dataType +
                '}';
    }
}
