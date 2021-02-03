package com.lz.bmp.entity.widget;

import java.util.List;

/**
 * 经纬度属性类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class LatLngProps {

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
    private List<String> placeHolder;

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

    public List<String> getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(List<String> placeHolder) {
        this.placeHolder = placeHolder;
    }

    @Override
    public String toString() {
        return "LatLngProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", placeHolder=" + placeHolder +
                '}';
    }
}
