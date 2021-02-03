package com.lz.bmp.entity.widget;

public class ImageUploadProps {

    /**
     * 中文显示名称
     */
    private String label;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 图片数量限制
     */
    private Integer limit;

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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ImageUploadProps{" +
                "label='" + label + '\'' +
                ", required=" + required +
                ", limit=" + limit +
                '}';
    }
}
