package com.lz.bmp.entity.widget;

/**
 * 多选框选择项类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class CheckboxOption {

    /**
     * 中文显示名称
     */
    private String label;

    /**
     * 对应值
     */
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CheckboxOption{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
