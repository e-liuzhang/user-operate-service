package com.lz.bmp.entity.widget;

/**
 * 下拉多选框选择项类
 *
 * @author shangang_luo
 * @date 20201031
 */
public class SelectMultiOption {
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
        return "Select3Option{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
